
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
    
    private void viewCustomers() {
        String votersQuery = "SELECT * FROM tbl_customer";
        String[] votersHeaders = {"ID", "fname", "address", "phone_number", "Status"};
        String[] votersColumns = {"c_id", "c_Slot_ID", "c_fname", "c_lname", "c_entry_time", "c_exit_time", "c_status"};
        config conf = new config();
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
        
    }
    
     private void updateCustomer(){
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter ID to update: ");
         int id = sc.nextInt();
         
         System.out.println("New First Name: ");
         String fname = sc.next();
         
         System.out.println("New Address: ");
         String address = sc.next();
         
         System.out.println("New Phone Number: ");
         int phone_number = sc.nextInt();
         
         System.out.println("New Status: ");
         String status = sc.next();
         
         
         String qry = "UPDATE tbl_customers SET c_fname = ?, c_address = ?, c_phone_number = ?, c_status = ? WHERE C_id = ?";
         
         config conf = new config();
         conf.updateRecord(qry, fname, address, phone_number, status, id);
         
     }
    
     private void deleteCustomer(){
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter ID to delete: ");
         int id = sc.nextInt();
         
         String qry = "DELETE FROM tbl_customers WHERE c_id = ?";
         config conf = new config();
         conf.deleteRecord(qry, id);
         
     }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resp;
        
        do{
        System.out.println("1 ADD");
        System.out.println("2 VIEW");
        System.out.println("3 UPDATE");
        System.out.println("4 DELETE");
        System.out.println("5 EXIT");

        System.out.println("Enter action:");
        int action = sc.nextInt();
        IT2EAbarquezParkingSlotTrackerr test = new IT2EAbarquezParkingSlotTrackerr();
        switch (action) {
            
        case 1:
                 test.addCustomer();
                 
                 break;
                 
                 
            case 2:
                test.viewCustomers();
                
                
            break;
            
            
            case 3:
                 test.viewCustomers();
                test.updateCustomer();
                
                
            break;
           
            case 4:
                  test.viewCustomers();
                test.deleteCustomer();
                test.viewCustomers();
                break;
     
            
        }
        System.out.print("Continue? ");
            resp = sc.next();

        }while(resp.equalsIgnoreCase("yes"));
            System.out.println("Thank You!");
        

    }

 
}




