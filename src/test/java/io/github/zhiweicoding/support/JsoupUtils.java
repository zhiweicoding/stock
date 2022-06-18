package io.github.zhiweicoding.support;

import cn.hutool.http.HttpUtil;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author by diaozhiwei on 2022/06/16.
 * @email diaozhiwei2k@163.com
 */
public class JsoupUtils {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        for (int i = 6; i <= 20; i++) {
            try {
                retry(i, sb);
            } catch (Exception e) {
            }
        }
        FileUtils.writeStringToFile(new File("/Users/zhiwei/Desktop/t6" + 2 + ".md"), sb.toString(), StandardCharsets.UTF_8);
//        download("/Users/zhiwei/Desktop/t6",
//                URLEncoder.encode("222/gznp.jpg", StandardCharsets.UTF_8),
//                "https://a.gac2.xyz/222/gznp.jpg");
    }

    public static void retry(int i, StringBuilder sb) throws Exception {
        String itemStr = "|%s|![img](%s)|[网页](%s)|[torrent](%s)|\n";
        Document doc = Jsoup.connect("https://t66y.com/thread0806.php?fid=26&search=&page=" + i).get();
        Elements tr3TOneTac = doc.getElementsByClass("tr3 t_one tac");
        int j = 0;
        for (Element item : tr3TOneTac) {
            try {
                Elements tal = item.getElementsByClass("tal");
                Element talItem = tal.get(0);
                Element hrefEle = talItem.select("h3 a").get(0);
                String href = hrefEle.attr("href");
                String text = hrefEle.text();

                Document sonDoc = Jsoup.connect("https://t66y.com/" + href).get();
                Elements select = sonDoc.select("#conttpc a");
                String downloadHref = "";
                for (Element downloadEle : select) {
                    if (downloadEle.text().contains("http://www.rmdown.com/link.php?hash=")) {
                        downloadHref = downloadEle.text();
                    }
                }

                String tumbImg = "";
                Elements spanEle = talItem.select("span");
                if (spanEle.size() > 0 && spanEle.get(0) != null) {
                    Element spanItem = spanEle.get(0);
                    tumbImg = "https://a.gac2.xyz/" + spanItem.attr("d") + ".jpg";
                    //download
//                    download("/Users/zhiwei/Desktop/t6",
//                            downloadHref.replace("http://www.rmdown.com/link.php?hash=", "") + ".jpg",
//                            tumbImg);
                }

                System.out.println("i:" + i + " - j:" + j);
                sb.append(String.format(itemStr, text, tumbImg, "https://t66y.com/" + href, downloadHref));

                j++;
            } catch (Exception e) {

            }

            int random = new Random().nextInt(35);
            while (random < 15) {
                random = new Random().nextInt(35);
            }
            Thread.sleep(random * 1000);
        }
        Thread.sleep(1000);
    }


    public static void download(String path, String fileName, String url) {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .connectionPool(new ConnectionPool(30, 5, TimeUnit.MINUTES))
                    .build();
            Request request = new Request.Builder()
                    //访问路径
                    .url(url)
                    .build();
            Response response = null;
            response = client.newCall(request).execute();
            byte[] bytes = response.body().bytes();
            Path folderPath = Paths.get(path);
            boolean desk = Files.exists(folderPath);
            if (!desk) {
                Files.createDirectories(folderPath);
            }

            Path filePath = Paths.get(path + "/" + fileName);
            boolean exists = Files.exists(filePath, LinkOption.NOFOLLOW_LINKS);
            if (!exists) {
                Files.write(filePath, bytes, StandardOpenOption.CREATE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
