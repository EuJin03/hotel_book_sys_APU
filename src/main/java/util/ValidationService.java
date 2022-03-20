package main.java.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ValidationService {

  public ArrayList<String> validateReservationPersonal(
    String guestName,
    String IC,
    String contact,
    String email
  ) {
    ArrayList<String> error = new ArrayList<String>();
    Pattern emailRegEx = Pattern.compile(
      "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
      Pattern.CASE_INSENSITIVE
    );
    Pattern phoneRegEx = Pattern.compile(
      "^(01)[02-46-9]-*[0-9]{7}$|^(6?01)[1]-*[0-9]{8}$"
    );
    Pattern ICRegEx = Pattern.compile("[0-9]{12}");

    // Validate GuestName
    if (guestName.length() < 5) error.add("guestName");

    // ValidateIC
    if (!ICRegEx.matcher(IC).find()) error.add("IC");

    // Validate Phone Number
    if (!phoneRegEx.matcher(contact).find()) error.add("contact");

    // Validate email
    if (!emailRegEx.matcher(email).find()) error.add("email");

    return error;
  }

  public boolean validateReservationDetails(
    String roomType,
    int roomID,
    LocalDate checkIn,
    LocalDate checkOut
  ) {
    if (
      roomType.length() == 0 ||
      checkIn == null ||
      checkOut == null ||
      roomID == 0
    ) return false;

    return true;
  }

  public ArrayList<String> registerValidation(
    String username,
    String password,
    String confirmPassword
  ) {
    return null;
  }
}
