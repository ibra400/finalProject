package com.codingelab.tutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * done by Ibrahim on 25/12/2019.
 */

public class DBHelper extends SQLiteOpenHelper {
    // Arguments. database_id. The identification number (ID) of the database whose name DB_NAME will return.
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;
    //special method that is used to initialize objects. The constructor is called when an object of a class is created.
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text,email text)"//, street text,place text)"
        );
    }
    // when version of your DB changed which means underlying table structure changed etc.
    //Called when the database needs to be upgraded. The implementation should use this method to drop tables.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
        //inserts a new contact in the database.
    public boolean insertContact  (String name, String phone, String email)//, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        db.insert("contacts", null, contentValues);
        return true;
    }
        //the cursor stores the rows of data returned by the query along with a position that points to the current row of data in the result set.
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
        // stores the rows of max data returned that points to the current row of data in the result set.
    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id=(select Max(id) from contacts)", null );
        return res;
    }
        // select row as id entered from contacts
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select rowid as _id,* from contacts", null );
        return res;
    }
        // didn't use it.
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }
        // update using id .
    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
        // deleted content from table where id = to.
    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
        // getting all ids.
    public ArrayList<String> getAllContactsIDs()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }       // getting all names.
    public ArrayList<String> getAllContactsNames()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }       // getting all phones.
    public ArrayList<String> getAllContactsPhones()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_PHONE)));
            res.moveToNext();
        }
        return array_list;
    }       // getting all emails .
    public ArrayList<String> getAllContactsEmails()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
            res.moveToNext();
        }
        return array_list;
    }       // detected were name and id .
    public Cursor getData3(String s){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where name like'%"+s+"%' or email like'%"+s+"%'", null );
        return res;
    }       // Search by Words in local db.
    public List<Contacts> SearchWords(String searchWord){
        List<Contacts> mItems = new ArrayList<Contacts>();

        String query = "Select * from contacts where name like " + "'%" + searchWord + "%'";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null); ArrayList<String> wordTerms = new ArrayList<String>(); if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));

                mItems.add(new Contacts(id,name)); }while(cursor.moveToNext());

        }
        cursor.close();

        return mItems;
    }


}
