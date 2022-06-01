package Controler.Reservation;

import Controler.Train.AddTrainController;
import Controler.Train.ControlerTrain;
import Repository.ReservationRp;
import Table.Place;
import Table.Reservation;
import Table.Train;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class CrudRes implements Initializable {
    ReservationRp reservationRp;
    Reservation reservation;
    Train train;
    Place place;
    AddTrainController addTrainController;
    ControlerTrain controlerTrain;

    public  ControlerReservation controlerReservation;

    @FXML
    public TextField txtnumRes;

    @FXML
    public TextField txtNomVoyeur;

    @FXML
    public ChoiceBox<String> txtNumTrain;

    @FXML
    public ChoiceBox<String> txtNumPlace;

    @FXML
    public DatePicker txtDateRes;

    @FXML
    public Button addRes;

    @FXML
    public Button mdfRes;

    @FXML
    public Button supRes;

    private String idRes;

    public String getIdRes() {
        return idRes;
    }

    public void setIdRes(String idRes) {
        this.idRes = idRes;
    }

    public void formaDate(){
        txtDateRes.setOnAction(action->{
            LocalDate dates = txtDateRes.getValue();
            String datenow =  dates.format(DateTimeFormatter.ofPattern("yyy/MM/dd"));
            txtDateRes.getEditor().setText(datenow);
        });
    }
    public void variable(){
        train = new Train();
        reservation = new Reservation();
        place = new Place();
        String numRes = txtnumRes.getText();
        train.setNumTrain(txtNumTrain.getSelectionModel().getSelectedItem());
        place.setNumPlace(txtNumPlace.getSelectionModel().getSelectedItem());
        txtNumPlace.getSelectionModel().select(1);
        String dataRes = txtDateRes.getEditor().getText();
        String nomVoy = txtNomVoyeur.getText();
        reservation.setId(numRes);
        reservation.setTrain(train);
                    txtNumTrain.getSelectionModel().select(1);
        reservation.setPlace(place);
        reservation.setDateReservation(dataRes);
        reservation.setNomVoyageur(nomVoy);
    }
    public boolean validationTrain(){
       String nomVoyageurs = txtNomVoyeur.getText();
        if (nomVoyageurs.isEmpty()){
            txtNomVoyeur.setStyle("-fx-border-color: red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Remplire tous le champ !!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public void addRes(ActionEvent event) {
        reservationRp = new ReservationRp();
        variable();
        if (validationTrain()){
            txtNomVoyeur.setStyle(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment Ajouter Reservation");
            Optional<ButtonType> resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK){
                reservationRp.insert(reservation);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
                controlerReservation.rafrechirTableRes();
            }
        }

    }

    public void mdfRes(ActionEvent event) {
        reservationRp = new ReservationRp();
        variable();
        if (validationTrain()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment Modifier Reservation ??");
            Optional<ButtonType>resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK){
                reservationRp.update(reservation);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
                controlerReservation.rafrechirTableRes();
            }
        }

    }

    public void supRes(ActionEvent event) {
        reservationRp = new ReservationRp();
        variable();
        if (validationTrain()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez vraiment supprimer Reservation ??");
            Optional<ButtonType>resulte = alert.showAndWait();
            if (resulte.isPresent() && resulte.get() == ButtonType.OK){
                reservationRp.delete(reservation);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
                controlerReservation.rafrechirTableRes();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date dates = new Date();
        formaDate();
        controlerTrain = new ControlerTrain();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String datenow =  simpleDateFormat.format(dates);
        txtDateRes.getEditor().setText(datenow);
        txtDateRes.setEditable(false);
        txtnumRes.setText(controlerTrain.affichageNum("R"));
        reservationRp = new ReservationRp();
       ResultSet sqlTrain = reservationRp.comboRp("train","NumTrain");
        try {
            int i =0;
            while (sqlTrain.next()){
                if (i==0){
                    String train1 = sqlTrain.getString("NumTrain");
                    ResultSet sqlPlace = reservationRp.comboPlace(train1);
                    while (sqlPlace.next()){
                        txtNumPlace.getItems().addAll(sqlPlace.getString("NumPlace"));
                    }
                }
                txtNumTrain.getItems().addAll(sqlTrain.getString("NumTrain"));
                i++;
            }
            txtNumPlace.getSelectionModel().select(0);
            txtNumTrain.getSelectionModel().select(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtNumTrain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try{
                    String train1 = observableValue.getValue();

                    ResultSet sqlPlace = reservationRp.comboPlace(train1);
                    txtNumPlace.getItems().clear();
                    while (sqlPlace.next()){
                        txtNumPlace.getItems().addAll(sqlPlace.getString("NumPlace"));
                    }

                    txtNumPlace.getSelectionModel().select(0);

                }catch (Exception e){
                    System.out.println("ok");
                }
            }
        });
        txtNumPlace.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try{
                    String place = observableValue.getValue();
                    System.out.println(place);

                }catch (Exception e){
                    System.out.println("ok");
                }
            }
        });
    }

    public void closed(MouseEvent mouseEvent) {
        addTrainController = new AddTrainController();
        addTrainController.close(mouseEvent);
    }
}
