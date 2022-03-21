/**
 * * Data Access Object Interface - This interface defines the standard operations to be performed on a model object(s).
 * * Data Access Object concrete class -This class implements above interface. This class is responsible to get data from a datasource which can be database or any other storage
 * * Model Object or Value Object - This object is simple POJO containing get/set methods to store data retrieved using DAO class.
 */

/******************************************************************************************************************************************************
 * ReservationDAO is a DAO interface class that defines the instructions to be performed on a model object(s), Reservation.
 * It should be implemented on the Controller
 *****************************************************************************************************************************************************/

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
