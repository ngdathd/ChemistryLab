package com.ngdat.chemistrylab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ngdat.chemistrylab.R;
import com.ngdat.chemistrylab.activity.DocumentActivity;
import com.ngdat.chemistrylab.adapter.LectureAdapter;
import androidx.fragment.app.Fragment;
/**
 * Created by Admin on 10/19/2016.
 */

public class LectureFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String KEY_PDF_PATH = "KEY_PDF_PATH";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lecture_fragment, container, false);
        ListView listViewLecture = (ListView) rootView.findViewById(R.id.list_view_lecture);
        listViewLecture.setOnItemClickListener(this);
        listViewLecture.setAdapter(new LectureAdapter(getActivity()));
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String path = ((LectureAdapter)parent.getAdapter()).getItem(position).getPath();
        Bundle sendBundle = new Bundle();
        sendBundle.putString(KEY_PDF_PATH, path);
        LectureContentViewFragment lectureContentViewFragment = new LectureContentViewFragment();
        lectureContentViewFragment.setArguments(sendBundle);
        DocumentActivity documentActivity = (DocumentActivity) getActivity();
        documentActivity.getSupportFragmentManager().beginTransaction()
                .hide(LectureFragment.this)
                .add(R.id.ll_main,lectureContentViewFragment)
                .show(lectureContentViewFragment).addToBackStack("BACK_TO_LECTURE_LIST")
                .commit();
//        ((DocumentActivity)getActivity()).showFragment(lectureContentViewFragment);
    }
}
