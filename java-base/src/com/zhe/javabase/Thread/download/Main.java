package com.zhe.javabase.Thread.download;

/**
 * @author zhangzhe
 */
public class Main {

    public static void main(String[] args){
        String url = "https://s3.shuidimiaoyi.com/mt/20190115/d83a8a6db585c25a9f01d5ea26393e50.png";
        int workerThreadCount = 1;
        long reportInterval = 3;

        try {
            BigFileDownloader downloader = new BigFileDownloader(url);

            downloader.download(workerThreadCount, reportInterval * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
