package com.example.praktica3gritsakovichandrey493;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper
{
    private  Context context;
    private static  final  String DATABASE_NAME="Database.db";
    private static final int DATABASE_VERSION=1;

    private  static  final String TABLE_NAME="TableBall2";
    private static  final String COLUMN_ID="_id";
    private static  final String COLUMN_NAME="name";
    private  static  final String COLUMN_NOMER="nomer";

    private  static  final String TABLE_NODE="Node";
    private static  final String COLUMN_ID_NODE="id";
    private  static  final String COLUMN_NOMER_NODE="nomer";
    private static  final String COLUMN_X_NODE="x";
    private static  final String COLUMN_Y_NODE="y";
    private static  final String COLUMN_TEXTNAME="Name";

    private  static  final String TABLE_LINK="Link";
    private static  final String COLUMN_ID_LINK="id";
    private  static  final String COLUMN_NOMER_LINK="nomer";
    private static  final String COLUMN_NODE_A_LINK="a";
    private static  final String COLUMN_NODE_B_LINK="b";
    private static  final String COLUMN_TEXTLINK="Name";

    DB(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query ="CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NOMER + " INTEGER);";
        db.execSQL(query);

         query ="CREATE TABLE " + TABLE_NODE +
                " (" + COLUMN_ID_NODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COLUMN_NOMER_NODE + " INTEGER, " +
                 COLUMN_X_NODE + " DOUBLE, " +
                COLUMN_Y_NODE + " DOUBLE, " +
                COLUMN_TEXTNAME + " TEXT);";
        db.execSQL(query);

        query ="CREATE TABLE " + TABLE_LINK +
                " (" + COLUMN_ID_LINK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMER_LINK + " INTEGER, " +
                COLUMN_NODE_A_LINK + " INTEGER, " +
                COLUMN_NODE_B_LINK + " INTEGER, " +
                COLUMN_TEXTLINK + " Text);";
        db.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME ) ;
        onCreate(db);
    }

    void addDataNode(int nomer,double x,double y,String text)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv=new ContentValues();
        cv.put(COLUMN_NOMER_NODE,nomer);
        cv.put(COLUMN_X_NODE,x);
        cv.put(COLUMN_Y_NODE,y);
        cv.put(COLUMN_TEXTNAME,text);

        long result = db.insert(TABLE_NODE,null,cv);
        if(result ==-1)
        {
            Toast.makeText(context,"Ne",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"YEs",Toast.LENGTH_SHORT).show();
        }
    }

    void addDataLink(int nomer,int a,int b,String text)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv=new ContentValues();
        cv.put(COLUMN_NOMER_LINK,nomer);
        cv.put(COLUMN_NODE_A_LINK,a);
        cv.put(COLUMN_NODE_B_LINK,b);
        cv.put(COLUMN_TEXTLINK,text);
        long result = db.insert(TABLE_LINK,null,cv);
        if(result ==-1)
        {
            Toast.makeText(context,"Ne",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"YEs",Toast.LENGTH_SHORT).show();
        }
    }

    void addDataTable(String name,int nomer)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_NOMER,nomer);

        long result = db.insert(TABLE_NAME,null,cv);

        if(result ==-1)
        {
            Toast.makeText(context,"Ne",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"YEs",Toast.LENGTH_SHORT).show();
        }

    }

    void renameData(String row_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(COLUMN_NAME,MainActivityDataBase.nameBall.getText().toString());

        long result = db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if (result == -1)
        {
            Toast.makeText(context,"No Update!",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(context,"Update!",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteBall(String row_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        //long result2 = db.delete(TABLE_NODE,"nomer",new String[]{"976"});
        //0]ong result3= db.delete(TABLE_LINK,"nomer",new String[]{MainActivityDataBase.Nomer.toString()});
        if (result == -1)
        {
            Toast.makeText(context,"No Delete!",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(context,"Delete!",Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAlldata()
    {
        String query ="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor =null;
        if (db != null)
        {
           cursor= db.rawQuery(query,null);

        }
        return  cursor;
    }

    Cursor NomerDataBase()
    {
        String query ="SELECT * FROM " + TABLE_NAME + " WHERE name = " + "'" + MainActivityDataBase.nameBall.getText().toString() + "'" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =null;
        if (db != null)
        {
            cursor= db.rawQuery(query,null);

        }
        return  cursor;

    }

    Cursor LoadNode()
    {
        String query ="SELECT * FROM " + TABLE_NODE + " WHERE nomer = " + "'" + MainActivityDataBase.Nomer + "'" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =null;
        if (db != null)
        {
            cursor= db.rawQuery(query,null);

        }
        return  cursor;

    }
    Cursor LoadLink()
    {
        String query ="SELECT * FROM " + TABLE_LINK + " WHERE nomer = " + "'" + MainActivityDataBase.Nomer + "'" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =null;
        if (db != null)
        {
            cursor= db.rawQuery(query,null);

        }
        return  cursor;

    }

}
