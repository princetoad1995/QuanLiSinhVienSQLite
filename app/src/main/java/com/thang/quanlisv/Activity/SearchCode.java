package com.thang.quanlisv.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thang.quanlisv.R;

public class SearchCode extends Activity {
    private EditText inputCode;
    private Button btnFindCode;
    String keyword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_code);

        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tìm kiếm theo mã sinh viên");

        inputCode = (EditText) findViewById(R.id.inputCode);
        btnFindCode = (Button) findViewById(R.id.btnFindCode);
        btnFindCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = inputCode.getText().toString();
                Intent i = new Intent(SearchCode.this, ShowListCode.class);
                i.putExtra("inputCode", keyword);
                startActivity(i);
            }
        });
    }
}
