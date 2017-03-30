package com.uni.model;

/**
 * Created by catal on 3/26/2017.
 */
public class Client {

    private int clientId;
    private String name;
    private int icNumber;
    private String cnp;
    private int age;
    private String address;

    public Client(){}

    public Client(int clientId, String name, int icNumber, String cnp, int age, String address) {
        this.clientId = clientId;
        this.name = name;
        this.icNumber = icNumber;
        this.cnp = cnp;
        this.age = age;
        this.address = address;
    }

    public Client( String name, int icNumber, String cnp, int age, String address) {
        this.name = name;
        this.icNumber = icNumber;
        this.cnp = cnp;
        this.age = age;
        this.address = address;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(int icNumber) {
        this.icNumber = icNumber;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", icNumber=" + icNumber +
                ", cnp='" + cnp + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
