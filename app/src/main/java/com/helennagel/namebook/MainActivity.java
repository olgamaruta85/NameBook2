package com.helennagel.namebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private People people;
    private EditText txtName, txtPhone, txtEmail, txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The method calls loadPersons()
        loadPeople();

        // Instantiating variables to the 4 input fields
        txtName = findViewById(R.id.etName);
        txtPhone = findViewById(R.id.etPhone);
        txtEmail = findViewById(R.id.etEmail);
        txtText = findViewById(R.id.etText);
    }

    // Clear button click event handler that calls clear()
    public void onClear(View view) {
        clear();
    }

    /* onSave must create a Person object corresponding to what the user has entered.
    Otherwise, the method does not do much else than to call the method save() in the class People.
    The method shows a Toast that tells whether the contact was saved or not.  */
    public void onSave(View view)
    {
        String name = txtName.getText().toString().trim();
        String phone = txtPhone.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String text = txtText.getText().toString().trim();

        if (name.isEmpty()){
            txtName.setError(getResources().getString(R.string.err_name));
            Toast.makeText(this, getResources().getString(R.string.err_save), Toast.LENGTH_LONG).show();
        } else {
            try {
                people.save(new Person(name, phone, email, text));
                Toast.makeText(this, getResources().getString(R.string.msg_save), Toast.LENGTH_LONG).show();
                clear();
            }
            catch (Exception ex){
            }
        }

        /* NB!!! Changed this if loop because if we wanted to inform the user with a Toast message in the catch block about error in saving data it would not work.
        if (name.length() > 0){
            try{
                people.save(new Person(name,phone,email,text));
                Toast.makeText(this, getResources().getString(R.string.msg_save), Toast.LENGTH_SHORT).show();
                clear();
            }
            catch (Exception ex){}
        }
        */
    }

    /* The event handler onShow() must open another activity. It indicates that the object people must be sent as a parameter and identified by the key persons.
    The class Intent has many overrides of puExtra(), including overrides for all the simple types and strings, but there
    is also an override for a serializable object, which is why the classes Person and People are defined Serializable. */
    public void onShow(View view) {
        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        intent.putExtra("persons", people);
        startActivity(intent);
    }

    // Method that'll clear input field from entered data. Using it in two different places so makes sense to write it once & then just used this method.
    public void clear(){
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtText.setText("");
    }

    /* Initializes the variable persons by creating a new Persons object, thus reading all contacts in the file.
    Note the parameter, which is the Context object. The constructor in the class Persons can raise an exception if the file for one reason or another can not be read correctly.
    If so, there is nothing more to do than stop the application, and the method can be perceived as a standard way to stop an app. */
    private void loadPeople(){
        try{
            people = new People(this);
        }
        catch(Exception ex){
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home);
        }
    }
}
