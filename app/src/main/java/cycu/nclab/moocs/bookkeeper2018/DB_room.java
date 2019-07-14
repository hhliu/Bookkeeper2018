package cycu.nclab.moocs.bookkeeper2018;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;

import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DB_room {
    private static final String TAG = DB.class.getSimpleName();

    static final int VERSION = 1;
    public static final String DATABASE = "moneycare.db";
    public static final String TABLE = "moneycare";

    public static final String KEY_ID = "_id"; // 資料流水號，_id由SimpleCursorAdapter綁定，不可更改
    public static final String KEY_CATEGORY = "category"; // 資料分類
    public static final String KEY_ITEM = "item"; // 項目名稱
    public static final String KEY_MONEY = "money"; // 項目金額
    public static final String KEY_DATE = "myDate"; // 項目日期與時間，格式為yyyy-mm-dd
    // HH:MM:SS
    public static final String KEY_PAYSTYLE = "payStyle"; // 付款方式
    public static final String KEY_MEMO = "memo"; // 備註
    public static final String KEY_THUMBNAIL = "thumbnail"; // 照片縮圖

    private static Object LOCK;


    @Entity
    public class Moneycare {
        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "category")
        public String category;

        @ColumnInfo(name = "item")
        public String item;

        @ColumnInfo(name = "money")
        public double money;

        @ColumnInfo(name = "myDate")
        public Timestamp myDate;

        @ColumnInfo(name = "payStyle")
        public String payStyle;

        @ColumnInfo(name = "memo")
        public String memo;

        @ColumnInfo(name = "thumbnail")
        public Blob thumbnail;
    }

    @Entity
    public class User {
        @PrimaryKey
        public int uid;

        @ColumnInfo(name = "first_name")
        public String firstName;

        @ColumnInfo(name = "last_name")
        public String lastName;
    }

    @Dao
    public interface UserDao {
        @Query("SELECT * FROM Moneycare")
        List<Moneycare> getAll();

        @Query("SELECT * FROM Moneycare WHERE id IN (:userIds)")
        List<Moneycare> loadAllByIds(int[] userIds);

        @Query("SELECT * FROM Moneycare WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1")
        Moneycare findByName(String first, String last);

        getDailyData(Calendar today) {
            synchronized (LOCK) {
                SimpleDateFormat dF = new SimpleDateFormat("yyyy-MM-dd");
                String TODAY = dF.format(today.getTime());
                String cmd = "SELECT * FROM " + TABLE + " WHERE " + KEY_DATE
                        + " GLOB '" + TODAY + "*'" + " ORDER BY " + KEY_DATE
                        + " ASC ";
                return db.rawQuery(cmd, null);

        @Insert
        void insertAll(Moneycare... records);

        @Delete
        void delete(Moneycare record);
    }
}
