package com.atish.employeeapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.atish.employeeapplication.DB.EmployeeOperations;
import com.atish.employeeapplication.Model.Employee;


public class MainActivity extends AppCompatActivity{

    private Button addEmployeeButton;
    private Button editEmployeeButton;
    private Button deleteEmployeeButton;
    private Button viewAllEmployeeButton;
    private EmployeeOperations employeeOps;
    private static final String EXTRA_EMP_ID = "com.atish.empId";
    private static final String EXTRA_ADD_UPDATE = "com.atish.add_update";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addEmployeeButton = (Button) findViewById(R.id.button_add_employee);
        editEmployeeButton = (Button) findViewById(R.id.button_edit_employee);
        deleteEmployeeButton = (Button) findViewById(R.id.button_delete_employee);
        viewAllEmployeeButton = (Button)findViewById(R.id.button_view_employees);



        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddUpdateEmployee.class);
                i.putExtra(EXTRA_ADD_UPDATE, "Add");
                startActivity(i);
            }
        });
        editEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmpIdAndUpdateEmp();
            }
        });
        deleteEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmpIdAndRemoveEmp();
            }
        });
        viewAllEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewAllEmployees.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.employee_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void getEmpIdAndUpdateEmp(){
        Intent i = new Intent(MainActivity.this,delete_emp.class);
        startActivity(i);
////                        i.putExtra(EXTRA_ADD_UPDATE, "Update");
////                        i.putExtra(EXTRA_EMP_ID, Long.parseLong(userInput.getText().toString()));
////                        startActivity(i);

////        EditText empId = (EditText)findViewById(R.id.editTextDialogUserInput);
////        int id = Integer.parseInt(empId.getText().toString());
////        Log.d("Emp ID", empId.getText().toString());
//
//
//        LayoutInflater li = LayoutInflater.from(this);
//        View getEmpIdView = li.inflate(R.layout.dialog_get_emp_id, null);
////        int emp_id = R.id.editTextDialogUserInput;
//        Log.d("Emp ID>>>>>>>>>>>>", getEmpIdView.toString() );
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        // set dialog_get_emp_id.xml to alertdialog builder
//        alertDialogBuilder.setView(getEmpIdView);
//
//        final EditText userInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogUserInput);
//        Log.d("EMPLOYEE ID", getEmpIdView.toString());
//
//        // set dialog message
//        alertDialogBuilder
//                .setCancelable(false)
//                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // get user input and set it to result
//                        // edit text
////                        Intent i = new Intent(MainActivity.this,AddUpdateEmployee.class);
////                        i.putExtra(EXTRA_ADD_UPDATE, "Update");
////                        i.putExtra(EXTRA_EMP_ID, Long.parseLong(userInput.getText().toString()));
////                        startActivity(i);
//                    }
//                }).create()
//                .show();

    }


    public void getEmpIdAndRemoveEmp(){

        LayoutInflater li = LayoutInflater.from(this);
        View getEmpIdView = li.inflate(R.layout.dialog_get_emp_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog_get_emp_id.xml to alertdialog builder
        alertDialogBuilder.setView(getEmpIdView);

        final EditText userInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        employeeOps = new EmployeeOperations(MainActivity.this);
                        Long temp = Long.parseLong(userInput.getText().toString());
                        Employee tempEmp = employeeOps.getEmployee(temp);
                        employeeOps.removeEmployee(tempEmp);
                        Toast t = Toast.makeText(MainActivity.this,"Employee removed successfully!",Toast.LENGTH_SHORT);
                        t.show();
                    }
                }).create()
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (employeeOps != null){
            employeeOps.open();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (employeeOps != null) {
            employeeOps.close();
        }
    }
}