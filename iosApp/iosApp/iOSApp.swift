import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoinIos(
            nativeModule: nativeModule
        )
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
