package main.java;

import java.io.IOException;
import java.util.ArrayList;
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
}
