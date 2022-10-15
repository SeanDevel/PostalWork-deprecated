package indi.wangsc.hotline;
import indi.wangsc.paperwork.word.Word;
import indi.wangsc.paperwork.word.WordFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("Application");
        stage.setScene(scene);
        stage.show();
    }
 

    public static void main(String[] args) {
        launch(args);
    }
}
