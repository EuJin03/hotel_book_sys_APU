/***********************************************************************************************************************************************************
 * Seeder is an data migration class that contain methods that handles data instances creation and seeding process
 ***********************************************************************************************************************************************************/

package main.java;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import main.java.entities.Reservation;
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

    al.add(staff1);
    al.add(staff2);
    al.add(staff3);
    al.add(staff4);
    al.add(staff5);

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

  public void seedReservation() throws IOException {
    ArrayList<Reservation> al = new ArrayList<Reservation>();
    Reservation Reservation1 = new Reservation(
      1000,
      "Eugene Tin",
      "020603101803",
      "0123787986",
      "eugenetin@gmail.com",
      100,
      "Jungle",
      LocalDate.parse("2022-02-20"),
      LocalDate.parse("2022-03-01")
    );
    Reservation Reservation2 = new Reservation(
      1001,
      "Wen Xuen",
      "020603101803",
      "01827272922",
      "wenxuen@gmail.com",
      101,
      "Jungle",
      LocalDate.parse("2022-01-20"),
      LocalDate.parse("2022-02-01")
    );
    Reservation Reservation3 = new Reservation(
      1003,
      "Steben Sim",
      "020603101803",
      "0123787986",
      "steven@gmail.com",
      102,
      "Jungle",
      LocalDate.parse("2022-02-10"),
      LocalDate.parse("2022-02-21")
    );
    Reservation Reservation4 = new Reservation(
      1004,
      "Jie Lin",
      "836475839265",
      "0123787986",
      "ryanlin@gmail.com",
      104,
      "Jungle",
      LocalDate.parse("2022-02-20"),
      LocalDate.parse("2022-03-01")
    );
    Reservation Reservation5 = new Reservation(
      1005,
      "Susunesen",
      "628463856294",
      "0123787986",
      "susunesan@gmail.com",
      201,
      "Sea",
      LocalDate.parse("2022-02-20"),
      LocalDate.parse("2022-02-23")
    );
    Reservation Reservation6 = new Reservation(
      1006,
      "Nyavinash a/l Balan",
      "275638563283",
      "0123787986",
      "nyavinash@gmail.com",
      202,
      "Sea",
      LocalDate.parse("2022-02-20"),
      LocalDate.parse("2022-03-01")
    );
    Reservation Reservation7 = new Reservation(
      1007,
      "Jia Hao",
      "929282827262",
      "0123787986",
      "jiahao@gmail.com",
      203,
      "Sea",
      LocalDate.parse("2022-03-10"),
      LocalDate.parse("2022-03-21")
    );
    Reservation Reservation8 = new Reservation(
      1008,
      "Kelvin Liow",
      "717263849403",
      "0123787986",
      "kelvinliow999@gmail.com",
      205,
      "Sea",
      LocalDate.parse("2022-02-20"),
      LocalDate.parse("2022-03-01")
    );
    Reservation Reservation9 = new Reservation(
      1009,
      "Liow Shan Kai",
      "283758374937",
      "0123787986",
      "liowshankai69@gmail.com",
      107,
      "Jungle",
      LocalDate.parse("2022-02-27"),
      LocalDate.parse("2022-03-11")
    );
    Reservation Reservation10 = new Reservation(
      1010,
      "Ritteechai",
      "020493837483",
      "0183216766",
      "ritteechai@gmail.com",
      209,
      "Sea",
      LocalDate.parse("2022-01-26"),
      LocalDate.parse("2022-03-20")
    );

    al.add(Reservation1);
    al.add(Reservation2);
    al.add(Reservation3);
    al.add(Reservation4);
    al.add(Reservation5);
    al.add(Reservation6);
    al.add(Reservation7);
    al.add(Reservation8);
    al.add(Reservation9);
    al.add(Reservation10);
    new FileService().writeReservationData(al);
  }
}
