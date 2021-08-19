package gui_pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerManager  implements Initializable{

    @FXML
    private ImageView brandingImageView;
    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File loginFile = new File("C:\\Users\\Raul\\Desktop\\Project HMS_2\\images\\banner.jpg");
        Image loginImage = new Image(loginFile.toURI().toString());
        brandingImageView.setImage(loginImage);
    }

    //minimize Button
    public void minimizeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    //close Button
    public void closeButtonOnAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
