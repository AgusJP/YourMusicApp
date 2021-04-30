package com.example.yourmusic.v1.presenters;

import androidx.recyclerview.widget.RecyclerView;
import com.example.yourmusic.v1.interfaces.ListInterface;
import com.example.yourmusic.v1.models.AlbumEntity;
import com.example.yourmusic.v1.models.AlbumModel;

import java.util.ArrayList;
import java.util.Date;

public class ListPresenter implements ListInterface.Presenter {

    private ListInterface.View view;
    private AlbumModel albumModel;

    public ListPresenter(ListInterface.View view) {
        this.view = view;
        this.albumModel = new AlbumModel();
    }

    @Override
    public void onClickAddAlbum() {
        view.startFormActivity();
    }

    @Override
    public void onClickSearchIcon() {
        view.startSearchActivity();
    }

    @Override
    public void onClickAboutAppCRUD() {
        view.startAboutActivity();
    }

    @Override
    public void onSwipedAlbum(RecyclerView.ViewHolder target, String id) {
        albumModel.deleteAlbum(id);
        view.onSwipedRemove(target);
    }

    @Override
    public ArrayList<AlbumEntity> getAlbumsFilter(String artistName, Date date, String genre) {
        return albumModel.getAlbumFilter(artistName, date, genre);
    }

    @Override
    public void onClickRecyclerViewAlbum(String id){
        view.startFormActivity(id);
    }

    @Override
    public ArrayList<AlbumEntity> getAllAlbumsSummarize(){
        return albumModel.getAllSummarize();
    }

    @Override
    public ArrayList<String> getGenres() {
        return albumModel.getGenreAlbum();
    }

}
