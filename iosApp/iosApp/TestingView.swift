//
//  TestingView.swift
//  iosApp
//
//  Created by Vaibhav Patil on 26/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

class SwiftUIInUIView<Content: View>: UIView {
    init(content: Content) {
        super.init(frame: CGRect())
        let hostingController = UIHostingController(rootView: content)
        hostingController.view.translatesAutoresizingMaskIntoConstraints = false
        addSubview(hostingController.view)
        NSLayoutConstraint.activate([
            hostingController.view.topAnchor.constraint(equalTo: topAnchor),
            hostingController.view.leadingAnchor.constraint(equalTo: leadingAnchor),
            hostingController.view.trailingAnchor.constraint(equalTo: trailingAnchor),
            hostingController.view.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }
    required init?(coder: NSCoder) {
            super.init(coder: coder)
            // Since the `content` is not available in this initializer,
            // you might not be able to directly use it to embed a SwiftUI view.
            // You may need to initialize your SwiftUI view differently here
            // or set it later when the view is being configured.
        }
}
