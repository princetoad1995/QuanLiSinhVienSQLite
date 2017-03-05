package com.thang.quanlisv.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.Toast;

import com.thang.quanlisv.DAO.DatabaseHandler;
import com.thang.quanlisv.Model.Student;
import com.thang.quanlisv.R;


public class ViewStudentActivity extends Activity {
    private EditText txtMa,txtName,txtDress, txtEmail, txtPhone;
    private Spinner cbCate;
    private Button btnUpdate,btnCancle;
    private int cateId,studentId;
    private ShareActionProvider mShareActionProvider;
    DatabaseHandler databaseHandler = new DatabaseHandler(ViewStudentActivity.this);
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        connectView();
        Bundle bundle=getIntent().getExtras();
        studentId=bundle.getInt("studentId");
        student = databaseHandler.getStudentByID(studentId);
        actionBar.setTitle(student.getSv_name());
        String[] cateTitles={"Nhóm 1","Nhóm 2","Nhóm 3"};
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,cateTitles);
        cbCate.setAdapter(arrayAdapter);
        cbCate.setSelection(student.getSv_cate() - 1);
        cbCate.setEnabled(false);
        txtMa.setText(student.getSv_ma());
        txtMa.setEnabled(false);
        txtName.setText(student.getSv_name());
        txtName.setEnabled(false);
        txtDress.setText(student.getSv_dress());
        txtDress.setEnabled(false);
        txtEmail.setText(student.getSv_email());
        txtEmail.setEnabled(false);
        txtPhone.setText(student.getSv_phone());
        txtPhone.setEnabled(false);
        btnUpdate.setVisibility(View.GONE);
        cbCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cateId = position + 1;
//                txtDescription.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    private void connectView(){
        txtMa=(EditText)findViewById(R.id.viewMa);
        txtName=(EditText)findViewById(R.id.viewName);
        txtDress=(EditText)findViewById(R.id.viewDress);
        cbCate=(Spinner)findViewById(R.id.viewcbCate);
        txtEmail=(EditText)findViewById(R.id.viewEmail);
        txtPhone=(EditText)findViewById(R.id.viewPhone);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
//        btnCancle=(Button)findViewById(R.id.btnCancel);

    }
    private void startUpdate(){
        btnUpdate.setVisibility(View.VISIBLE);
        txtMa.setEnabled(true);
        txtName.setEnabled(true);
        txtDress.setEnabled(true);
        cbCate.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma=txtMa.getText().toString();
                String name=txtName.getText().toString();
                String dress=txtDress.getText().toString();
                String email=txtEmail.getText().toString();
                String phone=txtPhone.getText().toString();
                Student bookedt=new Student(student.getSv_id(),ma,name,cateId,dress,email,phone);
                if (databaseHandler.updateStudent(bookedt)){
                    showMsg("Cap nhat thanh cong");
                    finish();
                }else showMsg("Khong thanh cong");

            }
        });
    }
    private void deleteStudent(){
        if (databaseHandler.deleteStudent(student.getSv_id())){
            showMsg("Xoa thanh cong");
            finish();
        }else showMsg("Khong thanh cong");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_viewstudent,menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);
        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        setShareIntent(createShareIntent());
        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit:
                startUpdate();
//                showMsg("edit");
                break;
            case R.id.action_delete:
                deleteStudent();
//                showMsg("delete");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    private Intent createShareIntent() {
        String nhom = null;
        if(cateId == 0){
            nhom = "Nhóm 1";
        }
        else if (cateId == 1){
            nhom = "Nhóm 2";
        }
        else
            nhom = "Nhóm 3";
        String txtShare =   "Mã sinh viên: " + student.getSv_ma() + "\n"
                            +  "Tên sinh viên: " + student.getSv_name() + "\n"
                            + "Nơi sinh: " + student.getSv_dress() + "\n"
                            + nhom + "\n"
                            + "Email: " +student.getSv_email() + "\n"
                            + "Số điện thoại: " + student.getSv_phone();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                txtShare);
        return shareIntent;
    }
}
