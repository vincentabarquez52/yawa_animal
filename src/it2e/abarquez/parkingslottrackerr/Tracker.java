package it2e.abarquez.parkingslottrackerr;

import java.util.Scanner;

public class Tracker {
    Config conf = new Config();

    public void manageParkingRecords(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nParking Record Management:");
            System.out.println("1. View All Parking Records");
            System.out.println("2. Add a Parking Record");
            System.out.println("3. Update Exit Time");
            System.out.println("4. Delete a Parking Record");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewParkingRecords();
                    break;
                case 2:
                    addParkingRecord(scanner);
                    break;
                case 3:
                    updateExitTime(scanner);
                    break;
                case 4:
                    deleteParkingRecord(scanner);
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    public void viewParkingRecords() {
        String query = "SELECT * FROM tbl_tracker";
        String[] headers = {"ID", "Slot ID", "Vehicle ID", "Entry Time", "Exit Time"};
        String[] columns = {"t_id", "s_id", "v_id", "t_entry_time", "t_exit_time"};
        conf.viewRecords(query, headers, columns);
    }

    public void addParkingRecord(Scanner scanner) {
        
        Slots sl = new Slots();
        sl.viewSlots();
        int slotId;
        do {
            System.out.print("Enter Slot ID: ");
            slotId = scanner.nextInt();
            if (!conf.doesIDExist("tbl_slot", "s_id", slotId)) {
                System.out.println("Slot ID doesn't exist. Please try again.");
            }
        } while (!conf.doesIDExist("tbl_slot", "s_id", slotId));
        
        Vehicles vh = new Vehicles();
        vh.viewVehicles();
        int vehicleId;
        do {
            System.out.print("Enter Vehicle ID: ");
            vehicleId = scanner.nextInt();
            if (!conf.doesIDExist("tbl_vehicle", "v_id", vehicleId)) {
                System.out.println("Vehicle ID doesn't exist. Please try again.");
            }
        } while (!conf.doesIDExist("tbl_vehicle", "v_id", vehicleId));

        scanner.nextLine();
        System.out.print("Enter Entry Time (MMM DD, YYYY HH:MM): ");
        String entryTime = scanner.nextLine();

        String sql = "INSERT INTO tbl_tracker (s_id, v_id, t_entry_time) VALUES (?, ?, ?)";
        conf.addRecord(sql, slotId, vehicleId, entryTime);
    }


    public void updateExitTime(Scanner scanner) {
        System.out.print("Enter Parking Record ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!conf.doesIDExist("tbl_tracker", "t_id", id)) {
            System.out.println("Parking Record ID not found.");
            return;
        }

        System.out.print("Enter Exit Time (MMM DD, YYYY HH:MM): ");
        String exitTime = scanner.nextLine();

        String sql = "UPDATE tbl_tracker SET t_exit_time = ? WHERE t_id = ?";
        conf.updateRecord(sql, exitTime, id);
    }

    public void deleteParkingRecord(Scanner scanner) {
        System.out.print("Enter Parking Record ID to Delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM tbl_tracker WHERE t_id = ?";
        conf.deleteRecord(sql, id);
    }
}
