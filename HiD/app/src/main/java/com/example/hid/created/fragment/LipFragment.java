package com.example.hid.created.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hid.R;
import com.example.hid.created.AddImageListener;
import com.example.hid.created.adapter.IconColorAcdapter;
import com.example.hid.created.adapter.LipAdapter;
import com.example.hid.created.adapter.NoseAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LipFragment extends BottomSheetDialogFragment implements LipAdapter.LipAdapterListener, IconColorAcdapter.IconColorAcdapterListener {

    RecyclerView recycler_lipFrame;
    RecyclerView recycler_lipColors;
    Button btn_add_imageLips;
    int lip_selected = -1;
    int color_selected = -1;
    ArrayList<Integer> lipColorsList = new ArrayList<>();

    AddImageListener addImageListener;
    public void  setListener(AddImageListener listener){
        this.addImageListener = listener;
    }

    static LipFragment instance;

    public static LipFragment getInstance(){
        if(instance == null){
            instance = new LipFragment();
        }
        return instance;
    }

    public void insetImage(){
        lipColorsList.add(R.drawable.lip1);
        lipColorsList.add(R.drawable.lip2);
        lipColorsList.add(R.drawable.lip3);
        lipColorsList.add(R.drawable.lip4);
        lipColorsList.add(R.drawable.lip5);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LipFragment newInstance(String param1, String param2) {
        LipFragment fragment = new LipFragment();
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

        instance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View itemView = inflater.inflate(R.layout.fragment_lip, container, false);

        recycler_lipFrame = (RecyclerView) itemView.findViewById(R.id.recycler_lipIcons);
        recycler_lipColors = (RecyclerView) itemView.findViewById(R.id.recycler_lipColors);
        btn_add_imageLips = (Button) itemView.findViewById(R.id.btn_add_imagelips);

        changeLipColor(color_selected);

        Log.d("ColorIndex1: ", "" + color_selected);
        recycler_lipFrame.setHasFixedSize(true);
        recycler_lipFrame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_lipFrame.setAdapter(new LipAdapter(getContext(), this, lipColorsList));

        recycler_lipColors.setHasFixedSize(true);
        recycler_lipColors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_lipColors.setAdapter(new IconColorAcdapter(getContext(), this));

        btn_add_imageLips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageListener.onAddImage(lip_selected);
            }
        });

        return itemView;
    }

    @Override
    public void onLipSelected(int lip) {
        lip_selected = lip;
    }

    @Override
    public void onColorSeledted(int index) {
        color_selected = index;
        refreshFragment();
    }

    public void refreshFragment() {

        getActivity().getSupportFragmentManager().beginTransaction().detach(this).commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().beginTransaction().attach(this).commitAllowingStateLoss();
        //adapter.notifyDataSetChanged();
    }

    private void changeLipColor(int index) {
        lipColorsList = new ArrayList<>();

        if (index == 0) {
            lipColorsList.add(R.drawable.lipred1);
            lipColorsList.add(R.drawable.lipred2);
            lipColorsList.add(R.drawable.lipred3);
            lipColorsList.add(R.drawable.lipred4);
            lipColorsList.add(R.drawable.lipred5);

        } else if (index == 1) {
            lipColorsList.add(R.drawable.liporange1);
            lipColorsList.add(R.drawable.liporange2);
            lipColorsList.add(R.drawable.liporange3);
            lipColorsList.add(R.drawable.liporange4);
            lipColorsList.add(R.drawable.liporange5);

        } else if (index == 2) {
            lipColorsList.add(R.drawable.lipyellow1);
            lipColorsList.add(R.drawable.lipyellow2);
            lipColorsList.add(R.drawable.lipyellow3);
            lipColorsList.add(R.drawable.lipyellow4);
            lipColorsList.add(R.drawable.lipyellow5);

        } else if (index == 3) {
            lipColorsList.add(R.drawable.lipgreen1);
            lipColorsList.add(R.drawable.lipgreen2);
            lipColorsList.add(R.drawable.lipgreen3);
            lipColorsList.add(R.drawable.lipgreen4);
            lipColorsList.add(R.drawable.lipgreen5);

        } else if (index == 4) {
            lipColorsList.add(R.drawable.liplblue1);
            lipColorsList.add(R.drawable.liplblue2);
            lipColorsList.add(R.drawable.liplblue3);
            lipColorsList.add(R.drawable.liplblue4);
            lipColorsList.add(R.drawable.liplblue5);

        } else if (index == 5) {
            lipColorsList.add(R.drawable.lipdblue1);
            lipColorsList.add(R.drawable.lipdblue2);
            lipColorsList.add(R.drawable.lipdblue3);
            lipColorsList.add(R.drawable.lipdblue4);
            lipColorsList.add(R.drawable.lipdblue5);

        } else if (index == 6) {
            lipColorsList.add(R.drawable.lippurple1);
            lipColorsList.add(R.drawable.lippurple2);
            lipColorsList.add(R.drawable.lippurple3);
            lipColorsList.add(R.drawable.lippurple4);
            lipColorsList.add(R.drawable.lippurple5);
        }

        insetImage();
    }

}