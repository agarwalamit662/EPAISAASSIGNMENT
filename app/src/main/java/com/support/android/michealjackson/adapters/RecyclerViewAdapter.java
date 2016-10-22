package com.support.android.michealjackson.adapters;

/**
 * Created by amitagarwal3 on 3/5/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.support.android.michealjackson.R;
import com.support.android.michealjackson.activities.SongDetailsActivity;
import com.support.android.michealjackson.model.Songs;
import com.support.android.michealjackson.viewholders.RecyclerViewHolders;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private ArrayList<Songs> songs;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Songs item);
    }

    private final OnItemClickListener listener;

    ImageLoaderConfiguration config;

    DisplayImageOptions imgDisplayOptions;

    static ImageLoader imageLoader = ImageLoader.getInstance();

    public RecyclerViewAdapter(Context context, ArrayList<Songs> songs,OnItemClickListener listener) {
        this.songs = songs;
        this.context = context;
        this.listener = listener;
        config  = new ImageLoaderConfiguration.Builder(context).memoryCacheSize(41943040).discCacheSize(104857600).threadPoolSize(10).build();
        imgDisplayOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.ic_launcher).showImageOnFail(R.drawable.ic_mj).showImageOnLoading(R.drawable.ic_mj).cacheInMemory().cacheOnDisc().build();
        imageLoader.init(config);
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tracks_item, parent,false);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);

        return rcv;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {

        holder.bind(songs.get(position), listener);


        holder.trackName.setText(songs.get(position).getTrackName());

        imageLoader.displayImage("", holder.trackImage); //clears previous one

        imageLoader.displayImage(songs.get(position).getArtworkUrl100(),holder.trackImage,
                    imgDisplayOptions
            );

        holder.trackImage.setVisibility(View.VISIBLE);

            holder.trackName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, SongDetailsActivity.class);
                    i.putExtra("SongsObject", songs.get(position));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                }
            });

        holder.trackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, SongDetailsActivity.class);
                i.putExtra("SongsObject", songs.get(position));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                context.startActivity(i);

            }
        });

    }
    @Override
    public int getItemCount() {
        if(songs == null || songs.size() == 0)
            return 0;
        return this.songs.size();
    }
}
