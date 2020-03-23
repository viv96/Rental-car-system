package CarRentMgmtSys;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class rental_system {

    private static Scanner sc = new Scanner(System.in);

    private static ArrayList<vehicle> vehs = new ArrayList<vehicle>();
    private static int vehs_index;

    private static ArrayList<Customer> cust = new ArrayList<Customer>();
    private static int cust_index;
    private static String cust_ID;

    private static ArrayList<String> cust_vehs = new ArrayList<String>();
    private static String hirerID;
    private static String hireVehicleID;

    public static boolean empty_message(char check_type) {
        if (vehs.size() == 0 && check_type == 'v') {
            System.out.println("Sorry there are no vehicles in the System.");
            return false;
        }
        if (cust.size() == 0 && check_type == 'c') {
            System.out.println("Sorry there are no customers in the System.");
            return false;
        }
        return true;
    }

    private static Boolean cust_matching() {
        System.out.print("\nEnter Customer ID       : ");
        cust_ID = sc.next();
        if (cust_ID.length() != 6) {
            System.out.println("Customer ID must be in 6 digits.");
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

    private static Boolean vehs_matching() {
        System.out.print("\nEnter vehicle ID                      : ");
        hireVehicleID = sc.next();
        if (hireVehicleID.length() != 6){
            System.out.println("Vehicle ID must be in 6 digits.");
            vehs_matching();
        }
        for (int i = 0; i < vehs.size(); i++) {
            if (vehs.get(i).getVehicleID().equals(hireVehicleID)) {
                vehs_index = i;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws InputMismatchException {

        String reply;
        int menu_reply;
        int odometer;
        boolean exit_reply = true;

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
                    System.out.println("Exit                      8");
                    System.out.print("\nYour choice :  ");
                    menu_reply = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.print("Please enter a valid option: ");
                    menu_reply = sc.nextInt();
                }
            } else {
                continue;
            }

            switch (menu_reply) {
                case 1406: {
                    System.out.println("Customer database is getting ready...");
                    break;
                }
                case 1: {   //add vehicles
                    System.out.println("\nType of vehicle:");
                    System.out.println("Premium Vehicle     1");
                    System.out.println("Vehicle             2");
                    System.out.print("Your choice: ");
                    reply = sc.next();
                    if (vehs_matching()) {
                        System.out.println("This vehicle is already present in the system.");
                        continue;
                    }
                    System.out.print("Enter vehicle model                   : ");
                    String vehs_model = sc.next();
                    System.out.print("Enter Daily rate of the vehicle       : ");
                    double vehs_dailyRate = sc.nextDouble();
                    System.out.print("Enter odometer reading of the vehicle : ");
                    int vehs_odometer = sc.nextInt();
                    char vehs_status = 'A';
                    if (reply.equals("1")) {
                        System.out.print("Enter mileage allowance               : ");
                        int pre_vehs_allow = sc.nextInt();
                        System.out.print("Enter service mileage range           : ");
                        int pre_vehs_serv = sc.nextInt() + vehs_odometer;
                        vehs.add(new premium_vehicle(hireVehicleID, vehs_status, vehs_model, vehs_dailyRate, vehs_odometer, pre_vehs_allow, pre_vehs_serv, vehs_odometer+pre_vehs_serv, 0.10));
                    } else if (reply.equals("2")) {
                        vehs.add(new vehicle(hireVehicleID, vehs_status, vehs_model, vehs_dailyRate, vehs_odometer));
                    }
                    System.out.println("Vehicle successfully added to the system.");
                    break;
                }

                case 2: {   //add customers
                    System.out.println("Please chosse the type of the customer.");
                    System.out.println("Individual Customer :       1");
                    System.out.println("Corporate Csutomer  :       2\n");
                    System.out.print("Your choice: ");
                    reply = sc.next();
                    System.out.print("\n");
                    if (!reply.equals("1")&&!reply.equals("2")){
                        System.out.println("Please enter a valid option.");
                        continue;
                    }
                    System.out.print("Enter Customer Name     : ");
                    String cust_name = sc.next();
                    if (cust_matching()){
                        System.out.println("This customer is already present in the system.");
                        continue;
                    }
                    System.out.print("Enter Customer Phone No.: ");
                    String cust_phone = sc.next();
                    if (reply.equals("1")){
                        cust.add(new ICustomer(cust_ID, cust_name, cust_phone, 0, 'N'));
                    } else if (reply.equals("2")){
                        System.out.print("\nPlease enter the discount rate (in %) for customer: ");
                        double cust_rate = sc.nextDouble();
                        cust.add(new CCustomer(cust_ID, cust_name, cust_phone, cust_rate));
                    } else{
                        System.out.println("Enter a valid reply.");
                        continue;
                    }
                    System.out.println("Customer successfully added to the system.");
//                    System.out.print("Want to add more customers? Y/N:    ");
//                    reply = sc.next();
//                    if (reply.equals("Y")||reply.equals("y")){
//
//                    }
                    break;
                }
                case 3: { //Display Vehicles
                    if (!empty_message('v')) {
                        continue;
                    }

                    System.out.println("Display all cars            1");
                    System.out.println("Display cars with range     2");
                    System.out.print("Your choice :  ");
                    reply = sc.next();
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
                    break;
                }
                case 4: { //hire
                    if (!empty_message('v')||!empty_message('c')) {
                        continue;
                    }
                    if (!cust_matching()){
                        System.out.println("Customer not found in the system.");
                        break;
                    }
                    if (!vehs_matching()) {
                        System.out.println("Vehicle not found in the system.");
                    } else {
                        hirerID = cust.get(cust_index).getCust_ID();
                        if (vehs.get(vehs_index).hire(hirerID)) {
                            System.out.println("Vehicle " + hireVehicleID + " has been successfully hired by " + hirerID + ".");
                        }
                    }
                    break;
                }
                case 5: { //Hire complete
                    if (!empty_message('v')||!empty_message('c')) {
                        continue;
                    }
                    if (!vehs_matching()) {
                        System.out.println("Vehicle not found in the system.");
                    } else {
                        if (vehs.get(vehs_index).getVehicleStatus() == 'H') {
                            System.out.print("Please enter odometer reading for " + hireVehicleID + ":  ");
                            odometer = sc.nextInt();
                            double final_charge = vehs.get(vehs_index).hireComplete(odometer);
                            if (final_charge != 0) {
                                final_charge = cust.get(cust_index).getDiscount(final_charge);
                                System.out.println("The final charge is " + final_charge);
                            }
                        } else {
                            System.out.println(hireVehicleID + " is not hired");
                        }
                    }
                    break;
                }
                case 6: { //Service
                    if (!empty_message('v')){
                        continue;
                    }
                    if (!vehs_matching()) {
                        System.out.println("Vehicle not found in the system.");
                    } else {
                        vehs.get(vehs_index).service();
                    }
                    break;
                }
                case 7: { //Service Complete
                    if (vehs.size()==0){
                        System.out.println("There are no vehicle in the system to service.");
                        continue;
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
                    break;
                }
                case 8: {
                    System.out.println("Thank you for using our System.");
                    break;
                }
                default: {
                    System.out.println("Please enter a valid option:");
                    continue;
                }
            }
            if (menu_reply == 8) {
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