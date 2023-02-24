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
import com.example.hid.created.adapter.NoseAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoseFragment extends BottomSheetDialogFragment implements NoseAdapter.NosesAdapterListener, IconColorAcdapter.IconColorAcdapterListener {

    RecyclerView recycler_noseFrame;
    RecyclerView recycler_noseColors;
    Button btn_add_imageNoses;
    int nose_selected = -1;
    int color_selected = -1;
    ArrayList<Integer> noseColorsList = new ArrayList<>();

    AddImageListener addImageListener;
    public void  setListener(AddImageListener listener){
        this.addImageListener = listener;
    }

    static NoseFragment instance;

    public static NoseFragment getInstance(){
        if(instance == null){
            instance = new NoseFragment();
        }
        return instance;
    }

    public void insetImage(){
        noseColorsList.add(R.drawable.nose1);
        noseColorsList.add(R.drawable.nose2);
        noseColorsList.add(R.drawable.nose3);
        noseColorsList.add(R.drawable.nose4);
        noseColorsList.add(R.drawable.nose5);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoseFragment newInstance(String param1, String param2) {
        NoseFragment fragment = new NoseFragment();
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
        View itemView = inflater.inflate(R.layout.fragment_nose, container, false);

        recycler_noseFrame = (RecyclerView) itemView.findViewById(R.id.recycler_noseIcons);
        recycler_noseColors = (RecyclerView) itemView.findViewById(R.id.recycler_noseColors);
        btn_add_imageNoses = (Button) itemView.findViewById(R.id.btn_add_imageNoses);


        changeNoseColor(color_selected);

        Log.d("ColorIndex1: ", "" + color_selected);
        recycler_noseFrame.setHasFixedSize(true);
        recycler_noseFrame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_noseFrame.setAdapter(new NoseAdapter(getContext(), this, noseColorsList));

        recycler_noseColors.setHasFixedSize(true);
        recycler_noseColors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_noseColors.setAdapter(new IconColorAcdapter(getContext(), this));

        btn_add_imageNoses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageListener.onAddImage(nose_selected);
            }
        });

        return itemView;
    }

    @Override
    public void onNoseSelected(int nose) {
        nose_selected = nose;
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

    public void changeNoseColor(int index){
        noseColorsList = new ArrayList<>();

        if (index == 0) {
            noseColorsList.add(R.drawable.nosered1);
            noseColorsList.add(R.drawable.nosered2);
            noseColorsList.add(R.drawable.nosered3);
            noseColorsList.add(R.drawable.nosered4);
            noseColorsList.add(R.drawable.nosered5);

//            for(int i = 0; i < eyeColorsList.size(); i++ ){
//                Log.d("red eyes:  ", eyeColorsList.get(i).toString());
//            }
        } else if(index == 1){
            noseColorsList.add(R.drawable.noseorange1);
            noseColorsList.add(R.drawable.noseorange2);
            noseColorsList.add(R.drawable.noseorange3);
            noseColorsList.add(R.drawable.noseorange4);
            noseColorsList.add(R.drawable.noseorange5);

        } else if(index == 2){
            noseColorsList.add(R.drawable.noseyellow1);
            noseColorsList.add(R.drawable.noseyellow2);
            noseColorsList.add(R.drawable.noseyellow3);
            noseColorsList.add(R.drawable.noseyellow4);
            noseColorsList.add(R.drawable.noseyellow5);

        } else if(index == 3){
            noseColorsList.add(R.drawable.nosegreen1);
            noseColorsList.add(R.drawable.nosegreen2);
            noseColorsList.add(R.drawable.nosegreen3);
            noseColorsList.add(R.drawable.nosegreen4);
            noseColorsList.add(R.drawable.nosegreen5);

        } else if(index == 4){
            noseColorsList.add(R.drawable.noselblue1);
            noseColorsList.add(R.drawable.noselblue2);
            noseColorsList.add(R.drawable.noselblue3);
            noseColorsList.add(R.drawable.noselblue4);
            noseColorsList.add(R.drawable.noselblue5);

        } else if(index == 5){
            noseColorsList.add(R.drawable.nosedblue1);
            noseColorsList.add(R.drawable.nosedblue2);
            noseColorsList.add(R.drawable.nosedblue3);
            noseColorsList.add(R.drawable.nosedblue4);
            noseColorsList.add(R.drawable.nosedblue5);

        } else if(index == 6){
            noseColorsList.add(R.drawable.nosepurple1);
            noseColorsList.add(R.drawable.nosepurple2);
            noseColorsList.add(R.drawable.nosepurple3);
            noseColorsList.add(R.drawable.nosepurple4);
            noseColorsList.add(R.drawable.nosepurple5);

        }


        insetImage();

    }

}