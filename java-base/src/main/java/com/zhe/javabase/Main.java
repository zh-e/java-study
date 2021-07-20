package com.zhe.javabase;

import org.xxtea.XXTEA;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Main {

    private static final String KEY = "hall.58hld-YBHD-PK";

    private static final String KEY_1 = "gcf3E3uDI&tCA&2u";


    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "Bgn//f0C1TfbUar3FiOr8bSSiQJUtFWk02YkIoz5JyswVDJsFmxlgF8XsRLHiKr8iV73lfQmfsn52VroIZrLnQQo3vJhAH7gSVTLdmBFUUizf/tVYP6UrBcsXoR+de00BiWWZtmecFxMA/FKtdeppA==";
        System.out.println(req(str));
    }

    public static String req(String str) {
        return XXTEA.decryptBase64StringToString(str, KEY);
    }

    public static String resp(String str) {
        try {
            String a = URLDecoder.decode(str, "utf-8");
            return XXTEA.decryptBase64StringToString(a, KEY);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
