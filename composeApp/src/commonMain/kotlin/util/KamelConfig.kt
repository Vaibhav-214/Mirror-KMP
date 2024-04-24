package util

import io.kamel.core.config.KamelConfig
import io.kamel.image.config.svgDecoder

val customKamelConfig = KamelConfig {
    // Copies the default implementation if needed
//    takeFrom(KamelConfig.Default)

    // Sets the number of images to cache
//    imageBitmapCacheSize = DefaultCacheSize
//
//    // adds an ImageBitmapDecoder
//    imageBitmapDecoder()
//
//    // adds an ImageVectorDecoder (XML)
//    imageVectorDecoder()

    // adds an SvgDecoder (SVG)
    svgDecoder()

    // adds a FileFetcher
//    fileFetcher()
//
//    // Configures Ktor HttpClient
//    httpFetcher {
//        // httpCache is defined in kamel-core and configures the ktor client
//        // to install a HttpCache feature with the implementation provided by Kamel.
//        // The size of the cache can be defined in Bytes.
//        httpCache(10 * 1024 * 1024  /* 10 MiB */)
//
//        defaultRequest {
//            url("https://www.example.com/")
//            cacheControl(CacheControl.MaxAge(maxAgeSeconds = 10000))
//        }
//
//        install(HttpRequestRetry) {
//            maxRetries = 3
//            retryIf { httpRequest, httpResponse ->
//                !httpResponse.status.isSuccess()
//            }
//        }
//
//        // Requires adding "io.ktor:ktor-client-logging:$ktor_version"
//        Logging {
//            level = LogLevel.INFO
//            logger = Logger.SIMPLE
//        }
//    }

    // more functionality available.
}