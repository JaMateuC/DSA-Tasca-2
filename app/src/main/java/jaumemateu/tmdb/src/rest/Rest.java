package jaumemateu.tmdb.src.rest;

import java.util.Map;

import jaumemateu.tmdb.src.model.ListaPeliculas;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by usuario on 15/12/2017.
 */

public interface Rest {

    @GET("movie/")
    Call<ListaPeliculas> getMovieDetails(@QueryMap Map<String, String> options);

}
