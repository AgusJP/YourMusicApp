package com.example.yourmusic.v1.presenters;

import com.example.yourmusic.v1.interfaces.SearchInterface;
import com.example.yourmusic.v1.models.AlbumModel;

import java.util.ArrayList;

public class SearchPresenter implements SearchInterface.Presenter {

    private SearchInterface.View view;
    private AlbumModel albumModel;

    public SearchPresenter(SearchInterface.View view) {
        this.view = view;
        albumModel = new AlbumModel();
    }

    @Override
    public void onClickSearchButton() {
        view.SearchAlbum();
    }

    @Override
    public ArrayList<String> getGenres() {
        return albumModel.getGenreAlbum();
    }



}
