
package it2e.abarquez.parkingslottrackerr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class IT2EAbarquezParkingSlotTrackerr {
    

    public void addCustomer() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Customer Slot ID: ");
        String c_slotid = sc.next();
        System.out.print("Customer First Name: ");
        String c_fname = sc.next();
        System.out.print("Customer Last Name: ");
        String c_lname = sc.next();
        System.out.print("Customer Entry Time: ");
        String c_intime = sc.next();
        System.out.print("Customer Exit Time: ");
        String c_extime = sc.next();

        String sql = "INSERT INTO tbl_tracker (c_id, c_Slot_ID, c_fname, c_lname, c_entry_time, c_exit_time, c_status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        conf.addRecord(sql, c_slotid, c_fname, c_lname, c_intime, c_extime, "active");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 ADD");
        System.out.println("2 VIEW");
        System.out.println("3 UPDATE");
        System.out.println("4 DELETE");
        System.out.println("5 EXIT");

        System.out.println("Enter action:");
        int action = sc.nextInt();

        switch (action) {
            case 1:
                IT2EAbarquezParkingSlotTrackerr test = new IT2EAbarquezParkingSlotTrackerr();
                test.addCustomer();
                break;
            
        }
    }
}



