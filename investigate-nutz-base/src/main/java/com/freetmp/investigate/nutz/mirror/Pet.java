package com.freetmp.investigate.nutz.mirror;

import java.util.Calendar;

/**
 * Created by LiuPin on 2015/3/3.
 */
public class Pet {
    private String name;

    private int age;

    private Calendar birthday;

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Pet(String name){
        this.name = name;
    }

    public  Pet(String name,int age){
        this.name = name;
        this.age = age;
    }

    public Pet(String name, Calendar birthday, Object mark){
        this.name = name;
        this.birthday = birthday;
        this.age = Calendar.getInstance().get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    public static Pet create(String name){
        return new Pet("Pet:" + name);
    }

    public void say(String word){
        System.out.println(this.name + " says :\"" + word + "\"");
    }
}
