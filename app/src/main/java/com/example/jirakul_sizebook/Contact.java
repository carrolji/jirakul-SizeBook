package com.example.jirakul_sizebook;

import java.io.Serializable;

/**
 * The type Contact.
 */
public class Contact implements Serializable{

    private String name;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;

    /**
     * Instantiates a new Contact.
     *
     * @param nameContact    the name contact
     * @param dateContact    the date contact
     * @param neckContact    the neck contact
     * @param bustContact    the bust contact
     * @param chestContact   the chest contact
     * @param waistContact   the waist contact
     * @param hipContact     the hip contact
     * @param inseamContact  the inseam contact
     * @param commentContact the comment contact
     */
    public Contact (String nameContact, String dateContact, String neckContact
                    , String bustContact, String chestContact, String waistContact
                    , String hipContact, String inseamContact,String commentContact) {

        name = nameContact;
        bust = bustContact;
        chest = chestContact;
        waist = waistContact;
        inseam = inseamContact;
        hip = hipContact;
        date = dateContact;
        neck = neckContact;
        comment = commentContact;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets neck.
     *
     * @return the neck
     */
    public String getNeck() {
        return neck;
    }

    /**
     * Gets bust.
     *
     * @return the bust
     */
    public String getBust() {
        return bust;
    }

    /**
     * Gets chest.
     *
     * @return the chest
     */
    public String getChest() {
        return chest;
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public String getWaist() {
        return waist;
    }

    /**
     * Gets hip.
     *
     * @return the hip
     */
    public String getHip() {
        return hip;
    }

    /**
     * Gets inseam.
     *
     * @return the inseam
     */
    public String getInseam() {
        return inseam;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets name.
     *
     * @param _name the name
     */
    public void set_name(String _name) {
        this.name = _name;
    }

    /**
     * Sets date.
     *
     * @param _date the date
     */
    public void set_date(String _date) {
        this.date = _date;
    }

    /**
     * Sets neck.
     *
     * @param _neck the neck
     */
    public void set_neck(String _neck) {
        this.neck = _neck;
    }

    /**
     * Sets bust.
     *
     * @param _bust the bust
     */
    public void set_bust(String _bust) {
        this.bust = _bust;
    }

    /**
     * Sets chest.
     *
     * @param _chest the chest
     */
    public void set_chest(String _chest) {
        this.chest = _chest;
    }

    /**
     * Sets waist.
     *
     * @param _waist the waist
     */
    public void set_waist(String _waist) {
        this.waist = _waist;
    }

    /**
     * Sets hip.
     *
     * @param _hip the hip
     */
    public void set_hip(String _hip) {
        this.hip = _hip;
    }

    /**
     * Sets inseam.
     *
     * @param _inseam the inseam
     */
    public void set_inseam(String _inseam) {
        this.inseam = _inseam;
    }

    /**
     * Sets comment.
     *
     * @param _comment the comment
     */
    public void set_comment(String _comment) {
        this.comment = _comment;
    }
}
