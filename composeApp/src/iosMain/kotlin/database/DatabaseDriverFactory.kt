package database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.mirror.database.MirrorLocalDatabase
import org.koin.core.scope.Scope

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            MirrorLocalDatabase.Schema,
            "journal.db"
        )
    }
}
