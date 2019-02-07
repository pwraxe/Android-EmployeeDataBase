package com.example.akshay.employeedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText edit_id,edit_fname,edit_lname,edit_email,edit_mobile,edit_dept,edit_sal,edit_city,edit_exp;
    String Fname,Lname,Email,Department,City;
    int id,Exp,Salary;
    long MobileNo;
    Button submit,retrive,update,delete;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submit_button);
        retrive = findViewById(R.id.getData_button);
        update = findViewById(R.id.update_button);
        delete = findViewById(R.id.delete_button);

        edit_id = findViewById(R.id.edit_emp_id);
        edit_fname = findViewById(R.id.edit_fname_id);
        edit_lname = findViewById(R.id.edit_lname_id);
        edit_email = findViewById(R.id.edit_email_id);
        edit_mobile = findViewById(R.id.edit_mobile_id);
        edit_dept = findViewById(R.id.edit_dept_id);
        edit_sal = findViewById(R.id.edit_salary_id);
        edit_city = findViewById(R.id.edit_city_id);
        edit_exp = findViewById(R.id.edit_experience);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllDataByUser();
            }
        });

        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllDataByDatabase();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_id = findViewById(R.id.edit_emp_id);
                int affectedRows = helper.deleteData(Integer.parseInt(edit_id.getText().toString()));
                if(affectedRows > 0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Error to Delete Data",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAllDataByUser()
    {
        id = Integer.parseInt(edit_id.getText().toString());
        Fname = edit_fname.getText().toString();
        Lname = edit_lname.getText().toString();
        Email = edit_email.getText().toString();
        MobileNo = Long.parseLong(edit_mobile.getText().toString());
        Department = edit_dept.getText().toString();
        Salary = Integer.parseInt(edit_sal.getText().toString());
        City = edit_city.getText().toString();
        Exp = Integer.parseInt(edit_exp.getText().toString());

        boolean isDataInserted = helper.insertData(id,Fname,Lname,Email,MobileNo,Department,Salary,City,Exp);

        if(isDataInserted == true)
            Toast.makeText(this,"Data Successfully Inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Something went Wrong",Toast.LENGTH_SHORT).show();

    }

    public void getAllDataByDatabase()
    {
        Cursor c = helper.getAllDatabaseData();
        if(c.getCount() == 0)
        {
            showMessage("Error","No Record Found");
        }
        StringBuffer bf = new StringBuffer();
        while (c.moveToNext())
        {
            bf.append("ID : "+c.getString(0)+"\n");
            bf.append("Name : "+c.getString(1)+" "+c.getString(2)+"\n");
            bf.append("Email ID : "+c.getString(3)+"\n");
            bf.append("Mobile No : "+c.getString(4)+"\n");
            bf.append("Department : "+c.getString(5)+"\n");
            bf.append("Salary : "+c.getString(6)+"\n");
            bf.append("City : "+c.getString(7)+"\n");
            bf.append("Experience : "+c.getString(8)+"\n\n");
        }
        showMessage("Data",bf.toString());
    }
    public void showMessage(String title,String msg)
    {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setCancelable(true).setTitle(title).setMessage(msg).show();

    }


    public void updateData()
    {
        boolean isUpdate = helper.updateAllData(id,Fname,Lname,Email,MobileNo,Department,Salary,City,Exp);
        if(isUpdate == true)
            Toast.makeText(this,"Data Successfully Updated",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Data not Updated",Toast.LENGTH_SHORT).show();
    }



}
