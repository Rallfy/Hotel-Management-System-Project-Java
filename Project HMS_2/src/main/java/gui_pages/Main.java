package gui_pages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import module.Rooms;
import service.RoomsService;

import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {//OpenLoginPage
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainPage.fxml")));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        //       primaryStage.setOnCloseRequest(e -> Platform.exit());
    }


    public static void main(String[] args) {
        launch(args);

//
//    //    DB commands
//
//        Rooms r1 = new Rooms();
//        r1.setNumberOfBeds(1);
//        r1.setRoomStatus("RoomStatus.LIBER");
//
//        RoomsService roomsService = new RoomsService();
//        roomsService.addRoom(r1);
    }
}












/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {


        transaction.begin();

        Employee e1 = new Employee();
        e1.setFirstName("andrei");
        e1.setLastName("boalba");
        e1.setGender("asdasd");
        entityManager.persist(e1);

        Login l1 = new Login();
        l1.setIdEmployee(e1.getIdEmployee());
        entityManager.persist(l1);

        System.out.println(e1);
        System.out.println(l1);

        transaction.commit();


        } finally {
        if (transaction.isActive()) {
        transaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
        }
        }*/