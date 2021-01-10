package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteList = new ArrayList<>();
        for (Neighbour neighbour : getNeighbours()) {
            if (neighbour.isFavorite()) {
                favoriteList.add(neighbour);
            }
        }
        return favoriteList;
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        for (Neighbour neighbour1 : getNeighbours()) {
            if (neighbour.getId().equals(neighbour1.getId())) {
                neighbour1.setFavorite(true);
            }
        }
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        for (Neighbour favNeighbour : getNeighbours()) {
            if (neighbour.getId().equals(favNeighbour.getId())) {
                favNeighbour.setFavorite(false);
            }
        }
    }
}
