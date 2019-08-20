package io.rra3b.linetvtest.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

  private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy.MM.dd", Locale.TAIWAN);

  public static String toDateString(Date date) {
    return sdfDate.format(date);
  }
}
