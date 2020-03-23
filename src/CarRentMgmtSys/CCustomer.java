package CarRentMgmtSys;

import java.io.Serializable;

public class CCustomer extends Customer implements Serializable {
    private static double rate;

    public static double getRate() {
        return rate;
    }

    public static void setRate(double rate) {
        CCustomer.rate = rate;
    }

    public CCustomer (String cust_ID, String cust_name, String cust_phone, double rate){
        super(cust_ID, cust_name,cust_phone);
        this.rate = rate;
    }

    public static double getDiscount(double amount) {
        return amount - getRate();
    }
}
