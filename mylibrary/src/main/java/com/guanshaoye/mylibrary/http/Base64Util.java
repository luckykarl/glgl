package com.guanshaoye.mylibrary.http;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Base64Util {
    public static String Base64ToString(String str) {
        // Base64 编码：
        byte[] encode = Base64.encode(str.getBytes(), Base64.DEFAULT);
        String enc = new String(encode);
        return enc;
    }

    public static String Base64ToString(byte[] bytes){
        // Base64 编码：
        byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
        String enc = new String(encode);
        return enc;
    }

    public static byte[] StringToBase64(String str) {
        // Base64 解码：
        byte[] result = Base64.decode(str, Base64.DEFAULT);
//        String res = new String(result);
        return result;
    }

    // 输入数据的最大长度
    private static final int MAXLENGTH = 1024*1000;
    // 设置缓存大小
    private static final int BUFFERSIZE = 1024;
//
//    public static String zlib(byte[] bytes) {
//        // Decompress the bytes // 开始解压,
//        Inflater decompresser = new Inflater();
//        decompresser.setInput(bytes, 0, bytes.length);
//        int resultLength = 0;
//        // 对byte[]进行解压，同时可以要解压的数据包中的某一段数据，就好像从zip中解压出某一个文件一样。
//        byte[] result = new byte[MAXLENGTH];
//        try {
//            resultLength = decompresser.inflate(result); // 返回的是解压后的的数据包大小，
//        } catch (DataFormatException e) {
//            e.printStackTrace();
//        }
//        decompresser.end();
//        byte[] trueData = new byte[resultLength];
//        System.arraycopy(result, 0, trueData, 0, resultLength);
//        Log.e("aaa", "resultLength=" + resultLength);
//        return new String(trueData);
//    }


   /* *//**
     * ZLib压缩数据
     *
     * @param bContent
     * @return
     * @throws IOException
     *//*

    public static byte[] zLib(byte[] bContent){
        byte[] data = null;
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ZOutputStream zOut = new ZOutputStream(out, JZlib.Z_BEST_COMPRESSION); // 压缩级别,缺省为1级
            DataOutputStream objOut = new DataOutputStream(zOut);
            objOut.write(bContent);
            objOut.flush();
            zOut.close();

            data = out.toByteArray();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }*/

 /*   *//**
     * ZLib解压数据
     *
     * @param bContent
     * @return
     * @throws IOException
     *//*

    public static String unZLib(byte[] bContent) {
        byte[] data = new byte[MAXLENGTH];
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bContent);
            ZInputStream zIn = new ZInputStream(in);
            DataInputStream objIn = new DataInputStream(zIn);
            int len = 0;
            int count = 0;

            while ((count = objIn.read(data, len, len + BUFFERSIZE)) != -1) {
                len = len + count;
            }

            byte[] trueData = new byte[len];
            System.arraycopy(data, 0, trueData, 0, len);
            objIn.close();
            zIn.close();
            in.close();
            return new String(trueData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

*/

    /**
     * 图片转成string
     *
     * @param bitmap
     * @return
     */
    public static String convertIconToString(Context context, Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);

    }

    /**
     * encodeBase64File:(将文件转成base64 字符串). <br/>
     * @author guhaizhou@126.com
     * @param path 文件路径
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }
    public static String encodeBase64File(File path) throws Exception {

        FileInputStream inputFile = new FileInputStream(path);
        byte[] buffer = new byte[(int)path.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }
}
