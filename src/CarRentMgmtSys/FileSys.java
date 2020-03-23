package CarRentMgmtSys;

import java.io.*;
import java.util.ArrayList;
import java.io.Serializable;

public class FileSys implements Serializable {

    public static File file;

    public static ArrayList<Customer> readCust(String fname) throws FileNotFoundException, IOException, ClassNotFoundException{
        file = new File(fname);
        ArrayList<Customer> cust = new ArrayList<Customer>();
        if (file.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname));
            cust = (ArrayList<Customer>) in.readObject();
            in.close();
        }
        return cust ;
    }

    public static void writeCust(ArrayList<Customer> cust, String fname) throws IOException{
        FileOutputStream file = new FileOutputStream(fname);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(cust);
        out.close();
        file.close();
    }

    public static ArrayList<Vehicle> readVehs(String fname) throws FileNotFoundException, IOException, ClassNotFoundException{
        file = new File(fname);
        ArrayList<Vehicle> vehs = new ArrayList<Vehicle>();
        if (file.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname));
            vehs = (ArrayList<Vehicle>) in.readObject();
            in.close();
        }
        return vehs;
    }

    public static void writeVehs(ArrayList<Vehicle> vehs, String fname) throws IOException, ClassNotFoundException{
        FileOutputStream file = new FileOutputStream(fname);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(vehs);
        out.close();
        file.close();
    }

    public ArrayList<HireDetails> readHireDetails(String fname) throws FileNotFoundException, IOException, ClassNotFoundException {
        file = new File(fname);
        ArrayList<HireDetails> cust_vehs = new ArrayList<HireDetails>();
        if (file.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname));
            cust_vehs = (ArrayList<HireDetails>) in.readObject();
            in.close();
        }
        return cust_vehs ;
    }

    public void writeHireDetails(ArrayList<HireDetails> cust_vehs, String fname ) throws IOException {
        FileOutputStream file = new FileOutputStream(fname);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(cust_vehs);
        out.close();
        file.close();
    }

    public ArrayList<HireReport> readRentHistory(String fname) throws IOException, ClassNotFoundException {
        file = new File(fname);
        ArrayList<HireReport> report = new ArrayList<HireReport>();
        if (file.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname));
            report = (ArrayList<HireReport>) in.readObject();
            in.close();
        }
        return report;
    }

    public void writeRentHistory(ArrayList<HireReport> report, String fname) throws IOException {
        FileOutputStream file = new FileOutputStream(fname);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(report);
        out.close();
        file.close();
    }
}
