package com.coletaneaicm.coletanea.coletaneaicm;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coletaneaicm.coletanea.coletaneaicm.Entities.Login;
import com.coletaneaicm.coletanea.coletaneaicm.Entities.Registro;
import com.coletaneaicm.coletanea.coletaneaicm.Session.SessionManager;
import com.coletaneaicm.coletanea.coletaneaicm.retrofit.RetrofitInicializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

public class RegistroActivity extends AppCompatActivity {

    private SharedPreferences.Editor editor;

    SessionManager session;

    private AutoCompleteTextView mNomeView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    boolean cancel = false;
    View focusView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        SharedPreferences sharedPreferences = RegistroActivity.this.getSharedPreferences(com.coletaneaicm.coletanea.coletaneaicm.config.Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        session = new SessionManager(getApplicationContext());

        mNomeView = (AutoCompleteTextView) findViewById(R.id.registro_nome);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.registro_email);
        mPasswordView = (EditText) findViewById(R.id.registro_password);

        Button btnSaveUser = (Button) findViewById(R.id.email_sign_up_button_2);

        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nome = mNomeView.getText().toString();
                final String email = mEmailView.getText().toString();
                final String password = mPasswordView.getText().toString();

                // Check for a valid password, if the user entered one.
                if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    focusView = mPasswordView;
                    cancel = true;
                }

                Log.i("Nome", "" + nome);

                if (TextUtils.isEmpty(nome)) {
                    mNomeView.setError(getString(R.string.error_field_required));
                    focusView = mNomeView;
                    cancel = true;
                } else if (TextUtils.isEmpty(email)) {
                    mEmailView.setError(getString(R.string.error_field_required));
                    focusView = mEmailView;
                    cancel = true;
                } else if (TextUtils.isEmpty(password)) {

                    mPasswordView.setError(getString(R.string.error_field_required));
                    focusView = mPasswordView;
                    cancel = true;

                } else if (!isEmailValid(email)) {
                    mEmailView.setError(getString(R.string.error_invalid_email));
                    focusView = mEmailView;
                    cancel = true;
                }

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {

                    final ProgressDialog progressDialog = new ProgressDialog(RegistroActivity.this);
                    progressDialog.setMessage("Registrando usuário");
                    progressDialog.show();

                    Call<Registro> call = new RetrofitInicializador().getRegistroService().saveUser(nome, email, password);

                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {

                            Registro resultado = (Registro) response.body();

                            progressDialog.dismiss();

                            if (!response.isSuccessful()) {
                                Log.i("Erro", "ERROR onResponde: " + response.errorBody().toString());
                                Log.i("Erro", "ERROR onResponde: " + response.code());
                            } else {

                                if (resultado.getBln()) {

                                    final ProgressDialog progressDialogLogin = new ProgressDialog(RegistroActivity.this);
                                    progressDialogLogin.setMessage("Registrando Login");
                                    progressDialogLogin.show();

                                    Call<Login> callLogin = new RetrofitInicializador().LoginService().getUser(email, password);

                                    callLogin.enqueue(new Callback<Login>() {
                                        @Override
                                        public void onResponse(Call<Login> call, Response<Login> response) {

                                            Login retorno = (Login) response.body();

                                            progressDialogLogin.dismiss();

                                            if (!response.isSuccessful()) {

                                                Log.i("Erro", "ERROR onResponde: " + response.errorBody().toString());
                                                Log.i("Erro", "ERROR onResponde: " + response.code());
                                                Toast.makeText(RegistroActivity.this, retorno.getMsg(), Toast.LENGTH_SHORT).show();

                                            } else {

                                                session.createLoginSession(mEmailView.getText().toString(), mPasswordView.getText().toString());

                                                Toast.makeText(RegistroActivity.this, "Logado com Sucesso", Toast.LENGTH_SHORT);

                                                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);

                                                startActivity(intent);

                                                finish();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Login> call, Throwable t) {

                                            progressDialogLogin.dismiss();

                                            Toast.makeText(RegistroActivity.this, "Erro na tentativa de Login", Toast.LENGTH_SHORT).show();

                                            Intent goLogin = new Intent(RegistroActivity.this, LoginActivity.class);
                                            startActivity(goLogin);

                                            finish();;

                                        }
                                    });


                                } else {
                                    Toast.makeText(RegistroActivity.this, resultado.getMensagem(), Toast.LENGTH_SHORT);
                                }

                            }



                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            progressDialog.dismiss();
                            Log.e("onFailure", " Erro ao Salva usuário " + t.getMessage());
                        }
                    });

                }


            }
        });

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
