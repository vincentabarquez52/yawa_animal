package it2e.abarquez.parkingslottrackerr;

import java.util.Scanner;

public class Vehicles {
    Config conf = new Config();

    public void manageVehicles(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nVehicle Management:");
            System.out.println("1. View All Vehicles");
            System.out.println("2. Add a Vehicle");
            System.out.println("3. Edit Vehicle Details");
            System.out.println("4. Delete a Vehicle");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewVehicles();
                    break;
                case 2:
                    addVehicle(scanner);
                    break;
                case 3:
                    editVehicle(scanner);
                    break;
                case 4:
                    deleteVehicle(scanner);
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    public void viewVehicles() {
        String query = "SELECT * FROM tbl_vehicle";
        String[] headers = {"ID", "Plate Number", "Owner Name", "Contact Info"};
        String[] columns = {"v_id", "v_plate_number", "v_owner_name", "contact_num"};
        conf.viewRecords(query, headers, columns);
    }

    public void addVehicle(Scanner scanner) {
        System.out.print("Enter Plate Number: ");
        String plateNumber = scanner.nextLine();

        System.out.print("Enter Owner Name: ");
        String ownerName = scanner.nextLine();

        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();

        String sql = "INSERT INTO tbl_vehicle (v_plate_number, v_owner_name, contact_num) VALUES (?, ?, ?)";
        conf.addRecord(sql, plateNumber, ownerName, contactInfo);
    }

    public void editVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle ID to Update: ");
        int v_id = scanner.nextInt();
        scanner.nextLine();

        if (!conf.doesIDExist("tbl_vehicle", "v_id", v_id)) {
            System.out.println("Vehicle ID not found.");
            return;
        }

        System.out.print("Enter New Plate Number: ");
        String newPlate = scanner.nextLine();

        System.out.print("Enter New Owner Name: ");
        String newOwner = scanner.nextLine();

        System.out.print("Enter New Contact Info: ");
        String newContact = scanner.nextLine();

        String sql = "UPDATE tbl_vehicle SET v_plate_number = ?, v_owner_name = ?, contact_num = ? WHERE v_id = ?";
        conf.updateRecord(sql, newPlate, newOwner, newContact, v_id);
    }

    public void deleteVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle ID to Delete: ");
        int v_id = scanner.nextInt();

        String sql = "DELETE FROM tbl_vehicle WHERE v_id = ?";
        conf.deleteRecord(sql, v_id);
    }
}

