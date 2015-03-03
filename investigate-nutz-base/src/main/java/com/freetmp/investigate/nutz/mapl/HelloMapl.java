package com.freetmp.investigate.nutz.mapl;


import org.nutz.json.Json;
import org.nutz.lang.Stopwatch;
import org.nutz.mapl.Mapl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuPin on 2015/3/3.
 */
public class HelloMapl {
    public static void main(String[] args) {

        Stopwatch sw = Stopwatch.begin();

        // Mapl转对象
        String json = "{'name':'b', 'as':[{'name':'nutz','id':1},{'name':'jk','id':2}]}";
        final Object obj = Json.fromJson(json);
        B b = (B) Mapl.maplistToObj(obj,B.class);
        System.out.println(b);

        // 对象转Maplist
        A a = new A();
        a.name = "a";
        b = new B();
        b.name = "b";
        b.as = new ArrayList<>();
        b.as.add(a);
        System.out.println(Mapl.toMaplist(b));

        // 访问Maplist
        String name  = (String) Mapl.cell(obj,"as[1].name");
        System.out.println(name);

        // Maplist合并
        String json1 = "{'name':'nutz'}";
        String json2 = "{'age':12}";
        Object obj1 = Json.fromJson(json1);
        Object obj2 = Json.fromJson(json2);
        Object obj3 = Mapl.merge(obj1,obj2);
        System.out.println(obj3);

        // Maplist过滤
        json = "{name:'nutz', age:12, address:[{area:1,name:'abc'}, {area:2,name:'123'}]}";
        Object obj4 = Json.fromJson(json);
        List<String> list = new ArrayList<>();
        list.add("age");
        list.add("address[].area");
        System.out.println(Mapl.excludeFilter(obj4, list));
        System.out.println(Mapl.includeFilter(obj4,list));

        //Maplist结构转换
        json = "[{'name':'jk', 'age':12},{'name':'nutz', 'age':5}]";
        String model = "[{'name':['user[].姓名','people[].name'],'age':['user[].年龄','people[].age']}]";
        Object obj5 = Mapl.convert(Json.fromJson(json),new StringReader(model));
        System.out.println(obj5);

        sw.stop();
        System.out.println(sw);
    }
}
