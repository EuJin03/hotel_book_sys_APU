/***********************************************************************************************************************************************************
 * FileService is an utility class that contain methods that handles READING and OVERWRITING over different Entity Databases
 ***********************************************************************************************************************************************************/

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

/**
 * @author Eugene Tin
 */
public class FileService {

  // Retrieve files from its relative path
  public File staffFile = new File("src/main/resources/data/Staff.txt");
  public File roomFile = new File("src/main/resources/data/Room.txt");
  public File reservationFile = new File(
    "src/main/resources/data/Reservation.txt"
  );

  /**
   * * Read Staff objects that are stored within the staff file
   * * By using ObjectInputStream to read a stream of serialized JVM bytecodes and convert it back to object
   * @return (ArrayList<Reservation>) staffAl
   */
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

  /**
   * * Overwrite staff objects (ArrayList) into the staff file
   * * By using ObjectOutputStream, it allows the method to serialize the arraylist of objects into JVM bytecodes
   * @param staffAl arraylist of staff instances
   * @throws IOException
   */
  public void writeStaffData(ArrayList<Staff> staffAl) throws IOException {
    try {
      ObjectOutputStream oos = null;
      oos = new ObjectOutputStream(new FileOutputStream(staffFile));
      oos.writeObject(staffAl);
      oos.close();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  /**
   * * Read Room objects that are stored within the staff file
   * * By using ObjectInputStream to read a stream of serialized JVM bytecodes and convert it back to object
   * @return (ArrayList<Room>) roomAl
   */
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

  /**
   * * Overwrite room objects (ArrayList) into the room file
   * * By using ObjectOutputStream, it allows the method to serialize the arraylist of objects into JVM bytecodes
   * @param roomAl arraylist of room instances
   * @throws IOException
   */
  public void writeRoomData(ArrayList<Room> roomAl) throws IOException {
    try {
      ObjectOutputStream oos = null;
      oos = new ObjectOutputStream(new FileOutputStream(roomFile));
      oos.writeObject(roomAl);
      oos.close();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  /**
   * * Read Reservation objects that are stored within the reservation file
   * * By using ObjectInputStream to read a stream of serialized JVM bytecodes and convert it back to object
   * @return (ArrayList<Reservation>) reservationAl
   */
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

  /**
   * * Overwrite reservation objects (ArrayList) into the reservation file
   * * By using ObjectOutputStream, it allows the method to serialize the arraylist of objects into JVM bytecodes
   * @param reservationAl arraylist of reservation instances
   * @throws IOException
   */
  public void writeReservationData(ArrayList<Reservation> reservationAl)
    throws IOException {
    try {
      ObjectOutputStream oos = null;
      oos = new ObjectOutputStream(new FileOutputStream(reservationFile));
      oos.writeObject(reservationAl);
      oos.close();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }
}
