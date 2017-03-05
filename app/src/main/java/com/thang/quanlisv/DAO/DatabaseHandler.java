package com.thang.quanlisv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.lang.reflect.Array;
import java.util.ArrayList;

import com.thang.quanlisv.Model.Student;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static int DATA_VERSION=1;
    private static String DATA_NAME="STUDENTSTORE.DB",
                            TABLE_NAME="Students",
                            _svId="studentId",
                            _svMa="studentMa",
                            _svName="studentName",
                            _svDress="studentDress",
                            _svCate="studentCate",
                            _svEmail="studentEmail",
                            _svPhone="studentPhone";

    public DatabaseHandler(Context context) {
        super(context, DATA_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB="CREATE TABLE "+TABLE_NAME+" ( "+_svId+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + _svMa+" TEXT, "
                        + _svName+" TEXT, "
                        + _svDress+" TEXT,"
                        +_svCate+" INTEGER,"
                        +_svEmail+" TEXT,"
                        +_svPhone+" TEXT)";
        db.execSQL(createDB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addStudent(Student student){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(_svMa, student.getSv_ma());
        values.put(_svName, student.getSv_name());
        values.put(_svDress, student.getSv_dress());
        values.put(_svCate, student.getSv_cate());
        values.put(_svEmail, student.getSv_email());
        values.put(_svPhone, student.getSv_phone());
        return db.insert(TABLE_NAME,null,values)>0;
    }

    public ArrayList<Student> getSearchContact(String keyword){
        ArrayList<Student> list= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{_svId, _svMa, _svName, _svCate, _svDress, _svEmail, _svPhone},
                _svName + " LIKE '%" + keyword + "%'",
                null, null, null, null, null);
        if (cursor!=null){
            Student student =null;
            while (cursor.moveToNext()){
                student = new Student(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
                list.add(student);
            }
        }
        return list;
    }

    public ArrayList<Student> getSearchMaSV(String keyword){
        ArrayList<Student> list= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{_svId, _svMa, _svName, _svCate, _svDress, _svEmail, _svPhone},
                _svMa + " LIKE '%" + keyword + "%'",
                null, null, null, null, null);
        if (cursor!=null){
            Student student =null;
            while (cursor.moveToNext()){
                student = new Student(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
                list.add(student);
            }
        }
        return list;
    }

    public ArrayList<Student> getSearchDress(String keyword){
        ArrayList<Student> list= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{_svId, _svMa, _svName, _svCate, _svDress, _svEmail, _svPhone},
                _svDress + " LIKE '%" + keyword + "%'",
                null, null, null, null, null);
        if (cursor!=null){
            Student student =null;
            while (cursor.moveToNext()){
                student = new Student(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
                list.add(student);
            }
        }
        return list;
    }

    public ArrayList<Student> getListByCate(int cate){
        ArrayList<Student> list= new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME+" WHERE "+_svCate+"="+cate;
        if (cate==0){
            sql="SELECT * FROM "+TABLE_NAME;
        }
        Cursor cursor=db.rawQuery(sql,null);
        if (cursor!=null){
            Student student =null;
            while (cursor.moveToNext()){
                student = new Student(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
                list.add(student);
            }
        }
        return list;
    }
    public Student getStudentByID(int id){
        Student student =null;
        String sql="SELECT * FROM "+TABLE_NAME+" WHERE "+_svId+"="+id;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if (cursor!=null){
            cursor.moveToFirst();
            student = new Student(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
        }
        return student;
    }
    public boolean updateStudent(Student student){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(_svMa, student.getSv_ma());
        values.put(_svName, student.getSv_name());
        values.put(_svDress, student.getSv_dress());
        values.put(_svCate, student.getSv_cate());
        values.put(_svEmail, student.getSv_email());
        values.put(_svPhone, student.getSv_phone());
        return db.update(TABLE_NAME,values,_svId+"="+ student.getSv_id(),null)>0;
    }
    public boolean deleteStudent(int id){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(TABLE_NAME,_svId+"="+id,null)>0;
    }
}
