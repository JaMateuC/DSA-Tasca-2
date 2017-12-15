package jaumemateu.tmdb.src.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jaumemateu.tmdb.R;
import jaumemateu.tmdb.src.model.Tarjeta;

public class Results extends AppCompatActivity {

    private List<String> listaTitulos;
    private List<String> listaImage;
    private List<Tarjeta> tarjetaList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        listaTitulos = new ArrayList<>();
        listaImage = new ArrayList<>();
        tarjetaList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        listaTitulos = extras.getStringArrayList("movieTitulo");
        listaImage = extras.getStringArrayList("movieImage");

        if(listaTitulos.isEmpty()){

            Toast.makeText(getBaseContext(), "Error: No hay ninguna pelicula con ese nombre", Toast.LENGTH_LONG).show();

            setResult(1);
            finish();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        for(int i = 0;  i < listaTitulos.size(); i++){

            tarjetaList.add(new Tarjeta(listaTitulos.get(i),listaImage.get(i)));

        }

        mAdapter = new MovieAdapter(this,tarjetaList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {
        setResult(1);
        finish();
    }
}
