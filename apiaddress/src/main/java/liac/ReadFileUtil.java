package liac;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import liac.bean.IpResponse;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * @author 单耀
 * @version 1.0
 * @description
 * @date 2020/8/19 11:18
 */
public class ReadFileUtil {
    public static final Charset defaultCharset = Charset.forName("UTF-8");
    public static void readFile(String filePath) {
        try {
            StringBuffer sb = new StringBuffer();
            FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            int line = 1;
            while((str = br.readLine()) != null) {
                try {
                    sb.append(str+"/n");
                    String[] s = str.split(" ");
                    String ip = s[0];

                    URI uri = new URI("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query="+ip+"&co=&resource_id=6006&t=1597810754961&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110207806745870067118_1597810303801&_=1597810303806");
                    String[] riqi = str.split("\\[");
                    String riqiDate = riqi[1].substring(0, 26);
//                System.out.println(uri);
                    HttpGet request = new HttpGet(uri);
                    HttpResponse httpResponse = doGet(request);
//                System.out.println(httpResponse);
                    String s1 = EntityUtils.toString(httpResponse.getEntity(), defaultCharset);
                    String[] split = s1.split("\\(");
                    String temp = split[1].substring(0, split[1].length()- 2);
//                System.out.println(temp);
                    IpResponse ipResponse = JSON.parseObject(temp, IpResponse.class);
//                System.out.println(ipResponse);
                    System.out.print(ipResponse.getData().get(0).getLocation());
                    System.out.print("===" + riqiDate);
                    System.out.print("===" + ip);
                    System.out.println("===" + line ++);
//                System.out.println(s1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpResponse doGet(HttpGet httpGet) throws ClientProtocolException, IOException {
        HttpClient httpclient = HttpClients.createDefault();
        httpGet.addHeader("Content-Type", "application/json");
        return httpclient.execute(httpGet);
    }


    public static void main(String[] args) {
        readFile("E:\\jiaoshi\\lic\\host.access.log");
    }
}
