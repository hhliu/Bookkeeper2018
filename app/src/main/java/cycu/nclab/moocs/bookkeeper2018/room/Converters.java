package cycu.nclab.moocs.bookkeeper2018.room;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Converters {
    static SimpleDateFormat todayFormat = new java.text.SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault());
    /*用來存在資料庫裏面的日期與時間格式*/

    @TypeConverter
    public static String dateToString(Calendar c) {
        return c == null ? null : todayFormat.format(c.getTime());
    }
}
