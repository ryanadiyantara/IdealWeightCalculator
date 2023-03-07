package model;

import java.io.Serializable;

public class Data implements Serializable {

    private int data_id;
    private String data_name;

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getData_name() {
        return data_name;
    }

    public void setData_name(String data_name) {
        this.data_name = data_name;
    }

    public float getData_bb() {
        return data_bb;
    }

    public void setData_bb(float data_bb) {
        this.data_bb = data_bb;
    }

    public float getData_tinggi() {
        return data_tinggi;
    }

    public void setData_tinggi(float data_tinggi) {
        this.data_tinggi = data_tinggi;
    }

    public float getData_IMT() {
        return data_IMT;
    }

    public void setData_IMT(float data_IMT) {
        this.data_IMT = data_IMT;
    }

    public String getData_ket() {
        return data_ket;
    }

    public void setData_ket(String data_ket) {
        this.data_ket = data_ket;
    }

    private float data_bb;
    private float data_tinggi;
    private float data_IMT;
    private String data_ket;

}
