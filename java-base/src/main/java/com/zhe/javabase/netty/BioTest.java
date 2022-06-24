package com.zhe.javabase.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioTest {

    public static void main(String[] args) throws IOException {

        ExecutorService newCachedExecutorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动");
        while (true) {
            final Socket socket = serverSocket.accept();

            newCachedExecutorService.execute(() -> {
                read(socket);
            });

        }
    }

    private static void read(Socket socket) {
        byte[] bytes = new byte[1024];
        try(InputStream inputStream = socket.getInputStream()) {

            while (true) {
                int read = inputStream.read(bytes);
                if (read == -1) {
                    break;
                }
                System.out.println(new String(bytes, 0, read));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
