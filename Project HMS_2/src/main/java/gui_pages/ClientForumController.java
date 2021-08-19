package gui_pages;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import module.Clients;
import module.Rooms;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.w3c.dom.Text;
import service.ClientsService;
import service.RoomsService;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientForumController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private ImageView imageLogoReverse;
    @FXML
    private ImageView profileImage;
    @FXML
    private Button rezervaAcumButton;

    @FXML
    private TextField tfNume;

    @FXML
    private TextField tfPrenume;

    @FXML
    private TextField tfCnp;
    @FXML
    private TextField tfVarsta;

    @FXML
    private TextField tfGen;
    @FXML
    private Label avertismentLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File pfFile = new File("C:\\Users\\Raul\\Desktop\\Project HMS_2\\images\\profile_image.png");
        Image pfImage = new Image(pfFile.toURI().toString());
        profileImage.setImage(pfImage);

        File logoFile = new File("C:\\Users\\Raul\\Desktop\\Project HMS_2\\images\\logo_upsitedown.jpg");
        Image logoImage = new Image(logoFile.toURI().toString());
        imageLogoReverse.setImage(logoImage);
    }

    public void minimizeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeButtonOnAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void RezervaAcumButtonOnAction() throws IOException {

        FadeTransition fader = createFader(avertismentLabel);
        SequentialTransition fade = new SequentialTransition(
                avertismentLabel,
                fader
        );
        if (tfPrenume.getText().isEmpty() || tfNume.getText().isEmpty() || tfVarsta.getText().isEmpty() || tfCnp.getText().isEmpty() || tfGen.getText().isEmpty()){
            avertismentLabel.setText("TE ROG COMPLETEAZA TOATE CAMPURILE PENTRU A PUTEA REZERVA CAMERA!");
        fade.play();
    }else

    {
        ClientsService clientsService = new ClientsService();
        Clients clients = new Clients();
        clients.setFirstName(tfPrenume.getText());
        clients.setLastName(tfNume.getText());
        int i = Integer.parseInt(tfVarsta.getText());
        clients.setAge(i);
        i = Integer.parseInt(tfCnp.getText());
        clients.setCnp(i);
        clients.setGendere(tfGen.getText());
        clientsService.addClient(clients);

        execute(MainPageController.date, MainPageController.string, clients.getIdClients(), MainPageController.rooms);
        Stage stage = (Stage) rezervaAcumButton.getScene().getWindow();
        ButtonOnAction(stage);
        stage.setOnCloseRequest(e -> Platform.exit());

    }
//System.out.println(clients.getIdClients());
    }

    public void ButtonOnAction(Stage secondary) throws IOException {
        Parent next1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FinalPage.fxml")));
        Scene scene1 = new Scene(next1, 600, 400);
        secondary.setScene(scene1);
        secondary.show();
    }

    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(10), node);
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }

    public void execute(java.sql.Date date, String string, int Id, Rooms rooms) {
        RoomsService rs = new RoomsService();
        rooms.setIdClients(Id);
        rooms.setRoomStatus(string);
        rooms.setDate(date);
        rs.updateRoom(rooms);
    }

}
