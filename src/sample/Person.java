package sample;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String address;
    private String telefonNumber;

    public Person(String name, String address, String telefonNumber) {
        this.name = name;
        this.address = address;
        this.telefonNumber = telefonNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelefonNumber() {
        return telefonNumber;
    }

    public void setTelefonNumber(String telefonNumber) {
        this.telefonNumber = telefonNumber;
    }
}
