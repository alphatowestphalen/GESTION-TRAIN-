package model;

import DB.ConnexionDB;
import Repository.PlaceRp;
import Repository.TrainRp;
import Table.Place;
import Table.Train;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableViewPlace {
    private Connection connection;

    public TableViewPlace() {
        ConnexionDB connexionDB = new ConnexionDB();
        connection = connexionDB.getConnexion();
    }
    public ObservableList<Place> getTableViews() {
        ObservableList<Place> PlaceListe = FXCollections.observableArrayList();
        try {
            PlaceRp placeRp = new PlaceRp();
            Place place;
            ResultSet load = placeRp.loadTable();
            while (load.next()){
                place = new Place();
                place.setId(load.getString(1));
                place.setNumTrain(load.getString(2));
                place.setNumPlace(load.getString("NumPlace"));
                place.setOccupation(load.getString("Occupation"));
                PlaceListe.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PlaceListe;
    }
}
