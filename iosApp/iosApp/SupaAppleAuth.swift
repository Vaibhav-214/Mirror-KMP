//
//  SupaAppleAuth.swift
//  iosApp
//
//  Created by Vaibhav Patil on 08/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import AuthenticationServices
import SwiftUI
import ComposeApp

//Sign in using supabase auth
struct SignInView: View {
//    let client = SupabaseClient(
//        supabaseURL: URL(string: "https://dtfedhvrsjzqvfpjzuwb.supabase.co")!,
//        supabaseKey: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR0ZmVkaHZyc2p6cXZmcGp6dXdiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDE5NzQwOTAsImV4cCI6MjAxNzU1MDA5MH0.sCwYsWImNd3kIh6PRs61jiGK_WHvW61DssJN9Jc7LjA"
//    )
    
    let client = SignInWithApple()

    var body: some View {
      SignInWithAppleButton { request in
        request.requestedScopes = [.email, .fullName]
      } onCompletion: { result in
        Task {
          do {
            guard let credential = try result.get().credential as? ASAuthorizationAppleIDCredential
            else {
              return
            }

            guard let idToken = credential.identityToken
              .flatMap({ String(data: $0, encoding: .utf8) })
            else {
              return
            }
//              try await client.auth.signInWithIdToken(
//              credentials: .init(
//                provider: .apple,
//                idToken: idToken
//              )
//            )
              client.onSignInResponse(idToken: idToken)
          } catch {
            dump(error)
          }
        }
      }
      .fixedSize()
    }
}

final class SwiftViewProvider : ViewProvider {
     func provideView() -> UIView {
      SwiftUIInUIView(
        content: SignInView()
      )
    }
}
