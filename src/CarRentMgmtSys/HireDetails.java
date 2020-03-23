package CarRentMgmtSys;

import java.io.Serializable;

public class HireDetails implements Serializable {
    private static String custID;
    private static String vehsID;

    public static String getCustID() {
        return custID;
    }

    public static void setCustID(String custID) {
        HireDetails.custID = custID;
    }

    public static String getVehsID() {
        return vehsID;
    }

    public static void setVehsID(String vehsID) {
        HireDetails.vehsID = vehsID;
    }

    public HireDetails (String custID, String vehsID){
        this.custID = custID;
        this.vehsID = vehsID;
    }
}
