package by.andreiblinets.web.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Coding {

    public Coding() {
    }

    public static String md5Apache(String st) {
        return DigestUtils.md5Hex(st);
    }
}
