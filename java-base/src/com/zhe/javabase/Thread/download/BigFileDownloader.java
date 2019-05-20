package com.zhe.javabase.Thread.download;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhangzhe
 */
public class BigFileDownloader {

    protected final URL requestURL;

    protected final long fileSize;

    protected final Storage storage;

    protected final AtomicBoolean taskCanceled = new AtomicBoolean(false);

    public BigFileDownloader(String strUrl) throws Exception {
        requestURL = new URL(strUrl);
        fileSize = retieveFileSize(requestURL);

        System.out.println("file total size:" + fileSize);
        String fileName = strUrl.substring(strUrl.lastIndexOf("/") + 1);
        storage = new Storage(fileSize, fileName);
    }

    public void download(int taskCount, long reportInterval) throws Exception {
        long chunkSizePerThread = fileSize / taskCount;
        long lowerBound = 0;
        long upperBound = 0;

        DownLoadTask dt;
        for (int i = taskCount - 1; i >= 0; i--) {
            lowerBound = i * chunkSizePerThread;
            if (i == taskCount - 1) {
                upperBound = fileSize;
            } else {
                upperBound = lowerBound + chunkSizePerThread - 1;
            }

            dt = new DownLoadTask(lowerBound, upperBound, requestURL, storage, taskCanceled);

            dispatchWork(dt, i);
        }

        //reportProgress(reportInterval);

        doCleanup();
        System.out.println("main over");
    }

    protected void doCleanup() {
        Tools.silentClose(storage);
    }

    protected void cancelDownload() {
        if (taskCanceled.compareAndSet(false, true)) {
            doCleanup();
        }
    }

    protected void dispatchWork(final DownLoadTask dt, int workerIndex) {
        Thread workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dt.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    cancelDownload();
                }
            }
        });
        workerThread.setName("downloader-" + workerIndex);
        workerThread.start();
    }

    private static long retieveFileSize(URL requestURL) throws Exception {
        long size = -1;
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) requestURL.openConnection();
            conn.setRequestMethod("HEAD");
            conn.setRequestProperty("Connection", "Keep-alive");
            conn.connect();

            int statusCode = conn.getResponseCode();
            if (HttpURLConnection.HTTP_OK != statusCode) {
                throw new Exception("server exception, status code" + statusCode);
            }

            String cl = conn.getHeaderField("Content-Length");
            size = Long.valueOf(cl);
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
        return size;
    }

    private void reportProgress(long reportInterval) throws InterruptedException {
        float lastCompletion;
        int completion = 0;
        while (!taskCanceled.get()) {
            lastCompletion = completion;
            completion = (int) (storage.getTotalWrites() * 100 / fileSize);

            if (completion == 100) {
                break;
            } else if (completion - lastCompletion >= 1) {
                System.out.println("下载进度：" + completion);
                if(completion >= 90) {
                    reportInterval = 1000;
                }
            }
            Thread.sleep(reportInterval);
        }
        System.out.println("下载进度：" + completion);
    }

}
