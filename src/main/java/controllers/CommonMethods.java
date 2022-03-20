package main.java.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import main.java.entities.Reservation;
import main.java.entities.Room;
import main.java.util.FileService;

public class CommonMethods {

  public FXMLLoader loadButtonScene(ActionEvent event) {
    String newscene = "";
    if (
      ((Button) (event.getSource())).getId().equals("loginButton") ||
      ((Button) (event.getSource())).getId().equals("returnButton")
    ) newscene = "Home.fxml";

    if (
      ((Button) (event.getSource())).getId().equals("logoutButton")
    ) newscene = "Login.fxml";

    if (
      ((Button) (event.getSource())).getId().equals("receiptButton") ||
      ((Button) (event.getSource())).getId().equals("confirmButton")
    ) newscene = "Receipt.fxml";

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(newscene));
      Parent root = loader.load();
      Node source = (Node) event.getSource();
      Stage stage = (Stage) source.getScene().getWindow();
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      return loader;
    } catch (IOException ex) {
      System.out.println("Error in switching stages");
      ex.printStackTrace();
    }
    return null;
  }

  public FXMLLoader loadLinkScene(ActionEvent event) {
    String newscene = "";
    if (
      ((Hyperlink) (event.getSource())).getId().equals("loginLink")
    ) newscene = "Login.fxml";

    if (
      ((Hyperlink) (event.getSource())).getId().equals("registerLink")
    ) newscene = "Register.fxml";

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(newscene));
      Parent root = loader.load();
      Node source = (Node) event.getSource();
      Stage stage = (Stage) source.getScene().getWindow();
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.show();
      return loader;
    } catch (IOException ex) {
      System.out.println("Error in switching stages");
      ex.printStackTrace();
    }
    return null;
  }

  public boolean appendAlert(String title, String header, String content) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);

    if (alert.showAndWait().get() == ButtonType.OK) return true;

    return false;
  }

  public ArrayList<Reservation> sortByLatestReservation(
    ArrayList<Reservation> reservationAl
  ) {
    Collections.sort(
      reservationAl,
      new Comparator<Reservation>() {
        public int compare(Reservation r1, Reservation r2) {
          return r2.get_id() - r1.get_id();
        }
      }
    );

    return reservationAl;
  }

  public void editRoomDetails(int roomID) throws IOException {
    ArrayList<Room> rooms = new FileService().readRoomData();
    ListIterator<Room> li = rooms.listIterator();
    boolean found = false;

    while (li.hasNext()) {
      Room room = (Room) li.next();
      if (room.get_id() == roomID) {
        li.set(new Room(room.get_id(), room.getType(), true));
        found = true;
      }
    }

    if (!found) System.out.println("Something went wrong with the code"); else {
      Collections.sort(
        rooms,
        new Comparator<Room>() {
          public int compare(Room r1, Room r2) {
            return r1.get_id() - r2.get_id();
          }
        }
      );

      new FileService().writeRoomData(rooms);
    }
  }

  public void refreshRoomDetails() throws IOException {
    // Compare dates and set occupy to false
    ArrayList<Reservation> reservationAl = new FileService()
      .readReservationData();
    ArrayList<Room> roomAl = new FileService().readRoomData();
    ListIterator<Room> roomLi = roomAl.listIterator();

    for (Reservation reservation : reservationAl) {
      LocalDate today = LocalDate.now();
      LocalDate checkOutDate = reservation.getCheckout();

      if (today.compareTo(checkOutDate) > 0) {
        while (roomLi.hasNext()) {
          Room room = (Room) roomLi.next();
          if (room.get_id() == reservation.getRoom_id()) {
            roomLi.set(new Room(room.get_id(), room.getType(), false));
          }
        }
      }
      roomLi = roomAl.listIterator();
    }

    new FileService().writeRoomData(roomAl);
  }
}
