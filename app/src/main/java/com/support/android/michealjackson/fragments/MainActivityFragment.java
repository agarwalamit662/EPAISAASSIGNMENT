package com.support.android.michealjackson.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.support.android.michealjackson.R;
import com.support.android.michealjackson.activities.SongDetailsActivity;
import com.support.android.michealjackson.adapters.RecyclerViewAdapter;
import com.support.android.michealjackson.helper.ConnectionDetector;
import com.support.android.michealjackson.helper.DividerItemDecoration;
import com.support.android.michealjackson.model.Songs;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView rv;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_main_activity_jackson, container, false);
        final ConnectionDetector conDet = new ConnectionDetector(getActivity());



        ArrayList<Songs> songs = (ArrayList) getActivity().getIntent().getParcelableArrayListExtra("SONGS_LIST_INTENT");

        rv = (RecyclerView) view.findViewById(R.id.recycler_view_mj);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getActivity(), songs, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Songs item) {

                Intent i = new Intent(getContext(), SongDetailsActivity.class);
                i.putExtra("SongsObject", item);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
        rv.setAdapter(rcAdapter);

        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        return view;
    }




}
