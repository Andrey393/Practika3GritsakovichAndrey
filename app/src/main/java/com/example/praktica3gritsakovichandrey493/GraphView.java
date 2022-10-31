package com.example.praktica3gritsakovichandrey493;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GraphView extends SurfaceView
{
    Graph g =new Graph();
    Paint p;

    int selected1 = -1;
    int selected2 = -1;
    int lasthit = -1;



    float rad = 70.0f;
    float halfside = 5.0f;

    float lastX;
    float lastY;

    public void addNode()
    {
        g.addNode(500,500);//местоположение шарика
        invalidate();
    }
    public void addText()//2
    {
        if(selected1<0)return;
        g.addText(selected1,MainActivity.text);
        invalidate();
    }
    public void removeSelectedNode()
    {

        if (selected1 < 0) return;
        ArrayList<Link> involvedLinks = new ArrayList<Link>();
        for (int i = 0; i < g.link.size();i++)
        {
            Link l = g.link.get(i);
            if (g.link.get(i).a == selected1 || g.link.get(i).b == selected1)
            {
                involvedLinks.add(l);
            }
        }
        g.link.removeAll(involvedLinks);
        g.removeNode(selected1);
        selected1 =-1;
        selected2 =-1;
        invalidate();

    }
    public void linkSelectedNodes()
    {
        if(selected1 < 0) return;
        if(selected2 < 0) return;
        g.addLink(selected1,selected2);
        invalidate();
    }
    public void addTetxLink()
    {
        if(selected1 < 0) return;
        if(selected2 < 0) return;
        g.addTextLink(selected1,selected2,MainActivity.textLink);
    }
    public void removeSelectedLink()
    {
        if(selected1 < 0) return;
        if(selected2 < 0) return;
        g.removeLink(selected1,selected2);
        invalidate();
    }

    public void copyNode()
    {
        if(selected1 < 0) return;
        g.copyNode(selected1);
        invalidate();
    }

    public GraphView(Context context,AttributeSet attrs)
    {
        super(context,attrs);
        p=new Paint();
        p.setAntiAlias(true);
        setWillNotDraw(false);
    }
    public  int getNodeAtXY(float x,float y)
    {
        for (int i = g.node.size() - 1; i>=0; i--)
        {
            Node n = g.node.get(i);
            float dx = x-n.x;
            float dy=y- n.y;
            if (dx * dx + dy * dy <= rad * rad) return i;
        }
        return  -1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        float x = event.getX();
        float y= event.getY();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                int i =getNodeAtXY(x,y);
                lasthit = i;
                if(i < 0)
                {
                  selected1 =-1;
                  selected2 =-1;
                }
                else
                {
                    if(selected1 >= 0) selected2=i;
                    else selected1 = i;
                }
                lastX = x;
                lastY = y;
                invalidate();
                return  true;

            case MotionEvent.ACTION_UP:break;

            case MotionEvent.ACTION_MOVE:
            {
                if(lasthit>=0)
                {
                 Node n =g.node.get(lasthit);
                 n.x += x-lastX;
                 n.y += y-lastY;
                 invalidate();

                }
                lastX = x;
                lastY = y;
                return  true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        canvas.drawColor(Color.rgb(255,255,255));

        for (int i = 0;i< g.link.size();i++)
        {

            Link l = g.link.get(i);
            Node na = g.node.get(l.a);
            Node nb = g.node.get(l.b);

            p.setStrokeWidth(2);
            p.setColor(Color.argb(127,0,0,0));
            canvas.drawLine(na.x,na.y,nb.x,nb.y, p);

            float bx=(na.x + nb.x) * 0.5f;
            float by=(na.y + nb.y) * 0.5f;
            float x0 = bx - halfside;
            float x1 = bx + halfside;
            float y0 = by - halfside;
            float y1 = by + halfside;

            if(l.textLink !="")
            {
                p.setTextSize(35);
                p.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(l.textLink, x0,y0,p);
            }
            float angle = 45,anglerad, radius = 30, lineangle;
            anglerad= (float) (Math.PI*angle/180.0f);
            lineangle= (float) (Math.atan2(na.y-nb.y,na.x-nb.x));

            Path path = new Path();
            p.setStrokeWidth(3);
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(na.x, na.y);
            path.lineTo((float)(na.x-radius*Math.cos(lineangle - (anglerad / 2.0))),
                    (float)(na.y-radius*Math.sin(lineangle - (anglerad / 2.0))));
            path.lineTo((float)(na.x-radius*Math.cos(lineangle + (anglerad / 2.0))),
                    (float)(na.y-radius*Math.sin(lineangle + (anglerad / 2.0))));
            path.close();

            canvas.drawPath(path, p);


        }

        for(int i =0; i< g.node.size();i++)
        {
            Node n = g.node.get(i);


            p.setStyle(Paint.Style.FILL);

            if(i == selected1)p.setColor(Color.argb(50,127,0,255));
            else if (i == selected2)p.setColor(Color.argb(50,255,0,50));
            else  p.setColor(Color.argb(50,0,127,255));

            canvas.drawCircle(n.x, n.y, rad, p);

            p.setStyle(Paint.Style.STROKE);
            if(i == selected1)p.setColor(Color.rgb(127,0,255));
            else if (i == selected2)p.setColor(Color.rgb(255,0,50));
            else  p.setColor(Color.rgb(0,127,255));

            canvas.drawCircle(n.x, n.y, rad, p);

            if(n.textname !="")
            {
                p.setTextSize(30);
                p.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(n.textname, n.x,n.y+100,p);
            }
        }
        //super.onDraw(canvas);
    }

    public GraphView(Context context)
    {
        super(context);
    }


}
