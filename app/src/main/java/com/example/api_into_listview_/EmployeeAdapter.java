package com.example.api_into_listview_;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Employee> employeeList;

    public EmployeeAdapter(Context context, int layout, List<Employee> employeeList) {
        this.context = context;
        this.layout = layout;
        this.employeeList = employeeList;
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView tvFirstName, tvLastName, tvGender, tvSalary;
        ImageView imgDelete, imgEdit;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView ==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.tvFirstName = (TextView) convertView.findViewById(R.id.tvFirstName);
            holder.tvLastName = (TextView) convertView.findViewById(R.id.tvLastName);
            holder.tvGender = (TextView) convertView.findViewById(R.id.tvGender);
            holder.tvSalary = (TextView) convertView.findViewById(R.id.tvSalary);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        final Employee employee = employeeList.get(position);
        holder.tvFirstName.setText(employee.getFirstName());
        holder.tvLastName.setText(employee.getLastName());
        holder.tvGender.setText(employee.getGender());
        holder.tvSalary.setText(employee.getSalary());


        //get Event
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateEmployee.class);
                intent.putExtra("dataEmployee", employee);
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDelete(employee.getLastName(), employee.getId());
            }
        });
        return convertView;
    }
    private void ConfirmDelete(String lastname, final int id){
        final String url = "http://5b7e85ceadf2070014bfa383.mockapi.io/users";
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
        dialogDelete.setMessage("Do you want to remove " + lastname + " employee?");
        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteEmployee(url, id);
            }
        });
        dialogDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogDelete.show();

    }

    private void DeleteEmployee(String url, int id){
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url +'/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Succesfully delete!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error make by Delete method!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
