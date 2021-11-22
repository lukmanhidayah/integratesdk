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
                    val check_out_payment = JSONObject()
                    try {
                        check_out_payment.put("description", "Bayar Tagihan dari Julo")
                        check_out_payment.put("merchantId", "6346354")
                        check_out_payment.put("merchantName", "Kantin Buah")
                        check_out_payment.put("requestDate", "2021-02-16 09:47:37")
                        check_out_payment.put("amount", 65000)
                        check_out_payment.put("fee", 0)
                        check_out_payment.put("total", 65000)
                    } catch (e: JSONException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
                    }

                    val jsonStr = check_out_payment.toString()

                    OttoSdk.runAtProduction().initSdk(
                        this,
                        "BOGASARI",
                        android_id,
                        "081351869149",
                        "",
                        jsonStr,
                        "WaqRwEWpGnLdhRVFJtAMTreKDtMa", "94y4z78uOkcTmgddgFaYoCiAX9ga"
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
