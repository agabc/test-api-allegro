package pl.allegro.api.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class StringUtil {

    public static String stringToBase64(String stringToEncode){
        return Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
    }
}
