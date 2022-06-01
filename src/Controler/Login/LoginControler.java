package Controler.Login;

import Controler.Train.AddTrainController;
import Controler.Train.ControlerTrain;
import Views.Home.ControlerHome;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginControler implements Initializable {
    AddTrainController addTrainController;
    ControlerHome controlerHome;
    @FXML
    private ImageView close;

    @FXML
    private Button login;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtmdp;

    public void redirection(String url){
        try {
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            parent = loader.load();
            this.controlerHome = loader.getController();
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
    void closed(MouseEvent event) {
        addTrainController = new AddTrainController();
        addTrainController.close(event);
    }

    @FXML
    void login(ActionEvent event) {
        String user = txtUser.getText().toString();
        String mdp = txtmdp.getText().toString();
        if (user.equals("root") &&  mdp.equals("root")){
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            redirection("/Views/Home/Home.fxml");
        }
        else {
            txtUser.setStyle("-fx-border-color: red;-fx-border-width: 0 0 2px; -fx-background-color: transparent;");
            txtmdp.setStyle("-fx-border-color: red;-fx-border-width: 0 0 2px; -fx-background-color: transparent;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Insert votre mots de passe et verifier Bien");
            Optional<ButtonType> resulte = alert.showAndWait();

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
