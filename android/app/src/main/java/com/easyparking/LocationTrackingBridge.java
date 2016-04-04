package com.easyparking;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Intent;
import android.util.Log;

public class LocationTrackingBridge extends ReactContextBaseJavaModule {
  private ReactApplicationContext reactContext;
  public LocationTrackingBridge(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "LocationTrackingBridge";
  }

  @ReactMethod
  public void greeting(String name) {
    Log.i("HelloWorld", "Hello, " + name);
  }

  @ReactMethod
  public void startService(){
    reactContext.startService(new Intent(this.reactContext.getBaseContext(), EasyParkingTrackingService.class));
  }

  @ReactMethod
  public void stopService(){
    reactContext.stopService(new Intent(this.reactContext.getBaseContext(),EasyParkingTrackingService.class));
  }
}