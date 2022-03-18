package main.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import main.java.entities.Reservation;
import main.java.entities.Room;
import main.java.entities.Staff;

public class FileService {

  public File staffFile = new File("src/main/resources/data/Staff.txt");
  public File roomFile = new File("src/main/resources/data/Room.txt");
  public File reservationFile = new File(
    "src/main/resources/data/Reservation.txt"
  );

  @SuppressWarnings("unchecked")
  public ArrayList<Staff> readStaffData() {
    ArrayList<Staff> staffAl = new ArrayList<Staff>();
    ObjectInputStream ois = null;

    try {
      if (staffFile.isFile()) {
        ois = new ObjectInputStream(new FileInputStream(staffFile));
        staffAl = (ArrayList<Staff>) ois.readObject();
        ois.close();
      }
      return staffAl;
    } catch (Exception e) {
      e.printStackTrace();
      return staffAl;
    }
  }

  public void writeStaffData(ArrayList<Staff> staffAl) throws IOException {
    ObjectOutputStream oos = null;
    oos = new ObjectOutputStream(new FileOutputStream(staffFile));
    oos.writeObject(staffAl);
    oos.close();
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Room> readRoomData() {
    ArrayList<Room> roomAl = new ArrayList<Room>();
    ObjectInputStream ois = null;

    try {
      if (roomFile.isFile()) {
        ois = new ObjectInputStream(new FileInputStream(roomFile));
        roomAl = (ArrayList<Room>) ois.readObject();
        ois.close();
      }
      return roomAl;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void writeRoomData(ArrayList<Room> roomAl) throws IOException {
    ObjectOutputStream oos = null;
    oos = new ObjectOutputStream(new FileOutputStream(roomFile));
    oos.writeObject(roomAl);
    oos.close();
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Reservation> readReservationData() {
    ArrayList<Reservation> reservationAl = new ArrayList<Reservation>();
    ObjectInputStream ois = null;

    try {
      if (reservationFile.isFile()) {
        ois = new ObjectInputStream(new FileInputStream(reservationFile));
        reservationAl = (ArrayList<Reservation>) ois.readObject();
        ois.close();
      }
      return reservationAl;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void writeReservationData(ArrayList<Reservation> reservationAl)
    throws IOException {
    ObjectOutputStream oos = null;
    oos = new ObjectOutputStream(new FileOutputStream(reservationFile));
    oos.writeObject(reservationAl);
    oos.close();
  }
}
