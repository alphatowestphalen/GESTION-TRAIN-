package Controler.Train;

import Repository.TrainRp;
import Table.Itineraire;
import Table.Train;
import Views.Home.ControlerHome;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.TableViewTrain;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddTrainController implements Initializable {
    Train train;
    TrainRp trainRp;
    Itineraire itineraire;
    TableViewTrain tableViewTrain;


    @FXML
    public ChoiceBox<String> Numiti;

    public ControlerTrain controlerTrain;


    private String txtId;

    @FXML
    public TextField txtnumTain;

    @FXML
    public TextField txtnombrePlace;

    @FXML
    public TextField txtdesignation;

    public String getTxtId() {
        return txtId;
    }

    public void setTxtId(String txtId) {
        this.txtId = txtId;
    }

    @FXML
    public Button addTain;
    @FXML
    public Button mdfTrain;
    @FXML
    public Button supTrain;

    public void ajouterValeur() {
        train = new Train();
        trainRp = new TrainRp();
        itineraire = new Itineraire();

        String id = txtId;
        String numTain = txtnumTain.getText();
        String designation = txtdesignation.getText();
        String nombrePlace = txtnombrePlace.getText();

        train.setId(id);
        itineraire.setNumItineraire(Numiti.getSelectionModel().getSelectedItem());
        train.setNumTrain(numTain);
        train.setDesing(designation);
        train.setNbrPlace(nombrePlace);
        train.setIttineraire(itineraire);
    }

   public boolean validationTrain(){
        String nbrPlace = txtnombrePlace.getText();
        String disingation = txtdesignation.getText();
        String numIti = Numiti.getValue();
        if (nbrPlace.isEmpty() | disingation.isEmpty() | numIti.isEmpty()){
            if (nbrPlace.isEmpty()){
                txtnombrePlace.setStyle("-fx-border-color: red;-fx-border-width: 0 0 2px 0;-fx-background-color: transparent;");
            }
            else {
                txtnombrePlace.setStyle(null);
            }
            if (disingation.isEmpty()){
                txtdesignation.setStyle("-fx-border-color: red;-fx-border-width: 0 0 2px 0;-fx-background-color: transparent;");
            }else {
                txtdesignation.setStyle(null);
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Remplire tous le champ !!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    @FXML
    void addTain(ActionEvent event) {
        tableViewTrain = new TableViewTrain();
        trainRp = new TrainRp();
        ajouterValeur();
        if (validationTrain()){
            txtdesignation.setStyle(null);
            txtnombrePlace.setStyle(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment Ajouter Train");
            Optional<ButtonType> resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK){
                trainRp.insert(train);
            }
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            controlerTrain.refrechireTable();
        }
    }


    public void mdfTrain(ActionEvent actionEvent) {
        trainRp = new TrainRp();
        ajouterValeur();
        if (validationTrain()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment Modifier le Train ??");
            Optional<ButtonType>resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK){
                trainRp.Uptade(train);
            }
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
            controlerTrain.refrechireTable();
        }

    }

    public void supTrain(ActionEvent actionEvent) {
        trainRp = new TrainRp();
        ajouterValeur();
        if (validationTrain()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment supprimer le Train ??");
            Optional<ButtonType>resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK){
                trainRp.delete(train);
            }
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
            controlerTrain.refrechireTable();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trainRp = new TrainRp();
        ResultSet sql = trainRp.comboRp();
        try {
            while (sql.next()){
                Numiti.getItems().addAll(sql.getString(1));
                Numiti.getSelectionModel().select(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clickNumTrain(ActionEvent event) {
        txtnumTain.setStyle("-fx-border-color: #EC7C5C;-fx-border-width: 0 0 2px; -fx-background-color: transparent;");
    }

    public void close(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez vraiment quiter ??");
        Optional<ButtonType>resulte = alert.showAndWait();
        if (resulte.isPresent() && resulte.get() == ButtonType.OK){
            Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    public void clickDes(ActionEvent event) {
    }
}
