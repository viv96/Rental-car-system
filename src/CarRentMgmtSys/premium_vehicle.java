package CarRentMgmtSys;

public class premium_vehicle extends vehicle {

    private int dist_allow;         //distance allowance for premium vehicles
    private int serv_read;          //odometer read counting for service fo the vehicle
    private int last_serv_read;     //odometer reading from last service
    private double extra_charge;    //extra charges beyond allowance

    public int getDist_allow() {
        return dist_allow;
    }

    public void setDist_allow(int dist_allow) {
        this.dist_allow = dist_allow;
    }

    public int getServ_read() {
        return serv_read;
    }

    public void setServ_read(int serv_read) {
        this.serv_read = serv_read;
    }

    public int getLast_serv_read() {
        return last_serv_read;
    }

    public void setLast_serv_read(int last_serv_read) {
        this.last_serv_read = last_serv_read;
    }

    public double getExtra_charge() {
        return extra_charge;
    }

    public void setExtra_charge(int extra_charge) {
        this.extra_charge = extra_charge;
    }

    public premium_vehicle(String vehicleID, char vehicleStatus, String vehicleModel, double dailyRate, int odometer, int dist_allow, int serv_read, int last_serv_read, double extra_charge) {
        super(vehicleID, vehicleStatus, vehicleModel, dailyRate, odometer);
        this.dist_allow = dist_allow;
        this.serv_read = serv_read;
        this.last_serv_read = last_serv_read;
        this.extra_charge = extra_charge;
    }

    @Override
    public boolean hire(String hirerID) {
        if (getOdometer() >= getLast_serv_read()){
            System.out.println("Vehicle cannot be hired, due for service.");
            return false;
        }
        else{
            return super.hire(hirerID);
        }
    }

    @Override
    public double hireComplete(int odometer) {
        int initial_read = getOdometer();
        double charge = super.hireComplete(odometer);
        System.out.println(initial_read);
        System.out.println(getOdometer());
        System.out.println(getDist_allow());
        if (charge > 0.0) {
            if ((getOdometer() - initial_read - getDist_allow())>0) {
                double final_charge = charge + (((getOdometer() - initial_read - getDist_allow()) * 0.10));
                if (final_charge < 0) {
                    return Math.abs(final_charge);
                } else {
                    return final_charge;
                }
            } else {
                return charge;
            }
        }else{
            return 0.0;
        }
    }

    @Override
    public boolean serviceComplete(int odometer) {
        if (super.serviceComplete(odometer)){
            setLast_serv_read(odometer+getServ_read());
            return true;
        } else {
            System.out.println(getVehicleID()+" was not in service.");
            return false;
        }
    }
}

