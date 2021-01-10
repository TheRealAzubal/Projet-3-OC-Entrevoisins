package com.openclassrooms.entrevoisins.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.MyNeighbourRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoriteNeighboursFragment extends Fragment {
    private List<Neighbour> mFavNeighbour;
    private NeighbourApiService mApiService;
    private RecyclerView mFavRecyclerView;

    public static FavoriteNeighboursFragment newInstance() {
        FavoriteNeighboursFragment fragment = new FavoriteNeighboursFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        Context context = view.getContext();
        mFavRecyclerView = (RecyclerView) view;
        mFavRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mFavRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }

    private void initList() {
        mFavNeighbour = mApiService.getFavoriteNeighbours();
        mFavRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavNeighbour,1));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteFavoriteNeighbourEvent event) {
        mApiService.deleteFavoriteNeighbour(event.neighbour);
        initList();
    }
}
