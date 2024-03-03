package com.example.meidiamusic.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meidiamusic.R;
import com.example.meidiamusic.adapter.NhacAdapter;
import com.example.meidiamusic.model.Nhac;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikeListSongGridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikeListSongGridFragment extends Fragment {
    ArrayList<Nhac> nhacItems;
    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LikeListSongGridFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LikeListSongGridFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LikeListSongGridFragment newInstance(String param1, String param2) {
        LikeListSongGridFragment fragment = new LikeListSongGridFragment();
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
        intSampleData();
    }

    void intSampleData() {
        nhacItems = new ArrayList<Nhac>();
        nhacItems.add(new Nhac(R.drawable.icon_user,"ThinhSuyNghi"));
        nhacItems.add(new Nhac(R.drawable.icon_user,"ThinhSuyNghi"));
        nhacItems.add(new Nhac(R.drawable.icon_user,"ThinhSuyNghi"));
        nhacItems.add(new Nhac(R.drawable.icon_user,"ThinhSuyNghi"));
        nhacItems.add(new Nhac(R.drawable.icon_user,"ThinhSuyNghi"));
        nhacItems.add(new Nhac(R.drawable.icon_user,"ThinhSuyNghi"));
        nhacItems.add(new Nhac(R.drawable.icon_user,"ThinhSuyNghi"));
        nhacItems.add(new Nhac(R.drawable.icon_user,"ThinhSuyNghi"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like_list_song_grid,container,false);
        recyclerView = view.findViewById(R.id.recyclerView2Columns);
        NhacAdapter nhacAdapter = new NhacAdapter(nhacItems,getContext());
        recyclerView.setAdapter(nhacAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        // Inflate the layout for this fragment
        return view;
    }
}