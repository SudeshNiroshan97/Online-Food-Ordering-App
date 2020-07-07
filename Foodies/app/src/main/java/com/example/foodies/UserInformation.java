package com.example.foodies;

public class UserInformation {

    public String item1;
    public String item2;
    public String item3;
    public String qty1;
    public String qty2;
    public String qty3;
    public String username;
    public String address;
    public String total;
    public String email;
    public  String tpno;
    public  String instructions;



    public UserInformation(){

    }

    public UserInformation(String item1, String item2, String item3, String qty1, String qty2, String qty3
    , String username, String address, String total, String email, String tpno,String instructions) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.qty1 = qty1;
        this.qty2=qty2;
        this.qty3=qty3;
        this.username=username;
        this.address=address;
        this.total=total;
        this.email=email;
        this.tpno=tpno;
        this.instructions=instructions;
    }
}
