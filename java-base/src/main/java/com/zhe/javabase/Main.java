package com.zhe.javabase;

public class Main {

    public static void main(String[] args) {
       String str = "aaa=12 and bbb = 123 and ccc    = 45";
       str = str.replaceAll("(\\w+\\s*=)", "@tableA.$1=");

        System.out.println(str);
    }

}
