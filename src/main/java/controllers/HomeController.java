package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.java.dao.ReservationDao;
import main.java.entities.Reservation;
import main.java.util.FileService;

public class HomeController
  extends CommonMethods
  implements ReservationDao, Initializable {

  @FXML
  private Label homeTitle;

  @FXML
  private TextField guestInput, icInput, emailInput, contactInput, searchInput;

  @FXML
  private Button confirmButton, cancelButton, receiptButton, editButton, deleteButton, searchButton, cancelEditReservationButton, confirmEditReservationButton;

  @FXML
  private RadioButton seaRadio, jungleRadio;

  @FXML
  private TableView<Reservation> reservationTable;

  @FXML
  private TableColumn<Reservation, Integer> _idCol, roomIdCol, durationCol;

  @FXML
  private TableColumn<Reservation, String> guestNameCol, icCol, emailCol, contactCol, roomTypeCol;

  @FXML
  private TableColumn<Reservation, LocalDate> checkInCol, checkOutCol;

  @Override
  public void viewAllReservation() {
    ObservableList<Reservation> reservationAl = FXCollections.observableArrayList(
      new FileService().readReservationData()
    );

    _idCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("_id")
    );
    guestNameCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("guestName")
    );
    icCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("IC")
    );
    contactCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("contact")
    );
    emailCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("email")
    );
    roomIdCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("room_id")
    );

    roomTypeCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("roomType")
    );

    checkInCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, LocalDate>("checkin")
    );
    checkOutCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, LocalDate>("checkout")
    );
    durationCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("duration")
    );

    reservationTable.setItems(reservationAl);
  }

  @Override
  public Reservation viewReservation(ActionEvent e) {
    return null;
  }

  @Override
  public void bookReservation(ActionEvent e) {}

  @Override
  public void editReservation(ActionEvent e) {
    cancelEditReservationButton.setStyle("visibility: visible");
    confirmEditReservationButton.setStyle("visilibity: visible");
    receiptButton.setStyle("visibility: hidden");
    reservationTable.setEditable(true);

    guestNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
    guestNameCol.setOnEditCommit(
      new EventHandler<CellEditEvent<Reservation, String>>() {
        @Override
        public void handle(CellEditEvent<Reservation, String> event) {
          Reservation reservation = event.getRowValue();
          reservation.setGuestName(event.getNewValue());
        }
      }
    );
  }

  public void confirmEditReservation(ActionEvent e) {}

  public void cancelEditReservation(ActionEvent e) {
    boolean CONFIRMATION = new CommonMethods()
    .appendAlert(
        "Cancel Edit Reservation",
        "Modification unsaved",
        "Are you sure you want to leave edit mode unsaved?"
      );

    if (CONFIRMATION) {
      reservationTable.setEditable(false);
      guestNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

      viewAllReservation();
      cancelEditReservationButton.setStyle("visibility: hidden");
      confirmEditReservationButton.setStyle("visibility: hidden");
      receiptButton.setStyle("visibility: visible");
    }
  }

  @Override
  public void deleteReservation(ActionEvent e) throws IOException {
    int selectedReservationID = reservationTable
      .getSelectionModel()
      .getSelectedItem()
      .get_id();
    int selectedID = reservationTable.getSelectionModel().getSelectedIndex();
    boolean CONFIRMATION = new CommonMethods()
    .appendAlert(
        "Delete Reservation",
        "Delete Reservation ID: " + selectedReservationID,
        "Are you sure you want to delete this reservation?"
      );

    if (CONFIRMATION) {
      reservationTable.getItems().remove(selectedID);

      ArrayList<Reservation> reservationAl = new FileService()
        .readReservationData();
      ListIterator<Reservation> li = reservationAl.listIterator();
      boolean found = false;

      while (!found) {
        Reservation r = (Reservation) li.next();
        if (r.get_id() == selectedReservationID) {
          li.remove();
          found = true;
        }
      }

      if (!found) {
        System.out.println("Cannot delete this reservation");
      } else {
        new FileService().writeReservationData(reservationAl);
      }
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    viewAllReservation();
  }

  public void logout(ActionEvent e) {
    boolean CONFIRMATION = new CommonMethods()
    .appendAlert("Logout", "Logout", "Are you sure to logout?");

    if (CONFIRMATION) new CommonMethods().loadButtonScene(e);
  }

  @Override
  public void displayName(String username) {
    homeTitle.setText("Welcome, " + username + "!");
  }
}
