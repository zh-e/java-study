package com.zhe.javabase;

public class Main {

    public static void main(String[] args) {

        String a = "12131";

        StringBuilder sb = new StringBuilder();
        sb.append(a, 0, 1);
        System.out.println(sb.toString());
    }

}
