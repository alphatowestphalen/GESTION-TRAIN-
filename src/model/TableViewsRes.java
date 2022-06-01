package model;

import Controler.Reservation.ControlerReservation;
import DB.ConnexionDB;
import Repository.ReservationRp;
import Repository.TrainRp;
import Table.Reservation;
import Table.Train;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableViewsRes {
    public ControlerReservation controlerReservation;
    private Connection connection;
    public TableViewsRes() {
        ConnexionDB connexionDB = new ConnexionDB();
        connection = connexionDB.getConnexion();
    }
    public ObservableList<Reservation> getTableViews() {
        ObservableList<Reservation> ResListe = FXCollections.observableArrayList();
        try {
            ReservationRp reservationRp = new ReservationRp();
            Reservation reservation;
            ResultSet load = reservationRp.loadTable();
            while (load.next()){
                reservation = new Reservation();
                reservation.setId(load.getString("idReservation"));
                reservation.setNumTrain(load.getString("NumTrain"));
                reservation.setNumPlace(load.getString("NumPlace"));
                reservation.setDateReservation(load.getString("DateReservation"));
                reservation.setNomVoyageur(load.getString("NomVoyageur"));
                ResListe.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResListe;
    }
}
