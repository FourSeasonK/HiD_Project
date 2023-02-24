package com.example.hid.created.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hid.R;
import com.example.hid.created.AddImageListener;
import com.example.hid.created.adapter.IconColorAcdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawFragment extends BottomSheetDialogFragment implements IconColorAcdapter.IconColorAcdapterListener{

    RecyclerView recycler_drawColors;
    ImageView img_pencil, img_eraser;
    Button btn_add_imagedraw;
    int draw_selected = -1;
    int color_selected = -1;

    ArrayList<Integer> drawColorsList = new ArrayList<>();

    AddImageListener addImageListener;
    public void  setListener(AddImageListener listener){
        this.addImageListener = listener;
    }

    static DrawFragment instance;

    public static DrawFragment getInstance(){
        if(instance == null){
            instance = new DrawFragment();
        }
        return instance;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DrawFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrawFragment newInstance(String param1, String param2) {
        DrawFragment fragment = new DrawFragment();
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

        View itemView = inflater.inflate(R.layout.fragment_draw, container, false);

        recycler_drawColors = itemView.findViewById(R.id.recycler_drawColors);
        img_pencil = itemView.findViewById(R.id.img_pencil);
        img_eraser = itemView.findViewById(R.id.img_eraser);
        btn_add_imagedraw = itemView.findViewById(R.id.btn_add_imageEyes);

        recycler_drawColors.setHasFixedSize(true);
        recycler_drawColors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_drawColors.setAdapter(new IconColorAcdapter(getContext(), this));

        img_pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        img_eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_add_imagedraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageListener.onAddImage(draw_selected);
            }
        });

        return itemView;
    }

    @Override
    public void onColorSeledted(int index) {

        draw_selected = index;
//        refreshFragment();
    }

    public void refreshFragment() {

        getActivity().getSupportFragmentManager().beginTransaction().detach(this).commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().beginTransaction().attach(this).commitAllowingStateLoss();
        //adapter.notifyDataSetChanged();
    }
}