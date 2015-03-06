package com.freetmp.investigate.nutz.mirror;

import org.nutz.lang.Dumps;
import org.nutz.lang.Mirror;
import org.nutz.lang.born.Borning;

/**
 * Created by LiuPin on 2015/3/3.
 */
public class HelloMirror {
    public static void main(String[] args) {

        // 自动判断构造函数
        Pet p1 = Mirror.me(Pet.class).born("XiaoBai",3);
        System.out.println(Dumps.obj(p1));

        // 自动判断工厂方法
        Pet p2 = Mirror.me(Pet.class).born("XiaoHei");
        System.out.println(Dumps.obj(p2));

        // 自动转换类型
        Pet p3 = Mirror.me(Pet.class).born("XiaoBai","2008-10-12 12:23:24",new Object());
        System.out.println(Dumps.obj(p3));

        // 缓存构造方法
        Borning<Pet> borning = Mirror.me(Pet.class).getBorning("XiaoBai");
        Pet xb = borning.born("XiaoBai");
        Pet xh = borning.born("XiaoHei");
        System.out.println(Dumps.obj(xb));
        System.out.println(Dumps.obj(xh));

        // 调用一个函数
        Mirror.me(Pet.class).invoke(xh, "say", "wang wang");
        Mirror.me(Pet.class).invoke(xh,"say",123456);

        // 设置字段的值
        Mirror.me(Pet.class).setValue(xb,"age",4);
        System.out.println(Dumps.obj(xb));
    }
}
