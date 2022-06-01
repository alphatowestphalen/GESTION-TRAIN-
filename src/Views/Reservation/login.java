package Views.Reservation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class login extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginRes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        String css = this.getClass().getResource("../../Public/CSS/App.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("App");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
