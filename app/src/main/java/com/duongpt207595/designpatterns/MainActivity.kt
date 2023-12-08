package com.duongpt207595.designpatterns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.duongpt207595.designpatterns.singleton.database.DatabaseHelperGenericType
import com.duongpt207595.designpatterns.singleton.database.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    // Khai báo DatabaseHelper với kiểu dữ liệu là Person
    private lateinit var dbHelper: DatabaseHelperGenericType<Person>
    private lateinit var dbHelper2: DatabaseHelperGenericType<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbHelper = DatabaseHelperGenericType.getInstance(this)
        Log.d(TAG, "onCreate: dbHelper: $dbHelper")

        dbHelper2 = DatabaseHelperGenericType.getInstance(this)
        Log.d(TAG, "onCreate: dbHelper2: $dbHelper2")

        Log.d(TAG, "onCreate: multi thread.....")
        multiThread()

    }

    /**
     * Kiểm tra trong môi trường đa luồng: coroutine.
     */
    private fun multiThread() = runBlocking {
        val jobs = List(50) {
            async(Dispatchers.Default) {
                val dbHelper = DatabaseHelperGenericType.getInstance<Person>(applicationContext)
                // Thực hiện các thao tác cần thiết với dbHelper
                Log.d(TAG, "multiThread ${Thread.currentThread().name}: $dbHelper")
            }
        }


        jobs.forEach {
            it.await()
        }
    }
}