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

public class MyDDiaryDialog extends AppCompatDialogFragment {

    TextView confirmMyDDiary;
    private DoWriteDDiaryDialogListener doWriteDDiaryDialogListener;
    boolean checkValue;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_myddiary_dialog, null);

        builder.setView(view)
                .setTitle("Write Your Depression Diary")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        checkValue = true;
                        doWriteDDiaryDialogListener.startDDiary(checkValue);
//                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        checkValue = false;
                        doWriteDDiaryDialogListener.startDDiary(checkValue);
                    }
                });

        confirmMyDDiary = view.findViewById(R.id.dialog_myDDiary);
        confirmMyDDiary.setText("Record your daily mood along with the depression image you created");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            doWriteDDiaryDialogListener = (DoWriteDDiaryDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DoWriteDDiaryDialogListener");
        }

    }

    public interface DoWriteDDiaryDialogListener{
        void startDDiary(boolean checkCancel);
    }
}
