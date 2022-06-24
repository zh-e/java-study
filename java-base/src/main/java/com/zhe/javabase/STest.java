package com.zhe.javabase;

public class STest extends FTest{

    public static void main(String[] args) throws CloneNotSupportedException {
        FTest fTest = new FTest();
        STest sTest = (STest) fTest.clone();

    }

}
