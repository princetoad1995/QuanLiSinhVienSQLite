package com.thang.quanlisv.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thang.quanlisv.R;

public class Search extends Activity {
    private Button findCode, findName, findAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tìm kiếm");

        findCode = (Button) findViewById(R.id.findCode);
        findName = (Button) findViewById(R.id.findName);
        findAdress = (Button) findViewById(R.id.findAdress);

        findCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Search.this, SearchCode.class);
                startActivity(i);
            }
        });
        findName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Search.this, SearchName.class);
                startActivity(i);
            }
        });
        findAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Search.this, SearchDress.class);
                startActivity(i);
            }
        });
    }
}
