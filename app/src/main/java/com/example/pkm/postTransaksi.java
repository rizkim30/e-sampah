package com.example.pkm;

public class postTransaksi {
    public String id;
    public String berat;
    public String tabungan;
    public String pesan;
    public postTransaksi(String Id,String Berat,String Tabungan){
        this.id = Id;
        this.berat = Berat;
        this.tabungan = Tabungan;
    }

    public String getPesan() {
        return pesan;
    }
}
