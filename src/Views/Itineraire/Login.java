package Views.Itineraire;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loadre = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent parent = loadre.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

    }
}
