/*******************************************************************************
            Controller class and logic implementation for Receipt.fxml
 ******************************************************************************/
package main.java.controllers;

import java.text.DecimalFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.entities.Reservation;

public class ReceiptController extends CommonMethods {

  // format price strings into 2 decimals place
  private static final DecimalFormat df = new DecimalFormat("0.00");

  @FXML
  private Label invoiceLabel, guestNameLabel, ICLabel, contactLabel, emailLabel, roomLabel, roomTypeLabel, checkInLabel, checkOutLabel, durationLabel, subtotalLabel, serviceTaxLabel, roomFeeLabel, grandTotalLabel;

  public void returnHome(ActionEvent event) {
    new CommonMethods().loadButtonScene(event);
  }

  /**
   * * Generate receipt by displaying details from reservation array
   * @param reservation selected single reservation data
   */
  public void generateReceipt(Reservation reservation) {
    invoiceLabel.setText(String.valueOf(reservation.get_id()));
    guestNameLabel.setText(reservation.getGuestName());
    ICLabel.setText(reservation.getIC());
    contactLabel.setText(reservation.getContact());
    emailLabel.setText(reservation.getEmail());
    roomLabel.setText(String.valueOf(reservation.getRoom_id()));
    roomTypeLabel.setText(reservation.getRoomType());
    checkInLabel.setText(reservation.getCheckin().toString());
    checkOutLabel.setText(reservation.getCheckout().toString());
    durationLabel.setText(String.valueOf(reservation.getDuration()) + " days");
    subtotalLabel.setText("RM" + df.format(reservation.getNetPrice()));
    serviceTaxLabel.setText("RM" + df.format(reservation.getServiceTax()));
    roomFeeLabel.setText("RM" + df.format(reservation.getTotalRoomTax()));
    grandTotalLabel.setText("RM" + df.format(reservation.getTotalPrice()));
  }
}
