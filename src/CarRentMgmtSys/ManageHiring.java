package CarRentMgmtSys;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Serializable;

public class ManageHiring implements Serializable {
//    private static final long serialVersionUID = 1L;
    private static Scanner sc = new Scanner(System.in);

    private static ArrayList<Vehicle> vehs = new ArrayList<Vehicle>();
    private static Vehicle vehs_list;
    private static String vehs_ID;
    private static int vehs_index;
    private static String vehs_file = "vehicle.txt";
    private static int odometer;

    private static ArrayList<Customer> cust = new ArrayList<Customer>();
    private static Customer cust_list;
    private static String cust_ID;
    private static int cust_index;
    private static String cust_file = "customer.txt";


    private static ArrayList<HireDetails> cust_vehs = new ArrayList<HireDetails>();
    private static String hirerID;
    private static String hireVehicleID;
    private static String cust_vehs_file = "rentDirectory.txt";

    private static ArrayList<HireReport> report = new ArrayList<HireReport>();
    private static String report_file = "hireReport.txt";

    private static FileSys fs = new FileSys();

    private static Boolean cust_matching() throws IOException, ClassNotFoundException{
        cust = fs.readCust(cust_file);
        System.out.print("\nEnter Customer ID       : ");
        cust_ID = sc.next();
        if (cust_ID.length() != 6) {
            System.out.println("Customer ID must be in 6 digits.");
            cust_matching();
        } else if (cust_ID.charAt(0)!='C'){
            System.out.println("Customer ID must begin with 'C'.");
            cust_matching();
        }
        for (int i = 0; i < cust.size(); i++) {
            if (cust.get(i).getCust_ID().equals(cust_ID)) {
                cust_index = i;
                return true;
            }
        }
        return false;
    }

    private static Boolean vehs_matching() throws IOException, ClassNotFoundException {
        vehs = fs.readVehs(vehs_file);
        System.out.print("\nEnter vehicle ID         : ");
        vehs_ID = sc.next();
        if (vehs_ID.length() != 6) {
            System.out.println("Vehicle ID must be in 6 digits.");
            vehs_matching();
        }
        for (int i = 0; i < vehs.size(); i++) {
            if (vehs.get(i).getVehicleID().equals(vehs_ID)) {
                vehs_index = i;
                return true;
            }
        }
        return false;
    }

    public static void addVehs() throws IOException, ClassNotFoundException {
        vehs = fs.readVehs(vehs_file);
        cust = fs.readCust(cust_file);
        cust_vehs = fs.readHireDetails(cust_vehs_file);
        report = fs.readRentHistory(report_file);
            System.out.println("\nType of vehicle:");
            System.out.println("Premium Vehicle     1");
            System.out.println("Vehicle             2");
            System.out.print("Your choice :       ");
            String reply = sc.next();
            if (vehs_matching()) {
                System.out.println("This vehicle is already present in the system.");
                addVehs();
            }
            if (!reply.equals("1") && !reply.equals("2")) {
                System.out.println("Please enter a valid option.");
                addVehs();
            }
            System.out.print("Enter vehicle model              : ");
            String vehs_model = sc.next();
            System.out.print("Enter Daily rate of the vehicle  : ");
            double vehs_dailyRate = sc.nextDouble();
            System.out.print("Enter odometer reading           : ");
            int vehs_odometer = sc.nextInt();
            char vehs_status = 'A';
            if (reply.equals("1")) {
                System.out.print("Enter mileage allowance           : ");
                int pre_vehs_allow = sc.nextInt();
                System.out.print("Enter service mileage range       : ");
                int pre_vehs_serv = sc.nextInt();
                vehs.add(new premiumVehicle(vehs_ID, vehs_status, vehs_model, vehs_dailyRate, vehs_odometer, pre_vehs_allow, pre_vehs_serv, vehs_odometer + pre_vehs_serv, 0.10));
            } else if (reply.equals("2")) {
                vehs.add(new Vehicle(vehs_ID, vehs_status, vehs_model, vehs_dailyRate, vehs_odometer));
            }
            fs.writeVehs(vehs, vehs_file);
            System.out.println("Vehicle successfully added to the system.");
    }

    public static void addCust() throws IOException, ClassNotFoundException {
        vehs = fs.readVehs(vehs_file);
        cust = fs.readCust(cust_file);
        cust_vehs = fs.readHireDetails(cust_vehs_file);
        report = fs.readRentHistory(report_file);
        System.out.println("Please chose the type of the customer.");
        System.out.println("Individual Customer :       1");
        System.out.println("Corporate Customer  :       2\n");
        System.out.print("Your choice: ");
        String reply = sc.next();
        System.out.print("\n");
        if (!reply.equals("1") && !reply.equals("2")) {
            System.out.println("Please enter a valid option.");
            addCust();
        }
        if (cust_matching()) {
            System.out.println("This customer is already present in the system.");
            addCust();
        }
        System.out.print("Enter Customer Name        : ");
        String cust_name = sc.next();
        System.out.print("Enter Customer Phone No.   : ");
        String cust_phone = sc.next();
        System.out.print("Enter mileage for customer : ");
        int cust_mileage = sc.nextInt();
        if (reply.equals("1")) {
            cust.add(new ICustomer(cust_ID, cust_name, cust_phone, cust_mileage));
            fs.writeCust(cust, cust_file);
        } else if (reply.equals("2")) {
            System.out.print("\nPlease enter discount rate (%): ");
            double cust_rate = sc.nextDouble();
            cust.add(new CCustomer(cust_ID, cust_name, cust_phone, cust_rate));
            fs.writeCust(cust, cust_file);
        } else {
            System.out.println("Please enter a valid reply.");
        }
        System.out.println("Customer successfully added to the system.");
    }

    public static void displayVehs() throws IOException, ClassNotFoundException {
        vehs = fs.readVehs(vehs_file);
        System.out.println("Display all cars            1");
        System.out.println("Display cars with range     2");
        System.out.print("Your choice:      ");
        String reply = sc.next();
        if (reply.equals("1")) {
            for (int j = 0; j < vehs.size(); j++) {
                System.out.println(vehs.get(j).toString());
            }
        } else {
            System.out.print("\nStarting range: ");
            int range_start = sc.nextInt();
            System.out.print("Ending range: ");
            int range_end = sc.nextInt();
            System.out.print("\n");
            for (int i = 0; i < vehs.size(); i++) {
                if ((range_start <= vehs.get(i).getDailyRate() && vehs.get(i).getDailyRate() <= range_end) || (range_end <= vehs.get(i).getDailyRate() && vehs.get(i).getDailyRate() <= range_start)) {
                    System.out.println(vehs.get(i).toString());
                }
            }
        }
    }

    public static void hireVehs() throws IOException, ClassNotFoundException {
        vehs = fs.readVehs(vehs_file);
        cust = fs.readCust(cust_file);
        cust_vehs = fs.readHireDetails(cust_vehs_file);
        report = fs.readRentHistory(report_file);
        if (!cust_matching()) {
            System.out.println("Customer not found in the system.");
        } else if (!vehs_matching()) {
            System.out.println("Vehicle not found in the system.");
        } else {
            hirerID = cust.get(cust_index).getCust_ID();
            if (cust.get(cust_index) instanceof ICustomer) {
                for (int i = 0; i < cust_vehs.size(); i++) {
                    if (cust_vehs.size() <= 0){
                        break;
                    }
                    if (cust_vehs.get(i).getCustID().equals(hirerID)) {
                        System.out.println("Individual customers cannot hire more than one vehicle at a time.");
                        return;
                    }
                }
            }
            hireVehicleID = vehs.get(vehs_index).getVehicleID();
            if (vehs.get(vehs_index).hire(hirerID)) {
                System.out.println("Vehicle " + hireVehicleID + " has been successfully hired by " + hirerID + ".");
                cust_vehs.add(new HireDetails(hirerID, hireVehicleID));
                fs.writeHireDetails(cust_vehs, cust_vehs_file);
            }
        }
    }

    public static void completeHire() throws IOException, ClassNotFoundException {
        vehs = fs.readVehs(vehs_file);
        cust = fs.readCust(cust_file);
        cust_vehs = fs.readHireDetails(cust_vehs_file);
        report = fs.readRentHistory(report_file);
        if (!cust_matching()) {
            System.out.println("Customer not found in the system.");
        } else if (!vehs_matching()) {
            System.out.println("Vehicle not found in the system.");
        } else {
            hirerID = cust.get(cust_index).getCust_ID();
            for (int i = 0; i<cust_vehs.size();i++ ){
                if(cust_vehs.get(i).getCustID() != hirerID){
                    System.out.println("This vehicle was not hired by "+hirerID);
                    return;
                }
            }
            hireVehicleID = vehs.get(vehs_index).getVehicleID();
            if (vehs.get(vehs_index).getVehicleStatus() == 'H') {
                System.out.print("Please enter odometer reading for " + hireVehicleID + ":  ");
                odometer = sc.nextInt();
                double final_charge = vehs.get(vehs_index).hireComplete(odometer);
                if (final_charge != 0) {
                    if (cust.get(cust_index) instanceof ICustomer) {
                        ICustomer.setMileage(vehs.get(vehs_index).getOdo_diff() + ICustomer.getMileage());
                        final_charge = ICustomer.getDiscount(final_charge);
                    } else {
                        final_charge = CCustomer.getDiscount(final_charge);
                    }
                    System.out.println("The final charge is " + final_charge);
                    for (int i = 0; i<cust_vehs.size();i++ ){
                        if(cust_vehs.get(i).getCustID() == hirerID){
                            cust_vehs.remove(i);
                        }
                    }
                    fs.writeHireDetails(cust_vehs, cust_vehs_file);

                    String from_date = vehs.get(vehs_index).getStringD1();
                    String to_date = vehs.get(vehs_index).getStringD2();
                    String customer = cust.get(cust_index).getCust_ID();
                    String vehicle = vehs.get(vehs_index).getVehicleID();
                    report.add(new HireReport(from_date, to_date, customer,vehicle));
                }
            } else {
                System.out.println(hireVehicleID + " is not hired");
            }
        }
    }

    public static void serviceVehs() throws IOException, ClassNotFoundException {
        vehs = fs.readVehs(vehs_file);
        cust = fs.readCust(cust_file);
        cust_vehs = fs.readHireDetails(cust_vehs_file);
        report = fs.readRentHistory(report_file);
        if (!vehs_matching()) {
            System.out.println("Vehicle not found in the system.");
        } else {
            vehs.get(vehs_index).service();
        }
    }

    public static void completeService() throws IOException, ClassNotFoundException {
        vehs = fs.readVehs(vehs_file);
        cust = fs.readCust(cust_file);
        cust_vehs = fs.readHireDetails(cust_vehs_file);
        report = fs.readRentHistory(report_file);
        if (vehs.size() == 0) {
            System.out.println("There are no vehicle in the system to service.");
//            continue;
        }
        if (!vehs_matching()) {
            System.out.println("Vehicle not found in the system.");
        } else {
            if (vehs.get(vehs_index).getVehicleStatus() == 'S') {
                System.out.print("Please enter the new odometer reading :  ");
                odometer = sc.nextInt();
                if (vehs.get(vehs_index).serviceComplete(odometer)) {
                    System.out.println("Service is now complete for " + hireVehicleID);
                }
            } else {
                System.out.println(hireVehicleID + " was not in service");
            }
        }
    }

    public static void report() throws IOException, ClassNotFoundException {
        report = fs.readRentHistory(report_file);
        for (int i = 0; i<report.size();i++){
            System.out.println(report.get(i));
        }
    }

    public static void main(String[] args) throws InputMismatchException, IOException, ClassNotFoundException {

        String reply;
        String menu_reply;
        boolean exit_reply = true;

        vehs = fs.readVehs(vehs_file);
        cust = fs.readCust(cust_file);
        cust_vehs = fs.readHireDetails(cust_vehs_file);
        report = fs.readRentHistory(report_file);

        do {
            if (exit_reply) {
                try {
                    System.out.println("\n\t=-=-=-=-=-=-=-=-=-=-= Welcome to the vehicle system =-=-=-=-=-=-=-=-=-=-=\t");
                    System.out.println("Add Vehicles              1");
                    System.out.println("Add Customers             2");
                    System.out.println("Display available cars    3");
                    System.out.println("Hire Vehicle              4");
                    System.out.println("Complete hire             5");
                    System.out.println("Service Vehicle           6");
                    System.out.println("Complete service          7");
                    System.out.println("Rental History            8");
                    System.out.println("Exit                      9");
                    System.out.print("\nYour choice :             ");
                    menu_reply = sc.next();
                } catch (InputMismatchException e) {
                    System.out.print(e);
                    menu_reply = sc.next();
                }
            } else {
                continue;
            }
            vehs = fs.readVehs(vehs_file);
            cust = fs.readCust(cust_file);

            if ((menu_reply.equals("3") || menu_reply.equals("4") || menu_reply.equals("5") || menu_reply.equals("6") || menu_reply.equals("7")) && vehs.size() == 0) {
                System.out.println("There are no Vehicles in the system.");
                continue;
            }

            if ((menu_reply.equals("4") || menu_reply.equals("5")) && (cust.size() == 0)) {
                System.out.println("There are no customers in the system.");
                continue;
            }

            switch (menu_reply) {
                case "1406": {
                    cust = fs.readCust(cust_file);
                    for (int j = 0; j < cust.size(); j++) {
                        System.out.println(cust.get(j).toString());
                    }
                    break;
                }
                case "1": {   //add vehicles
                    addVehs();
                    break;
                }
                case "2": {   //add customers
                    addCust();
                    break;
                }
                case "3": { //Display Vehicles
                    displayVehs();
                    break;
                }
                case "4": { //hire
                    hireVehs();
                    break;
                }
                case "5": { //Hire complete
                    completeHire();
                    break;
                }
                case "6": { //Service
                    serviceVehs();
                    break;
                }
                case "7": { //Service Complete
                    completeService();
                    break;
                }
                case "8": { //Hire Report
                    report();
                    break;
                }
                case "9": {
                    System.out.println("Thank you for using our System.");
                    break;
                }
                default: {
                    System.out.println("Please enter a valid option:");
                    continue;
                }
            }
            fs.writeCust(cust, cust_file);
            fs.writeVehs(vehs, vehs_file);
            if (menu_reply.equals("9")) {
                break;
            }
            System.out.print("\nAny more Transactions? Y/N : ");
            reply = sc.next();
            if (reply.equals("Y") || reply.equals("y")) {
                exit_reply = true;
            } else if (reply.equals("N") || reply.equals("n")) {
                System.out.println("Thank you for using our System.");
                break;
            } else {
                System.out.println("Please enter a valid reply.");
                exit_reply = false;
            }
        } while (true);
    }
}