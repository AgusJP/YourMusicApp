package com.example.yourmusic;

import com.example.yourmusic.v1.models.AlbumEntity;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AlbumEntityTest {

    private AlbumEntity album;

    @Before
    public void instantiateAlbum(){
        this.album = new AlbumEntity();
    }

    @Test
    public void AlbumId(){
        this.album.setId("1");
        assertEquals("1", this.album.getId());
        this.album.setId("10");
        assertEquals("10", this.album.getId());
    }

    @Test
    public void ArtistName(){
        assertEquals(true, this.album.setArtistName("Dani"));
        assertEquals(false, this.album.setArtistName("DANI"));
        assertEquals(false, this.album.setArtistName("Ro"));
        assertEquals("Dani", this.album.getArtistName());
    }

    @Test
    public void AlbumName(){
        assertEquals(true, this.album.setAlbumName("Los Invencibles"));
        assertEquals(false, this.album.setAlbumName("Los3 Invencibles"));
        assertEquals(false, this.album.setAlbumName("34 pardillos"));
        assertEquals(false, this.album.setAlbumName("Los Invenciblesssssssssssssssss"));
        assertEquals("Los Invencibles", this.album.getAlbumName());
    }

    @Test
    public void Company(){
        assertEquals(true, this.album.setCompany("The Company"));
        assertEquals(false, this.album.setCompany("THE cOMPANY"));
        assertEquals("The Company", this.album.getCompany());
    }

    @Test
    public void Appreciation(){
        assertEquals(false, this.album.setAppreciation("-1"));
        assertEquals(true, this.album.setAppreciation("0"));
        assertEquals(0, this.album.getAppreciation());
        assertEquals(false, this.album.setAppreciation("11"));
        assertEquals(true, this.album.setAppreciation("5"));
        assertEquals(5, this.album.getAppreciation());
    }

    @Test
    public void AlbumDate(){
        assertEquals(true, this.album.setDate("27/07/2000"));
        assertEquals(false, this.album.setDate("78/56/2021"));
        assertEquals(true, this.album.setDate("1/2/2021"));
        SimpleDateFormat newDate = new SimpleDateFormat("dd/mm/yyyy");
        assertEquals("01/02/2021", newDate.format(this.album.getDate()));
    }

    @Test
    public void Fav(){
        this.album.setFav(true);
        assertEquals(true, this.album.isFav());
        this.album.setFav(false);
        assertEquals(false, this.album.isFav());
    }

    @Test
    public void AlbumGenre(){
        this.album.setGenre("Pop");
        assertEquals("Pop", this.album.getGenre());
        this.album.setGenre("Rap");
        assertEquals("Rap", this.album.getGenre());
    }

    @Test
    public void AlbumImage(){
        this.album.setImage("ImagenDeEjemplo");
        assertEquals("ImagenDeEjemplo", this.album.getImage());
    }

}
