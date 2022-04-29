package com.dvd.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class DVDLibraryDAO {
    private static final String GET_LIBRARY_ITEMS = "SELECT id, title, year, genre FROM Item WHERE username=?";
    private static final String CREATE_DVD_ITEM = "INSERT INTO Item (id, username, title, year, genre) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_GENRES = "SELECT DISTINCT genre FROM Item WHERE username=?";
    private static final String GET_USERNAMES = "SELECT DISTINCT username FROM Item WHERE username IS NOT NULL";

    private static final String URL_CONNECTION = "jdbc:mysql://127.0.0.1:3306/ead_lab_10?user=root&password=5246264425";

    private static final String GET_NEXT_ID = "SELECT id FROM ObjectIDs WHERE table_name=?";
    private static final String UPDATE_ID = "UPDATE ObjectIDs SET id=? WHERE table_name=?";
    private static final String CREATE_ID = "INSERT INTO ObjectIDs (table_name, id) VALUES (?, ?)";

    public DVDLibraryDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public List getGenres(String username) {
        List genres = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL_CONNECTION);
            preparedStatement = connection.prepareStatement(GET_GENRES);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                genres.add(resultSet.getString("genre"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeConnection(connection, preparedStatement, resultSet);
        }
        return genres;
    }

    public List getDVDCollection(String username){
        List dvdItems = new ArrayList<DVDItem>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL_CONNECTION);
            preparedStatement = connection.prepareStatement(GET_LIBRARY_ITEMS);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                String genre = resultSet.getString("genre");
                DVDItem dvdItem = new DVDItem(title, year, genre);
                dvdItems.add(dvdItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection, preparedStatement, resultSet);
        }
        return dvdItems;
    }

    public void addDVD(String username, DVDItem item){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            int id = getNextID("Item");
            connection = DriverManager.getConnection(URL_CONNECTION);
            preparedStatement = connection.prepareStatement(CREATE_DVD_ITEM);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, item.getTitle());
            preparedStatement.setString(4, item.getYear());
            preparedStatement.setString(5, item.getGenre());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection, preparedStatement, resultSet);
        }
    }

    public int getNextID(String tableName) {
        int id = 1;
        Connection connection = null;
        PreparedStatement queryStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL_CONNECTION);
            queryStatement = connection.prepareStatement(GET_NEXT_ID);
            queryStatement.setString(1, tableName);
            resultSet = queryStatement.executeQuery();
            if(resultSet.next()) {
                id = resultSet.getInt("id");
                updateStatement = connection.prepareStatement(UPDATE_ID);
                updateStatement.setInt(1, id+1);
                updateStatement.setString(2, tableName);
                updateStatement.executeUpdate();
            } else {
                updateStatement = connection.prepareStatement(CREATE_ID);
                updateStatement.setString(1, tableName);
                updateStatement.setInt(2, id+1);
                updateStatement.executeUpdate();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if(resultSet != null) {
                try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if(queryStatement != null) {
                try { queryStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if(updateStatement != null) {
                try { updateStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if(connection != null) {
                try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return id;
    }

    public static List getUsernames(){
        ArrayList<String> usernames = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            connection = DriverManager.getConnection(URL_CONNECTION);
            preparedStatement = connection.prepareStatement(GET_USERNAMES);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                usernames.add(resultSet.getString("username"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(resultSet!=null){
                try{
                    resultSet.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return usernames;
    }

    private void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(resultSet!=null){
            try{
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null){
            try{
                preparedStatement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
