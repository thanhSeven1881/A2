package com.example.a2

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader

fun loadJsonData(context: Context): DataModel {
    return try {
        val inputStream = context.assets.open("data.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val json = bufferedReader.use { it.readText() }
        Gson().fromJson(json, DataModel::class.java)
    } catch (e: Exception) {
        Log.e("JSON Error", "Failed to load JSON: ${e.message}")
        DataModel(emptyList()) // Trả về danh sách rỗng nếu lỗi
    }
}
