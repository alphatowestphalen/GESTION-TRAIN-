package Repository;

import DB.ConnexionDB;
import Table.Train;

import java.sql.*;

public class TrainRp {
    private static Connection connection;
    public TrainRp() {
        if (this.connection == null){
            this.connection = (new ConnexionDB()).getConnexion();
        }
    }

    public void insert(Train train){
        PreparedStatement requet;
        PreparedStatement requet2;
        String query = "INSERT INTO `train` (`id`, `NumTrain`, `NbrPlace`, `Desing`, `NumItineraire`) VALUES (NULL,?,?, ?,?)";
        String query2 = "";
        int nbrPlace = Integer.parseInt(train.getNbrPlace());
        String numTrain = train.getNumTrain();
        try {
            requet = connection.prepareStatement(query);
            requet.setString(1,train.getNumTrain());
            requet.setString(2,train.getNbrPlace());
            requet.setString(3,train.getDesing());
            requet.setString(4,train.getItineraire().getNumItineraire());
            requet.execute();
            for (int i=1; i<= nbrPlace; i++){
                query2="INSERT INTO place (NumTrain,NumPlace,Occupation) VALUES ('"+numTrain+"',"+i+",0)";
                requet2 = connection.prepareStatement(query2);
                requet2.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Uptade(Train train){
        PreparedStatement requet;
        String query1 = "DELETE FROM place WHERE NumTrain =?";
        String query = "UPDATE `train` SET `NumTrain` = ?, `NbrPlace` = ?, `Desing` = ?, `NumItineraire` = ? WHERE `id` = ?";
        try {
            requet = connection.prepareStatement(query);
            requet.setString(1,train.getNumTrain());
            requet.setString(2,train.getNbrPlace());
            requet.setString(3,train.getDesing());
            requet.setString(4,train.getItineraire().getNumItineraire());
            requet.setString(5,train.getId());
            requet.execute();
            requet = connection.prepareStatement(query1);
            requet.setString(1,train.getNumTrain());
            requet.execute();
            int nbrPlace = Integer.parseInt(train.getNbrPlace());
            for (int i=1; i<= nbrPlace; i++){
                query1="INSERT INTO place (NumTrain,NumPlace,Occupation) VALUES ('"+train.getNumTrain()+"',"+i+",0)";
                requet = connection.prepareStatement(query1);
                requet.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Train train){
        PreparedStatement requet;
        PreparedStatement requet2;
        String numtrain = train.getNumTrain();

        String query = "DELETE FROM `train` WHERE `id` = ?";
        String query2 = "DELETE FROM place WHERE NumTrain ='"+numtrain+"'";
        try {
            requet = connection.prepareStatement(query);
            requet2 = connection.prepareStatement(query2);
            requet.setString(1,train.getId());
            requet.execute();
            requet2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet loadTable() {
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM `train` WHERE 1 LIMIT 10");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet comboRp() {
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT DISTINCT `NumItinaire` FROM `itineraire`");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet RecuperationNumTrain(){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT NumTrain AS NumTrainMax FROM `train` ORDER BY NumTrain DESC LIMIT 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet Recherche(String txtRech){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM `train` WHERE (NumTrain LIKE '%"+txtRech+"%' OR Desing LIKE '%"+txtRech+"%' OR NbrPlace LIKE '%"+txtRech+"%'  OR NumItineraire LIKE '%"+txtRech+"%')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
