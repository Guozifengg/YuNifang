package yunifang.bwei.com.yunifang.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/17.
 * 作用：
 */
public class StreamUtils {
    public static String parser(InputStream inputStream){
        StringBuilder sbb=new StringBuilder();
        String str;
        BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
        try {
            while((str=br.readLine())!=null){
                sbb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbb.toString();
    }

}
