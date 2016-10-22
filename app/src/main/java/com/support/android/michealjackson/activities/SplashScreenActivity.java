
package com.support.android.michealjackson.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.support.android.michealjackson.helper.ConnectionDetector;
import com.support.android.michealjackson.R;
import com.support.android.michealjackson.model.Songs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class SplashScreenActivity extends AppCompatActivity {

    private static String apiUrl = "https://itunes.apple.com/search?term=Michael+jackson";
    private ConnectionDetector conDet;
    private ProgressBar pgBar;
    private Button tryAgain;

    @Override
    protected void onResume(){
        super.onResume();
        conDet = new ConnectionDetector(SplashScreenActivity.this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        conDet = new ConnectionDetector(SplashScreenActivity.this);
        pgBar = (ProgressBar) findViewById(R.id.splashProgressBar);
        tryAgain = (Button) findViewById(R.id.tryAgain);
        if (conDet.isConnectingToInternet()){
            (new AsyncListViewLoaderTiled()).execute(apiUrl);
            tryAgain.setVisibility(View.INVISIBLE);
            pgBar.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please Check Network Connectivity",Toast.LENGTH_LONG).show();
            tryAgain.setVisibility(View.VISIBLE);
            pgBar.setVisibility(View.INVISIBLE);
        }

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conDet.isConnectingToInternet()){
                    pgBar.setVisibility(View.VISIBLE);
                    tryAgain.setVisibility(View.INVISIBLE);
                    (new AsyncListViewLoaderTiled()).execute(apiUrl);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Check Network Connectivity",Toast.LENGTH_LONG).show();
                    tryAgain.setVisibility(View.VISIBLE);
                    pgBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestart(){
        super.onRestart();

    }

    private class AsyncListViewLoaderTiled extends AsyncTask<String, Void, ArrayList<Songs>> {
        private final ProgressDialog dialog = new ProgressDialog(SplashScreenActivity.this);
        InputStreamReader inputStream = null;
        String result = "";
        String listName;

        @Override
        protected void onPostExecute(ArrayList<Songs> result) {
            super.onPostExecute(result);


                if(result == null){
                    Toast.makeText(SplashScreenActivity.this,"Something went wrong! Check your network connection or Try Later",Toast.LENGTH_LONG).show();
                    pgBar.setVisibility(View.INVISIBLE);
                    tryAgain.setVisibility(View.VISIBLE);
                }
                else if (result != null && result.size() > 0 && SplashScreenActivity.this != null) {

                    pgBar.setVisibility(View.INVISIBLE);
                    tryAgain.setVisibility(View.INVISIBLE);
                    Intent goToMainActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
                    goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    goToMainActivity.putParcelableArrayListExtra("SONGS_LIST_INTENT", result);
                    startActivity(goToMainActivity);

                }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tryAgain.setVisibility(View.INVISIBLE);

        }

        @Override
        protected ArrayList<Songs> doInBackground(String... params) {

            ArrayList<Songs> mReturn = new ArrayList<Songs>();

            URL u;
            try {
                u = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
                urlConnection.setConnectTimeout(3000);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                int code = urlConnection.getResponseCode();
                inputStream = new InputStreamReader(urlConnection.getInputStream());

                try {
                    BufferedReader bReader = new BufferedReader(inputStream);
                    StringBuilder sBuilder = new StringBuilder();

                    String line = null;
                    while ((line = bReader.readLine()) != null) {
                        sBuilder.append(line + "\n");
                    }

                    inputStream.close();
                    result = sBuilder.toString();
                    int mid = 0,sid=0;
                    String artistName, collectionName,trackName,artworkUrl100,trackViewUrl,previewUrl,releaseDate,trackTimeMillis,primaryGenreName;
                    Songs songsObject;
                    try {

                        JSONObject jObj = new JSONObject(result);

                        JSONArray jSongArray = jObj.getJSONArray("results");
                        for(int j = 0; jSongArray != null && jSongArray.length() > 0 && j < jSongArray.length(); j++)
                        {
                            JSONObject jObjectSong = jSongArray.getJSONObject(j);
                            if(jObjectSong.isNull("artistName"))
                                artistName = "NA";
                            else{
                                artistName = jObjectSong.getString("artistName").toUpperCase();
                            }

                            if(jObjectSong.isNull("collectionName"))
                                collectionName = "NA";
                            else{
                                collectionName = jObjectSong.getString("collectionName").toUpperCase();
                            }
                            if(jObjectSong.isNull("trackName"))
                                trackName = "NA";
                            else{
                                trackName = jObjectSong.getString("trackName").toUpperCase();
                            }
                            if(jObjectSong.isNull("artworkUrl100"))
                                artworkUrl100 = "NA";
                            else{
                                artworkUrl100 = jObjectSong.getString("artworkUrl100");
                            }
                            if(jObjectSong.isNull("trackViewUrl"))
                                trackViewUrl = "NA";
                            else{
                                trackViewUrl = jObjectSong.getString("trackViewUrl");
                            }
                            if(jObjectSong.isNull("previewUrl"))
                                previewUrl = "NA";
                            else{
                                previewUrl = jObjectSong.getString("previewUrl");
                            }
                            if(jObjectSong.isNull("releaseDate"))
                                releaseDate = "NA";
                            else{
                                releaseDate = jObjectSong.getString("releaseDate").toUpperCase();
                            }
                            if(jObjectSong.isNull("primaryGenreName"))
                                primaryGenreName = "NA";
                            else{
                                primaryGenreName = jObjectSong.getString("primaryGenreName").toUpperCase();
                            }
                            Songs sObj = new Songs();
                            sObj.setArtistName(artistName);
                            sObj.setCollectionName(collectionName);
                            sObj.setTrackName(trackName);
                            sObj.setArtworkUrl100(artworkUrl100);
                            sObj.setTrackViewUrl(trackViewUrl);
                            sObj.setPreviewUrl(previewUrl);
                            sObj.setReleaseDate(releaseDate);
                            sObj.setPrimaryGenreName(primaryGenreName);
                            mReturn.add(sObj);

                        }
                    } catch (JSONException e) {
                        Log.e("JSONException", "Error: " + e.toString());
                        return null;
                    }


                } catch (Exception e) {
                    Log.e("StringBuilding ", "Error converting result " + e.toString());
                    return null;
                }

            } catch (MalformedURLException e) {
                Log.e("Malformed URL ", "Error converting result " + e.toString());
                return null;
            } catch (ProtocolException e) {
                Log.e("ProtocolExc", "Error converting result " + e.toString());
                return null;
            } catch (IOException e) {
                Log.e("IO EXCEPTION ", "Error converting result " + e.toString());
                return null;

            }


            return mReturn;
        }
    }

}