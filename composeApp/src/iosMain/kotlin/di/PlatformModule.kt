package di

import audioplayer.AudioPlayer
import audiorecorder.AudioRecorder
import database.DatabaseDriverFactory
import org.example.mirror.database.MirrorLocalDatabase
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        MirrorLocalDatabase(DatabaseDriverFactory().create())
    }

    factory {
        AudioPlayer()
    }

    factory {
        AudioRecorder()
    }

}





