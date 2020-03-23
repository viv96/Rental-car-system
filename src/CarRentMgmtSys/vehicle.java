package CarRentMgmtSys;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Vehicle implements Serializable{

    private static Scanner sc = new Scanner(System.in);
    private String vehicleID;
    private char vehicleStatus;
    private String hirerID;
    private String vehicleModel;
    private int odometer;
    private double dailyRate;
    private int prev_odo;
    private int odo_diff;
    DateTime d1;
    DateTime d2;

    public int getOdo_diff() {
        return odo_diff;
    }

    public void setOdo_diff(int odo_diff) {
        this.odo_diff = odo_diff;
    }

    public int getPrev_odo() {
        return prev_odo;
    }

    public void setPrev_odo(int prev_odo) {
        this.prev_odo = prev_odo;
    }

    public DateTime getD1() {
        return d1;
    }

    public String getStringD1() { return d1.toString();}
    public String getStringD2() { return d2.toString();}


    public void setD1(DateTime d1) {
        this.d1 = d1;
    }

    public DateTime getD2() {
        return d2;
    }

    public void setD2(DateTime d2) {
        this.d2 = d2;
    }

    public Vehicle(String vehicleID, char vehicleStatus, String vehicleModel, double dailyRate, int odometer) { //constructor method taking details fo the vehicles
        this.vehicleID = vehicleID;
        this.vehicleStatus = vehicleStatus;
        this.vehicleModel = vehicleModel;
        this.dailyRate = dailyRate;
        this.odometer = odometer;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public char getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(char vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getHirer() {
        return hirerID;
    }

    public void setHirer(String hirer) {
        this.hirerID = hirer;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    void print() {
        System.out.println("\t*************** Vehicle Details ***************");
        System.out.println(DateTime.getCurrentTime());
        System.out.println("Vehicle ID: " + this.vehicleID + "\tDescription: " + this.vehicleModel + "\tStatus: " + this.vehicleStatus + "\tDaily Rate: " + this.dailyRate + "\tOdometer Reading: " + this.odometer);
    }

    public boolean hire(String hirerID) {
        setHirer(hirerID);
        setPrev_odo(getOdometer());
        if (getVehicleStatus() == 'A') {
            setVehicleStatus('H');
            setD1(new DateTime());
            System.out.println("Hirer: " + this.hirerID + " \tDate/time of the hire: " + DateTime.getCurrentTime()+"\n");
            return true;
        } else {
            System.out.println("Sorry, the vehicle cannot be hired.");
            return false;
        }
    }

    public double hireComplete(int odometer) {
        if (getVehicleStatus() == 'H') {
            if (getOdometer() <= odometer) {
                setOdometer(odometer);
                setOdo_diff(getOdometer()-getPrev_odo());
            } else {
                System.out.print("Please enter the correct Odometer reading: ");
                odometer = sc.nextInt();
                hireComplete(odometer);
            }
            System.out.print("Enter the days    :");
            int days = sc.nextInt();
            System.out.print("Enter the hours   :");
            int hours = sc.nextInt();
            System.out.print("Enter the minutes :");
            int mins = sc.nextInt();
            DateTime.setAdvance(days, hours, mins);
            setD2(new DateTime());
            System.out.println("Hirer : " + this.hirerID + "  Date/time of the hire : " + DateTime.getCurrentTime()+"\n");
            setVehicleStatus('A');
            return DateTime.diffDays(d2, d1) * this.dailyRate;
        }
        return 0.0;
    }

    public boolean service() {
        if (getVehicleStatus() == 'A') {
            setVehicleStatus('S');
            System.out.println(getVehicleID() + " is now in Service.");
            return true;
        } else {
            System.out.println("Sorry " + getVehicleID() + " is not available for service.");
            return false;
        }
    }

    public boolean serviceComplete(int odometer) {
        if (getVehicleStatus() == 'S') {
            if (getOdometer() < odometer) {
                setOdometer(odometer);
            } else {
                System.out.print("\nPlease enter the correct Odometer reading.");
                odometer = sc.nextInt();
                serviceComplete(odometer);
            }
            setVehicleStatus('A');
            return true;
        }
        System.out.println(getVehicleID()+" was not in service.");
        return false;
    }

    public String toString() {
        return "Vehicle ID: " + getVehicleID() + "\tDescription: " + getVehicleModel() + "\tStatus: " + getVehicleStatus() + "\tDaily Rate: " + getDailyRate() + "\tOdometer Reading: " + getOdometer();
    }
}