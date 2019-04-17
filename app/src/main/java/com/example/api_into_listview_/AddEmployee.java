package com.example.api_into_listview_;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddEmployee extends AppCompatActivity {

    Button btnCancel, btnAdd;
    EditText edtFirstName, edtLastName, edtGender, edtSalary;
    String url = "http://5b7e85ceadf2070014bfa383.mockapi.io/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtGender = (EditText) findViewById(R.id.edtGender);
        edtSalary = (EditText) findViewById(R.id.edtSalary);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEmployee(url);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });
    }

    private void AddEmployee(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddEmployee.this, "Adding successfully!", Toast.LENGTH_SHORT).show();
                startActivity( new Intent(AddEmployee.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddEmployee.this, "Error!" + error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("FIRSTNAME",edtFirstName.getText().toString().trim());
                params.put("LASTNAME",edtLastName.getText().toString().trim());
                params.put("GENDER",edtGender.getText().toString().trim());
                params.put("SALARY",edtSalary.getText().toString().trim());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}
