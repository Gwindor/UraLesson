package mcpp.com.uralesson.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import mcpp.com.uralesson.R;
import mcpp.com.uralesson.activitys.MainActivity;

public class FragmentTV extends Fragment {
    private final String SAVE_TAG = "tv";
    private final String TAG = FragmentTV.class.getCanonicalName();
    private Button btn;
    private TextView tv;
    private Uri outputFileUri;

    public static FragmentTV instanceFragmentTV(Uri uri) {
        FragmentTV fragmentTV = new FragmentTV();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.URI_PARAM, uri);
        fragmentTV.setArguments(bundle);
        return fragmentTV;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListeners();
    }


    private void initViews(View rooView) {
        btn = (Button) rooView.findViewById(R.id.btn_foto);
        tv = (TextView) rooView.findViewById(R.id.text_view);
    }

    private void initListeners() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(Environment.getExternalStorageDirectory(),
                        "test.jpg");
                outputFileUri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Log.d(TAG, outputFileUri.toString());
            PhotoEvent photoEvent = (PhotoEvent) getActivity();
            photoEvent.somePhoto(outputFileUri);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_TAG, String.valueOf(tv.getText()));
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null)
            tv.setText(savedInstanceState.getString(SAVE_TAG));
    }


    public void setMyText(String c) {
        tv.setText(c);
    }

    public interface PhotoEvent {
        void somePhoto(Uri uri);
    }
}
