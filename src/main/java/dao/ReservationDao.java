package main.java.dao;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import main.java.entities.Reservation;

public interface ReservationDao {
  ArrayList<Reservation> viewAllRoom();
  Reservation viewRoom(ActionEvent e);
  void bookReservation(ActionEvent e);
  void editReservation(ActionEvent e);
  void deleteReservation(ActionEvent e);
  // // Sort
  // ArrayList<Reservation> sortReservationByCheckOut();
  // ArrayList<Reservation> sortReservationByRoomID();

  // // Search
  // Reservation searchByID();
  // Reservation searchByName();
  // Reservation searchByRoomID();
}
