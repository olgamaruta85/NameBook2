package com.helennagel.namebook;

import java.io.Serializable;

/* The class is defined Serializable. The reason is that it should be possible to transfer a Person object as parameter from one activity to another.
This class is a major part of the work of saving person objects in a file. Reading and writing to a file takes place in the class People, which represents all contacts. */
public class Person implements Serializable {

    // The class represents a person with the four desired properties.
    private String name, phone, email, text;

    // First constructor + getters and setters
    public Person(String name, String phone, String email, String text) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.text = text;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

/*
The idea is that contacts should be saved as text lines in a file, and it is therefore necessary to separate the fields so you can read in the file again.
A separation mark must be used, which is just a character that does not appear in the text fields, and here I use a form feed (page shift).
The method getPerson() thus returns a string with the person’s fields separated by form feeds.
You should note that if a field is empty (the fields may be if it is NOT the name), a tab will be saved.
That’s what you can call a “work around”, and the reason is that the split() method in the String class ignores blank fields.

The second constructor has a String as a parameter and assumes that the String consists of 4 fields separated by form feeds.
The fields are determined by separating strings into fields using the split() method, the last three fields have a trim() performed, the
purpose is to remove any tab inserted by the above method. */

    public String getPerson(){
        StringBuilder builder = new StringBuilder(name);
        builder.append("\f"); // "\f" stands for form feed
        builder.append(phone.length() == 0 ? "\t" : phone); // "\t" stands for tab
        builder.append("\f");
        builder.append(email.length() == 0 ? "\t" : email);
        builder.append("\f");
        builder.append(text.length() == 0 ? "\t" : text);
        return builder.toString();
    }

    public Person(String line) throws Exception{
        String[] elems = line.split("\f");
        if (elems.length != 4) throw new Exception("Illegal text for person");
        name = elems[0];
        phone = elems[1].trim();
        email = elems[2].trim();
        text = elems[3].trim();
    }

    @Override
    public String toString() {
        return  name;
    }
}
