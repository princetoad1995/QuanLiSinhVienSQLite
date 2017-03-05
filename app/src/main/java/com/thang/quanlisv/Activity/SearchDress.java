package com.thang.quanlisv.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thang.quanlisv.R;

public class SearchDress extends Activity {
    private EditText inputDress;
    private Button btnFindDress;

    String keyword = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dress);

        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tìm kiếm theo nơi sinh");

        inputDress = (EditText) findViewById(R.id.inputDress);
        btnFindDress = (Button) findViewById(R.id.btnFindDress);
        btnFindDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = inputDress.getText().toString();
                Intent i = new Intent(SearchDress.this, ShowListDress.class);
                i.putExtra("inputDress", keyword);
                startActivity(i);
            }
        });
    }
}
