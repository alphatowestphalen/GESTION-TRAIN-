package Repository;

import DB.ConnexionDB;
import Table.Reservation;

import java.sql.*;

public class ReservationRp {
    private Connection connection;

    public ReservationRp() {
        this.connection = (new ConnexionDB()).getConnexion();
    }

    public void insert(Reservation reservation)
    {
        PreparedStatement requet;
        String query = "INSERT INTO `reservation` (idReservation,`NumTrain`, `NumPlace`, `DateReservation`, `NomVoyageur`)" +
                                "VALUES (?, ?, ?, ?, ?)";
        try {
            requet = connection.prepareStatement(query);
            requet.setString(1,reservation.getId());
            requet.setString(2,reservation.getTrain().getNumTrain());
            requet.setString(3,reservation.getPlace().getNumPlace());
            requet.setString(4,reservation.getDateReservation());
            requet.setString(5,reservation.getNomVoyageur());
            requet.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(Reservation reservation)
    {
        PreparedStatement requet;
        String query = "UPDATE `reservation` SET `NumTrain` = ?, `NumPlace` = ?, `DateReservation` = ?, `NomVoyageur` = ? " +
                                            "WHERE `reservation`.`idReservation` = ?";
        try {
            requet = connection.prepareStatement(query);
            requet.setString(1,reservation.getTrain().getNumTrain());
            requet.setString(2,reservation.getPlace().getNumPlace());
            requet.setString(3,reservation.getDateReservation());
            requet.setString(4,reservation.getNomVoyageur());
            requet.setString(5,reservation.getId());
            requet.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Reservation reservation)
    {
        PreparedStatement requet;
        String query = "DELETE FROM `reservation` WHERE idReservation = ?";
        try {
            requet = connection.prepareStatement(query);
            requet.setString(1,reservation.getId());
            requet.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet loadTable() {
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM `reservation`");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    public ResultSet comboRp(String table,String colone) {
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT DISTINCT "+colone+" FROM "+table;
            res = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet comboPlace(String numTrain) {
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM place WHERE NumPlace NOT IN(SELECT NumPlace FROM reservation WHERE NumTrain = '"+numTrain+"') AND NumTrain = '"+numTrain+"'";
            res = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet RecuperationNumReservation(){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT idReservation AS idResMax FROM `reservation` ORDER BY idReservation DESC LIMIT 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet recherche(String txtRech){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM `reservation` WHERE " +
                    "(idReservation LIKE '%"+txtRech+"%' " +
                    "OR NumTrain LIKE '%"+txtRech+"%' " +
                    "OR NumPlace LIKE '%"+txtRech+"%' " +
                    "OR DateReservation LIKE '%"+txtRech+"%' " +
                    "OR NomVoyageur LIKE '%"+txtRech+"%')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }



}
