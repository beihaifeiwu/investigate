package com.freetmp.investigate.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OGNL2  
{  
    public static void main(String[] args)  
    {  
        /* OGNL提供的一个上下文类，它实现了Map接口 */ 
        OgnlContext context = new OgnlContext();  
 
        People people1 = new People();  
        people1.setName("zhangsan");  
 
        People people2 = new People();  
        people2.setName("lisi");  
 
        People people3 = new People();  
        people3.setName("wangwu");  
 
        context.put("people1", people1);  
        context.put("people2", people2);  
        context.put("people3", people3);  
          
        context.setRoot(people1);  
 
        try 
        {  
            /* 调用 成员方法 */ 
            Object value = Ognl.getValue("name.length()", context, context.getRoot());  
            System.out.println("people1 name length is :" + value);  
              
            Object upperCase = Ognl.getValue("#people2.name.toUpperCase()", context, context.getRoot());  
            System.out.println("people2 name upperCase is :" + upperCase);  
 
            Object invokeWithArgs = Ognl.getValue("name.charAt(5)", context, context.getRoot());  
            System.out.println("people1 name.charAt(5) is :" + invokeWithArgs);  
 
            /* 调用静态方法 */ 
            Object min = Ognl.getValue("@java.lang.Math@min(4,10)", context, context.getRoot());  
            System.out.println("min(4,10) is :" + min);  
 
            /* 调用静态变量 */ 
            Object e = Ognl.getValue("@java.lang.Math@E", context, context.getRoot());  
            System.out.println("E is :" + e);  
            
            /* 条件判断 */
            Object res = Ognl.getValue("name != null", context,context.getRoot());
            System.out.println("name != null : " + res);
        }  
        catch (OgnlException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}  
 
class People  
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