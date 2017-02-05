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

    /**
     * Instantiates a new Contact.
     *
     * @param name    the name
     * @param date    the date
     * @param neck    the neck
     * @param bust    the bust
     * @param chest   the chest
     * @param waist   the waist
     * @param hip     the hip
     * @param inseam  the inseam
     * @param comment the comment
     */
    public Contact (String name, String date,
                    String neck, String bust, String chest, String waist,String hip, String inseam,String comment) {

        _name = name;
        _bust = bust;
        _chest = chest;
        _waist = waist;
        _inseam = inseam;
        _hip = hip;
        _date = date;
        _neck = neck;
        _comment = comment;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return _date;
    }

    /**
     * Gets neck.
     *
     * @return the neck
     */
    public String getNeck() {
        return _neck;
    }

    /**
     * Gets bust.
     *
     * @return the bust
     */
    public String getBust() {
        return _bust;
    }

    /**
     * Gets chest.
     *
     * @return the chest
     */
    public String getChest() {
        return _chest;
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public String getWaist() {
        return _waist;
    }

    /**
     * Gets hip.
     *
     * @return the hip
     */
    public String getHip() {
        return _hip;
    }

    /**
     * Gets inseam.
     *
     * @return the inseam
     */
    public String getInseam() {
        return _inseam;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return _comment;
    }

    /**
     * Sets name.
     *
     * @param _name the name
     */
    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     * Sets date.
     *
     * @param _date the date
     */
    public void set_date(String _date) {
        this._date = _date;
    }

    /**
     * Sets neck.
     *
     * @param _neck the neck
     */
    public void set_neck(String _neck) {
        this._neck = _neck;
    }

    /**
     * Sets bust.
     *
     * @param _bust the bust
     */
    public void set_bust(String _bust) {
        this._bust = _bust;
    }

    /**
     * Sets chest.
     *
     * @param _chest the chest
     */
    public void set_chest(String _chest) {
        this._chest = _chest;
    }

    /**
     * Sets waist.
     *
     * @param _waist the waist
     */
    public void set_waist(String _waist) {
        this._waist = _waist;
    }

    /**
     * Sets hip.
     *
     * @param _hip the hip
     */
    public void set_hip(String _hip) {
        this._hip = _hip;
    }

    /**
     * Sets inseam.
     *
     * @param _inseam the inseam
     */
    public void set_inseam(String _inseam) {
        this._inseam = _inseam;
    }

    /**
     * Sets comment.
     *
     * @param _comment the comment
     */
    public void set_comment(String _comment) {
        this._comment = _comment;
    }
}
