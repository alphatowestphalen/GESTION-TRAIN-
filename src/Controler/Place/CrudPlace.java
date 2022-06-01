package Controler.Place;

import Repository.PlaceRp;
import Table.Place;
import Table.Train;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CrudPlace implements Initializable {
    Train train;
    Place place;
    PlaceRp placeRp;
    public CotrolerPlace cotrolerPlace;


    @FXML
    public ChoiceBox<String> txtNumTrains;

    @FXML
    public TextField txtnumTrain;

    @FXML
    public TextField txtNumPlace;

    @FXML
    public TextField txtOccupation;

    @FXML
    public Button AjooutePlace;

    @FXML
    public Button mdfPlace;

    @FXML
    public Button supPlace;

    private String txtid;

    public String getTxtid() {
        return txtid;
    }

    public void setTxtid(String txtid) {
        this.txtid = txtid;
    }

    public void ajouterValeur() {
            train = new Train();
            place = new Place();
            placeRp = new PlaceRp();
            String id = txtid;
            train.setNumTrain(txtNumTrains.getSelectionModel().getSelectedItem());
            String numPlace = txtNumPlace.getText();
            String Occupation = txtOccupation.getText();

            place.setId(txtid);
            place.setTrain(train);
            place.setNumPlace(numPlace);
            place.setOccupation(Occupation);

        }
    public void AddPlace(ActionEvent event) {
        placeRp = new PlaceRp();
        ajouterValeur();
        placeRp.insert(place);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        cotrolerPlace.refrechirTablePlace();
    }

    public void mdfPlace(ActionEvent event) {
        placeRp = new PlaceRp();
        ajouterValeur();
        placeRp.update(place);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        cotrolerPlace.refrechirTablePlace();
    }

    public void SupPlace(ActionEvent event) {
        placeRp = new PlaceRp();
        ajouterValeur();
        placeRp.delete(place);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        cotrolerPlace.refrechirTablePlace();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeRp = new PlaceRp();
        ResultSet sql = placeRp.comboRp();
            try {
                while (sql.next()) {
                    txtNumTrains.getItems().addAll(sql.getString("NumTrain"));
                    txtNumTrains.getSelectionModel().select(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
