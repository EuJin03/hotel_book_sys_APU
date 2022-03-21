/******************************************************************************************************
 A Staff class that has constructor with private variables to creates instances of staff's 
 and has methods that provide necessary data needed by the controller classes.
 ******************************************************************************************************/
package main.java.entities;

import java.io.Serializable;
import main.java.util.EncryptionService;

public class Staff implements Serializable {

  private int _id;
  private String username;
  private String password;

  public Staff(int _id, String username, String password) {
    this.set_id(_id);
    this.setUsername(username);
    this.setPassword(password);
  }

  // Getter
  public int get_id() {
    return _id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return _id + " " + username + " " + password;
  }

  // Setter
  public void set_id(int _id) {
    // retrieve previous id and increment it by 1
    this._id = _id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  private void setPassword(String password) {
    String hashedPassword = EncryptionService.generateSecurePassword(password);
    this.password = hashedPassword;
  }
}
