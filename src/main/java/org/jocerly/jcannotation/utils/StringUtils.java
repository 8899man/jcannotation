package org.jocerly.jcannotation.utils;


import java.io.UnsupportedEncodingException;

/**
 * TODO<字符串操作>
 *
 * @data: 2015-8-24 下午2:56:43
 */
public class StringUtils {
    public final static String UTF_8 = "utf-8";

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 把null转成成空字符串
     *
     * @param obj
     * @return
     */
    public static String replaceNULLToStr(Object obj) {
        if (obj == null || "null".equals(obj + "")) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true
     */
    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * byte 数组转换为 String.
     *
     * @return byte[]
     */
    public static String bytesToString(byte[] encrytpByte) {
        String result = "";
        for (Byte bytes : encrytpByte) {
            result += (char) bytes.intValue();
        }
        return result;
    }

    /**
     * 判断是否为手机号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^1[3,4,5,6,8,7]\\d{9}$");
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param b 字节数组
     * @return 16进制字符串
     * @throws
     * @Title:bytes2HexString
     */
    public static String bytes2HexString(byte[] b) {
        StringBuffer result = new StringBuffer();
        String hex;
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            result.append(hex.toUpperCase());
        }
        return result.toString();
    }

    public static String byteToHexString(byte b) {
        byte[] bt = {b}; // 字节数组
        return bytes2HexString(bt);
    }

    /**
     * 汉字转换成区位码
     * @param chinese
     * @return
     */
    public static String chineseToCode(String chinese) {
        byte[] bt;
        String code = "";
        try {
            bt = chinese.getBytes("GB2312"); // 用GB2312编码为字节数组
            for (int i = 0; i < bt.length; i++) {
                int a = Integer.parseInt(byteToHexString(bt[i]), 16);
                code += (a - 0x80 - 0x20) + ""; // 获得区位码
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param src 16进制字符串
     * @return 字节数组
     * @throws
     * @Title:hexString2Bytes
     */
    public static byte[] hexString2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (byte) Integer
                    .valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    /**
     * 字符串转16进制字符串
     *
     * @param strPart 字符串
     * @return 16进制字符串
     * @throws
     * @Title:string2HexString
     */
    public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * 16进制字符串转字符串
     *
     * @param src 16进制字符串
     * @return 字节数组
     * @throws
     * @Title:hexString2String
     */
    public static String hexString2String(String src) {
        String temp = "";
        for (int i = 0; i < src.length() / 2; i++) {
            temp = temp
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),
                    16).byteValue();
        }
        return temp;
    }

    /**
     * 字符转成字节数据char-->integer-->byte
     *
     * @param src
     * @return
     * @throws
     * @Title:char2Byte
     */
    public static Byte char2Byte(Character src) {
        return Integer.valueOf((int) src).byteValue();
    }

    /**
     * 10进制数字转成16进制
     *
     * @param a   转化数据
     * @param len 占用字节数
     * @return
     * @throws
     * @Title:intToHexString
     */
    private static String intToHexString(int a, int len) {
        len <<= 1;
        String hexString = Integer.toHexString(a);
        int b = len - hexString.length();
        if (b > 0) {
            for (int i = 0; i < b; i++) {
                hexString = "0" + hexString;
            }
        }
        return hexString;
    }


}
