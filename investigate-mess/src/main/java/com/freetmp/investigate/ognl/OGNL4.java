package com.freetmp.investigate.ognl;

import java.util.ArrayList;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlContext;

public class OGNL4  
{  
    public static void main(String[] args) throws Exception  
    {  
        OgnlContext context = new OgnlContext();  
 
        Humen humen = new Humen();  
        humen.setName("qiuyi");  
        humen.setSex("n");  
        humen.setAge(22);  
        humen.getFriends().add(new Humen("zhangsan" , "n" , 22));  
        humen.getFriends().add(new Humen("lisi" , "f" , 21));  
        humen.getFriends().add(new Humen("wangwu" , "n" , 23));  
        humen.getFriends().add(new Humen("zhaoliu" , "n" , 22));  
        humen.getFriends().add(new Humen("qianqi" , "n" , 22));  
        humen.getFriends().add(new Humen("sunba" , "f" , 20));  
        humen.getFriends().add(new Humen("yangqiu" , "f" , 25));  
          
        context.put("humen", humen);  
        context.setRoot(humen);  
 
        /* OGNL过滤集合的语法为：collection.{? expression} */ 
        Object filterCollection = Ognl.getValue("friends.{? #this.name.length() > 7}", context, context.getRoot());  
        System.out.println("filterCollection is :" + filterCollection);  
 
        System.out.println("--------------------------飘逸的分割线--------------------------");  
 
        /* OGNL投影集合的语法为：collection.{expression} */ 
        Object projectionCollection = Ognl.getValue("friends.{name}", context, context.getRoot());  
        System.out.println("projectionCollection is :" + projectionCollection);  
    }  
}  
 
class Humen  
{  
    private String name;  
    private String sex;  
    private int age;  
    private List<Humen> friends = new ArrayList<Humen>();  
 
    public Humen()  
    {  
 
    }  
 
    public Humen(String name , String sex , int age)  
    {  
        this.name = name;  
        this.sex = sex;  
        this.age = age;  
    }  
 
    public String getName()  
    {  
        return name;  
    }  
 
    public void setName(String name)  
    {  
        this.name = name;  
    }  
 
    public String getSex()  
    {  
        return sex;  
    }  
 
    public void setSex(String sex)  
    {  
        this.sex = sex;  
    }  
 
    public int getAge()  
    {  
        return age;  
    }  
 
    public void setAge(int age)  
    {  
        this.age = age;  
    }  
 
    public List<Humen> getFriends()  
    {  
        return friends;  
    }  
 
    public void setFriends(List<Humen> friends)  
    {  
        this.friends = friends;  
    }  
 
    @Override 
    public String toString()  
    {  
        return "Humen [name=" + name + ", sex=" + sex + ", age=" + age + "]";  
    }  
} 