package mcpp.com.uralesson.activitys;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import mcpp.com.uralesson.R;
import mcpp.com.uralesson.fragments.FragmentET;
import mcpp.com.uralesson.fragments.FragmentTV;

public class MainActivity extends AppCompatActivity implements FragmentET.EventFragmentET, FragmentTV.PhotoEvent {
    public static final String URI_PARAM = "uri file";
    private Uri uri = getUri();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.et, FragmentET.instanceFragmentET(uri))
                    .add(R.id.tv, FragmentTV.instanceFragmentTV(uri))
                    .commit();
        }
    }

    @Override
    public void someEvent(String c) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.tv);
        if (fragment != null && fragment instanceof FragmentTV) {
            FragmentTV fragmentTV = (FragmentTV) fragment;
            fragmentTV.setMyText(c);
        }
    }

    @Override
    public void somePhoto(Uri uri) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.et);
        if (fragment != null && fragment instanceof FragmentET) {
            FragmentET fragmentET = (FragmentET) fragment;
            fragmentET.setImageURI(uri);
        }
    }

    public Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
        Uri outputFileUri = Uri.fromFile(file);
        return outputFileUri;
    }
}

