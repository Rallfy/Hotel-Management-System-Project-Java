package gui_pages;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import module.Rooms;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import service.RoomsService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class MainPageController implements Initializable {

    @FXML
    private Button closeButton;

    @FXML
    private Button saButton;

    @FXML
    private ImageView bannerImageView;

    @FXML
    private Button rezervaCameraButton;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label rezervaMessageLabel;

    public static java.sql.Date date;
    public static String string;
    public static Rooms rooms;


    public static void setDate(java.sql.Date dates) {
        date = dates;
    }
    public static void setStringz(String strings) {
        string = strings;
    }
    public static void setRooms(Rooms roomz) {
        rooms = roomz;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query1 = session.createQuery("FROM Rooms WHERE roomStatus = :liber ");
        query1.setParameter("liber", "LIBER");
        List rooms = query1.list();

        //ChoiceBox
        ObservableList observableList = FXCollections.observableList(rooms);
        choiceBox.getItems().addAll(observableList);

        File bannerFile = new File("C:\\Users\\Raul\\Desktop\\Project HMS_2\\images\\banner.jpg");
        Image bannerImage = new Image(bannerFile.toURI().toString());
        bannerImageView.setImage(bannerImage);
    }

    public void handleSwitchStageSaButton() throws IOException {
        Stage stage = (Stage) saButton.getScene().getWindow();
        handleSaButtonOnAction(stage);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void handleSaButtonOnAction(Stage secondary) throws IOException {
        Parent next = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Login.fxml")));
        Scene scene = new Scene(next, 600, 400);
        secondary.setScene(scene);
        secondary.show();
    }


    public void minimizeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeButtonOnAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public void rezervaCameraButtonOnAction() throws ParseException, IOException {
        FadeTransition fader = createFader(rezervaMessageLabel);
        SequentialTransition fade = new SequentialTransition(
                rezervaMessageLabel,
                fader
        );
        if (choiceBox.getSelectionModel().isEmpty() || datePicker.getValue() == null) {
            rezervaMessageLabel.setText("Va rugam sa completati toate campurile pentru a putea sa rezervati o camera!");
            fade.play();
        } else {
            Rooms selectChoiceBox = (Rooms) choiceBox.getSelectionModel().getSelectedItem();
            selectChoiceBox.setRoomStatus(RoomStatus.OCUPAT.toString());
            MainPageController.setStringz(RoomStatus.OCUPAT.toString());
            RoomsService roomsService = new RoomsService();
            LocalDate selectDataPicker = datePicker.getValue();
            String x = selectDataPicker.toString();
            Date data1 = new SimpleDateFormat("yyyy-MM-dd").parse(x);
            java.sql.Date data2 = new java.sql.Date(data1.getTime());
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            java.sql.Date d = java.sql.Date.valueOf(timeStamp);
            if (data2.equals(d) || data2.before(d)) {
                rezervaMessageLabel.setText("Data nu este valabila! Astazi suntem in "+d+". Alege o data din viitor!");
            } else {
                selectChoiceBox.setDate(data2);
                MainPageController.setDate(data2);
                /*roomsService.updateRoom(selectChoiceBox);*/
                MainPageController.setRooms(selectChoiceBox);
                Stage stage = (Stage) rezervaCameraButton.getScene().getWindow();
                switchRezervaCamera(stage);
                stage.setOnCloseRequest(e -> Platform.exit());
            }
        }

    }

    public void switchRezervaCamera(Stage secondary) throws IOException {
        Parent next = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ClientForum.fxml")));
        Scene scene = new Scene(next, 700, 500);
        secondary.setScene(scene);
        secondary.show();
    }

    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(10), node);
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }

//    public void test(java.sql.Date date, String string){
// //       RoomsService rs = new RoomsService();
//        Rooms rooms = new Rooms();
//        rooms.setRoomStatus(string);
//        rooms.setDate(date);
////        rs.addRoom(rooms);
//    }

}
