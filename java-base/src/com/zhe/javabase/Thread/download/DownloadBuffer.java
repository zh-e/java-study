package com.zhe.javabase.Thread.download;

import java.io.Closeable;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author zhangzhe
 */
public class DownloadBuffer implements Closeable {

    private long globalOffset;

    private long upperBound;

    private int offset = 0;

    public final ByteBuffer byteBuffer;

    private final Storage storage;

    public DownloadBuffer(long globalOffset, long upperBound, final Storage storage) {
        this.globalOffset = globalOffset;
        this.upperBound = upperBound;
        this.byteBuffer = ByteBuffer.allocate(1024 * 1024);
        this.storage = storage;
    }

    public void write(ByteBuffer buf) throws IOException {
        int length = buf.position();
        final int capacity = byteBuffer.capacity();
        if (offset + length > capacity || length == capacity) {
            flush();
        }
        byteBuffer.position(offset);
        buf.flip();
        byteBuffer.put(buf);
        offset += length;
    }

    public void flush() throws IOException {
        byteBuffer.flip();
        int length = storage.store(globalOffset, byteBuffer);
        byteBuffer.clear();
        globalOffset += length;
        offset = 0;
    }

    @Override
    public void close() throws IOException {
        System.out.println("globalOffset：" + globalOffset +"  upperBound：" + upperBound);
        if(globalOffset < upperBound) {
            flush();
        }
    }
}
