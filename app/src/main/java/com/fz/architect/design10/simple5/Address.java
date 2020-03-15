package com.fz.architect.design10.simple5;

/**
 * Created by fz on 2017/10/21.
 */

public class Address  implements Cloneable{
    public Address(String addressName, String city) {
        this.addressName = addressName;
        this.city = city;
    }

    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    public String addressName;
    public String city;
}
