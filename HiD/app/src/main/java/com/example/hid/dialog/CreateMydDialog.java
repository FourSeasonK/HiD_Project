package com.example.hid.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.hid.R;

public class CreateMydDialog extends AppCompatDialogFragment {

    TextView confirmCreateD;
    private DoCreateMyDDialogListener doCreateMyDDialogListener;
    boolean checkValue;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_creatd_dialog, null);

        builder.setView(view)
                .setTitle("Express My Depression")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        checkValue = true;
                        doCreateMyDDialogListener.startCreateMyD(checkValue);
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        checkValue = false;
                        doCreateMyDDialogListener.startCreateMyD(checkValue);
                    }
                });

        confirmCreateD = view.findViewById(R.id.dialog_created);
//        confirmCreateD.setText("Express your depression to psychologically objectify it");
        confirmCreateD.setText("Express your depression freely and comfortably. This can help create a psychological distance from it");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            doCreateMyDDialogListener = (DoCreateMyDDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() +
                    "must implement DoCreateMyDDialogListener");
        }
    }

    public interface DoCreateMyDDialogListener{
        void startCreateMyD(boolean checkCancel);
    }
}
