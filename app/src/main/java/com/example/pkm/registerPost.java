package com.example.pkm;

public class registerPost {
    public String nama;
    public String email;
    public String password;
    public registerPost(String Nama,String Email,String Password){
        this.nama = Nama;
        this.email = Email;
        this.password = Password;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }
}
