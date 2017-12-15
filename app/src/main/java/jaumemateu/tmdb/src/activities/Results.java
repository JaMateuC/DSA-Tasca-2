package jaumemateu.tmdb.src.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jaumemateu.tmdb.R;

public class Results extends AppCompatActivity {

    private List<String> listaTitulos;
    private List<String> listaImage;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        listaTitulos = new ArrayList<>();
        listaImage = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        listaTitulos = extras.getStringArrayList("movieTitulo");
        listaImage = extras.getStringArrayList("movieImage");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }
}
