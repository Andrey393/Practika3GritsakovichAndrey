package com.example.praktica3gritsakovichandrey493;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    Context context;
    ArrayList id,name,nomer;

    public static String textNameDo,idBall;



    CustomAdapter(Context context, ArrayList id, ArrayList name,ArrayList nomer)
    {
        this.context=context;
        this.id=id;
        this.name=name;
        this.nomer=nomer;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.recyler_row, parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)//
    {

        holder.text_id.setText(String.valueOf(id.get(position)));
        holder.text_name.setText(String.valueOf(name.get(position)));
        holder.text_nomer.setText(String.valueOf(nomer.get(position)));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                idBall=id.get(position).toString();
                textNameDo=name.get(position).toString();
                MainActivityDataBase.nameBall.setText(name.get(position).toString());

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return id.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView text_id,text_name,text_nomer;
        LinearLayout mainlayout;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            text_id=itemView.findViewById(R.id.textViewID);
            text_name=itemView.findViewById(R.id.textViewName);
            text_nomer=itemView.findViewById(R.id.textViewNomer);
            mainlayout=itemView.findViewById(R.id.baseLayot);
        }
    }
}
