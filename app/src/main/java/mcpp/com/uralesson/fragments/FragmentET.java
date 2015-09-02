package mcpp.com.uralesson.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import mcpp.com.uralesson.R;
import mcpp.com.uralesson.activitys.MainActivity;

public class FragmentET extends Fragment {
    private static final String TAG = FragmentET.class.getSimpleName();
    private EventFragmentET eventFragmentET;
    private EditText editText;
    private ImageView imgPhoto;

    public static FragmentET instanceFragmentET(Uri uri) {
        FragmentET fragmentET = new FragmentET();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.URI_PARAM, uri);
        fragmentET.setArguments(bundle);
        return fragmentET;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_et, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        eventFragmentET = (EventFragmentET) context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListener();
    }

    private void initViews(View view) {
        editText = (EditText) view.findViewById(R.id.edit_text);
        imgPhoto = (ImageView) view.findViewById(R.id.photo);
    }

    public void initListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (eventFragmentET != null) {
                    eventFragmentET.someEvent(s.toString());
                    Log.d(TAG, String.valueOf(s));
                }
            }
        });
    }

    public void setImageURI(Uri imageURI) {
        imgPhoto.setImageURI(imageURI);
    }

    public interface EventFragmentET {
        void someEvent(String c);
    }

}
