package model;

import DB.ConnexionDB;
import Repository.TrainRp;
import Table.Train;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableViewTrain {
    private Connection connection;

    public TableViewTrain() {
        ConnexionDB connexionDB = new ConnexionDB();
        connection = connexionDB.getConnexion();
    }

    public ObservableList<Train> getTableViews() {
        ObservableList<Train> trainListe = FXCollections.observableArrayList();
        try {
                TrainRp trainRp = new TrainRp();
                Train train;
                ResultSet load = trainRp.loadTable();
                while (load.next()){
                    train = new Train();
                    train.setId(load.getString("id"));
                    train.setNumItineraire(load.getString("NumItineraire"));
                    train.setNumTrain(load.getString("NumTrain"));
                    train.setNbrPlace(load.getString("NbrPlace"));
                    train.setDesing(load.getString("Desing"));
                    trainListe.add(train);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return trainListe;
    }



}
