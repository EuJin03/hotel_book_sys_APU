package main.java.dao;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import main.java.entities.Reservation;

public interface ReservationDao {
  void displayName(String username);
  void viewAllReservation();
  void viewReservation(ArrayList<Reservation> reservationAl);
  void search(ActionEvent e);
  void bookReservation(ActionEvent e) throws IOException;
  void editReservation(ActionEvent e);
  void deleteReservation(ActionEvent e) throws IOException;
  void confirmEditReservation(ActionEvent e) throws IOException;
  void cancelEditReservation(ActionEvent e);
  void viewReceipt(ActionEvent event);
  void logout(ActionEvent e);
  // // Sort
  // ArrayList<Reservation> sortReservationByCheckOut();
  // ArrayList<Reservation> sortReservationByRoomID();

  // // Search
  // Reservation searchByID();
  // Reservation searchByName();
  // Reservation searchByContactNo();
}
