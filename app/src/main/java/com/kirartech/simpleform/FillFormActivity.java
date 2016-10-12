package com.kirartech.simpleform;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FillFormActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);

        final DBHelper DBObj = new DBHelper(this.getApplicationContext());

        final EditText name = (EditText)findViewById(R.id.enterName);
        final EditText email = (EditText)findViewById(R.id.enterEmail);
        final EditText phone = (EditText)findViewById(R.id.enterPhone);
        final EditText dob = (EditText)findViewById(R.id.enterDOB);

        Button SubmitButton = (Button)findViewById(R.id.submitButton);
        Button ResetButton = (Button)findViewById(R.id.resetButton);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBObj.insertValues(name.getText().toString(), email.getText().toString(), phone.getText().toString(), dob.getText().toString());
                Toast.makeText(getApplicationContext(),"Data Submitted Successfully",Toast.LENGTH_SHORT).show();
            }
        });

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                email.setText("");
                phone.setText("");
                dob.setText("");
                Toast.makeText(getApplicationContext(),"Data Cleared",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fill_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
