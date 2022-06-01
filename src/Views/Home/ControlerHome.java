package Views.Home;

import Controler.Login.LoginControler;
import Controler.Train.AddTrainController;
import Controler.Train.ControlerTrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerHome implements Initializable {
    AddTrainController addTrainController;

     public LoginControler loginControler;
    @FXML
    private Label labelString;

    @FXML
    private Button home;

    @FXML
    private Button train;

    @FXML
    private Button resevation;

    @FXML
    private Button itineraire;

    @FXML
    private Button voyageur;

    @FXML
    public Pane pnHome;

    @FXML
    private Parent parent;

    @FXML
    void clickHome() {
        train.setStyle("-fx-background-color: #A1BAC2");
        itineraire.setStyle("-fx-background-color: #A1BAC2");
        resevation.setStyle("-fx-background-color: #A1BAC2");
        home.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #1D3E56");
        pnHome.getChildren().clear();
    }
    public void redirection(String url){
        try {
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void disableHome(){
        pnHome.setDisable(true);
    }
    @FXML
    void clickItineraire() throws IOException {
        labelString.setText("Itineraire");
        home.setStyle("-fx-background-color: #A1BAC2");
        train.setStyle("-fx-background-color: #A1BAC2");
        resevation.setStyle("-fx-background-color: #A1BAC2");
        voyageur.setStyle("-fx-background-color: #A1BAC2");
        itineraire.setStyle("-fx-background-color: #FFFFFF;-fx-text-fill: #1D3E56");
        parent = FXMLLoader.load(getClass().getResource("../Itineraire/Itineraire.fxml"));
        pnHome.getChildren().clear();
        pnHome.getChildren().addAll(parent);
    }

    @FXML
    void clickTrain() throws IOException {
        labelString.setText("Train");
        home.setStyle("-fx-background-color: #A1BAC2");
        itineraire.setStyle("-fx-background-color: #A1BAC2");
        resevation.setStyle("-fx-background-color: #A1BAC2");
        voyageur.setStyle("-fx-background-color: #A1BAC2");
        train.setStyle("-fx-background-color: #FFFFFF;-fx-text-fill: #1D3E56");
        parent = FXMLLoader.load(getClass().getResource("../Train/VTrain.fxml"));
        pnHome.getChildren().clear();
        pnHome.getChildren().addAll(parent);
    }

    @FXML
    void clickRes() throws IOException {
        labelString.setText("Reservation");
        home.setStyle("-fx-background-color: #A1BAC2");
        train.setStyle("-fx-background-color: #A1BAC2");
        itineraire.setStyle("-fx-background-color: #A1BAC2");
        voyageur.setStyle("-fx-background-color: #A1BAC2");
        resevation.setStyle("-fx-background-color: #FFFFFF;-fx-text-fill: #1D3E56");
        parent = FXMLLoader.load(getClass().getResource("../Reservation/Reservation.fxml"));
        pnHome.getChildren().clear();
        pnHome.getChildren().addAll(parent);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void closed(MouseEvent mouseEvent) {
       addTrainController = new AddTrainController();
       addTrainController.close(mouseEvent);
    }

    public void clickVoyageur(ActionEvent event) {
        redirection("../Voyageur/voyageur.fxml");
        labelString.setText("Reservation");
        home.setStyle("-fx-background-color: #A1BAC2");
        train.setStyle("-fx-background-color: #A1BAC2");
        itineraire.setStyle("-fx-background-color: #A1BAC2");
        resevation.setStyle("-fx-background-color: #A1BAC2");
        voyageur.setStyle("-fx-background-color: #FFFFFF;-fx-text-fill: #1D3E56");
    }
}
