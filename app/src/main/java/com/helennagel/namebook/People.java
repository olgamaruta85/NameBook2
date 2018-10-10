package com.helennagel.namebook;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// The class is defined Serializable as it should be transferred as parameter to an activity.
public class People implements Serializable {

    // The class has three variables, where first is the filename, the other an ArrayList to Person objects, and the latter is a Context.
    private static final String filename = "papersons";
    private List<Person> personList = new ArrayList<>();

    /* Context represents an activity, it is used when creating afile as it is associated with an activity
    It is defined as transient, because it should not be serializsed when a Person object is transferred to
    another activity.*/
    private transient Context context;

    /* The class constructor loads the file lines and parses them to Person objects.
    File opens with the method openFileInput(), which is a method in the context object that opens a
    file in internal storage and returns an InputFileStream object. */
    public People(Context context) throws Exception{
        this.context = context;
        FileInputStream stream = null;
        try {
            stream = context.openFileInput(filename);
        }
        catch (Exception ex)
        {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        for (String line = reader.readLine(); line != null; line = reader.readLine())
            try {
                personList.add(new Person(line));
            }
            catch (Exception ex){}
            reader.close();
    }

    public Person getPerson(int n){
        return personList.get(n);
    }

    public List<Person> getPeople(){
        return personList;
    }

    /* When saving an entered contact, you call the method save() with a Person object as parameter.
    context.openFileOutput(filename, Context.MODE_APPEND) opens an output file in internal storage and again it is with a method in the context object.
    The method returns a FileOutputStream object. File opens in append mode to add to the file and if the file does not exist, it will be automatically created. */
    public void save(Person person) throws Exception{
        FileOutputStream stream = context.openFileOutput(filename, Context.MODE_APPEND);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        writer.write(person.getPerson());
        writer.newLine();
        writer.close();
        personList.add(person);
    }


/* Method to delete a Person in the file. The file is this time opened as context.openFileOutput(filename, Context.MODE_PRIVATE)  which means that an existing content is overwritten.
The method works by writing the entire content of the list persons back into the file (but not the object that is deleted).
This is necessary because it is a sequential file and something similar would apply if you could change the information for a contact. */

    public void remove(Context context, Person person) throws Exception{
        if (personList.remove(person)){
            FileOutputStream stream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
            for (Person p : personList){
                writer.write(p.getPerson());
                writer.newLine();
            }
            writer.close();
        }
        else throw new Exception("Person could not be removed");
    }
}
