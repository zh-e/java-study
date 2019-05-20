package com.zhe.javabase.Thread.download;

import java.io.Closeable;

/**
 * @author zhangzhe
 */
public final class Tools {

    private Tools() {}

    public static void silentClose(Closeable... closeables) {
        if(null == closeables) {
            return;
        }
        for(Closeable c : closeables) {
            if(null == c) {
                continue;
            }
            try {
                c.close();
            } catch (Exception e) {
                //
            }
        }
    }

}
