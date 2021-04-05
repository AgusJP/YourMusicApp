package com.example.yourmusic.v1.presenters;


import com.example.yourmusic.R;
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

    @Override
    public int showError(int typeError) {
        int error;


        switch (typeError){

            case 1:
                error = R.string.artistNameError;
                break;
            case 2:
                error = R.string.albumNameError;
                break;
            case 3:
                error = R.string.companyNameError;
                break;
            case 4:
                error = R.string.appreciationError;
                break;
            case 5:
                error = R.string.dateError;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeError);
        }
        return error;
    }

    @Override
    public void AddSpinnerElement()
    {
        view.AddSpinnerElement();
    }

    @Override
    public void onClickDeleteButton() {
        view.DeleteAlbum();
    }


}
