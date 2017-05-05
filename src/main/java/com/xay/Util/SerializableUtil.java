package com.xay.Util;

import java.io.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class SerializableUtil {

    public SerializableUtil(){
        throw new AssertionError();
    }
    /**
     * 将对象转化为字节数组
     * @author lichmama
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] fileToBytes(File file){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int n;
            while ((n = fis.read(bytes)) != -1){
                baos.write(bytes, 0, n);
            }
            return baos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        return new byte[0];
    }
}
