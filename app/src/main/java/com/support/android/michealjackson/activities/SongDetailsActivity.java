package com.support.android.michealjackson.activities;

/**
 * Created by amitagarwal3 on 3/8/2016.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.support.android.michealjackson.R;
import com.support.android.michealjackson.model.Songs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongDetailsActivity extends AppCompatActivity {


    private TextView trackName;
    private TextView collectionName;
    private TextView genreName;
    private TextView relDate;
    private TextView previewUrl;
    private TextView trackUrl;
    private ImageView artworkImage;
    ImageLoaderConfiguration config;
    Songs s;
    DisplayImageOptions imgDisplayOptions;
    static ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_details);

        config  = new ImageLoaderConfiguration.Builder(this).memoryCacheSize(41943040).discCacheSize(104857600).threadPoolSize(10).build();
        imgDisplayOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.ic_mj).showImageOnFail(R.drawable.ic_mj).showImageOnLoading(R.drawable.ic_mj).cacheInMemory().cacheOnDisc().build();
        imageLoader.init(config);

        trackName = (TextView) findViewById(R.id.trackname);
        collectionName = (TextView) findViewById(R.id.collectionName);
        genreName = (TextView) findViewById(R.id.genreName);
        artworkImage = (ImageView) findViewById(R.id.artworkImage);
        relDate = (TextView) findViewById(R.id.relDateDetails);
        previewUrl = (TextView) findViewById(R.id.previewUrl);
        trackUrl = (TextView) findViewById(R.id.trackViewUrl);
        s = (Songs) getIntent().getParcelableExtra("SongsObject");

        trackName.setText(s.getTrackName());
        collectionName.setText(s.getCollectionName());
        genreName.setText(s.getPrimaryGenreName());
        relDate.setText(s.getReleaseDate());

        imageLoader.displayImage("", artworkImage); //clears previous one

        imageLoader.displayImage(s.getArtworkUrl100(), artworkImage,
                imgDisplayOptions
        );

        previewUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(s.getPreviewUrl());
                Intent videoIntent = new Intent(Intent.ACTION_VIEW);
                videoIntent.setData(uri);
                startActivity(videoIntent);

            }
        });

        trackUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(s.getTrackViewUrl());
                Intent videoIntent = new Intent(Intent.ACTION_VIEW);
                videoIntent.setData(uri);
                startActivity(videoIntent);

            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static String getOnlyStrings(String s) {
        Pattern pattern = Pattern.compile("[^a-z A-Z]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        number = number.replaceAll("-","");
        return number;
    }




}

