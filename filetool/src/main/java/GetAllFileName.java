import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by shanyao on 2020/2/21
 */
public class GetAllFileName {

    public static void folderMethod2(String path, String saveFolderPaht) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        folderMethod2(file2.getAbsolutePath(), saveFolderPaht);
                    } else {

                        writeToFile(file2.getAbsolutePath(), saveFolderPaht);
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void writeToFile(String path, String filePath) {
        String[] split = path.split("\\\\");
        String svaeFileName = split[split.length - 2];
        String fileName = split[split.length - 1];
        String saveFile = filePath + "\\\\" + svaeFileName + ".txt";

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
        String paht = "F:\\视频剪辑\\三人行1998-2017-part3\\三人行1998-2017\\蹡蹡三人行（1998-2017）\\蹡蹡三人行";
        folderMethod2(paht, paht);
    }
}
