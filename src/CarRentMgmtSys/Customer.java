package CarRentMgmtSys;

import java.io.*;
import java.io.Serializable;

public class Customer implements Serializable {
    private String cust_ID;
    private String cust_name;
    private  String cust_phone;

    public String getCust_ID() {
        return cust_ID;
    }

    public void setCust_ID(String cust_ID) {
        this.cust_ID = cust_ID;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public Customer(String cust_ID, String cust_name, String cust_phone){
        this.cust_ID = cust_ID;
        this.cust_name = cust_name;
        this.cust_phone = cust_phone;
    }

    public static double getDiscount(double amount){
        return 0.0;
    }

    public String toString() {
        return "Customer ID: " + getCust_ID()+"Customer Type: " + "\t\tCustomer Name: " + getCust_name();
    }
}
