package com.fz.architect.design08.simple4;

/**
 * Created by fz on 2017/10/14.
 */

public class Member {
    private String name;
    private String age;

    public Member(String name,String age){
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
