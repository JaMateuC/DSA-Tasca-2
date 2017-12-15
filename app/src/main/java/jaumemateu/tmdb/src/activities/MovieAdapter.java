package jaumemateu.tmdb.src.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jaumemateu.tmdb.R;
import jaumemateu.tmdb.src.model.Tarjeta;

/**
 * Created by usuario on 15/12/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ProductViewHolder> {


    private Context mCtx;

    private List<Tarjeta> productList;

    public MovieAdapter(Context mCtx, List<Tarjeta> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Tarjeta tarjeta = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(tarjeta.getTitle());

        Picasso.with(mCtx)
                .load("http://image.tmdb.org/t/p/w185/" + tarjeta.getImage() )
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);

            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
