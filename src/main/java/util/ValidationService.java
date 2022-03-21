/***********************************************************************************************************************************************************
 * ValidationService is an utility class that contain methods that handles validation of User Inputs including registrations and reservations
 ***********************************************************************************************************************************************************/

package main.java.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;
import main.java.entities.Staff;

public class ValidationService {

  /**
   * * Validate Personal Details Inputs
   * * If validation failed, append it into an arraylist that contains all the error
   * @param guestName String
   * @param IC String
   * @param contact String
   * @param email String
   * @return error
   */
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

    // Validate IC
    if (!ICRegEx.matcher(IC).find() || IC.length() > 12) error.add("IC");

    // Validate Phone Number
    if (!phoneRegEx.matcher(contact).find()) error.add("contact");

    // Validate email
    if (!emailRegEx.matcher(email).find()) error.add("email");

    return error;
  }

  /**
   * * Validate Reservation Details Inputs
   * * If validation failed, return valid = false
   * @param roomType String
   * @param roomID Integer
   * @param checkIn LocalDate
   * @param checkOut LocalDate
   * @return boolean
   */
  public boolean validateReservationDetails(
    String roomType,
    int roomID,
    LocalDate checkIn,
    LocalDate checkOut
  ) {
    if (
      roomType.length() == 0 || // room type should not be empty
      checkIn == null || // check in date should not be null
      checkOut == null || // check out date should not be null
      roomID == 0 // room id should not be empty or 0
    ) return false;

    return true;
  }

  /**
   * * Validate Registration Details Inputs
   * * If validation failed, append it into an arraylist that contains all the error
   * @param username String
   * @param password String
   * @param confirmPassword String
   * @return error
   */
  public ArrayList<String> registerValidation(
    String username,
    String password,
    String confirmPassword
  ) {
    // Retrieve staff objects from database
    ArrayList<Staff> staffAl = new FileService().readStaffData();
    ArrayList<String> error = new ArrayList<String>();
    boolean userExist = false;

    // validate username length
    if (username.length() < 3) error.add("username");

    // iterate through staff arraylist to check if username already exists
    for (Staff staff : staffAl) {
      if (staff.getUsername().equals(username)) userExist = true;
    }

    // validate username exists
    if (userExist) error.add("usernameExist");

    // validate password length
    if (password.length() <= 6) error.add("password");

    // compare password and confirmPassword inputs
    if (!password.equals(confirmPassword)) error.add("confirmPassword");

    return error;
  }
}
