package com.example.yourmusic.v1.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlbumEntity {

    private String artistName;
    private String albumName;
    private String company;
    private int appreciation;
    private Date date;
    private String genre;

    public String getArtistName() {
        return artistName;
    }

    public boolean setArtistName(String artistName) {
        boolean result = false;

        if (artistName.length() > 3 && artistName.matches("(^([A-Z]{1})[a-z0-9]+[ ]?){1,2}$") && artistName.length() < 15){
            this.artistName = artistName;
            result = true;
        }
        return result;
    }

    public String getAlbumName() {
        return albumName;
    }

    public boolean setAlbumName(String albumName) {
        boolean result = false;

        if (albumName.length() > 4 && albumName.matches("^([A-Z]{1}[a-z]+[ ]?){1,2}$") && artistName.length() < 21){
            this.albumName = artistName;
            result = true;
        }
        return result;
    }

    public String getCompany() {
        return company;
    }

    public boolean setCompany(String company) {
        boolean result = false;

        if (company.length() > 4 && albumName.matches("^([A-Z]{1}[a-z]+[ ]?){1,2}$") && company.length() < 16){
            this.company = company;
            result = true;
        }
        return result;
    }

    public int getAppreciation() {
        return appreciation;
    }

    public boolean setAppreciation(String appreciation) {
        boolean result = false;

        if(appreciation.matches("[0-9]*") && appreciation.matches("[0-9]|10")) {
            this.appreciation = Integer.parseInt(appreciation);
            result = true;
        }

        return result;
    }

    public Date getDate() {
        return date;
    }

    public boolean setDate(String date) {
        boolean result = false;
        SimpleDateFormat newDate = new SimpleDateFormat("dd/mm/yyyy");

        if (date.matches("^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$")){
            try {
                this.date = newDate.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            result = true;
        }
        return result;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
