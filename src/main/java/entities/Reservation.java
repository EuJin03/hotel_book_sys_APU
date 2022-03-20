package main.java.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation implements Serializable {

  private int _id;
  private String guestName;
  private String IC;
  private String contact;
  private String email;
  private int room_id;
  private String roomType;
  private LocalDate checkin;
  private LocalDate checkout;
  private long duration;

  final double price = 350;
  final int roomTax = 10;
  final double serviceTax = 1.1;

  public Reservation(
    int _id,
    String guestName,
    String IC,
    String contact,
    String email,
    int room_id,
    String roomType,
    LocalDate checkin,
    LocalDate checkout
  ) {
    this.set_id(_id);
    this.setGuestName(guestName);
    this.setIC(IC);
    this.setContact(contact);
    this.setEmail(email);
    this.setRoom_id(room_id);
    this.setRoomType(roomType);
    this.setCheckin(checkin);
    this.setCheckout(checkout);
    this.setDuration();
  }

  // Getter
  public int get_id() {
    return _id;
  }

  public String getGuestName() {
    return guestName;
  }

  public String getIC() {
    return IC;
  }

  public String getContact() {
    return contact;
  }

  public String getEmail() {
    return email;
  }

  public int getRoom_id() {
    return room_id;
  }

  public String getRoomType() {
    return roomType;
  }

  public LocalDate getCheckin() {
    return checkin;
  }

  public LocalDate getCheckout() {
    return checkout;
  }

  public long getDuration() {
    return duration;
  }

  public double getNetPrice() {
    return Math.round(price * getDuration());
  }

  public double getServiceTax() {
    return Math.round(getNetPrice() * 0.1);
  }

  public double getTotalRoomTax() {
    return Math.round(roomTax * getDuration());
  }

  public double getTotalPrice() {
    return Math.round(getNetPrice() * serviceTax + getTotalRoomTax());
  }

  @Override
  public String toString() {
    return (
      _id +
      " " +
      guestName +
      " " +
      IC +
      " " +
      contact +
      " " +
      email +
      " " +
      room_id +
      " " +
      roomType +
      " " +
      checkin +
      " " +
      checkout +
      " " +
      duration
    );
  }

  // Setter
  public void set_id(int _id) {
    this._id = _id;
  }

  public void setGuestName(String guestName) {
    this.guestName = guestName;
  }

  public void setIC(String iC) {
    this.IC = iC;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setRoom_id(int room_id) {
    this.room_id = room_id;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  public void setCheckin(LocalDate checkin) {
    this.checkin = checkin;
  }

  public void setCheckout(LocalDate checkout) {
    this.checkout = checkout;
  }

  public void setDuration() {
    this.duration = ChronoUnit.DAYS.between(getCheckin(), getCheckout());
  }
}
