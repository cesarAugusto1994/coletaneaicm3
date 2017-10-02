package main.java.com.coletaneaicm.coletanea.coletaneaicm.Remotes;

public class Import {
    
    private Call<List<Object>> call;
    private ArrayList<Object> data;
    private Object entity;
    private Repository repository;
    
    public Import(Object entity, Repository repository) {
        this.entity = entity;
        this.repository = repository;
        this.call = new RetrofitInicializador.getColecoes().getColecoes();
    }

    public ArrayList<Object> getData() {
        return this.data;
    }

    public void run() {
         
        this.call.enquee(new Callback<List<Object>>() {

            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {

                ArrayList<Object> item = (ArrayList<Object>) response.body();
                this.repository.criar(tipo, item);

                this.data = colecoes;

                Log.i("onResponse", " Sucesso ao Salvar Colecoes");
            }

            @Override
            public void onFailure(Call<List<Colecoes>> call, Throwable t) {
                Log.e("onFailure", " Erro ao Salvar Colecoes: " + t.getMessage());
            }

        });

    }
}