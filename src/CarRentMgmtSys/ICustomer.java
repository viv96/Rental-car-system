package CarRentMgmtSys;

import java.io.Serializable;

public class ICustomer extends Customer implements Serializable { // these in

//    private char hire_status;
    private static int mileage;
    private static double mileage_count;
    private static int previous_mileage;

//    public char getHire_status() {
//        return hire_status;
//    }
//
//    public void setHire_status(char hire_status) {
//        this.hire_status = hire_status;
//    }

    public static int getPrevious_mileage(){
        return previous_mileage;
    }

    public static void setPrevious_mileage(int previous_mileage){
        ICustomer.previous_mileage = previous_mileage;
    }

    public static double getMileage_count() {
        return mileage_count;
    }

    public static void setMileage_count(double mileage_count) {
        ICustomer.mileage_count = mileage_count;
    }

    public static int getMileage() {
        return mileage;
    }

    public static void setMileage(int mileage) {
        ICustomer.mileage = mileage;
    }

    public ICustomer(String cust_ID, String cust_name, String cust_phone, int mileage){
        super(cust_ID, cust_name, cust_phone);
        this.mileage = mileage;
//        this.hire_status = hire_status;
    }

    public static void discount_calculation(){
        if (getMileage() >= (getPrevious_mileage()+100000)){
            setMileage_count (getMileage_count()+0.1);
        }
    }

    public static double getDiscount(double amount) {
        discount_calculation();
        return amount - (getMileage_count()*amount);
    }
}
