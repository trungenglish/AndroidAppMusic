package com.example.meidiamusic.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meidiamusic.R;
import com.example.meidiamusic.adapter.NhacAdapter;
import com.example.meidiamusic.adapter.TuyenTapAdapter;
import com.example.meidiamusic.model.Nhac;
import com.example.meidiamusic.model.TuyenTap;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListMusicHorizontalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMusicHorizontalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListMusicHorizontalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListMusicHorizontalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListMusicHorizontalFragment newInstance(String param1, String param2) {
        ListMusicHorizontalFragment fragment = new ListMusicHorizontalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        intSampleDate();
    }

    private void intSampleDate() {
        ttItem = new ArrayList<TuyenTap>();
        ttItem.add(new TuyenTap(R.drawable.icon_user,"ThinhSuyNghi"));
        ttItem.add(new TuyenTap(R.drawable.icon_user,"ThinhSuyNghi"));
        ttItem.add(new TuyenTap(R.drawable.icon_user,"ThinhSuyNghi"));
        ttItem.add(new TuyenTap(R.drawable.icon_user,"ThinhSuyNghi"));
        ttItem.add(new TuyenTap(R.drawable.icon_user,"ThinhSuyNghi"));
        ttItem.add(new TuyenTap(R.drawable.icon_user,"ThinhSuyNghi"));

    }

    ArrayList<TuyenTap> ttItem;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_music_horizontal,container,false);
        recyclerView = view.findViewById(R.id.recyclerViewHorizontal);
        TuyenTapAdapter tuyenTapAdapter = new TuyenTapAdapter(ttItem,getContext());
        recyclerView.setAdapter(tuyenTapAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        return view;
    }
}