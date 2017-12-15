package jaumemateu.tmdb.src.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jaumemateu.tmdb.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButtonCercaListener();

    }

    public void addButtonCercaListener(){

        Button buttonCerca = findViewById(R.id.buttonCerca);
        buttonCerca.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent progressIntent = new Intent(v.getContext(),Progress.class);
                EditText movieNombre = findViewById(R.id.movieNombre);
                String movie = movieNombre.getText().toString();
                progressIntent.putExtra("movieNombre", movie); //añade el titulo de la pelicula
                startActivityForResult(progressIntent,1);

            }
        });

    }

}
