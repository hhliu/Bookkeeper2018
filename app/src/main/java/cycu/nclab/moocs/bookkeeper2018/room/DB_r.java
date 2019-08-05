package cycu.nclab.moocs.bookkeeper2018.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.Calendar;
import java.util.List;

@Database(entities = {MoneyEntity.class}, version = 1)
public abstract class DB_r extends RoomDatabase {
    public abstract MoneyDao moneyDao();

    private final static String databaseName = "moneycare.db";

    private static volatile DB_r instance;

    private static DB_r getDatabase(final Context context) {
        if (instance == null) {
            synchronized (DB_r.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            DB_r.class, databaseName).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }

    public static void insert(Context c, MoneyEntity m) {
        getDatabase(c).moneyDao().insert(m);
    }

    public static void delete(Context c, MoneyEntity m) {
        getDatabase(c).moneyDao().delete(m);
    }

    public static void update(Context c, MoneyEntity m) {
        getDatabase(c).moneyDao().update(m);
    }

    public static List<MoneyEntity> getDailyData(Context c, Calendar today) {
        return getDatabase(c).moneyDao().getDailyData(today);
    }

    public static void insert2(final Context c, final MoneyEntity m) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Insert Data
                DB_r.getDatabase(c).moneyDao().insert(m);
            }
        });
    }
}
