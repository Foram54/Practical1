
        package com.example.practical1;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.os.Handler;
        import android.util.Patterns;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int attempt=3;

    TextView signup ,timer;
    EditText lemail, lpass;
    Button btn;


    // ArrayList<String> _List = (ArrayList<String>) getIntent().getSerializableExtra("list");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.signnup);
        lemail = findViewById(R.id.lemail);
        lpass = findViewById(R.id.lpass);
        btn = findViewById(R.id.btn);
        timer=findViewById(R.id.timer);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    // If validation succeeds, proceed with login
                    String em = lemail.getText().toString();
                    String pa = lpass.getText().toString();
                    Intent i = getIntent();
                    if (i != null) {
                        String email = i.getStringExtra("email");
                        String pass = i.getStringExtra("pass");



                        if (email.trim().equals(em) && pass.trim().equals(pa))
                        {
                            Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            attempt--;
                            Toast.makeText(MainActivity.this, "Invalid email or password. Attempt is " +attempt, Toast.LENGTH_SHORT).show();
                            if (attempt == 0) {
                                btn.setEnabled(false);
                                if (attempt >= 0) {
                                    Toast.makeText(MainActivity.this, "Time Remain to . Attempt is " + attempt, Toast.LENGTH_SHORT).show();


                                    new CountDownTimer(15000, 1000) {

                                        @Override
                                        public void onTick(long l) {

                                            timer.setVisibility(View.VISIBLE);
                                            timer.setText("Remaing Time :" + l / 1000);


                                        }

                                        @Override
                                        public void onFinish() {
                                            timer.setVisibility(View.GONE);
                                            timer.setText("Finish");
                                            btn.setEnabled(true);
                                        }
                                    }.start();

                                    attempt=0;

                                }
                            }
                        }
                    }
                }
            }
        });
    }
    public void goToRegister(View view) {
        Intent i = new Intent(MainActivity.this,Homepage.class);
        startActivity(i);
    }
    private boolean validateInput() {


        // Validate Email
        String emailInput = lemail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            lemail.setError("Field cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            lemail.setError("Invalid email address");
            return false;
        } else {
            lemail.setError(null);
        }

        // Validate Password
        String password = lpass.getText().toString().trim();
        if (password.isEmpty()) {
            lpass.setError("Field cannot be empty");
            return false;
        } else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            lpass.setError("Password must contain at least 8 characters with letters and digits");
            return false;
        } else {
            lpass.setError(null);
        }

        // If all fields are valid, return true
        return true;
    }

}




