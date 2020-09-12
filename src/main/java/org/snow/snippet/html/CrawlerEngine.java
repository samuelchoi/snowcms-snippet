package org.snow.snippet.html;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CrawlerEngine {

    /**
     * 宿主启动
     *
     * @param args
     * @throws IOException
     */
    public void main(String[] args) throws IOException {

        HttpClient httpClient = new DefaultHttpClient();

        // 设置响应时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000)
                .setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);

        HttpGet httpGet = new HttpGet("https://www.zhxia.cn/qiming/showlist.php?xing=%E8%94%A1&gender=female");
        HttpResponse httpResponse = httpClient.execute(httpGet);

        String content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

        Document document = Jsoup.parse(content);
        Elements elements = document.select("col-md-8");

        for (Element element : elements){
            String text = element.text();
            System.out.print(text);
        }

    }
}
