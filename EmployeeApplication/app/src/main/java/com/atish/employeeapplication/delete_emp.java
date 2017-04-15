package com.atish.employeeapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.atish.employeeapplication.DB.EmployeeDBHandler;
import com.atish.employeeapplication.DB.EmployeeOperations;
import com.atish.employeeapplication.Model.Employee;

public class delete_emp extends AppCompatActivity {
    private Button deleteRecord;
    private EditText empId;
//    long tempId = Long.parseLong(empId.getText().toString());
    Context context;
//    private int staticEmpId = 1;
//    private String name = "atish kumbhar";
//    private String date = "16/02/2016";
//    private String department = "CSE";

    Employee emp;
    EmployeeOperations employeeOperations;
    SQLiteDatabase database;
    SQLiteOpenHelper dbhandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_emp);
        context = getApplicationContext();
        dbhandler = new EmployeeDBHandler(context);
        database = dbhandler.getWritableDatabase();

        deleteRecord = (Button)findViewById(R.id.delete_emp);
        empId = (EditText)findViewById(R.id.emp_id);
        deleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Employee Record Id" , empId.getText().toString());
//                int id = Integer.parseInt(empId.getText().toString());
                Long id = Long.parseLong(empId.getText().toString());

                emp = new Employee((Long)id, "atish", "kumbhar", "male", "16/02/2016", "CSE");
                employeeOperations = new EmployeeOperations(context);
                database.delete(EmployeeDBHandler.TABLE_EMPLOYEES, EmployeeDBHandler.COLUMN_ID + "=" + emp.getEmpId(), null);

                employeeOperations.removeEmployee(emp);
            }
        });
    }
}
