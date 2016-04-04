package com.easyparking;

import android.content.Intent;
import android.view.View;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "EasyParking";
    }

    /**
     * Returns whether dev mode should be enabled.
     * This enables e.g. the dev menu.
     */
    @Override
    protected boolean getUseDeveloperSupport() {
        return BuildConfig.DEBUG;
    }

    /**
     * A list of packages used by the app. If the app uses additional views
     * or modules besides the default ones, add more packages here.
     */
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new LocationTrackingPackage()
        );
    }
    //Method to start service
    public void startService(View view){
        startService(new Intent(getBaseContext(),EasyParkingTrackingService.class));
    }
    //Method to stop service
    public void stopService(View view){
        stopService(new Intent(getBaseContext(),EasyParkingTrackingService.class));
    }
}
