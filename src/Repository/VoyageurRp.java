package Repository;

import DB.ConnexionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VoyageurRp {
    private Connection connection;

    public VoyageurRp() {
        if (this.connection == null){
            this.connection = (new ConnexionDB()).getConnexion();
        }
    }

    public ResultSet loadeTableVoyageur(String numtrain, String DateRes){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            if (numtrain == null){
                res = statement.executeQuery("SELECT idReservation,NomVoyageur FROM `reservation` WHERE DateReservation = '"+DateRes+"'");
            }else {
                res = statement.executeQuery("SELECT idReservation,NomVoyageur FROM `reservation` WHERE NumTrain = '"+numtrain+"' AND DateReservation = '"+DateRes+"'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet loadeTableVoyageurMois(String numtrain, int DateMois){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            if (numtrain == null){
                res = statement.executeQuery("SELECT idReservation,NomVoyageur FROM `reservation` WHERE  (MONTH(DateReservation) = "+DateMois+")");
            }else {
                res = statement.executeQuery("SELECT idReservation,NomVoyageur FROM `reservation` WHERE NumTrain = '"+numtrain+"' AND (MONTH(DateReservation) = "+DateMois+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet loadeTableVoyageurReccete(String numtrain, String DateRes1, String DateRes2){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            if (numtrain == null){
                res = statement.executeQuery("SELECT idReservation,NomVoyageur FROM `reservation` WHERE DateReservation BETWEEN '"+DateRes1+"' AND '"+DateRes2+"' ");
            }else {
                res = statement.executeQuery("SELECT idReservation,NomVoyageur FROM `reservation` WHERE NumTrain ='"+numtrain+"' AND ( DateReservation BETWEEN '"+DateRes1+"' AND '"+DateRes2+"')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet somme(){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT SUM(itineraire.frais) AS somme FROM `itineraire`\n" +
                                                "\t\tINNER JOIN train ON itineraire.NumItinaire = train.NumItineraire\n" +
                                                    "INNER JOIN reservation ON train.NumTrain = reservation.NumTrain");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet somme(String numTrain,String dateRes){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            if (numTrain == null){
                res = statement.executeQuery("SELECT SUM(itineraire.frais) FROM itineraire " +
                        "INNER JOIN train ON itineraire.NumItinaire = train.NumItineraire " +
                        "INNER JOIN reservation ON train.NumTrain = reservation.NumTrain " +
                        "WHERE reservation.DateReservation = '"+dateRes+"'");
            }else {
                res = statement.executeQuery("SELECT SUM(itineraire.frais) FROM itineraire " +
                        "INNER JOIN train ON itineraire.NumItinaire = train.NumItineraire " +
                        "INNER JOIN reservation ON train.NumTrain = reservation.NumTrain " +
                        "WHERE reservation.NumTrain = '"+numTrain+"' AND reservation.DateReservation = '"+dateRes+"'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet sommeMois(String numTrain,int dateRes){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            if (numTrain == null){
                res = statement.executeQuery("SELECT SUM(itineraire.frais) FROM itineraire " +
                        "INNER JOIN train ON itineraire.NumItinaire = train.NumItineraire " +
                        "INNER JOIN reservation ON train.NumTrain = reservation.NumTrain " +
                        "WHERE  (MONTH(reservation.DateReservation) = "+dateRes+")");
            }else {
                res = statement.executeQuery("SELECT SUM(itineraire.frais) FROM itineraire " +
                        "INNER JOIN train ON itineraire.NumItinaire = train.NumItineraire " +
                        "INNER JOIN reservation ON train.NumTrain = reservation.NumTrain " +
                        "WHERE reservation.NumTrain = '"+numTrain+"' AND  (MONTH(DateReservation) ="+dateRes+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet sommeRecette(String numTrain ,String dateRes1, String dateRes2){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            if (numTrain == null){
                res = statement.executeQuery("SELECT SUM(itineraire.frais) FROM itineraire " +
                        "INNER JOIN train ON itineraire.NumItinaire = train.NumItineraire " +
                        "INNER JOIN reservation ON train.NumTrain = reservation.NumTrain " +
                        "WHERE reservation.DateReservation BETWEEN '"+dateRes1+"' AND '"+dateRes2+"' ");
            }else {
                res = statement.executeQuery("SELECT SUM(itineraire.frais) FROM itineraire " +
                        "INNER JOIN train ON itineraire.NumItinaire = train.NumItineraire " +
                        "INNER JOIN reservation ON train.NumTrain = reservation.NumTrain " +
                        "WHERE reservation.NumTrain = '"+numTrain+"' AND " +
                        "(reservation.DateReservation BETWEEN '"+dateRes1+"' AND '"+dateRes2+"')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

}
