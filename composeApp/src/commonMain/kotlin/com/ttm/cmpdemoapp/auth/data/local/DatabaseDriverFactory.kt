package com.ttm.cmpdemoapp.auth.data.local

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory{
    fun create(): SqlDriver
}
