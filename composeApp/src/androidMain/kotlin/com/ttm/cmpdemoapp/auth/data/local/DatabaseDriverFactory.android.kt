package com.ttm.cmpdemoapp.auth.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ttm.cmpdemoapp.AppDatabase

actual class DatabaseDriverFactory(private val context: Context){
    actual fun create(): SqlDriver{
        return AndroidSqliteDriver(AppDatabase.Schema,context,"auth.db")
    }
}
