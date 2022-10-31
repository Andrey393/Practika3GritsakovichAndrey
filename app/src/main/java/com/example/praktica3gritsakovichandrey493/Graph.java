package com.example.praktica3gritsakovichandrey493;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Graph
{
    public static ArrayList<Node> node=new ArrayList<Node>();
    public static ArrayList<Link> link=new ArrayList<Link>();

    static public void LoadNode(float x,float y ,String text)
    {
        node.add(new Node(x,y,text));
    }

    static public void LoadLink(int a,int b ,String text)
    {
        for (int i=0;i<link.size();i++)
        {
            if(link.get(i).a==a && link.get(i).b ==b ||link.get(i).a==b && link.get(i).b ==a )
            {
                return;
            }
        }
        link.add(new Link(a,b,text));
    }


  public void addNode(float x,float y)
  {
      node.add(new Node(x,y,""));
  }
  public void addText(int index,String text)
  {
     if(index<0)return;
      node.get(index).textname=text;
  }

    public Node getNode (int index)
    {
        if (index < 0) return null;
        return node.get(index);
    }


    public  void removeNode(int index)
  {
      if (index < 0) return;
      node.remove(index);

  }

  public  void addLink(int x,int y)
  {
      for (int i=0;i<link.size();i++)
      {
          if(link.get(i).a==x && link.get(i).b ==y ||link.get(i).a==y && link.get(i).b ==x )
          {
              return;
          }
      }
       link.add(new Link(x,y,""));
  }
  public void addTextLink(int x,int y,String textLink)
  {
      if(x<0)return;
      if(y<0)return;
      for (int i=0;i<link.size();i++)
      {
          if(link.get(i).a==x && link.get(i).b ==y ||link.get(i).a==y && link.get(i).b ==x )
          {

              link.get(i).textLink=textLink;
              return;
          }
      }

  }

  public  void removeLink(int x,int y)
  {

      for (int i=0;i<link.size();i++)
      {
          if(link.get(i).a==x && link.get(i).b ==y ||link.get(i).a==y && link.get(i).b ==x )
          {

              link.remove(i);
              return;
          }
      }

  }

  public  void copyNode(int index)
  {
      node.add(new Node(node.get(index).x,node.get(index).y,node.get(index).textname));

  }
}
