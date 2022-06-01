package model;

import DB.ConnexionDB;
import Repository.ItinenraireRep;
import Table.Itineraire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableViewsIti {
    private Connection connection;

    public TableViewsIti() {
        ConnexionDB connexionDB = new ConnexionDB();
        connection = connexionDB.getConnexion();
    }
    public ObservableList<Itineraire> getTableIti(){
        ObservableList<Itineraire> ListeItineraire = FXCollections.observableArrayList();
            try {
                ItinenraireRep itineraireRp = new ItinenraireRep();
                Itineraire itineraire;
                ResultSet load =  itineraireRp.loadTable();
                while (load.next()){
                    itineraire = new Itineraire();
                    itineraire.setId(load.getString(1));
                    itineraire.setNumItineraire(load.getString(2));
                    itineraire.setVilleDepart(load.getString(3));
                    itineraire.setVilleArrive(load.getString(4));
                    itineraire.setFrais(load.getInt(5));
                    ListeItineraire.add(itineraire);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ListeItineraire;
    }


}
