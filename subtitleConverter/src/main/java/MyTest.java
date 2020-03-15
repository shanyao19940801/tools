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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shanyao on 2020/3/15
 */
public class MyTest {
    public static void main(String[] args) {
        List<FileInfoBean> filePath = GetAllFileName.getFilePath("F:\\视频剪辑\\各种平台\\电影\\漫威\\字幕");
        System.out.println(filePath);
        formatSRT(filePath);

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

    public static void formatSRT(List<FileInfoBean> filePath) {
        String[] all = new String[0];
        for (FileInfoBean bean : filePath) {
            try {
                TimedTextObject tto;
                TimedTextFileFormat ttff;
                OutputStream output = null;

                ttff = new FormatSRTUtil();
                File file = new File(bean.getFilePath());
                InputStream is = new FileInputStream(bean.getFilePath());
                tto = ttff.parseFile(file.getName(), is);
                String[] fileName = {"MoveName:" + bean.getFileName()};
                all = concat(all, fileName, tto.toSRT());

//                IOClass.writeFileTxt("test.srt", tto.toSRT());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FatalParsingException e) {
                e.printStackTrace();
            }
        }
        System.out.println(all);
        IOClass.writeFileTxt("all.srt", all);
    }

    public static <T> T[] concat(T[] first, T[]... rest) {
        int totalLength = 0;
        T[] result = null,temp = null;
        if(first!=null && first.length>0) {
            totalLength = first.length;
        }
        if (totalLength == 0){
            for (T[] array : rest) {
                if(array != null && array.length>0) {
                    if (totalLength == 0){
                        temp = array;
                        totalLength += array.length;
                    }else {
                        temp = concat(temp,array);
                    }
                }
            }
            result = temp;
        }else {
            for (T[] array : rest) {
                if(array!=null && array.length>0) {
                    totalLength += array.length;
                }
            }
            if (totalLength != 0){
                result = Arrays.copyOf(first, totalLength);
                int offset = first.length;
                for (T[] array : rest) {
                    if(array!=null && array.length>0) {
                        System.arraycopy(array, 0, result, offset, array.length);
                        offset += array.length;
                    }
                }
            }
        }
        return result;
    }

}
