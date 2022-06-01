package Controler.Reservation;

import Repository.ReservationRp;
import Table.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.TableViewsRes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlerReservation implements Initializable {
    public TextField txtRech;
    public Button btnRch;
    TableViewsRes tableViewsRes;
    CrudRes crudRes;
    int index = -1;


    ObservableList<Reservation> listeRes;
    @FXML
    private TableView<Reservation> tableRes;
    @FXML
    private TableColumn<Reservation, String> numResCol;

    @FXML
    private TableColumn<Reservation, String> numTrainCol;

    @FXML
    private TableColumn<Reservation, String> numPlaceCol;

    @FXML
    private TableColumn<Reservation, String> dateResCol;

    @FXML
    private TableColumn<Reservation, String> nomVoyCol;
    @FXML
    private Button AddRes;

    public void redirection(String url){
        try {
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            parent = loader.load();
            this.crudRes = loader.getController();
            this.crudRes.controlerReservation = this;
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
    public ObservableList<Reservation> getTableViewsRecherc() {
        ObservableList<Reservation> ResListe = FXCollections.observableArrayList();
        try {
            ReservationRp reservationRp = new ReservationRp();
            Reservation reservation;
            String txtrech = txtRech.getText();
            ResultSet load = reservationRp.recherche(txtrech);
            while (load.next()){
                reservation = new Reservation();
                reservation.setId(load.getString("idReservation"));
                reservation.setNumTrain(load.getString("NumTrain"));
                reservation.setNumPlace(load.getString("NumPlace"));
                reservation.setDateReservation(load.getString("DateReservation"));
                reservation.setNomVoyageur(load.getString("NomVoyageur"));
                ResListe.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResListe;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewsRes = new TableViewsRes();

        numResCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id"));
        numTrainCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("NumTrain"));
        numPlaceCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("NumPlace"));
        dateResCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("DateReservation"));
        nomVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("NomVoyageur"));
        rafrechirTableRes();
    }
    void rafrechirTableRes(){
        listeRes = tableViewsRes.getTableViews();
       tableRes.setItems(listeRes);
    }
    void rafrechierApresKeyUp(){
        listeRes = getTableViewsRecherc();
        tableRes.setItems(listeRes);
    }
    @FXML
    public void AddRes(ActionEvent event) {
        redirection("/Views/Reservation/loginRes.fxml");
        crudRes.txtnumRes.setEditable(false);
        crudRes.mdfRes.setDisable(true);
        crudRes.supRes.setDisable(true);
    }

    public void afficherLogin(MouseEvent mouseEvent) {
            if(mouseEvent.getClickCount() == 2) {
                redirection("/Views/Reservation/loginRes.fxml");
                crudRes.addRes.setDisable(true);
                crudRes.txtnumRes.setEditable(false);
                index = tableRes.getSelectionModel().getSelectedIndex();
                Reservation reservation = listeRes.get(index);
                crudRes.setIdRes(reservation.getId());
                crudRes.txtnumRes.setText(reservation.getId());
                crudRes.txtNumTrain.getItems().addAll(reservation.getNumTrain());
                crudRes.txtNumTrain.getSelectionModel().select(reservation.getNumTrain());
                crudRes.txtNumPlace.getItems().addAll(reservation.getNumPlace());
                crudRes.txtNumPlace.getSelectionModel().select(reservation.getNumPlace());
                crudRes.txtNomVoyeur.setText(reservation.getNomVoyageur());
                crudRes.txtDateRes.getEditor().setText(reservation.getDateReservation());
            }
    }

    public void recherche(KeyEvent event) {
        rafrechierApresKeyUp();
    }

}
