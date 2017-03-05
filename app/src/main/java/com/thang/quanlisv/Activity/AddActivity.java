package com.thang.quanlisv.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.thang.quanlisv.DAO.DatabaseHandler;
import com.thang.quanlisv.Model.Student;
import com.thang.quanlisv.R;

public class AddActivity extends Activity{
    private EditText txtMaSV,txtName,txtDress, txtEmail, txtPhone;
    private Spinner cbCate;
    private Button btnAdd,btnCancle;
    private int cateId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Thêm Sinh Viên");
        connectView();
        String[] cateTitles={"Nhóm 1","Nhóm 2","Nhóm 3"};
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,cateTitles);
        cbCate.setAdapter(arrayAdapter);
        cbCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cateId=position+1;
//                txtDescription.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = txtMaSV.getText().toString();
                String name = txtName.getText().toString();
                String dress = txtDress.getText().toString();
                String email = txtEmail.getText().toString();
                String phone = txtPhone.getText().toString();
                Student student = new Student(ma,name, cateId, dress, email, phone);
                DatabaseHandler databaseHandler = new DatabaseHandler(AddActivity.this);
                if (databaseHandler.addStudent(student)) {
                    showMsg("Them sach thanh cong");
                    finish();
                } else showMsg("Them sach khong thanh cong");

            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    private void connectView(){
        txtMaSV = (EditText) findViewById(R.id.txtmaSV);
        txtName = (EditText) findViewById(R.id.txtName);
        txtDress = (EditText) findViewById(R.id.txtDress);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPhone = (EditText) findViewById(R.id.txtPhone);

        cbCate=(Spinner)findViewById(R.id.cbCate);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnCancle=(Button)findViewById(R.id.btnCancel);

    }
}
