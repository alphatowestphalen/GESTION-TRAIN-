package Controler.Train;

import DB.ConnexionDB;
import Repository.ItinenraireRep;
import Repository.ReservationRp;
import Repository.TrainRp;
import Table.Train;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.TableViewTrain;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlerTrain implements Initializable {

    public TextField txtRech;
    ObservableList<Train> listTrain;
    TableViewTrain tableViewTrain;
    AddTrainController addTrainController;

    TrainRp trainRp;
    ItinenraireRep itinenraireRep;
    ReservationRp reservationRp;
    ResultSet res= null;
    PreparedStatement requet;
    int index = -1;


    private Connection connection;

    public ControlerTrain() {
        this.connection = (new ConnexionDB()).getConnexion();
    }

    @FXML
    private TableView<Train> tableTrain;

    @FXML
    private TableColumn<Train, String> NumTrain_col;

    @FXML
    private TableColumn<Train, String> DesingationTrain_col;

    @FXML
    private TableColumn<Train, String> NombraPlace_col;
    @FXML
    private TableColumn<Train, String> NumraoItineraire_col;

    @FXML
    public Pane pnTrain;

    public void redirection(String url){
        try {
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            parent = loader.load();
            this.addTrainController = loader.getController();
            this.addTrainController.controlerTrain = this;
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

    public void AddTrain(ActionEvent event) {
        redirection("/Views/Train/AddTrain.fxml");
        addTrainController.txtnumTain.setText(affichageNum("T"));
        addTrainController.txtnumTain.setEditable(false);
        addTrainController.mdfTrain.setDisable(true);
        addTrainController.supTrain.setDisable(true);
    }
    
    public String affichageNum(String premierLettre ){
        trainRp = new TrainRp();
        reservationRp = new ReservationRp();
        itinenraireRep = new ItinenraireRep();
        ResultSet query =null;
        String ValeurInit = null;
        String colone = null;
        if (premierLettre == "T"){
            ValeurInit = "T001";
            colone = "NumTrainMax";
            query = trainRp.RecuperationNumTrain();
        }else if (premierLettre == "R"){
            ValeurInit = "R001";
            colone = "idResMax";
            query = reservationRp.RecuperationNumReservation();
        }else {
            ValeurInit = "I001";
            colone = "NumItinaireMax";
            query = itinenraireRep.RecuperationNumItineraire();
        }
        String numTrain = null;
        int NumTrainMax = 0;
        try {
            if (query.next()){
                numTrain = query.getString(colone);
                NumTrainMax = Integer.parseInt(numTrain.substring(1))+1;
                String stringNumTrainMax = String.valueOf(NumTrainMax);
                int i=3;
                int longeurNumTrainMax = stringNumTrainMax.length();
                while (i>longeurNumTrainMax){
                    stringNumTrainMax = "0"+stringNumTrainMax;
                    i--;
                }
                premierLettre += stringNumTrainMax;
                ValeurInit = premierLettre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ValeurInit;
    }
    public ObservableList<Train> affichageApresRech() {
        ObservableList<Train> trainListe = FXCollections.observableArrayList();
        try {
            TrainRp trainRp = new TrainRp();
            Train train;
            String txtRechs = txtRech.getText();
            System.out.println(txtRechs);
            ResultSet load = trainRp.Recherche(txtRechs);
            while (load.next()){
                train = new Train();
                train.setId(load.getString("id"));
                train.setNumItineraire(load.getString("NumItineraire"));
                train.setNumTrain(load.getString("NumTrain"));
                train.setNbrPlace(load.getString("NbrPlace"));
                train.setDesing(load.getString("Desing"));
                trainListe.add(train);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainListe;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewTrain = new TableViewTrain();
        NumTrain_col.setCellValueFactory(new PropertyValueFactory<Train,String>("NumTrain"));
        NombraPlace_col.setCellValueFactory(new PropertyValueFactory<Train,String>("NbrPlace"));
        DesingationTrain_col.setCellValueFactory(new PropertyValueFactory<Train,String>("Desing"));
        NumraoItineraire_col.setCellValueFactory(new PropertyValueFactory<Train,String>("NumItineraire"));
        refrechireTable();
    }

    public void refrechireTable(){
        listTrain = tableViewTrain.getTableViews();
        tableTrain.setItems(listTrain);
    }
    public void refrechireApresKeyUp(){
        listTrain = affichageApresRech();
        tableTrain.setItems(listTrain);
    }

    @FXML
    public void getSelect(MouseEvent event) {
        if (event.getClickCount() == 2 ){
            redirection("/Views/Train/AddTrain.fxml");
            addTrainController.addTain.setDisable(true);
            index = tableTrain.getSelectionModel().getSelectedIndex();
            Train train =  listTrain.get(index);
            addTrainController.setTxtId(train.getId());
            addTrainController.txtnumTain.setText(train.getNumTrain());
            addTrainController.txtnombrePlace.setText(train.getNbrPlace());
            addTrainController.txtdesignation.setText(train.getDesing());
            addTrainController.Numiti.getItems().addAll(train.getNumItineraire());
            addTrainController.Numiti.getSelectionModel().select(train.getNumItineraire());
        }
    }


    public void recherche(KeyEvent keyEvent) {
        refrechireApresKeyUp();
    }

    public void champRecherche(ActionEvent event) {
    }

    public void teste(MouseEvent dragEvent) {
        System.out.println("ok");
        txtRech.setStyle("-fx-border-width: 12px");
    }
}
