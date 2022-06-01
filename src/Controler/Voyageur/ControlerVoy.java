package Controler.Voyageur;

import Controler.Reservation.CrudRes;
import Controler.Train.AddTrainController;
import Controler.Train.ControlerTrain;
import Repository.ReservationRp;
import Repository.VoyageurRp;
import Table.Reservation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.TableVoyageur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ControlerVoy implements Initializable {


    AddTrainController addTrainController;
    ReservationRp reservationRp;
    TableVoyageur tableVoyageur;
    VoyageurRp voyageurRp;
    ObservableList<Reservation> listeVoy;

    public Label somme;

    @FXML
    public DatePicker dateDepart1;

    @FXML
    public DatePicker dateDepart2;


    @FXML
    public Pane pnDate;

    @FXML
    public ChoiceBox recette;

    @FXML
    private ImageView close;

    @FXML
    private TextField mtTotal;

    @FXML
    public DatePicker dateDepart;

    public DatePicker dateMois;

    @FXML
    public ChoiceBox<String> numTrain;

    @FXML
    private TableView<Reservation> tableListeVoy;

    @FXML
    private TableColumn<Reservation, String> nomVoyCol;

    @FXML
    private TableColumn<Reservation, String> numVoyCol;


    public void formaDate(){
        dateDepart.setOnAction(action->{
            LocalDate dates = dateDepart.getValue();
            String datenow =  dates.format(DateTimeFormatter.ofPattern("yyy/MM/dd"));
            dateDepart.getEditor().setText(datenow);
            numTrain.setDisable(false);
            afficherValeurColoneApresClickCombo();
        });

        dateMois.setOnAction(action->{
            LocalDate dates = dateMois.getValue();
            String datenow =  dates.format(DateTimeFormatter.ofPattern("yyy/MM/dd"));
            dateMois.getEditor().setText(datenow);
            numTrain.setDisable(false);
            afficherValeurColoneApresClickRecetteMois();
        });

        dateDepart1.setOnAction(action->{
            LocalDate dates = dateDepart1.getValue();
            String datenow =  dates.format(DateTimeFormatter.ofPattern("yyy/MM/dd"));
            dateDepart1.getEditor().setText(datenow);
            dateDepart2.setDisable(false);

        });
        dateDepart2.setOnAction(action->{
            LocalDate dates = dateDepart2.getValue();
            String datenow =  dates.format(DateTimeFormatter.ofPattern("yyy/MM/dd"));
            dateDepart2.getEditor().setText(datenow);
            numTrain.setDisable(false);
            afficherValeurColoneApresClickComboRecette();
        });
    }

    //recuperation du mois
    public static int recupererMois(DatePicker DateARecuperMois){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        int mois = 0;
        try {
            Date date = simpleDateFormat.parse(DateARecuperMois.getEditor().getText());
            SimpleDateFormat simpleDateFormatMois = new SimpleDateFormat("MM");
             mois = Integer.parseInt(simpleDateFormatMois.format(date)) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mois;
    }




    public void comboboxRecette(){
        String jours = "Jours";
        String semaine = "Semaine";
        String mois = "Mois";
        recette.getItems().addAll(jours,semaine,mois);
    }

    public void comboNumTrain() {
        reservationRp = new ReservationRp();
        numTrain.setDisable(true);
        ResultSet query = reservationRp.comboRp("train","NumTrain");
            try {
               while(query.next()){
                   numTrain.getItems().add(query.getString("NumTrain"));

               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void sommeFrais(){
        mtTotal.setEditable(false);
        voyageurRp = new VoyageurRp();
        ResultSet sql = voyageurRp.somme();
            try {
               while (sql.next()){
                   mtTotal.setText(sql.getString("somme")+".00");
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void afficherValeurColone(){
        tableVoyageur = new TableVoyageur();
        nomVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("NomVoyageur"));
        numVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id"));
            rafrechireTable();
    }

    public void afficherValeurColoneApresClickCombo(){
        tableVoyageur = new TableVoyageur();
        nomVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("NomVoyageur"));
        numVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id"));
        rafrechireTableApresClickCombo();
    }
    public void afficherValeurColoneApresClickRecetteMois(){
        tableVoyageur = new TableVoyageur();
        nomVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("NomVoyageur"));
        numVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id"));
        rafrechireTableApresClickRecetteMois();
    }


    public void afficherValeurColoneApresClickComboRecette(){
        tableVoyageur = new TableVoyageur();
        nomVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("NomVoyageur"));
        numVoyCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id"));
        refrechireTableAvecRecette();
    }

    public void rafrechireTable(){
        listeVoy = tableVoyageur.getTableViews();
        tableListeVoy.setItems(listeVoy);

    }

    public void rafrechireTableApresClickCombo(){
        listeVoy = tableVoyageur.getTableViewsVoy(numTrain,dateDepart,mtTotal);
        tableListeVoy.setItems(listeVoy);

    }

    public void rafrechireTableApresClickRecetteMois(){
        listeVoy = tableVoyageur.getTableViewsVoyMois(numTrain,dateMois,mtTotal);
        tableListeVoy.setItems(listeVoy);

    }

    public void refrechireTableAvecRecette(){
        listeVoy = tableVoyageur.getTableViewsVoyRecette(numTrain,dateDepart1,dateDepart2,mtTotal);
        tableListeVoy.setItems(listeVoy);
    }

    public void clickValeurChoisBoxNumTrain(){
        numTrain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String dateDep = dateDepart.getEditor().getText();

                if (!dateDep.equals("")){
                    afficherValeurColoneApresClickCombo();

                }else if(!dateMois.getEditor().getText().equals("") ){
                    afficherValeurColoneApresClickRecetteMois();
                }
                else{
                    afficherValeurColoneApresClickComboRecette();
                }
            }
        });
    }

    public void clickValeurChoisBoxReccete(){
        pnDate.getChildren().clear();
        recette.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                String nomReccette = observableValue.getValue();
                if (nomReccette.equals("Semaine")){
                  pnDate.getChildren().clear();
                  pnDate.getChildren().addAll(dateDepart1,dateDepart2);
                  numTrain.setDisable(true);
                  dateDepart1.setEditable(false);
                  dateDepart2.setEditable(false);
                  dateDepart1.setDisable(false);
                  dateDepart.getEditor().clear();
                  numTrain.getItems().clear();
                    comboNumTrain();
                  somme.setText("Recette par semaine en Ar");

              }else if (nomReccette.equals("Jours")){
                  afficherValeurColone();
                  pnDate.getChildren().clear();
                  pnDate.getChildren().addAll(dateDepart);
                  somme.setText("Recette par jours en Ar");
                  dateDepart1.getEditor().setText("");
                  dateDepart2.getEditor().setText("");
                  dateMois.getEditor().setText("");
                  numTrain.getItems().clear();
                  comboNumTrain();
              }else {
                    afficherValeurColone();
                    pnDate.getChildren().clear();
                    pnDate.getChildren().addAll(dateMois);
                    somme.setText("Recette par Mois en Ar");
                    dateDepart1.getEditor().setText("");
                    dateDepart2.getEditor().setText("");
                    numTrain.getItems().clear();
                    comboNumTrain();
                }

            }
        });
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date dates = new Date();
        formaDate();
        somme.setText("Recette Total en Ar");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String datenow = simpleDateFormat.format(dates);
        dateDepart.setEditable(false);
        dateDepart.getEditor().setText(datenow);
        numTrain.setDisable(true);
        dateDepart1.setDisable(true);
        dateDepart2.setDisable(true);
        dateDepart1.getEditor().setText(datenow);
        dateDepart2.getEditor().setText(datenow);
        comboNumTrain();
        comboboxRecette();
        afficherValeurColone();
        clickValeurChoisBoxReccete();
        clickValeurChoisBoxNumTrain();
        sommeFrais();

    }

    public void closeVoy(MouseEvent mouseEvent) {
        addTrainController = new AddTrainController();
        addTrainController.close(mouseEvent);
    }
}
