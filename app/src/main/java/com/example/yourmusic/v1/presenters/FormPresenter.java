package com.example.yourmusic.v1.presenters;


import com.example.yourmusic.v1.interfaces.FormInterface;

public class FormPresenter implements FormInterface.Presenter {

    private FormInterface.View view;

    public FormPresenter(FormInterface.View view) {
        this.view = view;
    }

    @Override
    public void onClickSaveAlbum() {
        view.SaveAlbum();
    }
}
