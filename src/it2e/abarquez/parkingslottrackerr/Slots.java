package it2e.abarquez.parkingslottrackerr;

import java.util.Scanner;

public class Slots {
    Config conf = new Config();

    public void manageSlots(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nSlot Management:");
            System.out.println("1. View All Slots");
            System.out.println("2. Add a Slot");
            System.out.println("3. Edit Slot Status");
            System.out.println("4. Delete a Slot");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewSlots();
                    break;
                case 2:
                    addSlot(scanner);
                    break;
                case 3:
                    editSlotStatus(scanner);
                    break;
                case 4:
                    deleteSlot(scanner);
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    public void viewSlots() {
        String query = "SELECT * FROM tbl_slot";
        String[] headers = {"ID", "Slot Number", "Status"};
        String[] columns = {"s_id", "slot_number", "s_status"};
        conf.viewRecords(query, headers, columns);
    }

    public void addSlot(Scanner scanner) {
        System.out.print("Enter Slot Number: ");
        String slotNumber = scanner.nextLine();

        System.out.print("Enter Slot Status (available/occupied): ");
        String s_status = scanner.nextLine();

        String sql = "INSERT INTO tbl_slot (slot_number, s_status) VALUES (?, ?)";
        conf.addRecord(sql, slotNumber, s_status);
    }

    public void editSlotStatus(Scanner scanner) {
        System.out.print("Enter Slot ID to Update: ");
        int s_id = scanner.nextInt();
        scanner.nextLine();

        if (!conf.doesIDExist("tbl_slot", "s_id", s_id)) {
            System.out.println("Slot ID not found.");
            return;
        }

        System.out.print("Enter New Status (available/occupied): ");
        String newStatus = scanner.nextLine();

        String sql = "UPDATE tbl_slot SET s_status = ? WHERE s_id = ?";
        conf.updateRecord(sql, newStatus, s_id);
    }

    public void deleteSlot(Scanner scanner) {
        System.out.print("Enter Slot ID to Delete: ");
        int s_id = scanner.nextInt();

        String sql = "DELETE FROM tbl_slot WHERE s_id = ?";
        conf.deleteRecord(sql, s_id);
    }
}
