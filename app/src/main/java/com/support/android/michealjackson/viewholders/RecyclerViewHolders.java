package com.support.android.michealjackson.viewholders;

/**
 * Created by amitagarwal3 on 3/5/2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.support.android.michealjackson.R;
import com.support.android.michealjackson.adapters.RecyclerViewAdapter;
import com.support.android.michealjackson.model.Songs;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView trackName;
    public ImageView trackImage;
    public RecyclerViewHolders(View itemView) {
        super(itemView);
        trackName = (TextView)itemView.findViewById(R.id.track_name);
        trackImage = (ImageView)itemView.findViewById(R.id.track_image);
    }
    @Override
    public void onClick(View view) {

    }

    public void bind(final Songs item, final RecyclerViewAdapter.OnItemClickListener listener) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }
}
