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
import com.example.hid.created.adapter.EyesAdapter;
import com.example.hid.created.adapter.IconColorAcdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EyeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EyeFragment extends BottomSheetDialogFragment implements EyesAdapter.EyesAdapterListener, IconColorAcdapter.IconColorAcdapterListener{

    RecyclerView recycler_eyesFrame;
    RecyclerView recycler_eyesColors;
    Button btn_add_imageEyes;
    int eye_selected = -1;
    int color_selected = -1;
//    private boolean isFragmentDisplayed = false;
    ArrayList<Integer> eyeColorsList = new ArrayList<>();

    AddImageListener addImageListener;
    public void  setListener(AddImageListener listener){
        this.addImageListener = listener;
    }

    static EyeFragment instance;

    public static EyeFragment getInstance(){
        if(instance == null){
            instance = new EyeFragment();
        }
        return instance;
    }

    public void insetImage(){
            eyeColorsList.add(R.drawable.eye1);
            eyeColorsList.add(R.drawable.eye2);
            eyeColorsList.add(R.drawable.eye3);
            eyeColorsList.add(R.drawable.eye4);
            eyeColorsList.add(R.drawable.eye5);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    public EyeFragment() {
//        // Required empty public constructor
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EyeFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static EyeFragment newInstance(String param1, String param2) {
//        EyeFragment fragment = new EyeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

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

        View itemView = inflater.inflate(R.layout.fragment_eye, container, false);

        recycler_eyesFrame = (RecyclerView) itemView.findViewById(R.id.recycler_eyeIcons);
        recycler_eyesColors = (RecyclerView) itemView.findViewById(R.id.recycler_eyeColors);
        btn_add_imageEyes = (Button) itemView.findViewById(R.id.btn_add_imageEyes);


        changeEyeColor(color_selected);

        Log.d("ColorIndex1: ", "" + color_selected);
        recycler_eyesFrame.setHasFixedSize(true);
        recycler_eyesFrame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_eyesFrame.setAdapter(new EyesAdapter(getContext(), this, eyeColorsList));

        recycler_eyesColors.setHasFixedSize(true);
        recycler_eyesColors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_eyesColors.setAdapter(new IconColorAcdapter(getContext(), this));

        btn_add_imageEyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageListener.onAddImage(eye_selected);
            }
        });

        return itemView;

    }

    @Override
    public void onEyeSelected(int eye) {
        eye_selected = eye;
//        itemView = view;
    }

    @Override
    public void onColorSeledted(int index) {

        color_selected = index;
        refreshFragment();
        Log.d("ColorIndex2: ", "" + index);
    }

    public void refreshFragment() {

        getActivity().getSupportFragmentManager().beginTransaction().detach(this).commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().beginTransaction().attach(this).commitAllowingStateLoss();
        //adapter.notifyDataSetChanged();

        /////////it works too//////
//        Fragment frg = EyeFragment.instance;
//        if (frg != null) {
//            getActivity().getSupportFragmentManager().beginTransaction().detach(frg).commitAllowingStateLoss();
//            getActivity().getSupportFragmentManager().beginTransaction().attach(frg).commitAllowingStateLoss();
//        }
    }

    public void changeEyeColor(int index){
        eyeColorsList = new ArrayList<>();

        if (index == 0) {
            eyeColorsList.add(R.drawable.eyered1);
            eyeColorsList.add(R.drawable.eyered2);
            eyeColorsList.add(R.drawable.eyered3);
            eyeColorsList.add(R.drawable.eyered4);
            eyeColorsList.add(R.drawable.eyered5);

//            for(int i = 0; i < eyeColorsList.size(); i++ ){
//                Log.d("red eyes:  ", eyeColorsList.get(i).toString());
//            }
        } else if(index == 1){
            eyeColorsList.add(R.drawable.eyeorange1);
            eyeColorsList.add(R.drawable.eyeorange2);
            eyeColorsList.add(R.drawable.eyeorange3);
            eyeColorsList.add(R.drawable.eyeorange4);
            eyeColorsList.add(R.drawable.eyeorange5);

        } else if(index == 2){
            eyeColorsList.add(R.drawable.eyeyellow1);
            eyeColorsList.add(R.drawable.eyeyellow2);
            eyeColorsList.add(R.drawable.eyeyellow3);
            eyeColorsList.add(R.drawable.eyeyellow4);
            eyeColorsList.add(R.drawable.eyeyellow5);

        } else if(index == 3){
            eyeColorsList.add(R.drawable.eyegreen1);
            eyeColorsList.add(R.drawable.eyegreen2);
            eyeColorsList.add(R.drawable.eyegreen3);
            eyeColorsList.add(R.drawable.eyegreen4);
            eyeColorsList.add(R.drawable.eyegreen5);

        } else if(index == 4){
            eyeColorsList.add(R.drawable.eyelblue1);
            eyeColorsList.add(R.drawable.eyelblue2);
            eyeColorsList.add(R.drawable.eyelblue3);
            eyeColorsList.add(R.drawable.eyelblue4);
            eyeColorsList.add(R.drawable.eyelblue5);

        } else if(index == 5){
            eyeColorsList.add(R.drawable.eyedblue1);
            eyeColorsList.add(R.drawable.eyedblue2);
            eyeColorsList.add(R.drawable.eyedblue3);
            eyeColorsList.add(R.drawable.eyedblue4);
            eyeColorsList.add(R.drawable.eyedblue5);

        } else if(index == 6){
            eyeColorsList.add(R.drawable.eyepurple1);
            eyeColorsList.add(R.drawable.eyepurple2);
            eyeColorsList.add(R.drawable.eyepurple3);
            eyeColorsList.add(R.drawable.eyepurple4);
            eyeColorsList.add(R.drawable.eyepurple5);

        } else {
            insetImage();
        }
    }

}



