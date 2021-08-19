package gui_pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import module.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.Column;
import javax.swing.table.TableColumn;
import javax.swing.text.TabableView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeList implements Initializable {
    @FXML
    private javax.swing.text.TabableView tableEmployee;
    @FXML
    private javafx.scene.control.TableColumn<Model, String> col_id;
    @FXML
    private javafx.scene.control.TableColumn<Model, String> col_firstName;
    @FXML
    private javafx.scene.control.TableColumn<Model, String> col_lastName;
    @FXML
    private javafx.scene.control.TableColumn<Model, String> col_gender;
    @FXML
    private javafx.scene.control.TableColumn<Model, String> col_jobPosition;
    @FXML
    private javafx.scene.control.TableColumn<Model, String> col_salary;

    ObservableList<Model> obs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        ResultSet rs = (ResultSet) session.createQuery("select '*' from Employee");
        try {
            obs.add(new Model(rs.getString("idEmployee"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("gender"),rs.getString("jobPosition"),rs.getString("salary")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_jobPosition.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));


    }

}
