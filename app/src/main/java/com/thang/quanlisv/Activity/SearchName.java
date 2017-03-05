package com.thang.quanlisv.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thang.quanlisv.R;

public class SearchName extends Activity {
    private EditText inputName;
    private Button btnFindName;

    String keyword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);

        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tìm kiếm theo tên");

        inputName = (EditText) findViewById(R.id.inputName);
        btnFindName = (Button) findViewById(R.id.btnFindName);
        btnFindName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = inputName.getText().toString();
                Intent i = new Intent(SearchName.this, ShowListName.class);
                i.putExtra("inputName", keyword);
                startActivity(i);
            }
        });
    }
}
