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

public class DoNotBlameDialog extends AppCompatDialogFragment {

    TextView confirmDoNotBlame;
    private DoNotBlameStartListener doNotBlameStartListener;
    boolean checkValue;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_donotblame_dialog, null);

        builder.setView(view)
                .setTitle("Love Yourself")
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        checkValue = true;
                        doNotBlameStartListener.startDoNotBlame(checkValue);
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        checkValue = false;
                        doNotBlameStartListener.startDoNotBlame(checkValue);
                    }
                });

        confirmDoNotBlame = view.findViewById(R.id.dialog_doNotBlame);
        confirmDoNotBlame.setText("Do you blame yourself for something? Or are you in an unbearable situation? Leave yourself a message of encouragement.");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            doNotBlameStartListener = (DoNotBlameStartListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DoNotBlameStartListener");
        }
    }

    public interface DoNotBlameStartListener{
        void startDoNotBlame(boolean checkCancel);
    }
}
