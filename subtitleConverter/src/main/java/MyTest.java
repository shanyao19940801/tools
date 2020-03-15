import subtitle.bean.FileInfoBean;
import subtitle.util.FormatSRTUtil;
import subtitle.util.GetAllFileName;
import subtitleFile.FatalParsingException;
import subtitleFile.FormatASS;
import subtitleFile.FormatSRT;
import subtitleFile.TimedTextFileFormat;
import subtitleFile.TimedTextObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by shanyao on 2020/3/15
 */
public class MyTest {
    public static void main(String[] args) {
        List<FileInfoBean> filePath = GetAllFileName.getFilePath("F:\\视频剪辑\\各种平台\\电影\\漫威\\字幕");
        System.out.println(filePath);
        formatSRT();

    }

    private static void formatASS() {
        try {
            TimedTextObject tto;
            TimedTextFileFormat ttff;
            OutputStream output = null;

            ttff = new FormatASS();
            File file = new File("F:\\work\\testfile\\钢铁侠.ass");
            InputStream is = new FileInputStream(file);
            tto = ttff.parseFile(file.getName(), is);
            IOClass.writeFileTxt("test.srt", tto.toSRT());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FatalParsingException e) {
            e.printStackTrace();
        }
    }

    public static void formatSRT() {
        try {
            TimedTextObject tto;
            TimedTextFileFormat ttff;
            OutputStream output = null;

            ttff = new FormatSRTUtil();
            File file = new File("F:\\work\\testfile\\钢铁侠1.srt");
            InputStream is = new FileInputStream(file);
            tto = ttff.parseFile(file.getName(), is);
            IOClass.writeFileTxt("test.srt", tto.toSRT());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FatalParsingException e) {
            e.printStackTrace();
        }
    }
}
