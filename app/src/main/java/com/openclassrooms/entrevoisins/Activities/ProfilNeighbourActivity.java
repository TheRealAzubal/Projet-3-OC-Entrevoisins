package com.openclassrooms.entrevoisins.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import static com.openclassrooms.entrevoisins.ui.MyNeighbourRecyclerViewAdapter.DETAIL_NEIGHBOUR;

public class ProfilNeighbourActivity extends AppCompatActivity {
    int neighbourId;
    String neighbourName;
    String neighbourAvatar;
    Boolean neighbourIsFavorite;
    Neighbour mNeighbour;
    NeighbourApiService mApiService;
    FloatingActionButton mButtonChoiceNeighbourFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareApiServiceAndNeighbour();
        mNeighbourNotNull();

    }

    public void prepareApiServiceAndNeighbour(){
        mApiService = DI.getNeighbourApiService();
        mNeighbour = getIntent().getParcelableExtra(DETAIL_NEIGHBOUR);
    }

    public void mNeighbourNotNull(){
        if (mNeighbour != null) {
            getFavoriteNeighbour();
            setUpViews();
            mButtonChoiceNeighbourFavoriteOnclickListner();
        }
    }

    public void getFavoriteNeighbour() {
        neighbourId = mNeighbour.getId();
        neighbourName = mNeighbour.getName();
        neighbourAvatar = mNeighbour.getAvatarUrl();
        neighbourIsFavorite = mNeighbour.isFavorite();
    }

    public void setUpViews() {
        setContentView(R.layout.activity_detail_neighbour);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView mNeighbourAvatar = findViewById(R.id.mDetailAvatar);
        Glide.with(mNeighbourAvatar.getContext())
                .load(neighbourAvatar)
                .into(mNeighbourAvatar);

        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.toolbar_layout);
        // Set title of Detail page
        collapsingToolbar.setTitle(neighbourName);

        TextView mNeighbourName = findViewById(R.id.mDetailName);
        mNeighbourName.setText(neighbourName);

        mButtonChoiceNeighbourFavorite = findViewById(R.id.fab);
        if (neighbourIsFavorite) {
            mButtonChoiceNeighbourFavorite.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            mButtonChoiceNeighbourFavorite.setImageResource(R.drawable.ic_star_border_yellow_24dp);
        }
        mButtonChoiceNeighbourFavorite.hide();
        mButtonChoiceNeighbourFavorite.show();
    }

    private void mButtonChoiceNeighbourFavoriteOnclickListner() {

        mButtonChoiceNeighbourFavorite.setOnClickListener(view -> {

            if (!neighbourIsFavorite) {
                mButtonChoiceNeighbourFavorite.setImageResource(R.drawable.ic_star_yellow_24dp);
                mButtonChoiceNeighbourFavorite.hide();
                mButtonChoiceNeighbourFavorite.show();
                addFavoriteNeighbour(view);
            } else {
                deleteFavoriteNeighbour(view);
            }
        });
    }

    private void addFavoriteNeighbour(View view) {
        mApiService.addFavoriteNeighbour(mNeighbour);
        neighbourIsFavorite = true;
        Snackbar.make(view, "Vous venez d'ajouter " + neighbourName + " à vos voisins favoris!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void deleteFavoriteNeighbour(View view) {
        mButtonChoiceNeighbourFavorite.setImageResource(R.drawable.ic_star_border_yellow_24dp);
        mButtonChoiceNeighbourFavorite.hide();
        mButtonChoiceNeighbourFavorite.show();
        Snackbar.make(view, "Ce voisin a été supprimé de vos favoris!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        neighbourIsFavorite = false;
        mApiService.deleteFavoriteNeighbour(mNeighbour);
    }

}