package com.example.praktica3gritsakovichandrey493;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivityDataBase extends AppCompatActivity {

    RecyclerView recyclerView;
    public static EditText nameBall;

    DB mydb;
    ArrayList<String> idList,nameList,nomerList;
    CustomAdapter recyclerAdapter;

    ArrayList<String> nomer;
    ArrayList<String> x,y,textNode;
    ArrayList<String> a,b,textLink;
    static  Integer Nomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_base);
        nameBall =findViewById(R.id.editTextNameBase);
        recyclerView=findViewById(R.id.recyclerViewDataBase);
        mydb= new DB(MainActivityDataBase.this);
        nomer = new ArrayList<>();

        idList=new ArrayList<>();
        nameList=new ArrayList<>();
        nomerList=new ArrayList<>();

        x=new ArrayList<>();
        y=new ArrayList<>();
        textNode=new ArrayList<>();

        a=new ArrayList<>();
        b=new ArrayList<>();
        textLink=new ArrayList<>();

        displayData();

        recyclerAdapter =new CustomAdapter(MainActivityDataBase.this,idList,nameList,nomerList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityDataBase.this));
    }
    void displayData()
    {
        Cursor cursor=mydb.readAlldata();
        if (cursor.getCount()==0)
        {
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else
        {
        while(cursor.moveToNext())
        {
            idList.add(cursor.getString(0));
            nameList.add(cursor.getString(1));
            nomerList.add(cursor.getString(2));
        }
        }
    }

    public void saveBall(View v)
    {
        Random random= new Random();
        int nomer = (int) (Math.random() * 9999);

        DB mybd=new DB(MainActivityDataBase.this);

        if(Graph.node.size()!=0)
        {
            for (int i=0;i<Graph.node.size();i++)
            {
                mybd.addDataNode(nomer,Graph.node.get(i).x,Graph.node.get(i).y,Graph.node.get(i).textname.toString().trim());
            }

        }
        if(Graph.link.size()!=0)
        {
            for (int i=0;i<Graph.link.size();i++)
            {
                mybd.addDataLink(nomer,Graph.link.get(i).a,Graph.link.get(i).b,Graph.link.get(i).textLink);
            }

        }
        mybd.addDataTable(nameBall.getText().toString().trim(),nomer);

    }
    void Nomer()
    {

        Cursor cursor = mydb.NomerDataBase();
        if (cursor.getCount() == 0) {

        } else {
            while (cursor.moveToNext()) {
                nomer.add(cursor.getString(2));
            }
            Nomer = Integer.valueOf(nomer.get(0).trim());

        }
    }
        public void LoadData(View v)
        {
            nomer.clear();
            x.clear();
            y.clear();
            textNode.clear();
            Graph.link.clear();
            Graph.node.clear();

            Nomer();
            Cursor cursor = mydb.NomerDataBase();
            cursor = mydb.LoadNode();
            if (cursor.getCount() == 0) {

            } else {
                while (cursor.moveToNext()) {
                    x.add(cursor.getString(2));
                    y.add(cursor.getString(3));
                    textNode.add(cursor.getString(4));
                }

                for (int j = 0; j < x.size(); j++) {
                    Graph.LoadNode(Float.valueOf(x.get(j)), Float.valueOf(y.get(j)), textNode.get(j));
                }

            }

            cursor = mydb.LoadLink();
            if (cursor.getCount() == 0) {

            } else {
                while (cursor.moveToNext()) {
                    a.add(cursor.getString(2));
                    b.add(cursor.getString(3));
                    textLink.add(cursor.getString(4));
                }

                for (int j = 0; j < a.size(); j++) {
                    Graph.LoadLink(Integer.valueOf(a.get(j)), Integer.valueOf(b.get(j)), textLink.get(j));
                }

            }

            Toast.makeText(this, "Успешно загрузили", Toast.LENGTH_SHORT).show();

        }

        public void createBase(View v)
        {
            Graph.link.clear();
            Graph.node.clear();
            this.finish();


        }
    public  void renameBall(View v)
    {
        DB mydb= new DB(MainActivityDataBase.this);
        mydb.renameData(CustomAdapter.idBall);
    }
    public void deleteBall(View v)
    {
        DB mydb= new DB(MainActivityDataBase.this);
        mydb.deleteBall(CustomAdapter.idBall);
    }
}