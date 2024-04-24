//
//  AudioRecorderBridge.swift
//  iosApp
//
//  Created by Vaibhav Patil on 25/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import AVFoundation
import ComposeApp

@objc class AudioRecorderBridge:NSObject, AVAudioRecorderDelegate, AudioRecorderIos {
        private var audioRecorder: AVAudioRecorder?
        private var audioFileURL: URL?


    @objc func startRecording() {
            let audioSession = AVAudioSession.sharedInstance()
            try? audioSession.setCategory(.playAndRecord, mode: .default)
            try? audioSession.setActive(true)

            let paths = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
            audioFileURL = paths[0].appendingPathComponent("temp_audio.m4a")

            let settings = [
                AVFormatIDKey: Int(kAudioFormatMPEG4AAC),
                AVSampleRateKey: 44100,
                AVNumberOfChannelsKey: 2,
                AVEncoderAudioQualityKey: AVAudioQuality.high.rawValue
            ]

            try? audioRecorder = AVAudioRecorder(url: audioFileURL!, settings: settings)
            audioRecorder?.delegate = self
            audioRecorder?.prepareToRecord()
            audioRecorder?.record()
        
            print("recording right now")
        }

    @objc func stopRecording() -> String {
            audioRecorder?.stop()
            if let url = audioFileURL {
                print("recording finished")
                return url.path
            }
            // Return an empty string if some error occurs or the URL is not available
            return "error Url"
        }
}

var nativeModule: Koin_coreModule = AudioRecorderIosKt.makeNativeModule(
  audioRecorder: { scope in
    return AudioRecorderBridge()
  },
  provider: { scope in
    return SwiftViewProvider()
  }
)


