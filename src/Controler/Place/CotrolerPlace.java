package Controler.Place;

import Table.Place;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.TableViewPlace;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CotrolerPlace implements Initializable {
    TableViewPlace tableViewPlace;
    CrudPlace crudPlace;
    int index = -1;

    ObservableList<Place> listeTablePlace;
    @FXML
    private TableView<Place> TableViewPlace;

    @FXML
    private TableColumn<Place, String> numTrainCol;

    @FXML
    private TableColumn<Place, String> numPlaceCol;

    @FXML
    private TableColumn<Place, String> occupationCol;
    @FXML
    private Button AddPlace;
    public void redirection(String url){
        try {
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            parent = loader.load();
            this.crudPlace = loader.getController();
            this.crudPlace.cotrolerPlace = this;
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void AddPlace(ActionEvent event) {
        redirection("/Views/Place/login.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewPlace = new TableViewPlace();
        numTrainCol.setCellValueFactory(new PropertyValueFactory<Place,String>("NumTrain"));
        numPlaceCol.setCellValueFactory(new PropertyValueFactory<Place,String>("NumPlace"));
        occupationCol.setCellValueFactory(new PropertyValueFactory<Place,String>("Occupation"));
        refrechirTablePlace();
    }
    void refrechirTablePlace(){
        listeTablePlace = tableViewPlace.getTableViews();
        TableViewPlace.setItems(listeTablePlace);

    }
    public void recupererValeurAjoutAuImput(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2){
            redirection("/Views/Place/login.fxml");
            index = TableViewPlace.getSelectionModel().getSelectedIndex();
            Place place = listeTablePlace.get(index);
            crudPlace.setTxtid(place.getId());
            crudPlace.txtNumTrains.getItems().addAll(place.getNumTrain());
            crudPlace.txtNumPlace.setText(place.getNumPlace());
            crudPlace.txtOccupation.setText(place.getOccupation());
            crudPlace.AjooutePlace.setDisable(true);
        }

    }
}
