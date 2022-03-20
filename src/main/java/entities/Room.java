package main.java.entities;

import java.io.Serializable;

public class Room implements Serializable {

  private int _id;
  private String type;
  private boolean occupied;

  public Room(int _id, String type, boolean occupied) {
    this.set_id(_id);
    this.setType(type); // Sea or Jungle View
    this.setOccupied(occupied);
  }

  // Getter
  public int get_id() {
    return _id;
  }

  public String getType() {
    return type;
  }

  public boolean isOccupied() {
    return occupied;
  }

  @Override
  public String toString() {
    return _id + " " + type + " " + occupied;
  }

  // Setter
  public void set_id(int _id) {
    this._id = _id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setOccupied(boolean occupied) {
    this.occupied = occupied;
  }
}
