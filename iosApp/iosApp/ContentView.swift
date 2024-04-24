import UIKit
import SwiftUI
import ComposeApp
import AuthenticationServices

struct ComposeView: UIViewControllerRepresentable {
    
    
    
    //if I want to pass multiple swift ui views, do I need to pass them from there only?
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(
            createUIView: { () -> UIView in
            SwiftUIInUIView(
                content: SignInView()
            )
        }
        )
//       
//        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
//    let button = LoginButton(createUIView: { () -> UIView in
//        SwiftUIInUIView(
//            content: SignInView()
//        )
//    })
    
    var body: some View {
    
        ComposeView()
            .edgesIgnoringSafeArea(.all)
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
//        SignInWithAppleView()
    }
}


// Sign in using apple auth
struct SignInWithAppleView: View {
    var body: some View {
                SignInWithAppleButton(
                    onRequest: { request in
                        request.requestedScopes = [.fullName, .email]
                    },
                    onCompletion: { result in
                        switch result {
                        case .success(let authResults):
                            if authResults.credential is ASAuthorizationAppleIDCredential {
                                print("authenticated")
                            }
                        case .failure(let error):
                            print("Authentication error: \(error.localizedDescription)")
                        }
                    }
                )
                .signInWithAppleButtonStyle(.black)
                .frame(width: 280, height: 45)
    }
}



