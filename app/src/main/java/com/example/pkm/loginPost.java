package com.example.pkm;

public class loginPost {
    public String email;
    public String password;
    public String id;
    public String pesan;

    public loginPost(String Email,String Password){
        this.email= Email;
        this.password= Password;
    }

    public  String getEmail() {
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getPesan(){return pesan;}
    public String getId() {
        return id;
    }

}
