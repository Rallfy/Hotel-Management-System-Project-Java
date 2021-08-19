package gui_pages;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class FinalPage {

    @FXML
    private Button backHomeButton;

    public void backHomeButtonOnAction() throws IOException {
        Stage stage = (Stage) backHomeButton.getScene().getWindow();
        ButtonOnAction(stage);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void ButtonOnAction(Stage secondary) throws IOException {
        Parent next = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainPage.fxml")));
        Scene scene = new Scene(next, 600, 400);
        secondary.setScene(scene);
        secondary.show();
    }

}
