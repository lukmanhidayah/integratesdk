import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Example Integration Sdk',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform = MethodChannel('samples.flutter.dev/log');

  Future<T> invoke<T>(String method, [dynamic argument]) async {
    try {
      final res = await platform.invokeMethod(method, argument);
      print(res);
      return res;
    } on PlatformException {
      rethrow;
    } on MissingPluginException {
      rethrow;
    }
  }

  // Future<void> method() async {
  //   String batteryLevel;
  //   try {
  //     final Function result = await platform.invokeMethod('sdkLog');
  //     batteryLevel = 'Battery level at $result % .';
  //   } on PlatformException catch (e) {
  //     batteryLevel = "Failed to get battery level: '${e.message}'.";
  //   }
  // }
  Future<void> sdkLog(params) async =>
      await invoke("sdkLog", {"params": params});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
                onPressed: () => sdkLog('Flutter'), child: Text('Call log sdk'))
          ],
        ),
      ),
    );
  }
}
