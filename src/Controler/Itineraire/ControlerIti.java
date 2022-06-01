package Controler.Itineraire;

import Controler.Train.ControlerTrain;
import Repository.ItinenraireRep;
import Repository.TrainRp;
import Table.Itineraire;
import Table.Train;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.TableViewsIti;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlerIti implements Initializable {


    TableViewsIti tableViewsIti;
    CrudIti crudIti;
    ControlerTrain controlerTrain;
    ItinenraireRep itinenraireRep;
    ObservableList<Itineraire> listeIti;
    int index = -1;

    @FXML
    public TextField txtRech;

    @FXML
    public Button btnRch;

    @FXML
    private TableView<Itineraire> tableIti;

    @FXML
    private TableColumn<Itineraire, String> NumItiCol;

    @FXML
    private TableColumn<Itineraire, String> VilleDepartCol;

    @FXML
    private TableColumn<Itineraire, String> VilleArrive;

    @FXML
    private TableColumn<Itineraire, String> Frais;

    @FXML
    private Button AddIti;


    public void redirection(String url){
        try {
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            parent = loader.load();
            this.crudIti = loader.getController();
            this.crudIti.controlerIti = this;
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
    @FXML
    void AddIti(ActionEvent event) {
        redirection("/Views/Itineraire/login.fxml");
        crudIti.txtNumIti.setEditable(false);
        crudIti.supIti.setDisable(true);
        crudIti.mdfIti.setDisable(true);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewsIti = new TableViewsIti();
        NumItiCol.setCellValueFactory(new PropertyValueFactory<Itineraire,String>("numItineraire"));
        VilleDepartCol.setCellValueFactory(new PropertyValueFactory<Itineraire,String>("VilleDepart"));
        VilleArrive.setCellValueFactory(new PropertyValueFactory<Itineraire,String>("VilleArrive"));
        Frais.setCellValueFactory(new PropertyValueFactory<Itineraire,String>("Frais"));
        RefrechireTableIti();
    }

    public ObservableList<Itineraire> recherche(){
        ObservableList<Itineraire> ListeItineraire = FXCollections.observableArrayList();
        try {
            ItinenraireRep itineraireRp = new ItinenraireRep();
            Itineraire itineraire;
            String txtRecherche = txtRech.getText();
            ResultSet load =  itineraireRp.Recherche(txtRecherche);
            while (load.next()){
                itineraire = new Itineraire();
                itineraire.setId(load.getString(1));
                itineraire.setNumItineraire(load.getString(2));
                itineraire.setVilleDepart(load.getString(3));
                itineraire.setVilleArrive(load.getString(4));
                itineraire.setFrais(load.getInt(5));
                ListeItineraire.add(itineraire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListeItineraire;
    }

    public void refrechireApresBtnreche(){
        listeIti = recherche();
        tableIti.setItems(listeIti);
    }
    public void RefrechireTableIti(){
        listeIti = tableViewsIti.getTableIti();
        tableIti.setItems(listeIti);
    }
    public void AjouterValeurAuLogin(MouseEvent mouseEvent){
        if(mouseEvent.getClickCount() == 2){
            redirection("/Views/Itineraire/login.fxml");
            index = tableIti.getSelectionModel().getSelectedIndex();
            Itineraire itineraire = listeIti.get(index);

            crudIti.setTxtIdIti(itineraire.getId());
            crudIti.txtNumIti.setText(itineraire.getNumItineraire());
            crudIti.txtVilleDepart.setText(itineraire.getVilleDepart());
            crudIti.txtVilleArrive.setText(itineraire.getVilleArrive());
            crudIti.txtFrais.setText(String.valueOf(itineraire.getFrais()));
            crudIti.AjouterIti.setDisable(true);
        }
    }


    public void recherches(KeyEvent keyEvent) {
        refrechireApresBtnreche();
    }
}
