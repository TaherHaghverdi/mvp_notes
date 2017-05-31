package ir.coursio.notes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Taher on 08/04/2017.
 * Project: RetrofitTutorial
 */

//Each activity that intends to get a permission from user needs to inherit from this activity
public class PermissionActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent intent = new Intent("PERMISSION_RECEIVER");
        intent.putExtra("requestCode", requestCode);
        intent.putExtra("permissions", permissions);
        intent.putExtra("grantResults", grantResults);
        sendBroadcast(intent);
    }
}
