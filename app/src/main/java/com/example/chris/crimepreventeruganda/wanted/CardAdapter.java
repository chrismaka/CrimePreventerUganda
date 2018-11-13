package com.example.chris.crimepreventeruganda.wanted;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.chris.crimepreventeruganda.R;

import java.util.List;

/**
 * Created by chris on 9/21/2017.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    //Imageloader to load image
    private ImageLoader imageLoader;
    private Context context;

    //List to store all superheroes
    List<Thugs> thugsLists;

    //Constructor of this class
    public CardAdapter(List<Thugs> thugsLists, Context context){
        super();
        //Getting all superheroes
        this.thugsLists = thugsLists;
        this.context = context;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thugs_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Getting the particular item from the list
        Thugs superthug =  thugsLists.get(position);

        //Loading image from url
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(superthug.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.drawable.image, android.R.drawable.ic_dialog_alert));

        //Showing data on the views
        holder.imageView.setImageUrl(superthug.getImageUrl(), imageLoader);
        holder.textViewName.setText(superthug.getName());
        holder.textViewDescription.setText(superthug.getDescription());

    }

    @Override
    public int getItemCount() {
        return thugsLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //Views
        public NetworkImageView imageView;
        public TextView textViewName;
        public TextView textViewDescription;

        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewthug);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
        }
    }
}
