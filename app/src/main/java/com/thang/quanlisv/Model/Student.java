package com.thang.quanlisv.Model;

public class Student {
    private int sv_id;
    private String sv_ma, sv_name,sv_dress,sv_email,sv_phone;
    private int sv_cate;

    public Student() {
    }

    public Student(int sv_id, String sv_ma, String sv_name, int sv_cate, String sv_dress, String sv_email, String sv_phone) {
        this.sv_id = sv_id;
        this.sv_ma = sv_ma;
        this.sv_name = sv_name;
        this.sv_dress = sv_dress;
        this.sv_email = sv_email;
        this.sv_phone = sv_phone;
        this.sv_cate = sv_cate;
    }

    public Student(String sv_ma, String sv_name,  int sv_cate, String sv_dress, String sv_email, String sv_phone) {
        this.sv_ma = sv_ma;
        this.sv_name = sv_name;
        this.sv_dress = sv_dress;
        this.sv_email = sv_email;
        this.sv_phone = sv_phone;
        this.sv_cate = sv_cate;
    }

    public String getSv_ma() {
        return sv_ma;
    }

    public void setSv_ma(String sv_ma) {
        this.sv_ma = sv_ma;
    }

    public int getSv_id() {
        return sv_id;
    }

    public void setSv_id(int sv_id) {
        this.sv_id = sv_id;
    }

    public String getSv_name() {
        return sv_name;
    }

    public void setSv_name(String sv_name) {
        this.sv_name = sv_name;
    }

    public String getSv_dress() {
        return sv_dress;
    }

    public void setSv_dress(String sv_dress) {
        this.sv_dress = sv_dress;
    }

    public String getSv_email() {
        return sv_email;
    }

    public void setSv_email(String sv_email) {
        this.sv_email = sv_email;
    }

    public String getSv_phone() {
        return sv_phone;
    }

    public void setSv_phone(String sv_phone) {
        this.sv_phone = sv_phone;
    }

    public int getSv_cate() {
        return sv_cate;
    }

    public void setSv_cate(int sv_cate) {
        this.sv_cate = sv_cate;
    }
}
