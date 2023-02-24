package com.example.hid.created.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hid.R;
import com.example.hid.created.AddImageListener;
import com.example.hid.created.adapter.FrameAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ImageFragment extends BottomSheetDialogFragment implements FrameAdapter.FrameAdapterListener {

    RecyclerView recycler_imageFrame;
    Button btn_add_imageFrame;
    int frame_selected = -1;

    AddImageListener addImageListener;
    public void  setListener(AddImageListener listener){
        this.addImageListener = listener;
    }

    static  ImageFragment instance;

    public static ImageFragment getInstance(){
        if(instance == null){
            instance = new ImageFragment();
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

//    public imageFragment() {
//        // Required empty public constructor
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment imageFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static ImageFragment newInstance(String param1, String param2) {
//        ImageFragment fragment = new ImageFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_image,container, false);

        recycler_imageFrame = (RecyclerView) itemView.findViewById(R.id.recycler_imageFrame);
        btn_add_imageFrame = (Button) itemView.findViewById(R.id.btn_add_imageFrame);

        recycler_imageFrame.setHasFixedSize(true);
        recycler_imageFrame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_imageFrame.setAdapter(new FrameAdapter(getContext(), this));

        btn_add_imageFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageListener.onAddImage(frame_selected);
            }
        });

        return itemView;
    }

    public void onFrameSelected(int frame){
        frame_selected = frame;
    }
}
