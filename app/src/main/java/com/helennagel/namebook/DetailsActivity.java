package com.helennagel.namebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

/* Will use getSerializableExtra method to Retrieve extended data from the intent, from the previous activity.
This will display from listview the selected persons entire input data. */

public class DetailsActivity extends AppCompatActivity {

    private EditText txtName, txtPhone, txtEmail, txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txtName=findViewById(R.id.shName);
        txtPhone=findViewById(R.id.shPhone);
        txtEmail=findViewById(R.id.shEmail);
        txtText=findViewById(R.id.shText);

        Person person =(Person)getIntent().getSerializableExtra("person");
        txtName.setText(person.getName());
        txtPhone.setText(person.getPhone());
        txtEmail.setText(person.getEmail());
        txtText.setText(person.getText());
        ;
    }
}
