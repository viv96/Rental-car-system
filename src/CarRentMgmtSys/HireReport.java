package CarRentMgmtSys;

import java.io.Serializable;

public class HireReport implements Serializable {
    private static String fromDate;
    private static String toDate;
    private static String customer;
    private static String vehicle;

    public HireReport(String fromDate, String toDate, String customer, String vehicle) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.customer = customer;
        this.vehicle = vehicle;
    }
}
