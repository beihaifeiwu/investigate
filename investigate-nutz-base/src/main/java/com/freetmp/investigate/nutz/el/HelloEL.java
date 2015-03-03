package com.freetmp.investigate.nutz.el;

import org.nutz.conf.NutConf;
import org.nutz.el.El;
import org.nutz.el.opt.custom.CustomMake;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.Context;

import java.math.BigDecimal;

/**
 * Created by LiuPin on 2015/3/3.
 */
public class HelloEL {
    public static void main(String[] args) {

        System.out.println(El.eval("3+4*5").equals(23));

        Context context = Lang.context();
        context.set("a",10);
        System.out.println(El.eval(context,"a*10"));

        // 预编译
        El exp = new El("a*10");
        System.out.println(exp.eval(context));

        // 自定义函数
        NutConf.load("el/conf.js");
        CustomMake.init();

        System.out.println(El.eval("hello('Jack','Rose')"));

        // 对象方法调用
        context.set("a",new BigDecimal("7"));
        context.set("b",new BigDecimal("3"));
        System.out.println(El.eval(context,"a.add(b).intValue()"));

        // 静态方法调用
        context.set("strings", Strings.class);
        System.out.println(El.eval(context,"strings.trim(\"   nutz    \")"));

        // 字符串操作
        System.out.println(El.eval("trim(\"  abc  \")"));

        // 数组访问
        context.set("array",Lang.array("A","B","C"));
        System.out.println(El.eval(context,"array[0].toLowerCase()"));

        // 列表访问
        context.set("list",Lang.list("A","B","C"));
        System.out.println(El.eval(context,"list.get(0).toLowerCase()"));

        // Map访问
        context.set("map",Lang.map("{x:10,y:5}"));
        System.out.println(El.eval(context,"map['x'] * map['y']"));

        // 判断
        context.set("a",5);
        System.out.println(El.eval(context,"a>10"));

        context.set("a",20);
        System.out.println(El.eval(context,"a>10"));

    }
}
