package it2e.abarquez.parkingslottrackerr;

import java.util.Scanner;

public class IT2EAbarquezParkingSlotTrackerr {

    Config config = new Config();
    Scanner scanner = new Scanner(System.in);

    public void addCustomer() {
        System.out.print("Customer Slot ID: ");
        String slotId = scanner.nextLine();
        System.out.print("Customer First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Customer Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Customer Entry Time: ");
        String entryTime = scanner.nextLine();
        System.out.print("Customer Exit Time: ");
        String exitTime = scanner.nextLine();

        String sql = "INSERT INTO tbl_tracker (c_Slot_ID, c_fname, c_lname, c_entry_time, c_exit_time, c_status) VALUES (?, ?, ?, ?, ?, ?)";
        config.addRecord(sql, slotId, firstName, lastName, entryTime, exitTime, "active");
    }

    private void viewCustomers() {
        String query = "SELECT * FROM tbl_tracker";
        String[] headers = {"ID", "Slot ID", "First Name", "Last Name", "Entry Time", "Exit Time", "Status"};
        String[] columns = {"c_id", "c_Slot_ID", "c_fname", "c_lname", "c_entry_time", "c_exit_time", "c_status"};
        config.viewRecords(query, headers, columns);
    }

    private void updateCustomer() {
        System.out.println("Enter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("New First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("New Last Name: ");
        String lastName = scanner.nextLine();
        System.out.println("New Entry Time: ");
        String entryTime = scanner.nextLine();
        System.out.println("New Exit Time: ");
        String exitTime = scanner.nextLine();
        System.out.println("New Status: ");
        String status = scanner.nextLine();

        String query = "UPDATE tbl_tracker SET c_fname = ?, c_lname = ?, c_entry_time = ?, c_exit_time = ?, c_status = ? WHERE c_id = ?";
        config.updateRecord(query, firstName, lastName, entryTime, exitTime, status, id);
    }

    private void deleteCustomer() {
        System.out.println("Enter ID to delete: ");
        int id = scanner.nextInt();

        String query = "DELETE FROM tbl_tracker WHERE c_id = ?";
        config.deleteRecord(query, id);
    }

    public void run() {
        
    }

    public static void main(String[] args) {
        IT2EAbarquezParkingSlotTrackerr app = new IT2EAbarquezParkingSlotTrackerr();
        Scanner scanner = new Scanner(System.in);
        
        String response;
        do {
            System.out.println("1 ADD");
            System.out.println("2 VIEW");
            System.out.println("3 UPDATE");
            System.out.println("4 DELETE");
            System.out.println("5 EXIT");
            System.out.print("Enter action: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    app.addCustomer();
                    break;
                case 2:
                    app.viewCustomers();
                    break;
                case 3:
                    app.viewCustomers(); 
                    app.updateCustomer();
                    break;
                case 4:
                    app.viewCustomers(); 
                    app.deleteCustomer();
                    break;
                case 5:
                    System.out.println("Thank You!");
                    return; 
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }

            System.out.print("Continue? (yes/no): ");
            response = scanner.next();

        } while (response.equalsIgnoreCase("yes"));
        
        System.out.println("Thank You!");
    }
}
