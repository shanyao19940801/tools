package liac.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 单耀
 * @version 1.0
 * @description
 * @date 2020/8/20 17:23
 */
public class IpUtils {
    public static List<String> getIps(String s) {
        String regEx="((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        List<String> ips = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            ips.add(matcher.group());
        }
        return ips;
    }
}
