package subtitle.util;

import subtitle.bean.FileInfoBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanyao on 2020/2/21
 */
public class GetAllFileName {

    public static void getAllFileList(String path, List<FileInfoBean> fileInfoBeanList) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        getAllFileList(file2.getAbsolutePath(), fileInfoBeanList);
                    } else {
                        addToList(file2.getAbsolutePath(), fileInfoBeanList);
                    }
                }
            }
        }
    }

    public static void folderMethod2(String path, String saveFolderPaht, List<FileInfoBean> fileInfoBeanList) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        folderMethod2(file2.getAbsolutePath(), saveFolderPaht, fileInfoBeanList);
                    } else {

                        writeToFile(file2.getAbsolutePath(), saveFolderPaht, fileInfoBeanList);
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void addToList(String path, List<FileInfoBean> fileInfoBeanList) {
        String[] split = path.split("\\\\");
        String fileName = split[split.length - 1];
        String[] fileSu = fileName.split("\\.");
        FileInfoBean bean = new FileInfoBean(path, fileName, fileSu[1]);
        fileInfoBeanList.add(bean);

    }
    public static void writeToFile(String path, String filePath, List<FileInfoBean> fileInfoBeanList) {
        String[] split = path.split("\\\\");
        String svaeFileName = split[split.length - 2];
        String fileName = split[split.length - 1];
        String saveFile = filePath + "\\\\" + svaeFileName + ".txt";
        String[] fileSu = fileName.split("\\.");
        FileInfoBean bean = new FileInfoBean(path, fileName, fileSu[1]);
        fileInfoBeanList.add(bean);

        File file = new File(saveFile);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(fileName); //写入内容
            osw.write("\r\n");  //换行
        } catch (Exception e) {
            e.printStackTrace();
        }finally {   //关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        List<FileInfoBean> fileInfoBeanList = new ArrayList<>();
        String paht = "F:\\work\\testfile";
        folderMethod2(paht, paht, fileInfoBeanList);
        System.out.println(paht);
    }
}
