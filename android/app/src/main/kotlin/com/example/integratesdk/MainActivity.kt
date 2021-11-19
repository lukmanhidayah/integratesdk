package com.example.integratesdk

import io.flutter.embedding.android.FlutterActivity
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import com.lukman.sdklog.lib

class MainActivity: FlutterActivity() {
    private val CHANNEL = "samples.flutter.dev/log"

    private val myLib: lib =lib()

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
                call, result ->
            // Note: this method is invoked on the main thread.
            // TODO
            when (call.method) {
                "sdkLog" -> {
                    myLib.d(argument = call.argument<String>("params").toString())
                    result.success(true)
                }
                else -> {
                    result.notImplemented()
                }
            }
        }
    }

}
