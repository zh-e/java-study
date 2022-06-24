package com.zhe.javabase.netty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./file01.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("./file02.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        fileChannel01.transferFrom(fileChannel01, 0, fileChannel02.size());

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        while (true) {
            byteBuffer.clear();
            int read = fileChannel01.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();


    }

}
