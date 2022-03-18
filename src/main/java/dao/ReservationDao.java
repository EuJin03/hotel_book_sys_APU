package main.java.dao;

import java.io.IOException;
import javafx.event.ActionEvent;
import main.java.entities.Reservation;

public interface ReservationDao {
  void displayName(String username);
  void viewAllReservation();
  Reservation viewReservation(ActionEvent e);
  void bookReservation(ActionEvent e);
  void editReservation(ActionEvent e);
  void deleteReservation(ActionEvent e) throws IOException;
  // // Sort
  // ArrayList<Reservation> sortReservationByCheckOut();
  // ArrayList<Reservation> sortReservationByRoomID();

  // // Search
  // Reservation searchByID();
  // Reservation searchByName();
  // Reservation searchByRoomID();
}
