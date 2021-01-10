package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public interface NeighbourApiService {

    List<Neighbour> getNeighbours();

    void deleteNeighbour(Neighbour neighbour);

    List<Neighbour> getFavoriteNeighbours();

    void addFavoriteNeighbour(Neighbour neighbour);

    void deleteFavoriteNeighbour(Neighbour neighbour);

}
