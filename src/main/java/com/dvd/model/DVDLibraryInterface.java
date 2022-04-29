
package com.dvd.model;

import java.util.List;

public interface DVDLibraryInterface {
    List getDVDCollection();
    DVDItem addDVD(String title, String year, String genre);
    List getGenres();
    void addGenre(String new_genre);
}


