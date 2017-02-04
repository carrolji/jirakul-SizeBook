package com.example.jirakul_sizebook;

import java.io.Serializable;

/**
 * Created by carrotji on 2017-02-01.
 */

public class Contact implements Serializable{

    private String _name, _date, _neck, _bust;

    public Contact (String name, String date, String neck, String bust) {

        _name = name;
        _date = date;
        _neck = neck;
        _bust = bust;
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
}
