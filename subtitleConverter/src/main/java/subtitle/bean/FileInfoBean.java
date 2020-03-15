package subtitle.bean;

/**
 * Created by shanyao on 2020/3/15
 */
public class FileInfoBean {
    private String filePath;
    private String fileName;
    private String suffix;

    public FileInfoBean() { }

    public FileInfoBean(String filePath, String fileName, String suffix) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.suffix = suffix;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
