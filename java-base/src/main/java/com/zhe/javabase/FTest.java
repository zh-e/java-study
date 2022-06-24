package com.zhe.javabase;

public class FTest implements Cloneable{

    private int f;

    public int f1;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
