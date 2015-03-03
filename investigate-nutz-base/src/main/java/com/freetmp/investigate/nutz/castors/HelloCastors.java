package com.freetmp.investigate.nutz.castors;

import org.nutz.castor.Castors;
import org.nutz.lang.Lang;
import org.nutz.lang.meta.Email;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LiuPin on 2015/3/3.
 */
public class HelloCastors {

    public static void main(String[] args) {
        System.out.println(Castors.me().castTo("563",int.class).getClass());
        System.out.println(Castors.me().castTo("liupin@gmail.com", Email.class).getClass());
        System.out.println(Castors.me().castTo("2015-3-3 14:12:54", Calendar.class).getClass());
        System.out.println(Castors.me().castTo(Calendar.getInstance(), Timestamp.class).getClass());

        List<String> ss = Lang.list("a","b","c");
        System.out.println(Castors.me().castTo(ss,String[].class).getClass());
    }

}
