package pat.pimplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;

public class DirArrayAdapter extends ArrayAdapter<File> {
    public DirArrayAdapter(Context context, File[] objects) {
        super(context, 0, objects); //TODO: possibly allow custom resources to replace d_entry
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String filename = getItem(position).toString();
        //If there's no view to recycle, inflate one
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.d_entry, parent, false);
        }
        TextView deText = convertView.findViewById(R.id.d_ent_text);
        //We only want the directory name relative to current dir
        deText.setText(filename.substring(filename.lastIndexOf('/') + 1));

        return convertView;
    }

}
