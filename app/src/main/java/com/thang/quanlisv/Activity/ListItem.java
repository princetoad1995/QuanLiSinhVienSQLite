package com.thang.quanlisv.Activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.thang.quanlisv.DAO.DatabaseHandler;
import com.thang.quanlisv.Model.Student;
import com.thang.quanlisv.R;


public class ListItem extends ArrayAdapter<Student> {
    private Activity context;
    private int layout;
    private List<Student> list;
    public ListItem(Context context, int resource,ArrayList<Student> list) {
        super(context, resource,list);
        this.context= (Activity) context;
        this.layout=resource;
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(layout, parent, false);
        TextView tvBookName=(TextView)view.findViewById(R.id.tvName);
        Student student =list.get(position);
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        tvBookName.setText(student.getSv_name());
        return view;
    }
}
