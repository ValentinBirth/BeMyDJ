package de.htw.bemydj.djData;

public class AvailablePeer {

    private String Name;
    private String Adress;


    public AvailablePeer(String name, String adress) {
        Name = name;
        Adress = adress;
    }

    public String getName() {
        return Name;
    }

    public String getAdress() {
        return Adress;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }
}
