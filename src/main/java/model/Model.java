package model;

import javafx.beans.InvalidationListener;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;
import java.net.*;

public class Model extends Observable {
    HashMap<String,String> properties;
    Socket fg;
    PrintWriter out2fg;

    public Model(String propertiesFileName) throws FileNotFoundException {

        properties = new HashMap<>();
        try {
        BufferedReader in = new BufferedReader(new FileReader(propertiesFileName));
        String line;
        while ((line = in.readLine())!= null) {
            String sp[] = line.split(",");
            properties.put(sp[0],sp[1]);
        }
        in.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
        String ip = properties.get("ip");
        int port= Integer.parseInt(properties.get("port"));
        try {
            fg = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out2fg = new PrintWriter(fg.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAileron(double x){
        String command = properties.get("aileron");
        out2fg.println(command+ " "+x);
        out2fg.flush();
    }
    public void setElevator(double x){
        String command = properties.get("elevator");
        out2fg.println(command+ " "+x);
        out2fg.flush();
    }
    public void setRudder(double x){
        String command = properties.get("rudder");
        out2fg.println(command+ " "+x);
        out2fg.flush();
    }
    public void setThrottle(double x){
        String command = properties.get("throttle");
        out2fg.println(command+ " "+x);
        out2fg.flush();
    }

    @Override
    public void finalize(){
        try {
            fg.close();
            out2fg.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
