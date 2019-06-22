package cycu.nclab.moocs.bookkeeper2018;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB {

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



	// 建立資料庫欄位格式
	private static final String createSQL = "CREATE TABLE IF NOT EXISTS "
			+ TABLE + " ("
			+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
			+ KEY_DATE + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ KEY_MONEY + " DOUBLE, "
			+ KEY_CATEGORY + " TEXT, "
			+ KEY_ITEM + " TEXT, "
			+ KEY_MEMO + " TEXT, "
			+ KEY_THUMBNAIL + " BLOB, "
			+ KEY_PAYSTYLE + " TEXT);";

	// DbHelper implementations, 宣告成為靜態
	// DbHelper類別為DB_demoClass類別的內隱類別(inner class)
	// 加上static關鍵字，DbHelper就變成DB_demoClass類別的巢狀類別(nested class)
	private static class DbHelper extends SQLiteOpenHelper {

		DbHelper(Context context) {
			super(context, DATABASE, null, VERSION);
		}

		@Override
		// 只會被呼叫一次，在第一次資料庫建立的時候
		public void onCreate(SQLiteDatabase db) {
			Log.d(TAG, "Creating database: " + DATABASE);
			db.execSQL(createSQL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE);
			this.onCreate(db);
		}
	}

	// instance of dbHelper
	private final DbHelper dbHelper; // 不希望被改變
	private SQLiteDatabase db;

	private Context context;

	public DB(Context context) {
		dbHelper = new DbHelper(context);
		this.context = context;
		LOCK = new Object();
		Log.d(TAG, "Initialized database in Constructor of DB");
	}

	/** 開啟寫入模式（可寫可讀） */
	public DB openToWrite() throws SQLException {
		synchronized (LOCK) {
			db = dbHelper.getWritableDatabase(); // 若資料庫存在則開啟；若不存在則建立一個新的
			return this;
		}
	}

	/** 開啟讀取模式 */
	public DB openToRead() throws SQLException {
		synchronized (LOCK) {
			db = dbHelper.getReadableDatabase(); // 若資料庫存在則開啟；若不存在則建立一個新的
			return this;
		}
	}

	/** 關閉資料庫 */
	public void close() {
		synchronized (LOCK) {
			dbHelper.close();
		}
	}

	/** 添加資料 */
	public void insert(ContentValues values) throws SQLException {
		synchronized (LOCK) {
			db.insertOrThrow(TABLE, null, values); // 如果插入錯誤會丟一個例外出來，暫不處理。
			Log.d(TAG, "insert data into db");
		}
	}

	/** 根據id，更新資料 */
	public void update(int id, ContentValues values) throws SQLException {
		synchronized (LOCK) {
			db.update(TABLE, values, DB.KEY_ID + "=" + id, null);
		}
	}

	/** 根據id，刪除資料 */
	public void delete(int id) throws SQLException {
		synchronized (LOCK) {
			db.delete(TABLE, DB.KEY_ID + "=" + id, null);
		}
	}

	/** 根據日期，刪除資料 */
	public boolean delete(Timestamp from, Timestamp to) throws SQLException {
		synchronized (LOCK) {
			String whereClause = KEY_DATE + " >= ? AND " + KEY_DATE + " <= ?";
			return db.delete(TABLE, whereClause, new String[] {
					from.toString(), to.toString() }) != 0;
		}
	}

	/** 根據id，獲得資料 */
	public Cursor getById(String id) {
		synchronized (LOCK) {
			String cmd = "SELECT * FROM " + TABLE + " WHERE " + KEY_ID + "="
					+ id;

			return db.rawQuery(cmd, null);
		}
	}

	/** 獲得全部資料 */
	public Cursor getAll() {
		synchronized (LOCK) {
			return db.rawQuery("SELECT * FROM " + TABLE, null);
		}
	}

	// 取得一天的資料
	public Cursor getDailyData(Calendar today) {
		synchronized (LOCK) {
			SimpleDateFormat dF = new SimpleDateFormat("yyyy-MM-dd");
			String TODAY = dF.format(today.getTime());
			String cmd = "SELECT * FROM " + TABLE + " WHERE " + KEY_DATE
					+ " GLOB '" + TODAY + "*'" + " ORDER BY " + KEY_DATE
					+ " ASC ";
			return db.rawQuery(cmd, null);
		}
	}

	public Cursor RawQuery(String cmd) {
		synchronized (LOCK) {
			return db.rawQuery(cmd, null);
		}
	}

}