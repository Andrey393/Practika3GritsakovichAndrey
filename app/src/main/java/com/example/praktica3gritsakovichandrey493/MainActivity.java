package com.example.praktica3gritsakovichandrey493;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity
{
    GraphView gv;
    static String text;
    static String textLink;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv=findViewById(R.id.graphView2);
        dialog=new Dialog(MainActivity.this);

    }
    public void onAddClick(View v)
    {
        gv.addNode();
    }

    public void copyNode(View v)
    {
        gv.copyNode();
    }
    public void addText(View v)
    {
        showDialog();
        Button buttonSave=dialog.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                EditText textBall=dialog.findViewById(R.id.editTextBall);
                text=textBall.getText().toString();
                gv.addText();

            }
        });

    }
    public void onRemoveClick(View v)
    {
       gv.removeSelectedNode();
    }

    public void addTextLink(View v)
    {
        showDialog();
        Button buttonSave=dialog.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                EditText text=dialog.findViewById(R.id.editTextBall);
                textLink=text.getText().toString();
                gv.addTetxLink();

            }
        });

    }
    private  void showDialog()
    {
        dialog.setContentView(R.layout.dialog_windows);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void onLinkClick(View v)
    {
        gv.linkSelectedNodes();
    }

    public void onRemoveLink(View v)
    {
        gv.removeSelectedLink();
    }

    public void DataBase(View v)
    {
        Intent intent= new Intent(MainActivity.this,MainActivityDataBase.class);
        startActivity(intent);

    }
}