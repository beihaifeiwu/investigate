package com.freetmp.investigate.ognl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlContext;

public class OGNL3  
{  
    public static void main(String[] args) throws Exception  
    {  
        OgnlContext context = new OgnlContext();  
          
        Classroom classroom = new Classroom();  
        classroom.getStudents().add("zhangsan");  
        classroom.getStudents().add("lisi");  
        classroom.getStudents().add("wangwu");  
        classroom.getStudents().add("zhaoliu");  
        classroom.getStudents().add("qianqi");  
          
        Student student = new Student();  
        student.getContactWays().put("homeNumber", "110");  
        student.getContactWays().put("companyNumber", "119");  
        student.getContactWays().put("mobilePhone", "112");  
          
        context.put("classroom", classroom);  
        context.put("student", student);  
        context.setRoot(classroom);  
 
        /* 获得classroom的students集合 */ 
        Object collection = Ognl.getValue("students", context, context.getRoot());  
        System.out.println("students collection is ：" + collection);  
 
        /* 获得classroom的students集合 */ 
        Object firstStudent = Ognl.getValue("students[0]", context, context.getRoot());  
        System.out.println("first student is : " + firstStudent);  
 
        /* 调用集合的方法 */ 
        Object size = Ognl.getValue("students.size()", context, context.getRoot());  
        System.out.println("students collection size is :" + size);  
 
        System.out.println("--------------------------飘逸的分割线--------------------------");  
          
        Object mapCollection = Ognl.getValue("#student.contactWays", context, context.getRoot());  
        System.out.println("mapCollection is :" + mapCollection);  
 
        Object firstElement = Ognl.getValue("#student.contactWays['homeNumber']", context, context.getRoot());  
        System.out.println("the first element of contactWays is :" + firstElement);  
 
        System.out.println("--------------------------飘逸的分割线--------------------------");  
 
        /* 创建集合 */ 
        Object createCollection = Ognl.getValue("{'aa','bb','cc','dd'}", context, context.getRoot());  
        System.out.println(createCollection);  
 
        /* 创建Map集合 */ 
        Object createMapCollection = Ognl.getValue("#{'key1':'value1','key2':'value2'}", context, context.getRoot());  
        System.out.println(createMapCollection);  

        System.out.println("--------------------------飘逸的分割线--------------------------");  
        
        OgnlContext oc = new OgnlContext();
        oc.setRoot(student.getContactWays());
        
        Object homeNumber = Ognl.getValue("homeNumber", oc, oc.getRoot());
        System.out.println("the home number is :" + homeNumber);
    }  
}  
 
class Classroom  
{  
    private List<String> students = new ArrayList<String>();  
 
    public List<String> getStudents()  
    {  
        return students;  
    }  
 
    public void setStudents(List<String> students)  
    {  
        this.students = students;  
    }  
}  
 
class Student  
{  
    private Map<String , Object> contactWays = new HashMap<String , Object>();  
 
    public Map<String , Object> getContactWays()  
    {  
        return contactWays;  
    }  
 
    public void setContactWays(Map<String , Object> contactWays)  
    {  
        this.contactWays = contactWays;  
    }  
}