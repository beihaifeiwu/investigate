package com.freetmp.investigate.ognl;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

public class OGNL1  
{  
    public static void main(String[] args)  
    {  
        /* 创建一个上下文Context对象，它是用保存多个对象一个环境 对象 */ 
        Map<String , Object> context = new HashMap<String , Object>();  
 
        Person person1 = new Person();  
        person1.setName("zhangsan");  
          
        Person person2 = new Person();  
        person2.setName("lisi");  
 
        Person person3 = new Person();  
        person3.setName("wangwu");  
 
        /* person4不放入到上下文环境中 */ 
        Person person4 = new Person();  
        person4.setName("zhaoliu");  
 
        /* 将person1、person2、person3添加到环境中（上下文中） */ 
        context.put("person1", person1);  
        context.put("person2", person2);  
        context.put("person3", person3);  
       // context.put("person4", person4);
        try 
        {  
            /* 获取根对象的"name"属性值 */ 
            Object value = Ognl.getValue("name", context, person2);  
            System.out.println("ognl expression \"name\" evaluation is : " + value);  
 
            /* 获取根对象的"name"属性值 */ 
            Object value2 = Ognl.getValue("#person2.name", context, person2);  
            System.out.println("ognl expression \"#person2.name\" evaluation is : " + value2);  
 
            /* 获取person1对象的"name"属性值 */ 
            Object value3 = Ognl.getValue("#person1.name", context, person2);  
            System.out.println("ognl expression \"#person1.name\" evaluation is : " + value3);  
 
            /* 将person4指定为root对象，获取person4对象的"name"属性，注意person4对象不在上下文中 */ 
            Object value4 = Ognl.getValue("name", context, person4);  
            System.out.println("ognl expression \"name\" evaluation is : " + value4);  
 
            /* 将person4指定为root对象，获取person4对象的"name"属性，注意person4对象不在上下文中 */ 
            Object value5 = Ognl.getValue("#person4.name", context, person4);  
            System.out.println("ognl expression \"person4.name\" evaluation is : " + value5);  
 
            /* 获取person4对象的"name"属性，注意person4对象不在上下文中 */ 
            // Object value6 = Ognl.getValue("#person4.name", context, person2);  
            // System.out.println("ognl expression \"#person4.name\" evaluation is : " + value6);  
 
        }  
        catch (OgnlException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}  
 
class Person  
{  
    private String name;  
 
    public String getName()  
    {  
        return name;  
    }  
 
    public void setName(String name)  
    {  
        this.name = name;  
    }  
} 