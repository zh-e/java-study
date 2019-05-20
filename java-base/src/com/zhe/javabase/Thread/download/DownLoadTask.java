package com.zhe.javabase.Thread.download;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhangzhe
 */
public class DownLoadTask implements Runnable {

    private final long lowerBound;

    private final long upperBound;

    private final DownloadBuffer xbuf;

    private URL requestURL;

    private final AtomicBoolean cancelFlag;

    public DownLoadTask(long lowerBound, long upperBound, URL requestURL, Storage storage, AtomicBoolean cancelFlag) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.requestURL = requestURL;
        this.xbuf = new DownloadBuffer(lowerBound, upperBound, storage);
        this.cancelFlag = cancelFlag;
    }

    public static InputStream issueRequest(URL requestURL, long lowerBound, long upperBound) throws IOException {
        Thread me = Thread.currentThread();
        System.out.println(me + "->[" + lowerBound + "," + upperBound + "]");
        final HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
        InputStream in = null;

        String strConnTimeout = System.getProperty("x.dt.conn.timeout");
        int connTimeout = null == strConnTimeout ? 60000 : Integer.valueOf(strConnTimeout);
        conn.setConnectTimeout(connTimeout);

        String strReadTimeout = System.getProperty("x.dt.read.timeout");
        int readTimeout = null == strReadTimeout ? 60000 : Integer.valueOf(strReadTimeout);
        conn.setReadTimeout(readTimeout);

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Range", "bytes=" + lowerBound + "-" + upperBound);
        conn.setDoInput(true);
        conn.connect();

        int statusCode = conn.getResponseCode();
        if (HttpURLConnection.HTTP_PARTIAL != statusCode) {
            conn.disconnect();
            throw new IOException("server exception, status code:" + statusCode);
        }

        System.out.println(me + "-Content-Range:" + conn.getHeaderField("Content-Range")
                + ", connection:" + conn.getHeaderField("connection"));

        in = new BufferedInputStream(conn.getInputStream()) {
            @Override
            public void close() throws IOException {
                try {
                    super.close();
                } finally {
                    conn.disconnect();
                }
            }
        };

        return in;
    }

    @Override
    public void run() {

        if(cancelFlag.get()) {
            ReadableByteChannel channel = null;

            try {
                channel = Channels.newChannel(issueRequest(requestURL, lowerBound, upperBound));
                ByteBuffer buf = ByteBuffer.allocate(1024);
                while (!cancelFlag.get() && channel.read(buf)>0) {
                    xbuf.write(buf);
                    buf.clear();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                Tools.silentClose(channel, xbuf);
            }
        }

    }
}
