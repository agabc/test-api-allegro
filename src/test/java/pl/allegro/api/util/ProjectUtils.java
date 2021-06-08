package pl.allegro.api.util;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectUtils {

    public static InputStream getFilePathTestResources(String fileName) {
        return ProjectUtils.class.getClassLoader().getResourceAsStream(fileName);
    }
}
