package com.example.jirakul_sizebook;

import java.io.Serializable;

/**
 * Created by carrotji on 2017-02-01.
 */

public class Contact implements Serializable {
    private String _name,_date,_comment,_neck,_bust,_chest,_waist,_hip,_inseam;


    public Contact(String _name) {
        this._name = _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
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

    public String get_name() {
        return _name;
    }

    public String get_date() {
        return _date;
    }

    public String get_comment() {
        return _comment;
    }

    public String get_neck() {
        return _neck;
    }

    public String get_bust() {
        return _bust;
    }

    public String get_chest() {
        return _chest;
    }

    public String get_waist() {
        return _waist;
    }

    public String get_hip() {
        return _hip;
    }

    public String get_inseam() {
        return _inseam;
    }
}
