package pat.pimplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DirArrayAdapter<T> extends ArrayAdapter {
    public DirArrayAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View ConvertView, ViewGroup parent) {



        return null;
    }

}
