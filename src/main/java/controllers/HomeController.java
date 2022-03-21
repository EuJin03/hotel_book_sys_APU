/*******************************************************************************
            Controller class and logic implementation for Home.fxml
 ******************************************************************************/
package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
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
import main.java.entities.Room;
import main.java.util.FileService;
import main.java.util.ValidationService;

/**
 * @author Eugene Tin
 */
public class HomeController
  extends CommonMethods
  implements ReservationDao, Initializable {

  String newGuestName;

  @FXML
  private Label homeTitle, guestNameErr, icErr, contactErr, emailErr, dateErr, roomErr;

  @FXML
  private TextField guestInput, icInput, emailInput, contactInput, searchInput;

  @FXML
  private Button confirmButton, cancelButton, receiptButton, editButton, deleteButton, searchButton, cancelEditReservationButton, confirmEditReservationButton;

  @FXML
  private RadioButton seaRadio, jungleRadio;

  @FXML
  private ChoiceBox<Integer> roomBox;

  @FXML
  private DatePicker inDate, outDate;

  @FXML
  private TableView<Reservation> reservationTable;

  @FXML
  private TableColumn<Reservation, Integer> _idCol, roomIdCol, durationCol;

  @FXML
  private TableColumn<Reservation, String> guestNameCol, icCol, emailCol, contactCol, roomTypeCol;

  @FXML
  private TableColumn<Reservation, LocalDate> checkInCol, checkOutCol;

  /**
   * Construct a TableView to view all reservation
   */
  @Override
  public void viewAllReservation() {
    // Retrieve all the reservation data from database and sort it by ID in descending orders.
    ArrayList<Reservation> sortByIDAl = new CommonMethods()
    .sortByLatestReservation(new FileService().readReservationData());
    ObservableList<Reservation> reservationOl = FXCollections.observableArrayList(
      sortByIDAl
    );

    // * Reservation ID Column
    _idCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("_id")
    );
    // * Guest Name Column
    guestNameCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("guestName")
    );
    // * IC Column
    icCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("IC")
    );
    // * Contact Number Column
    contactCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("contact")
    );
    // * Email Column
    emailCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("email")
    );
    // * Room ID column
    roomIdCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("room_id")
    );
    // * Room Type Column
    roomTypeCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("roomType")
    );
    // * Check In Date Column
    checkInCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, LocalDate>("checkin")
    );
    // * Check Out Date Column
    checkOutCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, LocalDate>("checkout")
    );
    // * Duration of stay Column
    durationCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("duration")
    );
    // * Set reservation table data
    reservationTable.setItems(reservationOl);
    // Reset existings search input fields
    searchInput.clear();
  }

  /**
   * * Append reservations that are selected or searched into the tableview
   * @param reservationAl reservation arraylist that contains single or searched reservation objects
   */
  @Override
  public void viewReservation(ArrayList<Reservation> reservationAl) {
    // Populate arraylist of reservation objects into ObservableList that recognized by TableView
    ObservableList<Reservation> reservationOl = FXCollections.observableArrayList(
      reservationAl
    );
    // * Reservation ID Column
    _idCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("_id")
    );
    // * Guest Name Column
    guestNameCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("guestName")
    );
    // * IC Column
    icCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("IC")
    );
    // * Contact Number Column
    contactCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("contact")
    );
    // * Email Column
    emailCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("email")
    );
    // * Room ID column
    roomIdCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("room_id")
    );
    // * Room Type Column

    roomTypeCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, String>("roomType")
    );
    // * Check In Date Column
    checkInCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, LocalDate>("checkin")
    );
    // * Check Out Date Column
    checkOutCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, LocalDate>("checkout")
    );
    // * Duration of stay Column

    durationCol.setCellValueFactory(
      new PropertyValueFactory<Reservation, Integer>("duration")
    );
    // * Set reservation table data
    reservationTable.setItems(reservationOl);
  }

  /**
   * * Create a method that allow user to search reservation by Reservation ID or Contact Number
   * TODO: implements more searchable types such as Name, IC, Email
   * @param ActionEvent retrieve events that happens onSearchButtonClicked
   */
  @Override
  public void search(ActionEvent e) {
    // Indicate whether or not the reservation object(s) is found
    boolean found = false;

    // Create arraylist to include all the found reservation object(s)
    ArrayList<Reservation> result = new ArrayList<Reservation>();
    ListIterator<Reservation> li = null;
    // Retrieve reservations from database
    ArrayList<Reservation> reservationAl = new FileService()
      .readReservationData();
    li = reservationAl.listIterator();

    // * iterate through the reservations to find results that matches the search condition
    while (li.hasNext()) {
      Reservation reservation = (Reservation) li.next();
      if (
        String.valueOf(reservation.get_id()).equals(searchInput.getText())
      ) result.add(reservation);
      if (reservation.getContact().equals(searchInput.getText())) result.add(
        reservation
      );

      if (!result.isEmpty()) found = true;
    }

    // if search input is empty, console log a notice
    if (!found && searchInput.getText().equals("")) System.out.println(
      "Nothing is on the search field!"
    ); else {
      if (!found) {
        // * If no result is found, append an alert to notice the user
        new CommonMethods()
        .appendAlert(
            "Reservation ID or Contact Number",
            "Reservation ID or Contact Number does not exist",
            "Please check the input and search again."
          );
        searchInput.clear();
      } else {
        // * if result is found, invoke the viewReservation method to display results
        viewReservation(result);
      }
    }
  }

  /**
   * * Create a method to handle booking request.
   * * Book reservation will validate inputs, append confirmation, and update Reservation and Room database
   * @param ActionEvent events that happens onBookingButtonClicked
   * @throws IOException
   */
  @Override
  public void bookReservation(ActionEvent e) throws IOException {
    // * Retrieve reservations from database
    ArrayList<Reservation> reservationAl = new FileService()
      .readReservationData();
    int recentID;

    // * Append latest ID into booking
    if (reservationAl.size() != 0) recentID =
      reservationAl
        .stream()
        .max(Comparator.comparing(Reservation::get_id))
        .get()
        .get_id() +
      1; else recentID = 1000;

    // * Input Handlers
    String newGuestName = guestInput.getText();
    String newIC = icInput.getText();
    String newContact = contactInput.getText();
    String newEmail = emailInput.getText();
    int roomID = 0;
    String roomType = "";
    if (seaRadio.isSelected()) roomType = "Sea";
    if (jungleRadio.isSelected()) roomType = "Jungle";

    if (roomType.length() != 0 && roomBox.getValue() != null) roomID =
      roomBox.getValue();
    LocalDate checkIn = inDate.getValue();
    LocalDate checkOut = outDate.getValue();

    // * Create a new alert to request confirmation upon new reservation
    boolean CONFIRMATION = new CommonMethods()
    .appendAlert(
        "New Reservation",
        "Reservation for Mr./Mrs. " + newGuestName,
        "Confirm Reservation."
      );

    // CONFIRMATION = true
    if (CONFIRMATION) {
      // Error Label styling reset
      guestNameErr.setStyle("-fx-opacity: 0");
      icErr.setStyle("-fx-opacity: 0");
      contactErr.setStyle("-fx-opacity: 0");
      emailErr.setStyle("-fx-opacity: 0");
      dateErr.setStyle("-fx-opacity: 0");
      roomErr.setStyle("-fx-opacity: 0");

      // * Validate reservation details input
      boolean valid = new ValidationService()
      .validateReservationDetails(roomType, roomID, checkIn, checkOut);

      // * Validate personal details input
      ArrayList<String> validateAl = new ValidationService()
      .validateReservationPersonal(newGuestName, newIC, newContact, newEmail);

      // Validation failed
      if (validateAl.size() != 0) valid = false;

      // * Validation success
      if (valid) {
        // * Create new instance of reservation object
        Reservation newReservation = new Reservation(
          recentID,
          newGuestName,
          newIC,
          newContact,
          newEmail,
          roomID,
          roomType,
          checkIn,
          checkOut
        );

        // * Append new instance into existing reservation arraylist from database
        reservationAl.add(newReservation);

        // * Sort the reservation arraylist by ID in descending orders
        ArrayList<Reservation> sortByIDAl = new CommonMethods()
        .sortByLatestReservation(reservationAl);
        // * Update reservation arraylist in database
        new FileService().writeReservationData(sortByIDAl);

        // * Update UI TableView with new reservation
        viewAllReservation();

        // reset the input fields
        clearInput();
        // Prompt user to the print receipt page
        FXMLLoader loader = new CommonMethods().loadButtonScene(e);
        ReceiptController receiptController = loader.getController();
        receiptController.generateReceipt(newReservation);
        // update room details
        new CommonMethods().editRoomDetails(roomID);
      } else {
        // Prompt error messages
        // Personal Details UI error
        for (String field : validateAl) {
          switch (field) {
            case "guestName":
              guestNameErr.setStyle("-fx-opacity: 1");
              break;
            case "IC":
              icErr.setStyle("-fx-opacity: 1");
              break;
            case "contact":
              contactErr.setStyle("-fx-opacity: 1");
              break;
            case "email":
              emailErr.setStyle("-fx-opacity: 1");
              break;
            default:
              break;
          }
        }
        // Reservation details UI error
        if (roomID == 0) roomErr.setStyle("-fx-opacity: 1");
        if (checkOut == null) dateErr.setStyle("-fx-opacity: 1");
      }
    }
  }

  /**
   * * UI Handling to reset user inputs and stylings
   */
  public void clearInput() {
    guestInput.clear();
    icInput.clear();
    contactInput.clear();
    emailInput.clear();
    outDate.getEditor().clear();
    outDate.setValue(null);
    guestNameErr.setStyle("-fx-opacity: 0");
    icErr.setStyle("-fx-opacity: 0");
    contactErr.setStyle("-fx-opacity: 0");
    emailErr.setStyle("-fx-opacity: 0");
    dateErr.setStyle("-fx-opacity: 0");
    roomErr.setStyle("-fx-opacity: 0");
    roomBox.getItems().clear();
    seaRadio.setSelected(false);
    jungleRadio.setSelected(false);
  }

  /**
   * * Create method to handle single reservation edits
   * * Edit Reservation method will verify selected fields, extract the field from reservation objects and record changes on cell fields
   * TODO: Validation on the selected inputs
   * @param ActionEvent perform actions onEditButtonClicked
   */
  @Override
  public void editReservation(ActionEvent e) {
    // Validate if the selected row is empty
    boolean isEmpty = reservationTable.getSelectionModel().isEmpty();

    // isEmpty, do nothing
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
      // * Record changes on guestName column
      guestNameCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Reservation, String>>() {
          @Override
          public void handle(CellEditEvent<Reservation, String> event) {
            Reservation res = event.getRowValue();
            res.setGuestName(event.getNewValue());
          }
        }
      );

      // * Record changes on IC column
      icCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Reservation, String>>() {
          @Override
          public void handle(CellEditEvent<Reservation, String> event) {
            Reservation res = event.getRowValue();
            res.setIC(event.getNewValue());
          }
        }
      );

      // * Record changes on contact number column
      contactCol.setOnEditCommit(
        new EventHandler<CellEditEvent<Reservation, String>>() {
          @Override
          public void handle(CellEditEvent<Reservation, String> event) {
            Reservation res = event.getRowValue();
            res.setContact(event.getNewValue());
          }
        }
      );

      // * Record changes on email column
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

  /**
   * * Extended method from editReservation
   * * Handles edit confirmation and actions afterwards
   * * Appends CONFIRMATION alert, retrieve row ID and reservation ID, retrieve reservation data,
   * * modify selected reservation object, overwrite and append it back to the arraylist
   * * Update Reservation database
   * @param ActionEvent record cells changes upon editing
   * @throws IOException
   */
  @Override
  public void confirmEditReservation(ActionEvent e) throws IOException {
    // Append CONFIRMATION alert
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

    // CONFIRMATION = true;
    if (CONFIRMATION) {
      // Retrieve reservation objects in an arraylist
      ArrayList<Reservation> reservationAl = new FileService()
        .readReservationData();
      // Initialize an reservation arraylist iterator
      ListIterator<Reservation> li = null;
      li = reservationAl.listIterator();
      // Check if the reservation object is found
      boolean found = false;
      Reservation selectedField = reservationTable
        .getSelectionModel()
        .getSelectedItem();

      // Edited inputs
      String newGuestName = selectedField.getGuestName();
      String newIC = selectedField.getIC();
      String newContact = selectedField.getContact();
      String newEmail = selectedField.getEmail();

      // Iterate through the reservation arraylist to find reservation ID that matches the reservation to be modified
      while (li.hasNext()) {
        Reservation res = (Reservation) li.next();
        if (res.get_id() == selectedReservationID) {
          // Update the reservation object with edited inputs if ID is found
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

      // If ID does not match the system console log a notice
      if (!found) System.out.println("What can you do?"); else new FileService() // update the reservation objects databse
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

  /**
   * * Extended method from editReservation
   * * Handles discards and actions of reservation object on both UI and database
   * * Appends CONFIRMATION alert and perform discard changes action upon user request
   * * Reset all inputs and reservation data upon discard
   * @param ActionEvent reset the reservation objects if the changes is discard
   */
  @Override
  public void cancelEditReservation(ActionEvent e) {
    // Append CONFIRMATION alert
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

  /**
   * * Handles deletion of reservation objects on both UI and database
   * * Appends CONFIRMATION alert and perform reservation removal action upon user request
   * * Reset removed reservation room "Occupied" field to false
   * * Reload TableView upon deletion
   * @param ActionEvent record the cell row selected, row ID and reservation object details
   * @throws IOException
   */
  @Override
  public void deleteReservation(ActionEvent e) throws IOException {
    // check if there is any selected cell row
    boolean isEmpty = reservationTable.getSelectionModel().isEmpty();

    // If no cell row is selected, do nothing
    if (isEmpty) e.consume(); else {
      // retrieve cell row reservation object id
      int selectedReservationID = reservationTable
        .getSelectionModel()
        .getSelectedItem()
        .get_id();

      // * retrieve cell row reservation object roomID
      int selectedRoomID = reservationTable
        .getSelectionModel()
        .getSelectedItem()
        .getRoom_id();

      // * retrieve cell row index for UI deletion
      int selectedID = reservationTable.getSelectionModel().getSelectedIndex();

      // append CONFIRMATION alert
      boolean CONFIRMATION = new CommonMethods()
      .appendAlert(
          "Delete Reservation",
          "Delete Reservation ID: " + selectedReservationID,
          "Are you sure you want to delete this reservation?"
        );

      // CONFIRMATION = true
      if (CONFIRMATION) {
        // * retrieve room objects from database
        ArrayList<Room> roomAl = new FileService().readRoomData();
        ListIterator<Room> roomLi = roomAl.listIterator();

        // * iterate through the room arraylist to find roomID that matches the reservation roomID
        while (roomLi.hasNext()) {
          Room room = (Room) roomLi.next();
          if (room.get_id() == selectedRoomID) {
            roomLi.set(new Room(room.get_id(), room.getType(), false));
          }
        }

        // * Remove the reservation object from the TableView
        reservationTable.getItems().remove(selectedID);

        // * retrieve reservation objects from database

        ArrayList<Reservation> reservationAl = new FileService()
          .readReservationData();
        ListIterator<Reservation> li = reservationAl.listIterator();
        boolean found = false;

        // * iterate through the reservation arraylist to find reservation ID that match with the selected row
        while (!found) {
          Reservation r = (Reservation) li.next();
          if (r.get_id() == selectedReservationID) {
            // * remove the reservation object from arraylist when found
            li.remove();
            found = true;
          }
        }

        // print out errors if deletion cannot complete
        if (!found) {
          System.out.println("Cannot delete this reservation");
        } else {
          // Overwrite the existing reservation and room database
          new FileService().writeReservationData(reservationAl);
          new FileService().writeRoomData(roomAl);
        }
      }
    }
  }

  /**
   * Handle user logout the programon request
   * @param ActionEvent logout onButtonClicked
   */
  @Override
  public void logout(ActionEvent e) {
    // append CONFIRMATION alert
    boolean CONFIRMATION = new CommonMethods()
    .appendAlert("Logout", "Logout", "Are you sure to logout?");

    // CONFIRMATION = true, switch user back to Login Scene
    if (CONFIRMATION) new CommonMethods().loadButtonScene(e);
  }

  /**
   * Allow user to view receipt details on a single reservation object
   * Handles reservation object and append it onto a new receipt scene
   * @param ActionEvent view receipt details on reservation object selected
   */
  @Override
  public void viewReceipt(ActionEvent event) {
    // check if there is any selected cell row
    boolean isEmpty = reservationTable.getSelectionModel().isEmpty();

    // If selected cell row is empty, do nothing
    if (isEmpty) event.consume(); else {
      // * retrieve cell row reservation object
      Reservation reservation = reservationTable
        .getSelectionModel()
        .getSelectedItem();

      // Prompt user to the print receipt page
      FXMLLoader loader = new CommonMethods().loadButtonScene(event);
      ReceiptController receiptController = loader.getController();
      // Invoke generate receipt method with reservation object as param
      receiptController.generateReceipt(reservation);
    }
  }

  /**
   * Display staff username upon logged in
   * @param username Staff name
   */
  @Override
  public void displayName(String username) {
    homeTitle.setText("Welcome, " + username + "!");
  }

  /**
   * * Handles onRadioChanges action
   * * Append rooms that are either Sea or Jungle views onto the choicebox if either is selected
   * @param ActionEvent track radio choice changes
   */
  public void onRadioChange(ActionEvent e) {
    // Retrieve room objects from database
    ArrayList<Room> roomAl = new FileService().readRoomData();
    ListIterator<Room> li = roomAl.listIterator();
    // Instantiate empty arrays to store sea and jungle view room IDs respectively
    ArrayList<Integer> seaAl = new ArrayList<Integer>();
    ArrayList<Integer> jungleAl = new ArrayList<Integer>();

    // Iterate through room arraylist to append Sea view room IDs into Sea arraylist
    while (li.hasNext()) {
      Room room = (Room) li.next();
      if (room.getType().equals("Sea") && !room.isOccupied()) seaAl.add(
        room.get_id()
      );
    }

    // reset list iterator
    li = roomAl.listIterator();

    // Iterate through room arraylist to append Jungle view room IDs into Jungle arraylist
    while (li.hasNext()) {
      Room room = (Room) li.next();
      if (room.getType().equals("Jungle") && !room.isOccupied()) jungleAl.add(
        room.get_id()
      );
    }

    // Append jungle arraylist with roomIDs into choicebox
    if (jungleRadio.isSelected()) {
      roomBox.getItems().clear();
      roomBox.getItems().addAll(jungleAl);
    }
    // Append sea arraylist with roomIDs into choicebox
    if (seaRadio.isSelected()) {
      roomBox.getItems().clear();
      roomBox.getItems().addAll(seaAl);
    }
  }

  /**
   * * Handles the default UI input fields
   * * Set default CheckIn dates and available dates
   * * Set available checkOut dates
   * TODO: Refactor and move it to Utils folder
   */
  public void setDefaultAddReservation() {
    // Set today date and date available for bookings
    LocalDate minDate = LocalDate.now();
    LocalDate minDate2 = minDate.plusDays(1);
    LocalDate maxDate = minDate.plusDays(7);

    // Disable unavailable checkIn dates in UI
    inDate.setDayCellFactory(
      d ->
        new DateCell() {
          @Override
          public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
          }
        }
    );
    // Disable unavailable checkOut dates in UI
    outDate.setDayCellFactory(
      d ->
        new DateCell() {
          @Override
          public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            setDisable(item.isAfter(maxDate) || item.isBefore(minDate2));
          }
        }
    );

    // Set default checkIn dates
    inDate.setValue(minDate);
  }

  /**
   *  * Initialise method required for implementing initializable and,
   *  * sets up and applies all css styles to nodes in Home.fxml
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    viewAllReservation();
    setDefaultAddReservation();
  }
}
