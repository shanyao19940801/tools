import java.io.File;

public class DeleteFile {
    public static void folderMethod2(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        folderMethod2(file2.getAbsolutePath());
                    } else {
                        String fileName = file2.getName();
                        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                        System.out.println(suffix);
                        //删除后缀为git的文件
                        if (suffix.equals("git")) {
                            file2.delete();
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
    public static void main(String[] args) {
        String paht = "E:\\qq\\project\\all-proto\\resources";
        folderMethod2(paht);
    }
}
