package common;

import java.io.File;

/**
 * @author 单耀
 * @version 1.0
 * @description
 * @date 2020/12/9 16:41
 */
public class FileUtils {
    public static String getFileExtensionName(File file) {
        if (file == null) return null;
        String filename = file.getName();
        // split用的是正则，所以需要用 //. 来做分隔符
        String[] split = filename.split("\\.");
        //注意判断截取后的数组长度，数组最后一个元素是后缀名
        if (split.length > 1) {
            return split[split.length - 1];
        } else {
            return "";
        }
    }
}
