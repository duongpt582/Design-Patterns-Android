package com.duongpt207595.designpatterns.singleton.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelperGenericType<T> private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "YourDatabaseName.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "persons"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_ADDRESS = "address"

        const val CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME TEXT," +
                    "$COLUMN_AGE INTEGER," +
                    "$COLUMN_ADDRESS TEXT" +
                    ");"

        // Sử dụng lazy initialization và double-checked locking để đảm bảo an toàn đa luồng
        @Volatile
        private var instance: DatabaseHelperGenericType<*>? = null

        fun <T> getInstance(context: Context): DatabaseHelperGenericType<T> {

            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = DatabaseHelperGenericType<T>(context.applicationContext)
                    }
                }
            }
            @Suppress("UNCHECKED_CAST")
            return instance as DatabaseHelperGenericType<T>
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Tạo bảng hoặc thực hiện các tác vụ khởi tạo cần thiết
        // Ví dụ: db.execSQL("CREATE TABLE IF NOT EXISTS your_table (...);")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Thực hiện nâng cấp cơ sở dữ liệu khi có sự thay đổi version
    }

    // Các phương thức khác cho việc thao tác với cơ sở dữ liệu
    fun insertData(data: T) {
        // Thực hiện việc chèn dữ liệu vào cơ sở dữ liệu
    }

    fun queryData(): List<T> {
        // Thực hiện truy vấn dữ liệu từ cơ sở dữ liệu
        return emptyList()
    }

    // ... Thêm các phương thức khác tùy thuộc vào nhu cầu ứng dụng
}
