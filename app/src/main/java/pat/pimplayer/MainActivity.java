package pat.pimplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_READ_STORAGE = 0;
    private static final int REQUEST_WRITE_STORAGE = 1;
    public static final String EXTRA_DIRNAME = "pat.pimplayer.DIRNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mainButtonClick(View v){
        //If we have permissions, go immediately render the page
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
             while(true){
                Toast.makeText(this, "Started while", Toast.LENGTH_SHORT).show();
                //TODO: allow directory selection
                String dirname = "/storage/emulated/0/Pimsleur";
                File dir = new File(dirname);
                if(dir.isDirectory()) {
                    viewFiles(dirname);
                    break;
                }
                break; //TODO: reselect
            }
        }
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
        }

    }

    protected void viewFiles(String dirname) {
        //Send an intent to start FileSelectActivity
        Intent intent = new Intent(this, FileSelectActivity.class);
        intent.putExtra(EXTRA_DIRNAME, dirname);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch(requestCode) {
            case REQUEST_READ_STORAGE:
            case REQUEST_WRITE_STORAGE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //TODO: allow directory selection
                    viewFiles("/storage/emulated/0/Pimsleur");
                }
                else {
                    Toast.makeText(this, "Hey enable that!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
