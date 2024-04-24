package database

import app.cash.sqldelight.db.SqlDriver
import org.example.mirror.database.MirrorLocalDatabase
import org.koin.core.scope.Scope

expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}


