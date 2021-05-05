package com.example.yourmusic.v1.models;

import android.util.Log;

import com.example.yourmusic.R;
import com.example.yourmusic.v1.views.MyApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import io.realm.Realm;
import io.realm.RealmResults;

public class AlbumModel {

    public boolean insertAlbum(AlbumEntity album){
        AtomicBoolean result = new AtomicBoolean(false);

        album.setId(UUID.randomUUID().toString());

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            realm.copyToRealm(album);
            result.set(true);
        });

        realm.close();

        return result.get();
    }

    public ArrayList<AlbumEntity> getAllSummarize(){
        Realm realm = Realm.getDefaultInstance();

        RealmResults<AlbumEntity> result = realm.where(AlbumEntity.class).findAll();

        ArrayList<AlbumEntity> albumList = new ArrayList<>();
        albumList.addAll(realm.copyFromRealm(result));

        realm.close();

        ArrayList<AlbumEntity> albumListSummarize = new ArrayList<>();

        for(AlbumEntity album: albumList){

            AlbumEntity auxAlbum= new AlbumEntity();
            auxAlbum.setId(album.getId());
            auxAlbum.setAlbumName(album.getAlbumName());
            auxAlbum.setArtistName(album.getArtistName()+"");
            auxAlbum.setImage(album.getImage());
            albumListSummarize.add(auxAlbum);
        }

        return albumListSummarize;
    }

    public AlbumEntity getAlbumById(String id){
        AlbumEntity result;
        Realm realm = Realm.getDefaultInstance();

        result = realm.where(AlbumEntity.class)
                .equalTo("id", id)
                .findFirst();

        return result;
    }

    public boolean updateAlbum(AlbumEntity album){
        AtomicBoolean result = new AtomicBoolean(false);
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            realm.copyToRealmOrUpdate(album);
            result.set(true);
        });

        return result.get();
    }

    public ArrayList<String> getGenreAlbum(){
        ArrayList<String> genders= new ArrayList<>();

        genders.add(MyApp.getContext().getString(R.string.Choose));
        //genders.add(MyApp.getContext().getString(R.string.pop));

        Realm realm = Realm.getDefaultInstance();
        RealmResults<AlbumEntity> result = realm.where(AlbumEntity.class).distinct("genre").findAll();
        ArrayList<AlbumEntity> albumList = new ArrayList<>();
        albumList.addAll(realm.copyFromRealm(result));

        for(AlbumEntity album: albumList){
            genders.add(album.getGenre());
        }

        return genders;

    }

    public boolean deleteAlbum(String id){
        AtomicBoolean result = new AtomicBoolean(false);
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            AlbumEntity album = realm.where(AlbumEntity.class)
                    .equalTo("id", id)
                    .findFirst();

            album.deleteFromRealm();
            result.set(true);
        });

        realm.close();
        return result.get();
    }

    public ArrayList<AlbumEntity> getAlbumFilter(String artistName, Date date, String genre){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<AlbumEntity> filtered;

        //Buscamos por el nombre del artista, genero y fecha
        if(date==null){
            filtered = realm.where(AlbumEntity.class).contains("artistName", artistName)
                    .contains("genre", genre)
                    .findAll();
        }else{
            filtered = realm.where(AlbumEntity.class).contains("artistName", artistName)
                    .equalTo("date", date)
                    .contains("genre", genre)
                    .findAll();
        }

        ArrayList<AlbumEntity> albums = new ArrayList<>();
        albums.addAll(realm.copyFromRealm(filtered));
        realm.close();

        //Metemos en una lista las propiedades de los albumes que se van a mostrar en el recycler view después de pulsar el boton guardar
        ArrayList<AlbumEntity> albumsSummarize = new ArrayList<>();
        for(AlbumEntity album: albums){
            AlbumEntity aux = new AlbumEntity();
            aux.setId(album.getId());
            aux.setArtistName(album.getArtistName());
            aux.setAlbumName(album.getAlbumName());
            aux.setImage(album.getImage());
            albumsSummarize.add(aux);
        }
        return albumsSummarize;
    }


}
