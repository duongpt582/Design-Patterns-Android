package com.duongpt207595.designpatterns.singleton.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPreferencesManager private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Sử dụng companion object để triển khai Singleton
    companion object {
        private const val PREF_NAME = "YourSharedPreferencesName"
        @Volatile private var instance: SharedPreferencesManager? = null

        // Sử dụng synchronized để đảm bảo tính đa luồng khi tạo đối tượng
        @Synchronized
        fun getInstance(context: Context): SharedPreferencesManager {
            if (instance == null) {
                instance = SharedPreferencesManager(context)
            }
            return instance!!
        }
    }

    // Hàm để lưu giá trị vào SharedPreferences
    suspend fun saveString(key: String, value: String) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().putString(key, value).apply()
        }
    }

    // Hàm để đọc giá trị từ SharedPreferences
    suspend fun getString(key: String, defaultValue: String): String {
        return withContext(Dispatchers.IO) {
            sharedPreferences.getString(key, defaultValue) ?: defaultValue
        }
    }

    // Các phương thức khác tương tự có thể được thêm tùy thuộc vào nhu cầu ứng dụng
}
