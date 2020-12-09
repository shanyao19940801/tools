package bilibili;

import bilibili.bean.VideoOnfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.FileExtensionName;
import common.FileUtils;
import sun.reflect.misc.FieldUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 单耀
 * @version 1.0
 * @description
 * @date 2020/12/9 16:34
 */
public class NameVideo {
    public static void folderMethod2(String path, String saveFolderPath) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        File[] detailFile = file2.listFiles();
                        File nameFile = null;
                        File videoFile = null;
                        for (File signleFile : detailFile) {
                            String name = signleFile.getName();
                            String fileExtensionName = FileUtils.getFileExtensionName(signleFile);
                            if (FileExtensionName.MP4.equals(fileExtensionName)) {
                                videoFile = signleFile;
                            }
                            if (FileExtensionName.INFO.equals(fileExtensionName)) {
                                nameFile = signleFile;

                            }
                            System.out.println(name);
                            System.out.println(fileExtensionName);
                        }
                        String name = file2.getName();
                        System.out.println(name);
                        String title = getTitle(nameFile);
                        String fileName = saveFolderPath + name + title + "." + FileExtensionName.MP4;
                        videoFile.renameTo(new File(fileName));
                        System.out.println(title);
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    private static String getTitle(File file) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        VideoOnfo videoOnfo = JSON.parseObject(sbf.toString(), VideoOnfo.class);
        return videoOnfo.getPartName();
    }
    public static void main(String[] args) {
        String paht = "C:\\Users\\shanyao11\\AppData\\Local\\Packages\\36699Atelier39.forWin10_pke1vz55rvc1r\\LocalCache\\BilibiliDownload\\66132916";
        String newPath = "C:\\Users\\shanyao11\\AppData\\Local\\Packages\\36699Atelier39.forWin10_pke1vz55rvc1r\\LocalCache\\BilibiliDownload\\new\\";
        folderMethod2(paht, newPath);
    }
}
