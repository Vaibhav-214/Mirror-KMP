package audiorecorder

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module
import platform.Foundation.NSData
import platform.ViewProvider
import platform.posix.memcpy

interface AudioRecorderIos {
    fun startRecording()
    fun stopRecording():String
}

typealias NativeInjectionFactory<T> = Scope.() -> T

fun makeNativeModule(
    audioRecorder: NativeInjectionFactory<AudioRecorderIos>,
    provider: NativeInjectionFactory<ViewProvider>
): Module {
    return module {
        single { audioRecorder()}
        factory { provider() }
    }
}