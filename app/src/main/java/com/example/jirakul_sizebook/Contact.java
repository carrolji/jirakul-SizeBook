package com.example.jirakul_sizebook;

import java.io.Serializable;

/**
 * Created by carrotji on 2017-02-01.
 */

public class Contact implements Serializable{

    private String _name;
    private String _date;
    private String _neck;
    private String _bust;
    private String _chest;
    private String _waist;
    private String _hip;
    private String _inseam;
    private String _comment;

    public Contact (String name, String date,
                    String neck, String bust, String chest, String waist, String inseam,String comment) {

        _name = name;
        _bust = bust;
        _chest = chest;
        _waist = waist;
        _inseam = inseam;
        _date = date;
        _neck = neck;
        _comment = comment;
    }
    public String getName() {
        return _name;
    }

    public String getDate() {
        return _date;
    }

    public String getNeck() {
        return _neck;
    }

    public String getBust() {
        return _bust;
    }

    public String getChest() {
        return _chest;
    }

    public String getWaist() {
        return _waist;
    }

    public String getHip() {
        return _hip;
    }

    public String getInseam() {
        return _inseam;
    }

    public String getComment() {
        return _comment;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public void set_neck(String _neck) {
        this._neck = _neck;
    }

    public void set_bust(String _bust) {
        this._bust = _bust;
    }

    public void set_chest(String _chest) {
        this._chest = _chest;
    }

    public void set_waist(String _waist) {
        this._waist = _waist;
    }

    public void set_hip(String _hip) {
        this._hip = _hip;
    }

    public void set_inseam(String _inseam) {
        this._inseam = _inseam;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }
}
