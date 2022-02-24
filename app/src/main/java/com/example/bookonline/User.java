package com.example.bookonline;

public class User {
    private String ID;
    private String Name;
    private String Email;
    private String Password;
    private String GioiTinh;
    private String HinhAnh;
    private String NgaySinh;
    private String SDT;
    private String DiaChi;

    public User(){
    }

    public User(String ID, String Name, String Email, String Password, String GioiTinh, String HinhAnh, String NgaySinh, String SDT, String DiaChi) {
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.GioiTinh = GioiTinh;
        this.HinhAnh = HinhAnh;
        this.NgaySinh = NgaySinh;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
}
