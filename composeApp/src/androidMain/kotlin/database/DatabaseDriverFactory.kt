package database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.mirror.database.MirrorLocalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            MirrorLocalDatabase.Schema,
            context,
            "journal.db"
        )
    }
}



