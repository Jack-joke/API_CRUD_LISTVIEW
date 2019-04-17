package com.example.api_into_listview_;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url = "http://5b7e85ceadf2070014bfa383.mockapi.io/users";

    ListView lvEmployee;
    ArrayList<Employee> employeeArrayList;
    EmployeeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEmployee = (ListView) findViewById(R.id.lvEmployee);
        employeeArrayList = new ArrayList<>();
        adapter = new EmployeeAdapter(this,R.layout.row_employee,employeeArrayList);
        lvEmployee.setAdapter(adapter);

        GetData(url);
    }
    private  void GetData(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0; i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        employeeArrayList.add(new Employee(
                                object.getInt("id"),
                                object.getString("FIRSTNAME"),
                                object.getString("LASTNAME"),
                                object.getString("GENDER"),
                                object.getString("SALARY")
                        ));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error in Main Activity!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_employee,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menuAddEmployee){
            startActivity(new Intent(MainActivity.this, AddEmployee.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
