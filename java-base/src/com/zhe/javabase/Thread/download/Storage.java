package com.zhe.javabase.Thread.download;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangzhe
 */
public class Storage implements Closeable, AutoCloseable {

    private final RandomAccessFile storeFile;

    private final FileChannel storeChannel;

    private final AtomicLong totalWrites = new AtomicLong(0);

    public Storage(long fileSize, String fileShortName) throws IOException {
        String fullFileName =  "/Users/zhe/Downloads" + "/" + fileShortName;
        String localFileName = createStoreFile(fileSize, fullFileName);
        this.storeFile = new RandomAccessFile(localFileName, "rw");
        this.storeChannel = storeFile.getChannel();
    }

    public int store(long offset, ByteBuffer byteBuffer) throws IOException {
        storeChannel.write(byteBuffer, offset);
        int length = byteBuffer.limit();
        totalWrites.addAndGet(length);
        return length;
    }

    public long getTotalWrites() {
        return totalWrites.get();
    }

    @Override
    public void close() {
        if(storeChannel.isOpen()) {
            Tools.silentClose(storeChannel, storeFile);
        }
    }

    private String createStoreFile(final long fileSize, String fullFileName) throws IOException {
        File file = new File(fullFileName);
        System.out.println("create local file: " + fullFileName);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(fileSize);
        } finally {
            Tools.silentClose(raf);
        }
        return fullFileName;
    }



}