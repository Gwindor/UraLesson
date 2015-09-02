package mcpp.com.uralesson.activitys;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import mcpp.com.uralesson.R;
import mcpp.com.uralesson.fragments.FragmentET;
import mcpp.com.uralesson.fragments.FragmentTV;

public class MainActivity extends Activity implements FragmentET.EventFragmentET, FragmentTV.PhotoEvent {
    public static final String URI_PARAM = "uri file";
    private Uri uri = getUri();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.et, FragmentET.instanceFragmentET(uri))
                    .add(R.id.tv, FragmentTV.instanceFragmentTV(uri))
                    .commit();
        }
    }

    @Override
    public void someEvent(String c) {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.tv);
        if (fragment != null && fragment instanceof FragmentTV) {
            FragmentTV fragmentTV = (FragmentTV) fragment;
            fragmentTV.setMyText(c);
        }
    }

    @Override
    public void somePhoto(Uri uri) {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.et);
        if (fragment != null && fragment instanceof FragmentTV) {
            View fragmentView = fragment.getView();
            ImageView imageView = (ImageView) (fragmentView != null ? fragmentView.findViewById(R.id.photo) : null);
            if (imageView != null)
                imageView.setImageURI(uri);
        }
    }

    public Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "test.jpg");
        Uri outputFileUri = Uri.fromFile(file);
        return outputFileUri;
    }
}

