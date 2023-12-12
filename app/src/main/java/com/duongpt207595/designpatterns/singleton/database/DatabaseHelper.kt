package com.duongpt207595.designpatterns.singleton.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    //companion object là một cách để tạo một đối tượng đặc biệt nằm trong phạm vi của một lớp
    companion object {
        private const val DATABASE_NAME = "YourDatabaseName"
        private const val DATABASE_VERSION = 1

        // Singleton instance
        // Khi một biến được đánh dấu là @Volatile,
        // nó đảm bảo rằng tất cả các luồng đều nhìn thấy giá trị mới nhất của biến khi nó được cập nhật.
        @Volatile
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Tạo bảng hoặc thực hiện các tác vụ khởi tạo cần thiết
        // Ví dụ: db.execSQL("CREATE TABLE IF NOT EXISTS your_table (...);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Thực hiện nâng cấp cơ sở dữ liệu khi có sự thay đổi version
    }

    // Các phương thức khác cho việc thao tác với cơ sở dữ liệu
    fun insertData(data: Person) {
        // Thực hiện việc chèn dữ liệu vào cơ sở dữ liệu
    }

    fun queryData(): List<Person> {
        // Thực hiện truy vấn dữ liệu từ cơ sở dữ liệu
        return emptyList()
    }

    // ... Thêm các phương thức khác tùy thuộc vào nhu cầu ứng dụng
}
