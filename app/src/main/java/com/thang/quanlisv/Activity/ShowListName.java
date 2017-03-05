package com.thang.quanlisv.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.thang.quanlisv.DAO.DatabaseHandler;
import com.thang.quanlisv.Model.Student;
import com.thang.quanlisv.R;

public class ShowListName extends Activity {
    private TextView noSearchName;
    private ListView listFindName;
    private DatabaseHandler db;
    private ArrayList<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_name);

        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Kết quả");

        db = new DatabaseHandler(this);
        noSearchName = (TextView) findViewById(R.id.noSearchName);
        listFindName = (ListView) findViewById(R.id.listFindName);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null) {
            String keyword = (String) b.get("inputName");
            list = db.getSearchContact(keyword);
            if (list.size() == 0) {
                noSearchName.setVisibility(View.VISIBLE);
                listFindName.setVisibility(View.GONE);
            } else {
                ListItem listItem = new ListItem(this, R.layout.item_student_activity, list);
                listFindName.setAdapter(listItem);
                listFindName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent it = new Intent(ShowListName.this, ViewStudentActivity.class);
                        it.putExtra("studentId", list.get(position).getSv_id());
                        startActivityForResult(it, 0);
                    }
                });
            }
        }
    }

}
