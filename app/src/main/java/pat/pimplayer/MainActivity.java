package pat.pimplayer;

import android.Manifest;
import android.util.Log;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_READ_STORAGE = 0;
    private static final int REQUEST_WRITE_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAndRequestPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_READ_STORAGE);
//        checkAndRequestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_WRITE_STORAGE);

        //Get the path to our top-level Pimsleur directory
        File baseDir = new File("/storage/emulated/0/Pimsleur");
        File[] courses = baseDir.listFiles();
        ArrayAdapter<File> adapter = new ArrayAdapter<File>(this,
                android.R.layout.simple_list_item_1, courses);
        ListView courseList = (ListView) findViewById(R.id.courseList);
        courseList.setAdapter(adapter);
    }

    private void checkAndRequestPermissions(String permission, int requestCode) {
        if( ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch(requestCode) {
            case REQUEST_READ_STORAGE:
            case REQUEST_WRITE_STORAGE:
                if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Hey enable that!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) ||
           Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
