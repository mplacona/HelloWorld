package info.androidsecurity.helloworld;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String PACKAGE_NAME = "info.androidsecurity.helloworld";
    private static final String GOOGLE_PLAY = "com.android.vending";
    private static final String AMAZON_STORE = "com.android.vending";

    static {
        System.loadLibrary("native-lib");
    }

    private native String invokeNativeFunction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context mContext = getApplicationContext();

        // Some super API call using that key
        Log.i(TAG, "key: " + invokeNativeFunction());


//        if(mContext.getPackageName().compareTo(PACKAGE_NAME) != 0){
//            Log.d(TAG, "this is a hack");
//            /*
//                Things you could do here:
//                - show an alert to the user and refuse to proceed
//                - make an HTTP request to your own server and alert you
//            */
//        }
//
//        String installer = mContext.getPackageManager().getInstallerPackageName(PACKAGE_NAME);
//
//        if (installer == null){
//            Log.d(TAG, "this is an APK");
//            /*
//                Things you could do here:
//                - show an alert to the user and refuse to proceed
//                - make an HTTP request to your own server and alert you
//            */
//        }
//
//        if (installer.compareTo(GOOGLE_PLAY) != 0 && installer.compareTo(AMAZON_STORE) != 0){
//            Log.d(TAG, "not installed from either stores");
//            /*
//                Things you could do here:
//                - show an alert to the user and refuse to proceed
//                - make an HTTP request to your own server and alert you
//            */
//        }

        Log.i(TAG, "isHacked: " + isHacked(mContext, PACKAGE_NAME, GOOGLE_PLAY, AMAZON_STORE));
    }

    public boolean isHacked(Context context, String myPackageName, String google, String amazon)
    {
        //Renamed?
        if (context.getPackageName().compareTo(myPackageName) != 0) {
            return true; // BOOM!
        }

        //Relocated?
        String installer = context.getPackageManager().getInstallerPackageName(myPackageName);

        if (installer == null){
            return true; // BOOM!
        }

        if (installer.compareTo(google) != 0 && installer.compareTo(amazon) != 0){
            return true; // BOOM!
        }
        return false;
    }

}
