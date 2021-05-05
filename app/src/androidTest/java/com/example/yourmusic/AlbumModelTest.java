package com.example.yourmusic;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.yourmusic.v1.models.AlbumEntity;
import com.example.yourmusic.v1.models.AlbumModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AlbumModelTest {

    private AlbumModel albumModel;
    private AlbumEntity albumEntity;

    @Before
    public void settingAlbum() {
        albumModel = new AlbumModel();
        albumEntity = new AlbumEntity();
        albumEntity.setId("1");
        albumEntity.setArtistName("Dani");
        albumEntity.setAlbumName("Promesas");
        albumEntity.setCompany("Ejemplo");
        albumEntity.setAppreciation("7");
        albumEntity.setImage("ImagenEjemplo");
        albumEntity.setDate("27/07/2000");
        albumEntity.setFav(true);
        albumEntity.setGenre("Pop");
        albumModel.insertAlbum(albumEntity);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.yourmusic", appContext.getPackageName());
    }

    @Test
    public void insertAlbum() {
        assertEquals(true, this.albumModel.insertAlbum(this.albumEntity));
        assertEquals("Dani", this.albumEntity.getArtistName());
        assertEquals("Promesas", this.albumEntity.getAlbumName());
        assertEquals("Ejemplo", this.albumEntity.getCompany());
        assertEquals(7, this.albumEntity.getAppreciation());
        assertEquals("ImagenEjemplo", this.albumEntity.getImage());
        assertEquals("Pop", this.albumEntity.getGenre());
    }

    @Test
    public void updateAlbum() {
        this.albumEntity.setArtistName("Pepe");
        assertEquals(true, this.albumModel.updateAlbum(this.albumEntity));

        this.albumEntity.setAlbumName("Teatros");
        assertEquals(true, this.albumModel.updateAlbum(this.albumEntity));

        this.albumEntity.setArtistName("OtroEjemplo");
        assertEquals(true, this.albumModel.updateAlbum(this.albumEntity));

        this.albumEntity.setAppreciation("8");
        assertEquals(true, this.albumModel.updateAlbum(this.albumEntity));

        this.albumEntity.setArtistName("OtraImagenEjemplo");
        assertEquals(true, this.albumModel.updateAlbum(this.albumEntity));

        this.albumEntity.setDate("20/07/2000");
        assertEquals(true, this.albumModel.updateAlbum(this.albumEntity));

        this.albumEntity.setGenre("Rap");
        assertEquals(true, this.albumModel.updateAlbum(this.albumEntity));

        this.albumEntity.setFav(false);
        assertEquals(true, this.albumModel.updateAlbum(this.albumEntity));

    }

    @Test
    public void deleteAlbum() {
        assertEquals(true, this.albumModel.deleteAlbum(this.albumEntity.getId()));
    }

    @Test
    public void getAlbumbyId(){
        assertEquals(this.albumEntity.getId(), this.albumModel.getAlbumById(this.albumEntity.getId()).getId());
        assertEquals(this.albumEntity.getArtistName(), this.albumModel.getAlbumById(this.albumEntity.getId()).getArtistName());
    }

    @Test
    public void getGenres(){
        ArrayList<String> genres = new ArrayList<>();
        genres.add("Choose a genre");
        assertEquals(genres.get(0), this.albumModel.getGenreAlbum().get(0));
    }

    @Test
    public void filterAlbum(){
        ArrayList<AlbumEntity> albums = new ArrayList<>();
        albums.add(this.albumEntity);
        SimpleDateFormat newDate = new SimpleDateFormat("dd/mm/yyyy");
        try {
            //Probamos que filtra de todas las maneras posibles
            assertEquals(albums.get(0).getArtistName(), this.albumModel.getAlbumFilter("Dani", newDate.parse("27/07/2000"), "Pop").get(0).getArtistName());
            assertEquals(albums.get(0).getArtistName(), this.albumModel.getAlbumFilter("", newDate.parse("27/07/2000"), "").get(0).getArtistName());
            assertEquals(albums.get(0).getArtistName(), this.albumModel.getAlbumFilter("", newDate.parse(""), "Pop").get(0).getArtistName());
            assertEquals(albums.get(0).getArtistName(), this.albumModel.getAlbumFilter("Dani", newDate.parse(""), "").get(0).getArtistName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}