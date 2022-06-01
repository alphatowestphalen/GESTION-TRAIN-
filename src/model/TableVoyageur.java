package model;

import Controler.Voyageur.ControlerVoy;
import DB.ConnexionDB;
import Repository.ReservationRp;
import Repository.VoyageurRp;
import Table.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableVoyageur {
    private Connection connection;

    public TableVoyageur() {
        ConnexionDB connexionDB = new ConnexionDB();
        connection = connexionDB.getConnexion();
    }

    public ObservableList<Reservation> getTableViews() {
        ObservableList<Reservation> VoyListe = FXCollections.observableArrayList();
        try {
            ReservationRp reservationRp = new ReservationRp();
            Reservation reservation;
            ResultSet load = reservationRp.loadTable();
            while (load.next()){
                reservation = new Reservation();
                reservation.setId(load.getString("idReservation"));
                reservation.setNomVoyageur(load.getString("NomVoyageur"));
                VoyListe.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return VoyListe;
    }
    public ObservableList<Reservation> getTableViewsVoy(ChoiceBox<String> numTrain, DatePicker dateDepart, TextField mtTotal) {
        ObservableList<Reservation> VoyListe = FXCollections.observableArrayList();
        try {

            String numTrains = numTrain.getValue();
            String dateDep = dateDepart.getEditor().getText();

            VoyageurRp voyageurRp = new VoyageurRp();
            Reservation reservation;
            ResultSet load = null;
            ResultSet loadSommr = null;
            if (numTrains == null){
                load = voyageurRp.loadeTableVoyageur(null,dateDep);
                loadSommr = voyageurRp.somme(null,dateDep);
            }
            else{
                load = voyageurRp.loadeTableVoyageur(numTrains,dateDep);
                loadSommr = voyageurRp.somme(numTrains,dateDep);
            }

            while (load.next()){
                reservation = new Reservation();
                reservation.setId(load.getString("idReservation"));
                reservation.setNomVoyageur(load.getString("NomVoyageur"));
                VoyListe.add(reservation);
            }
            while (loadSommr.next()){
                reservation = new Reservation();
                reservation.setId(loadSommr.getString(1));
                if (reservation.getId() == null){
                    reservation.setId("00");
                }
                mtTotal.setText(reservation.getId()+".00");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return VoyListe;
    }
    public ObservableList<Reservation> getTableViewsVoyMois(ChoiceBox<String> numTrain, DatePicker dateMois, TextField mtTotal) {
        ObservableList<Reservation> VoyListe = FXCollections.observableArrayList();
        try {

            String numTrains = numTrain.getValue();
            int mois =ControlerVoy.recupererMois(dateMois);
            VoyageurRp voyageurRp = new VoyageurRp();
            Reservation reservation;
            ResultSet load = null;
            ResultSet loadSommr = null;
            if (numTrains == null){
                load = voyageurRp.loadeTableVoyageurMois(null,mois);
                loadSommr = voyageurRp.sommeMois(null,mois);
            }
            else{
                load = voyageurRp.loadeTableVoyageurMois(numTrains,mois);
                loadSommr = voyageurRp.sommeMois(numTrains,mois);
            }

            while (load.next()){
                reservation = new Reservation();
                reservation.setId(load.getString("idReservation"));
                reservation.setNomVoyageur(load.getString("NomVoyageur"));
                VoyListe.add(reservation);
            }
            while (loadSommr.next()){
                reservation = new Reservation();
                reservation.setId(loadSommr.getString(1));
                if (reservation.getId() == null){
                    reservation.setId("00");
                }
                mtTotal.setText(reservation.getId()+".00");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return VoyListe;
    }

    public ObservableList<Reservation> getTableViewsVoyRecette(ChoiceBox<String> numTrain, DatePicker dateDepart1, DatePicker dateDepart2, TextField mtTotal) {
        ObservableList<Reservation> VoyListe = FXCollections.observableArrayList();
        try {
            String numTrains = numTrain.getValue();
            String dateDep1 = dateDepart1.getEditor().getText();
            String dateDep2 = dateDepart2.getEditor().getText();
            VoyageurRp voyageurRp = new VoyageurRp();
            Reservation reservation;
            ResultSet load = null;
            ResultSet loadSommr = null;
            if(numTrains != null){
                load = voyageurRp.loadeTableVoyageurReccete(numTrains,dateDep1,dateDep2);
                loadSommr = voyageurRp.sommeRecette(numTrains,dateDep1,dateDep2);
            }else {
                loadSommr = voyageurRp.sommeRecette(null,dateDep1,dateDep2);
                load = voyageurRp.loadeTableVoyageurReccete(null,dateDep1,dateDep2);
            }
            while (load.next()){
                reservation = new Reservation();
                reservation.setId(load.getString("idReservation"));
                reservation.setNomVoyageur(load.getString("NomVoyageur"));
                VoyListe.add(reservation);
            }
            while (loadSommr.next()){
                reservation = new Reservation();
                reservation.setId(loadSommr.getString(1));
                if (reservation.getId() == null){
                    reservation.setId("00");
                }else {
                    mtTotal.setText(reservation.getId()+".00");
                }
                mtTotal.setText(reservation.getId()+".00");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return VoyListe;
    }
}
