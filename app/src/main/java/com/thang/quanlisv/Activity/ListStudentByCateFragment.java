package com.thang.quanlisv.Activity;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import com.thang.quanlisv.DAO.DatabaseHandler;
import com.thang.quanlisv.Model.Student;


public class ListStudentByCateFragment extends ListFragment {
    private int cateId;
    private Activity context;
    private int layout;
    private ArrayList<Student> list;
    private DatabaseHandler databaseHandler;

    public ListStudentByCateFragment(int cateId, Context context, int layout) {
        this.cateId=cateId;
        this.context= (Activity) context;
        this.layout=layout;
        databaseHandler = new DatabaseHandler(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        list= databaseHandler.getListByCate(cateId);
        ListItem listItem= new ListItem(context,layout,list);
        setListAdapter(listItem);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent it= new Intent(context,ViewStudentActivity.class);
        it.putExtra("studentId",list.get(position).getSv_id());
        startActivityForResult(it,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        list= databaseHandler.getListByCate(cateId);
        ListItem listItem= new ListItem(context,layout,list);
        setListAdapter(listItem);
    }
}
