package gui_pages;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.LoginService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView profileImageView;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button closeButton;


    //image Function
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File loginFile = new File("C:\\Users\\Raul\\Desktop\\Project HMS_2\\images\\login_image.jpg");
        Image loginImage = new Image(loginFile.toURI().toString());
        brandingImageView.setImage(loginImage);

        File profileFile = new File("C:\\Users\\Raul\\Desktop\\Project HMS_2\\images\\profile_image.png");
        Image profileImage = new Image(profileFile.toURI().toString());
        profileImageView.setImage(profileImage);
    }

    //login on action button
    public void loginButtonOnAction() throws IOException {
        Timeline blinker = createBlinker(loginMessageLabel);
        blinker.setOnFinished(event -> loginMessageLabel.setText("Please try again!"));
        FadeTransition fader = createFader(loginMessageLabel);

        SequentialTransition blinkThenFade = new SequentialTransition(
                loginMessageLabel,
                blinker,
                fader
        );
        LoginService u1 = new LoginService();
        Object obj1 = u1.login(userNameTextField.getText(),enterPasswordField.getText());
        if (obj1==null){
            loginMessageLabel.setText("Login failed!");
        blinkThenFade.play();}
        else{
//trace la pagina SERVER MANAGER
                Stage stage = (Stage) loginButton.getScene().getWindow();
            loginhanddleButtonOnAction(stage);
                stage.setOnCloseRequest(e -> Platform.exit());



        }
    }
    public void loginhanddleButtonOnAction(Stage secondary) throws IOException {
        Parent next = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ServerManager.fxml")));
        Scene scene = new Scene(next, 600, 700);
        secondary.setScene(scene);
        secondary.show();
    }

    //fade and blinker in LogIn
    private Timeline createBlinker(Node node) {
        Timeline blink = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(
                                node.opacityProperty(),
                                1,
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.seconds(0.5),
                        new KeyValue(
                                node.opacityProperty(),
                                0,
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(
                                node.opacityProperty(),
                                1,
                                Interpolator.DISCRETE
                        )
                )
        );
        blink.setCycleCount(3);
        return blink;
    }

    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(2), node);
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }

    //cancel Button -> back to mainStage
    public void handleSwitchStageCancelButton() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        cancelButtonOnAction(stage);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void cancelButtonOnAction(Stage secondary) throws IOException {
        Parent next = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainPage.fxml")));
        Scene scene = new Scene(next, 600, 400);
        secondary.setScene(scene);
        secondary.show();
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
