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
import com.example.hid.created.adapter.LineAdapter;
import com.example.hid.created.adapter.ShapeAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LineFragment extends BottomSheetDialogFragment implements LineAdapter.LineAdapterListener, IconColorAcdapter.IconColorAcdapterListener {

    RecyclerView recycler_lineFrame;
    RecyclerView recycler_lineColors;
    Button btn_add_imageLines;
    int line_selected = -1;
    int color_selected = -1;
    ArrayList<Integer> lineColorsList = new ArrayList<>();

    AddImageListener addImageListener;
    public void  setListener(AddImageListener listener){
        this.addImageListener = listener;
    }

    static LineFragment instance;

    public static LineFragment getInstance(){
        if(instance == null){
            instance = new LineFragment();
        }
        return instance;
    }

    public void insetImage(){
        lineColorsList.add(R.drawable.line1);
        lineColorsList.add(R.drawable.line2);
        lineColorsList.add(R.drawable.line3);
        lineColorsList.add(R.drawable.line4);
        lineColorsList.add(R.drawable.line5);
        lineColorsList.add(R.drawable.line6);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LineFragment newInstance(String param1, String param2) {
        LineFragment fragment = new LineFragment();
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
        View itemView = inflater.inflate(R.layout.fragment_line, container, false);

        recycler_lineFrame = (RecyclerView) itemView.findViewById(R.id.recycler_lineIcons);
        recycler_lineColors = (RecyclerView) itemView.findViewById(R.id.recycler_lineColors);
        btn_add_imageLines = (Button) itemView.findViewById(R.id.btn_add_imageLines);


        changeLineColor(color_selected);

        Log.d("ColorIndex1: ", "" + color_selected);
        recycler_lineFrame.setHasFixedSize(true);
        recycler_lineFrame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_lineFrame.setAdapter(new LineAdapter(getContext(), this, lineColorsList));

        recycler_lineColors.setHasFixedSize(true);
        recycler_lineColors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_lineColors.setAdapter(new IconColorAcdapter(getContext(), this));

        btn_add_imageLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageListener.onAddImage(line_selected);
            }
        });

        return itemView;
    }

    @Override
    public void onLineSelected(int line) {
        line_selected = line;
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

    private void changeLineColor(int index) {
        lineColorsList = new ArrayList<>();

        if (index == 0) {
            lineColorsList.add(R.drawable.linered1);
            lineColorsList.add(R.drawable.linered2);
            lineColorsList.add(R.drawable.linered3);
            lineColorsList.add(R.drawable.linered4);
            lineColorsList.add(R.drawable.linered5);
            lineColorsList.add(R.drawable.linered6);

        } else if(index == 1){
            lineColorsList.add(R.drawable.lineorange1);
            lineColorsList.add(R.drawable.lineorange2);
            lineColorsList.add(R.drawable.lineorange3);
            lineColorsList.add(R.drawable.lineorange4);
            lineColorsList.add(R.drawable.lineorange5);
            lineColorsList.add(R.drawable.lineorange6);

        } else if(index == 2){
            lineColorsList.add(R.drawable.lineyellow1);
            lineColorsList.add(R.drawable.lineyellow2);
            lineColorsList.add(R.drawable.lineyellow3);
            lineColorsList.add(R.drawable.lineyellow4);
            lineColorsList.add(R.drawable.lineyellow5);
            lineColorsList.add(R.drawable.lineyellow6);

        } else if(index == 3){
            lineColorsList.add(R.drawable.linegreen1);
            lineColorsList.add(R.drawable.linegreen2);
            lineColorsList.add(R.drawable.linegreen3);
            lineColorsList.add(R.drawable.linegreen4);
            lineColorsList.add(R.drawable.linegreen5);
            lineColorsList.add(R.drawable.linegreen6);

        } else if(index == 4){
            lineColorsList.add(R.drawable.linelblue1);
            lineColorsList.add(R.drawable.linelblue2);
            lineColorsList.add(R.drawable.linelblue3);
            lineColorsList.add(R.drawable.linelblue4);
            lineColorsList.add(R.drawable.linelblue5);
            lineColorsList.add(R.drawable.linelblue6);

        } else if(index == 5){
            lineColorsList.add(R.drawable.linedblue1);
            lineColorsList.add(R.drawable.linedblue2);
            lineColorsList.add(R.drawable.linedblue3);
            lineColorsList.add(R.drawable.linedblue4);
            lineColorsList.add(R.drawable.linedblue5);
            lineColorsList.add(R.drawable.linedblue6);

        } else if(index == 6){
            lineColorsList.add(R.drawable.linepurple1);
            lineColorsList.add(R.drawable.linepurple2);
            lineColorsList.add(R.drawable.linepurple3);
            lineColorsList.add(R.drawable.linepurple4);
            lineColorsList.add(R.drawable.linepurple5);
            lineColorsList.add(R.drawable.linepurple6);

        } else {
            insetImage();
        }

    }

}