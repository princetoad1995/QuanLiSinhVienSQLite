package com.thang.quanlisv;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.thang.quanlisv.Activity.AddActivity;
import com.thang.quanlisv.Activity.ListStudentByCateFragment;
import com.thang.quanlisv.Activity.Search;


public class MainActivity extends Activity {
    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private ShareActionProvider shareActionProvider;
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        if (savedInstanceState!=null){
            currentPosition=savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        }else selectItem(0);
        drawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,
                R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fragmentManager=getFragmentManager();
                Fragment fragment=fragmentManager.findFragmentByTag("visible_fragment");
//                currentPosition=0;
                setActionBarTitle(currentPosition);
                drawerList.setItemChecked(currentPosition,true);
            }
        });
    }
    private void selectItem(int position){
        currentPosition=position;
        Fragment fragment;
        switch (position){
            case 1:
                fragment= new ListStudentByCateFragment(1,MainActivity.this,R.layout.item_student_activity);
                break;
            case 2:
                fragment= new ListStudentByCateFragment(2,MainActivity.this,R.layout.item_student_activity);
                break;
            case 3:
                fragment= new ListStudentByCateFragment(3,MainActivity.this,R.layout.item_student_activity);
                break;
            default:fragment=new ListStudentByCateFragment(0,MainActivity.this,R.layout.item_student_activity);
        }
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,fragment,"visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        setActionBarTitle(position);
        drawerLayout.closeDrawer(drawerList);
    }
    private void setActionBarTitle(int position){
        String title;
        if (position==0){
            title=titles[0];
        }else title=titles[position];
        getActionBar().setTitle(title);
    }
    private void connectView(){
        titles=getResources().getStringArray(R.array.titles);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList=(ListView)findViewById(R.id.drawer);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen=drawerLayout.isDrawerOpen(drawerList);
//        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",currentPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.action_add:
                Intent it=new Intent(this,AddActivity.class);
                startActivityForResult(it, 1);
                break;
            case R.id.action_search:
                Intent i = new Intent(this, Search.class);
                startActivity(i);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectItem(currentPosition);
    }


}