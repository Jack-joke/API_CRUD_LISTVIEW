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

public class UpdateEmployee extends AppCompatActivity {

    Button btnCancel, btnUpdate;
    EditText edtFirstName, edtLastName, edtGender, edtSalary;
    String url = "http://5b7e85ceadf2070014bfa383.mockapi.io/users";
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtGender = (EditText) findViewById(R.id.edtGender);
        edtSalary = (EditText) findViewById(R.id.edtSalary);

        Intent intent = getIntent();
        Employee employee = (Employee) intent.getSerializableExtra("dataEmployee");

        id = employee.getId();
        edtFirstName.setText(employee.getFirstName());
        edtLastName.setText(employee.getLastName());
        edtGender.setText(employee.getGender());
        edtSalary.setText(employee.getSalary());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateEmployee(url);
            }
        });

    }
    private void UpdateEmployee(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url+'/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(UpdateEmployee.this, "Successfully...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateEmployee.this, MainActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateEmployee.this, "Error make by...", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("FIRSTNAME",edtFirstName.getText().toString().trim());
                params.put("LASTNAME",edtLastName.getText().toString().trim());
                params.put("GENDER",edtGender.getText().toString().trim());
                params.put("SALARY",edtSalary.getText().toString().trim());
                //params.put("SALARY",20000);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
