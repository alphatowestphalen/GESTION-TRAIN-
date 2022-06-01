package Repository;

import DB.ConnexionDB;
import Table.Place;

import java.sql.*;

public class PlaceRp {
    private Connection connection;

    public PlaceRp() {
        this.connection = (new ConnexionDB()).getConnexion();
    }

    public void insert(Place place)
    {
        PreparedStatement requet;
        String querry = "INSERT INTO `place` (`idplace`, `NumTrain`, `NumPlace`, `Occupation`) VALUES (NULL, ?, ?,?)";
        try {
            requet =connection.prepareStatement(querry);
            requet.setString(1,place.getTrain().getNumTrain());
            requet.setString(2,place.getNumPlace());
            requet.setString(3,place.getOccupation());
            requet.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Place place)
    {
        PreparedStatement requet;
        String querry = "UPDATE `place` SET `NumTrain` = ? ,`NumPlace` = ?, `Occupation` = ? WHERE `place`.`idplace` = ?";
        try {
            requet =connection.prepareStatement(querry);
            requet.setString(1,place.getTrain().getNumTrain());
            requet.setString(2,place.getNumPlace());
            requet.setString(3,place.getOccupation());
            requet.setString(4,place.getId());
            requet.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Place place)
    {
        PreparedStatement requet;
        String querry = "DELETE FROM `place` WHERE `place`.`NumPlace` = ?";
        try {
            requet =connection.prepareStatement(querry);
            requet.setString(1,place.getNumPlace());
            requet.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet loadTable() {
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM `place`");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet comboRp() {
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT `NumTrain` FROM `train`");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
