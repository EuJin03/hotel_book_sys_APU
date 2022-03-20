package main.java;

import java.io.IOException;
import java.util.ArrayList;
import main.java.entities.Room;
import main.java.entities.Staff;
import main.java.util.FileService;

public class Seeder {

  public void seedStaff() throws IOException {
    ArrayList<Staff> al = new ArrayList<Staff>();
    Staff staff1 = new Staff(1, "eugene", "qwerty123");
    Staff staff2 = new Staff(2, "elonmusk", "qwerty123");
    Staff staff3 = new Staff(3, "jeffbezos", "qwerty132");
    Staff staff4 = new Staff(4, "stevejob", "qwerty123");
    Staff staff5 = new Staff(5, "warrenbuffet", "qwerty123");
    Staff staff6 = new Staff(0, "", "");

    al.add(staff1);
    al.add(staff2);
    al.add(staff3);
    al.add(staff4);
    al.add(staff5);
    al.add(staff6);
    new FileService().writeStaffData(al);
  }

  public void seedRoom() throws IOException {
    ArrayList<Room> al = new ArrayList<Room>();
    Room Room1 = new Room(100, "Jungle", false);
    Room Room2 = new Room(101, "Jungle", false);
    Room Room3 = new Room(102, "Jungle", false);
    Room Room4 = new Room(103, "Jungle", false);
    Room Room5 = new Room(104, "Jungle", false);
    Room Room6 = new Room(105, "Jungle", false);
    Room Room7 = new Room(106, "Jungle", false);
    Room Room8 = new Room(107, "Jungle", false);
    Room Room9 = new Room(108, "Jungle", false);
    Room Room10 = new Room(109, "Jungle", false);
    Room Room11 = new Room(200, "Sea", false);
    Room Room12 = new Room(201, "Sea", false);
    Room Room13 = new Room(202, "Sea", false);
    Room Room14 = new Room(203, "Sea", false);
    Room Room15 = new Room(204, "Sea", false);
    Room Room16 = new Room(205, "Sea", false);
    Room Room17 = new Room(206, "Sea", false);
    Room Room18 = new Room(207, "Sea", false);
    Room Room19 = new Room(208, "Sea", false);
    Room Room20 = new Room(209, "Sea", false);

    al.add(Room1);
    al.add(Room2);
    al.add(Room3);
    al.add(Room4);
    al.add(Room5);
    al.add(Room6);
    al.add(Room7);
    al.add(Room8);
    al.add(Room9);
    al.add(Room10);
    al.add(Room11);
    al.add(Room12);
    al.add(Room13);
    al.add(Room14);
    al.add(Room15);
    al.add(Room16);
    al.add(Room17);
    al.add(Room18);
    al.add(Room19);
    al.add(Room20);

    new FileService().writeRoomData(al);
  }
}
