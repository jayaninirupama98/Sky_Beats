package com.example.recyclermusic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class RegisterActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    Button button1;
    RadioGroup radioGroup;
    TextView textView;
    RadioButton radioButton;
    EditText firstname,lastname,gender,contact,address,email,password;
    String str_firstname,str_lastname,str_gender,str_contact,str_address,str_email,str_password;
    String url = "https://skybeats.ga/mobile_app/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        radioGroup = findViewById(R.id.radioGroup);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        lottieAnimationView = findViewById(R.id.lottie);
        contact = findViewById(R.id.contact);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        radioGroup = findViewById(R.id.radioGroup);
        //textView = findViewById(R.id.text_view_selected);

        lottieAnimationView.setRepeatCount(Animation.INFINITE);

        button1 = (Button) findViewById(R.id.login2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(RegisterActivity.this, "", Toast.LENGTH_SHORT).show();
                openLoginActivity();
            }
        });

    }
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void checkButton(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "SELECT :" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    //public void moveToLogin(View view) {

        //startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        //finish();
    //}

    public void Register(View view) {

        //int radioId = radioGroup.getCheckedRadioButtonId();

        //radioButton = findViewById(radioId);
        //textView.setText(radioButton.getText());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if(firstname.getText().toString().equals("")){
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show();
        }
        else if(lastname.getText().toString().equals("")){
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show();
        }
        else if(contact.getText().toString().equals("")){
            Toast.makeText(this, "Enter Contact", Toast.LENGTH_SHORT).show();
        }
        else if(address.getText().toString().equals("")){
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
        }
        else if(email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.show();
           // str_gender = radioButton.getText().toString().trim();
            str_firstname = firstname.getText().toString().trim();
            str_lastname = lastname.getText().toString().trim();
            str_contact = contact.getText().toString().trim();
            str_address = address.getText().toString().trim();
            str_email = email.getText().toString().trim();
            str_password = password.getText().toString().trim();
            str_gender = radioButton.getText().toString().trim();



            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                   // radioButton.setText("");
                    firstname.setText("");
                    lastname.setText("");
                    contact.setText("");
                    address.setText("");
                    email.setText("");
                    password.setText("");
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("firstname",str_firstname);
                    params.put("lastname",str_lastname);
                    params.put("gender",str_gender);
                    params.put("contact",str_contact);
                    params.put("address",str_address);
                    params.put("email",str_email);
                    params.put("password",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(request);


        }

    }
}
