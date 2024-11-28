package it2e.abarquez.parkingslottrackerr;

import java.util.Scanner;

public class IT2EAbarquezParkingSlotTrackerr {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            
            while (!exit) {
                System.out.println("\n=============================");
                System.out.println("== Parking Management System ==");
                System.out.println("1. Manage Slots");
                System.out.println("2. Manage Vehicles");
                System.out.println("3. Manage Parking Records");
                System.out.println("4. Specific Reports");
                System.out.println("5. Exit");
                System.out.print("Enter Option: ");
                
                int option = scanner.nextInt();
                scanner.nextLine();
                
                switch (option) {
                    case 1:
                        Slots slot = new Slots();
                        slot.manageSlots(scanner);
                        break;
                    case 2:
                        Vehicles car = new Vehicles();
                        car.manageVehicles(scanner);
                        break;
                    case 3:
                        Tracker tr = new Tracker();
                        tr.manageParkingRecords(scanner);
                        break;
                    case 4:
                        generateReports();
                        break;
                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        }
    }
    
    public static void generateReports() {
        System.out.println("\nPARKING SLOTS LIST");
        Slots slot = new Slots();
        slot.viewSlots();
        
        Scanner scan = new Scanner(System.in);
        Config conf = new Config();
        
        int slotId;
        do {
            System.out.print("\nEnter Slot ID for detailed report: ");
            slotId = scan.nextInt();
            if (!conf.doesIDExist("tbl_slot", "s_id", slotId)) {
                System.out.println("Slot ID doesn't exist. Try again.");
            }
        } while (!conf.doesIDExist("tbl_slot", "s_id", slotId));

        System.out.println("\n-----------------------------------------------");
        System.out.println("PARKING SLOT DETAILS");
        System.out.printf("Slot ID      : %d%n", slotId);
        System.out.printf("Slot Number  : %s%n", conf.getDataFromID("tbl_slot", slotId, "slot_number"));
        System.out.printf("Status       : %s%n", conf.getDataFromID("tbl_slot", slotId, "s_status"));
        System.out.println("-----------------------------------------------");
        
        System.out.println("Parking History: ");
        String sql = "SELECT t_id, v_id, t_entry_time, t_exit_time FROM tbl_tracker WHERE s_id = " + slotId;
        String[] Headers = {"Record ID", "Vehicle ID", "Entry Time", "Exit Time"};
        String[] Columns = {"t_id", "v_id", "t_entry_time", "t_exit_time"};
        conf.viewRecords(sql, Headers, Columns);
        

        System.out.println("=================================================");
    }

}
