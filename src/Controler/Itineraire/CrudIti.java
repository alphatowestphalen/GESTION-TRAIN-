package Controler.Itineraire;

import Controler.Train.AddTrainController;
import Controler.Train.ControlerTrain;
import Repository.ItinenraireRep;
import Table.Itineraire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CrudIti implements Initializable {
    Itineraire itineraire;
    ItinenraireRep itinenraireRep;
    ControlerTrain controlerTrain;
    AddTrainController addTrainController;
    public ControlerIti controlerIti;
    @FXML
    public TextField txtNumIti;

    @FXML
    public TextField txtVilleDepart;

    @FXML
    public TextField txtVilleArrive;

    @FXML
    public Button AjouterIti;

    @FXML
    public Button mdfIti;

    @FXML
    public Button supIti;

    @FXML
    public TextField txtFrais;

    private String txtIdIti;

    public String getTxtIdIti() {
        return txtIdIti;
    }

    public void setTxtIdIti(String txtIdIti) {
        this.txtIdIti = txtIdIti;
    }

    public boolean validation(){
        String villeDepart = txtVilleDepart.getText();
        String villeArriver = txtVilleArrive.getText();

        if (villeArriver.isEmpty() | villeDepart.isEmpty()){
            if (villeArriver.isEmpty()){
                txtVilleArrive.setStyle("-fx-border-color: red;-fx-border-width: 0 0 2px 0;-fx-background-color: #EC7C5C;");
            }else {
                txtVilleArrive.setStyle(null);
            }
            if (villeDepart.isEmpty()){
                txtVilleDepart.setStyle("-fx-border-color: red;-fx-border-width: 0 0 2px 0;-fx-background-color: #EC7C5C;");
            }else {
                txtVilleDepart.setStyle(null);
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

    public void variable(){
        itineraire = new Itineraire();
        String numIti = txtNumIti.getText();
        String id = txtIdIti;
        String villeDepart = txtVilleDepart.getText();
        String villeArrive = txtVilleArrive.getText();
        String frais =  txtFrais.getText();
        itineraire.setId(id);
        itineraire.setNumItineraire(numIti);
        itineraire.setVilleDepart(villeDepart);
        itineraire.setVilleArrive(villeArrive);
        itineraire.setFrais(Integer.parseInt(frais));
    }


    @FXML
    void mdfIti(ActionEvent event) {
        itinenraireRep = new ItinenraireRep();
        if(validation()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment modifier Itineraire ??");
            Optional<ButtonType> resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK){
                variable();
                itinenraireRep.Update(itineraire);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
                controlerIti.RefrechireTableIti();
            }
        }

    }

    @FXML
    void supIti(ActionEvent event) {
        itinenraireRep = new ItinenraireRep();
        if (validation()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment supprimer Itineraire ??");
            Optional<ButtonType>resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK){
                variable();
                itinenraireRep.Delete(itineraire);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
                controlerIti.RefrechireTableIti();
            }
        }

    }

    public void addIti(ActionEvent event) {
        itinenraireRep = new ItinenraireRep();
        variable();
        if (validation()){
            txtVilleDepart.setStyle(null);
            txtVilleArrive.setStyle(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment Ajouter Itineraire");
            Optional<ButtonType> resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK) {
                itinenraireRep.Insert(itineraire);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                controlerIti.RefrechireTableIti();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtNumIti.setEditable(true);
        txtFrais.setText("5000");
        controlerTrain = new ControlerTrain();
        txtNumIti.setText(controlerTrain.affichageNum("I"));

    }

    public void closed(MouseEvent mouseEvent) {
        addTrainController = new AddTrainController();
        addTrainController.close(mouseEvent);
    }
}
