package empolyesecurity.recyclerviewwithloadjson;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by vivid on 2/11/17.
 */
public class App extends Application {
    public static final int ADD_RECORD = 0;
    public static final int UPDATE_RECORD = 1;
    public static final String DML_TYPE = "DML_TYPE";
    public static final String UPDATE = "Update";
    public static final String INSERT = "Insert";
    public static final String DELETE = "Delete";
    public static final String FIRST_NAME = "Firstname";
    public static final String LAST_NAME = "Lastname";
    public static final String ID = "ID";
    @Override public void onCreate() {
        super.onCreate();

        ViewTarget.setTagId(R.id.glide_tag);

    }


        /** CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT */



        public static boolean checkConnection(Context context) {
            final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet
                Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true;
                } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    return true;
                }
            }
            return false;
        }

}
