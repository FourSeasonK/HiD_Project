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
import com.example.hid.created.adapter.ShapeAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShapeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShapeFragment extends BottomSheetDialogFragment implements ShapeAdapter.ShapeAdapterListener, IconColorAcdapter.IconColorAcdapterListener {

    RecyclerView recycler_shapeFrame;
    RecyclerView recycler_shapeColors;
    Button btn_add_imageShapes;
    int shape_selected = -1;
    int color_selected = -1;
    ArrayList<Integer> shapeColorsList = new ArrayList<>();

    AddImageListener addImageListener;
    public void  setListener(AddImageListener listener){
        this.addImageListener = listener;
    }

    static ShapeFragment instance;

    public static ShapeFragment getInstance(){
        if(instance == null){
            instance = new ShapeFragment();
        }
        return instance;
    }

    public void insetImage(){
        shapeColorsList.add(R.drawable.shapel1);
        shapeColorsList.add(R.drawable.shapel2);
        shapeColorsList.add(R.drawable.shapel3);
        shapeColorsList.add(R.drawable.shapel4);
        shapeColorsList.add(R.drawable.shapel5);

        shapeColorsList.add(R.drawable.shapef1);
        shapeColorsList.add(R.drawable.shapef2);
        shapeColorsList.add(R.drawable.shapef3);
        shapeColorsList.add(R.drawable.shapef4);
        shapeColorsList.add(R.drawable.shapef5);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShapeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShapeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShapeFragment newInstance(String param1, String param2) {
        ShapeFragment fragment = new ShapeFragment();
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
        View itemView = inflater.inflate(R.layout.fragment_shape, container, false);

        recycler_shapeFrame = (RecyclerView) itemView.findViewById(R.id.recycler_shapeIcons);
        recycler_shapeColors = (RecyclerView) itemView.findViewById(R.id.recycler_shapeColors);
        btn_add_imageShapes = (Button) itemView.findViewById(R.id.btn_add_imageShapes);


        changeShapeColor(color_selected);

        Log.d("ColorIndex1: ", "" + color_selected);
        recycler_shapeFrame.setHasFixedSize(true);
        recycler_shapeFrame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_shapeFrame.setAdapter(new ShapeAdapter(getContext(), this, shapeColorsList));

        recycler_shapeColors.setHasFixedSize(true);
        recycler_shapeColors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_shapeColors.setAdapter(new IconColorAcdapter(getContext(), this));

        btn_add_imageShapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageListener.onAddImage(shape_selected);
            }
        });

        return itemView;
    }

    @Override
    public void onShapeSelected(int shape) {
        shape_selected = shape;
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

    private void changeShapeColor(int index) {

        shapeColorsList = new ArrayList<>();

        if (index == 0) {

            shapeColorsList.add(R.drawable.shapelred1);
            shapeColorsList.add(R.drawable.shapelred2);
            shapeColorsList.add(R.drawable.shapelred3);
            shapeColorsList.add(R.drawable.shapelred4);
            shapeColorsList.add(R.drawable.shapelred5);

            shapeColorsList.add(R.drawable.shapefred1);
            shapeColorsList.add(R.drawable.shapefred2);
            shapeColorsList.add(R.drawable.shapefred3);
            shapeColorsList.add(R.drawable.shapefred4);
            shapeColorsList.add(R.drawable.shapefred5);

        } else if(index == 1){

            shapeColorsList.add(R.drawable.shapelorange1);
            shapeColorsList.add(R.drawable.shapelorange2);
            shapeColorsList.add(R.drawable.shapelorange3);
            shapeColorsList.add(R.drawable.shapelorange4);
            shapeColorsList.add(R.drawable.shapelorange5);

            shapeColorsList.add(R.drawable.shapeforange1);
            shapeColorsList.add(R.drawable.shapeforange2);
            shapeColorsList.add(R.drawable.shapeforange3);
            shapeColorsList.add(R.drawable.shapeforange4);
            shapeColorsList.add(R.drawable.shapeforange5);

        } else if(index == 2){

            shapeColorsList.add(R.drawable.shapelyellow1);
            shapeColorsList.add(R.drawable.shapelyellow2);
            shapeColorsList.add(R.drawable.shapelyellow3);
            shapeColorsList.add(R.drawable.shapelyellow4);
            shapeColorsList.add(R.drawable.shapelyellow5);

            shapeColorsList.add(R.drawable.shapefyellow1);
            shapeColorsList.add(R.drawable.shapefyellow2);
            shapeColorsList.add(R.drawable.shapefyellow3);
            shapeColorsList.add(R.drawable.shapefyellow4);
            shapeColorsList.add(R.drawable.shapefyellow5);

        } else if(index == 3){

            shapeColorsList.add(R.drawable.shapelgreen1);
            shapeColorsList.add(R.drawable.shapelgreen2);
            shapeColorsList.add(R.drawable.shapelgreen3);
            shapeColorsList.add(R.drawable.shapelgreen4);
            shapeColorsList.add(R.drawable.shapelgreen5);

            shapeColorsList.add(R.drawable.shapefgreen1);
            shapeColorsList.add(R.drawable.shapefgreen2);
            shapeColorsList.add(R.drawable.shapefgreen3);
            shapeColorsList.add(R.drawable.shapefgreen4);
            shapeColorsList.add(R.drawable.shapefgreen5);

        } else if(index == 4){

            shapeColorsList.add(R.drawable.shapellblue1);
            shapeColorsList.add(R.drawable.shapellblue2);
            shapeColorsList.add(R.drawable.shapellblue3);
            shapeColorsList.add(R.drawable.shapellblue4);
            shapeColorsList.add(R.drawable.shapellblue5);

            shapeColorsList.add(R.drawable.shapeflblue1);
            shapeColorsList.add(R.drawable.shapeflblue2);
            shapeColorsList.add(R.drawable.shapeflblue3);
            shapeColorsList.add(R.drawable.shapeflblue4);
            shapeColorsList.add(R.drawable.shapeflblue5);

        } else if(index == 5){

            shapeColorsList.add(R.drawable.shapeldblue1);
            shapeColorsList.add(R.drawable.shapeldblue2);
            shapeColorsList.add(R.drawable.shapeldblue3);
            shapeColorsList.add(R.drawable.shapeldblue4);
            shapeColorsList.add(R.drawable.shapeldblue5);

            shapeColorsList.add(R.drawable.shapefdblue1);
            shapeColorsList.add(R.drawable.shapefdblue2);
            shapeColorsList.add(R.drawable.shapefdblue3);
            shapeColorsList.add(R.drawable.shapefdblue4);
            shapeColorsList.add(R.drawable.shapefdblue5);

        } else if(index == 6){

            shapeColorsList.add(R.drawable.shapelpurple1);
            shapeColorsList.add(R.drawable.shapelpurple2);
            shapeColorsList.add(R.drawable.shapelpurple3);
            shapeColorsList.add(R.drawable.shapelpurple4);
            shapeColorsList.add(R.drawable.shapelpurple5);

            shapeColorsList.add(R.drawable.shapefpurple1);
            shapeColorsList.add(R.drawable.shapefpurple2);
            shapeColorsList.add(R.drawable.shapefpurple3);
            shapeColorsList.add(R.drawable.shapefpurple4);
            shapeColorsList.add(R.drawable.shapefpurple5);

        } else {
            insetImage();
        }

    }

}