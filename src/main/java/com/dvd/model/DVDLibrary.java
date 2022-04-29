package com.dvd.model;

import java.sql.SQLException;
import java.util.List;

public class DVDLibrary implements DVDLibraryInterface {
    private String username;
    private List dvdCollection;
    private List genreList;
    private DVDLibraryDAO dao;

    public DVDLibrary(String username) throws SQLException {
        this.username = username;
        this.dao = new DVDLibraryDAO();
    }

    public List getDVDCollection() {
        if(dvdCollection == null){
            dvdCollection = dao.getDVDCollection(username);
        }
        return dvdCollection;
    }

    public DVDItem addDVD(String title, String year, String genre) {
        DVDItem item = new DVDItem(title, year, genre);
        List dvds = getDVDCollection();
        dvds.add(item);
        dao.addDVD(username, item);
        return item;
    }

    public List getGenres() {
        if (genreList == null) {
            genreList = dao.getGenres(username);
        }
        return genreList;
    }

    public void addGenre(String new_genre) {
        if (!genreList.contains(new_genre) ) {
            genreList.add(new_genre);
        }
    }
}
