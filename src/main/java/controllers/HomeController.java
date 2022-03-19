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
import javafx.fxml.FXMLLoader;
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

  String newGuestName;

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
    ObservableList<Reservation> reservationOl = FXCollections.observableArrayList(
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

    reservationTable.setItems(reservationOl);
  }

  @Override
  public void viewReservation(ArrayList<Reservation> reservationAl) {
    ObservableList<Reservation> reservationOl = FXCollections.observableArrayList(
      reservationAl
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

    reservationTable.setItems(reservationOl);
  }

  @Override
  public void bookReservation(ActionEvent e) {}

  @Override
  public void editReservation(ActionEvent e) {
    boolean isEmpty = reservationTable.getSelectionModel().isEmpty();

    if (isEmpty) e.consume(); else {
      // UI Changes
      cancelEditReservationButton.setStyle("visibility: visible");
      confirmEditReservationButton.setStyle("visilibity: visible");
      receiptButton.setStyle("visibility: hidden");

      // Set table edit mode
      reservationTable.setEditable(true);
      guestNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
      icCol.setCellFactory(TextFieldTableCell.forTableColumn());
      emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
      contactCol.setCellFactory(TextFieldTableCell.forTableColumn());

      // Get col text changes
      guestNameCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Reservation, String>>() {
          @Override
          public void handle(CellEditEvent<Reservation, String> event) {
            Reservation res = event.getRowValue();
            res.setGuestName(event.getNewValue());
          }
        }
      );

      icCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Reservation, String>>() {
          @Override
          public void handle(CellEditEvent<Reservation, String> event) {
            Reservation res = event.getRowValue();
            res.setIC(event.getNewValue());
          }
        }
      );

      contactCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Reservation, String>>() {
          @Override
          public void handle(CellEditEvent<Reservation, String> event) {
            Reservation res = event.getRowValue();
            res.setContact(event.getNewValue());
          }
        }
      );

      emailCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Reservation, String>>() {
          @Override
          public void handle(CellEditEvent<Reservation, String> event) {
            Reservation res = event.getRowValue();
            res.setEmail(event.getNewValue());
          }
        }
      );
    }
  }

  @Override
  public void confirmEditReservation(ActionEvent e) throws IOException {
    boolean CONFIRMATION = new CommonMethods()
    .appendAlert(
        "Save Edit Reservation",
        "Save Modification",
        "Are you sure you want to save your changes?"
      );

    // Get column id
    int selectedReservationID = reservationTable
      .getSelectionModel()
      .getSelectedItem()
      .get_id();

    if (CONFIRMATION) {
      // Update database
      ArrayList<Reservation> reservationAl = new FileService()
        .readReservationData();
      ListIterator<Reservation> li = null;
      li = reservationAl.listIterator();
      boolean found = false;
      Reservation selectedField = reservationTable
        .getSelectionModel()
        .getSelectedItem();
      String newGuestName = selectedField.getGuestName();
      String newIC = selectedField.getIC();
      String newContact = selectedField.getContact();
      String newEmail = selectedField.getEmail();

      while (li.hasNext()) {
        Reservation res = (Reservation) li.next();
        if (res.get_id() == selectedReservationID) {
          li.set(
            new Reservation(
              res.get_id(),
              newGuestName,
              newIC,
              newContact,
              newEmail,
              res.getRoom_id(),
              res.getRoomType(),
              res.getCheckin(),
              res.getCheckout()
            )
          );
          found = true;
        }
      }

      if (!found) System.out.println("What can you do?"); else new FileService()
      .writeReservationData(reservationAl);

      // Reset table edit mode
      reservationTable.setEditable(false);

      // UI Changes
      cancelEditReservationButton.setStyle("visibility: hidden");
      confirmEditReservationButton.setStyle("visibility: hidden");
      receiptButton.setStyle("visibility: visible");
      // Refresh the new data table
      viewAllReservation();
    }
  }

  @Override
  public void cancelEditReservation(ActionEvent e) {
    boolean CONFIRMATION = new CommonMethods()
    .appendAlert(
        "Cancel Edit Reservation",
        "Modification unsaved",
        "Are you sure you want to leave edit mode unsaved?"
      );

    if (CONFIRMATION) {
      // Reset table edit mode
      reservationTable.setEditable(false);
      guestNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

      // UI Changes
      cancelEditReservationButton.setStyle("visibility: hidden");
      confirmEditReservationButton.setStyle("visibility: hidden");
      receiptButton.setStyle("visibility: visible");

      // Refresh the new data table
      viewAllReservation();
    }
  }

  @Override
  public void deleteReservation(ActionEvent e) throws IOException {
    boolean isEmpty = reservationTable.getSelectionModel().isEmpty();

    if (isEmpty) e.consume(); else {
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
  }

  @Override
  public void logout(ActionEvent e) {
    boolean CONFIRMATION = new CommonMethods()
    .appendAlert("Logout", "Logout", "Are you sure to logout?");

    if (CONFIRMATION) new CommonMethods().loadButtonScene(e);
  }

  @Override
  public void viewReceipt(ActionEvent event) {
    boolean isEmpty = reservationTable.getSelectionModel().isEmpty();

    if (isEmpty) event.consume(); else {
      Reservation reservation = reservationTable
        .getSelectionModel()
        .getSelectedItem();

      FXMLLoader loader = new CommonMethods().loadButtonScene(event);
      ReceiptController receiptController = loader.getController();
      receiptController.generateReceipt(reservation);
    }
  }

  @Override
  public void displayName(String username) {
    homeTitle.setText("Welcome, " + username + "!");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    viewAllReservation();
  }

  public void search(ActionEvent e) {
    boolean found = false;
    ArrayList<Reservation> singleReservation = new ArrayList<Reservation>();
    ListIterator<Reservation> li = null;

    ArrayList<Reservation> reservationAl = new FileService()
      .readReservationData();
    li = reservationAl.listIterator();

    while (li.hasNext()) {
      Reservation reservation = (Reservation) li.next();
      if (String.valueOf(reservation.get_id()).equals(searchInput.getText())) {
        found = true;
        singleReservation.add(reservation);
      }
    }

    if (!found) new CommonMethods()
    .appendAlert(
        "Reservation ID",
        "Reservation ID does not exist",
        "Please check the reservation ID and search again."
      ); else {
      viewReservation(singleReservation);
    }
  }
}
