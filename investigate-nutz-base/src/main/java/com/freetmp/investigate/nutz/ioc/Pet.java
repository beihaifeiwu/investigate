package com.freetmp.investigate.nutz.ioc;

import java.util.Calendar;

public class Pet {

    private String name;

    private Calendar birthday;

    private Pet friend;

    public Pet() {}

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Pet getFriend() {
        return friend;
    }

    public void setFriend(Pet friend) {
        this.friend = friend;
    }
}