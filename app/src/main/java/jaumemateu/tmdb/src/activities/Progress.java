package jaumemateu.tmdb.src.activities;

import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import jaumemateu.tmdb.R;
import jaumemateu.tmdb.src.model.ListaPeliculas;
import jaumemateu.tmdb.src.model.Pelicula;
import jaumemateu.tmdb.src.rest.Rest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Progress extends AppCompatActivity {

    public static final String BASE_URL = "http://api.themoviedb.org/3/search/";
    private static Retrofit retrofit = null;

    private String nombrePelicula;
    private List<String> listaTitulos;
    private List<String> listaImage;
    private List<Pelicula> listaPelis;

    private final static String API_KEY = "apiKey";
    private HashMap<String,String> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        Bundle extras = getIntent().getExtras();

        nombrePelicula = extras.getString("movieNombre");

        listaPelis = new ArrayList<>();
        listaImage = new ArrayList<>();
        listaTitulos = new ArrayList<>();
        options = new HashMap<>();

        getActivityResults();

    }

    public void getActivityResults(){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        options.put("api_key", API_KEY);
        options.put("query", nombrePelicula);

        Rest RestApiService = retrofit.create(Rest.class);

        Call<ListaPeliculas> call = RestApiService.getMovieDetails(options);
        String a = call.request().url().toString();
        call.enqueue(new Callback<ListaPeliculas>() {
            @Override
            public void onResponse(Call<ListaPeliculas> call, Response<ListaPeliculas> response) {
                Log.d("aaa", "Response returned by website is : " + response.code());
                listaPelis.addAll(response.body().getResults());
                getTituloImage();
                Intent resultIntent = new Intent(Progress.this,Results.class);
                resultIntent.putExtra("movieTitulo",(ArrayList<String>) listaTitulos);
                resultIntent.putExtra("movieImage",(ArrayList<String>) listaImage);
                startActivityForResult(resultIntent,2);
            }
            @Override
            public void onFailure(Call<ListaPeliculas> call, Throwable throwable) {
                Log.d("aaa", "Response returned by website is : " + throwable.toString());
                Toast.makeText(getBaseContext(), "Error: No hay conexion con la API", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    public void getTituloImage(){

        for(Pelicula peli : listaPelis){

            listaTitulos.add(peli.getTitle());
            listaImage.add(peli.getPosterPath());

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode == 2) && (resultCode == 1)){

            finish();

        }

    }

}
