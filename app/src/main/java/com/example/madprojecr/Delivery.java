package com.example.madprojecr;

public class Delivery {

    String prnumber;
    String cname;
    String pnumber;
    String address;
    String date;
    String key;



    public Delivery(String prnumber, String cname, String pnumber, String address, String date,String key) {
        this.key = key;
        this.prnumber = prnumber;
        this.cname = cname;
        this.pnumber = pnumber;
        this.address = address;
        this.date = date;
    }




    public String getPrnumber() {
        return prnumber;
    }

    public String getCname() {
        return cname;
    }

    public String getPnumber() {
        return pnumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getKey() { return key;}

    public Delivery(String prnumber, String cname, String pnumber, String address, String date) {
    }
}
