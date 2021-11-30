package com.example.integratesdk

import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import io.flutter.embedding.android.FlutterActivity
import androidx.annotation.NonNull
import com.otto.ottosdk.OttoSdk
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import org.json.JSONException
import org.json.JSONObject

//import com.lukman.sdklog.lib

class MainActivity: FlutterActivity() {
    private val CHANNEL = "samples.flutter.dev/log"

    //private val myLib: lib =lib()
    private var android_id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        android_id = Settings.Secure.getString(
            this.getContentResolver(),
            Settings.Secure.ANDROID_ID
        );

    }

override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
                call, result ->
            // Note: this method is invoked on the main thread.
            // TODO
            when (call.method) {
                "sdkLog" -> {
                    //myLib.d(argument = call.argument<String>("params").toString())

                    OttoSdk.runAtDev().initSdk(
                        this,
                        "OTTOSTAMP",
                        android_id,
                        "081351869149",
                        "",
                        "",
                        "l1ksb2PvqimtMsH42CxVdlLRSg4a", "3FEhGyB_HU3FDDfIWcc80rHv0j8a"
                    ).start()

                    result.success(true)
                }
                else -> {
                    result.notImplemented()
                }
            }
        }
    }

}
