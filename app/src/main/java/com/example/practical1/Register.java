package com.example.practical1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    TextView signin;

    Button btnsub , date ;

    RadioGroup gender;

    RadioButton male, female;
    EditText name, email, con, password, cpassword;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signin = findViewById(R.id.signin);
        btnsub = findViewById(R.id.btnsub);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        con = findViewById(R.id.con);
        gender = findViewById(R.id.gender);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        date = findViewById(R.id.date);

        Calendar calendar = Calendar.getInstance();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int date2 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datepick = new DatePickerDialog(
                        Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "-" + (month + 1) + "-" + year);
                    }
                },year,month,date2);
                datepick.show();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this,MainActivity.class);
                startActivity(i);
            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {

                    String fName = name.getText().toString();
                    String Email = email.getText().toString();
                    String Contact = con.getText().toString();
                    String gender1 = null;
                    int selectedRadioButtonId = gender.getCheckedRadioButtonId();
                    if (selectedRadioButtonId != -1) {
                        male = findViewById(selectedRadioButtonId);
                        String selectedRbText = male.getText().toString();
                        gender1 = selectedRbText;
                    }
                    String bdate = date.getText().toString();
                    String pas = password.getText().toString();
                    String cpas = cpassword.getText().toString();

                    Intent i = new Intent(Register.this, Homepage.class);
                    i.putExtra("name", fName);
                    i.putExtra("email", Email);
                    i.putExtra("Contact", Contact);
                    i.putExtra("date", bdate);
                    i.putExtra("pass", pas);
                    i.putExtra("cpas", cpas);
                    startActivity(i);
                }
            }
        });
    }


    private boolean validateInput() {
        // Validate Full Name
        String fullName = name.getText().toString().trim();
        if (fullName.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else if (!fullName.matches("^[a-zA-Z]+$")) {

            name.setError("Name Contains Only Alphabets");
            return false;
        } else {
            name.setError(null);
        }

        // Validate Email
        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
        }

        // Validate Contact Number
        String contactNumber = con.getText().toString().trim();
        if (contactNumber.isEmpty()) {
            con.setError("Field cannot be empty");
            return false;
        } else if (!contactNumber.matches("^\\d{10}$")) {
            con.setError("Invalid contact number");
            return false;
        } else {
            con.setError(null);
        }


        // Validate Password
        String Password = password.getText().toString().trim();
        String confirmPassword = cpassword.getText().toString().trim();
        if (Password.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!Password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            password.setError("Password must contain at least 8 characters with letters and digits");
            return false;
        } else {
            password.setError(null);
        }

        // Validate Confirm Password
        if (confirmPassword.isEmpty()) {
            cpassword.setError("Field cannot be empty");
            return false;
        } else if (!confirmPassword.equals(password)) {
            cpassword.setError("Passwords do not match");
            return false;
        } else {
            cpassword.setError(null);
        }
        return true;
    }

    public void goToLog(View view) {
        Intent i = new Intent(Register.this, Homepage.class);
        startActivity(i);
    }
}


