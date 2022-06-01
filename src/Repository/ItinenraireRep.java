package Repository;

import DB.ConnexionDB;
import Table.Itineraire;

import java.sql.*;

public class ItinenraireRep {
    private Connection connection;
    public ItinenraireRep() {
        this.connection = (new ConnexionDB()).getConnexion();
    }

    public void Insert(Itineraire itineraire) {
        PreparedStatement requete;
        String querry = "INSERT INTO `itineraire` (`idIti`, `NumItinaire`, `VilleDepart`, `VilleArriver`, `frais`)VALUES (NULL, ?, ?, ?,? )";
        try {
            requete =connection.prepareStatement(querry);
            requete.setString(1,itineraire.getNumItineraire());
            requete.setString(2,itineraire.getVilleDepart());
            requete.setString(3,itineraire.getVilleArrive());
            requete.setInt(4,itineraire.getFrais());
            requete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Update(Itineraire itineraire)
    {
        PreparedStatement requete;
        String querry = "UPDATE `itineraire` SET `NumItinaire` = ?, `VilleDepart` = ?, `VilleArriver` = ?, `frais` = ? WHERE `itineraire`.`idIti` = ?";
        try {
            requete =connection.prepareStatement(querry);
            requete.setString(1,itineraire.getNumItineraire());
            requete.setString(2,itineraire.getVilleDepart());
            requete.setString(3,itineraire.getVilleArrive());
            requete.setInt(4,itineraire.getFrais());

            requete.setString(5,itineraire.getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete(Itineraire itineraire)
    {
        PreparedStatement requete;
        String querry = "DELETE FROM itineraire WHERE idIti = ?";
        try {
            requete = connection.prepareStatement(querry);
            requete.setString(1,itineraire.getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet loadTable() {
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM `itineraire`");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ResultSet RecuperationNumItineraire(){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT NumItinaire AS NumItinaireMax FROM `itineraire` ORDER BY NumItinaire DESC LIMIT 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet Recherche(String txtRech){
        ResultSet res = null;
        try {
            Statement statement = connection.createStatement();
            res = statement.executeQuery("SELECT * FROM itineraire WHERE (NumItinaire LIKE '%"+txtRech+"%' OR VilleDepart LIKE '%"+txtRech+"%' OR VilleArriver LIKE '%"+txtRech+"%' OR frais LIKE '%"+txtRech+"%')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
