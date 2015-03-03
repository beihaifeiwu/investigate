package com.freetmp.investigate.nutz.dao;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

/**
 * Created by LiuPin on 2015/2/28.
 */
public class HelloDao {

    public static void main(String[] args) throws ClassNotFoundException {
        SimpleDataSource ds = new SimpleDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/nutz_test");
        ds.setUsername("postgres");
        ds.setPassword("123456");

        Dao dao = new NutDao(ds);
        dao.create(Person.class,true); //生产环境你可千万别这样写!!

        Person p = new Person();
        p.setName("Peter");
        p.setAge(20);
        dao.insert(p);

        System.out.println(p);

        p = dao.fetch(Person.class,"Peter");
        System.out.println(p);

        p = dao.fetch(Person.class,1);
        System.out.println(p);

        p.setAge(32);
        dao.update(p);

        dao.delete(Person.class,"Peter");
    }
}
