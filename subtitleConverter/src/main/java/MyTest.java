import subtitle.util.FormatSRTUtil;
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

/**
 * Created by shanyao on 2020/3/15
 */
public class MyTest {
    public static void main(String[] args) {


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
