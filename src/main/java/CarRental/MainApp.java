package CarRental;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/View/fxml/main.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        Parent rootNode = FXMLLoader.load(getClass().getResource(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 200, 141);
        //scene.getStylesheets().add("/CarRental.View.styles/CarRental.View.styles.css");

        stage.setTitle("CarRental");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
