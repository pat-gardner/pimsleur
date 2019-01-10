package pat.pimplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;

import java.io.File;

public class FileSelectActivity extends AppCompatActivity  {

    private String dirname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_select_layout);

        Intent intent = getIntent();
        String dirname = intent.getStringExtra(MainActivity.EXTRA_DIRNAME);
        this.dirname = dirname;
        render();
    }

//    private View.OnClickListener myClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            file_clicked(v);
//        }
//    };

    public void file_clicked(View v){
//        v.callOnClick(); TODO: some kind of animation?
        RelativeLayout vv = (RelativeLayout) v;
        TextView listItem = (TextView) vv.getChildAt(0);
        String filename = this.dirname + "/" + listItem.getText().toString();
//        Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();

        File f = new File(filename);
        //If f is a directory, start a new instance of this activity in the new directory
        if(f.isDirectory()) {
            Intent intent = new Intent(this, FileSelectActivity.class);
            intent.putExtra(MainActivity.EXTRA_DIRNAME, filename);
            startActivity(intent);
        }
    }

    private void render() {
        //Check that the SD card is there
        if ( !isExternalStorageReadable() ) {
            Toast.makeText(this, "Problem accessing files", Toast.LENGTH_SHORT).show();
            return;
        }
        //Get the path to our top-level Pimsleur directory
        File baseDir = new File(dirname);
        File[] files = baseDir.listFiles();
        DirArrayAdapter adapter = new DirArrayAdapter(this, files);
        ListView courseList = findViewById(R.id.courseList);
        courseList.setAdapter(adapter);
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }
}
