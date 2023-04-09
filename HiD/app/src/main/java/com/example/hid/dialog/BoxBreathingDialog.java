package com.example.hid.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.hid.R;

public class BoxBreathingDialog extends AppCompatDialogFragment {

    TextView confirmBoxbreath;
    private DoBoxBreathDialogListener doBoxBreathDialogListener;
    boolean checkValue;

//    SharedPreferences sharedPreferences = getContext().getSharedPreferences("prefs",Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPreferences.edit();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_boxbreath_dialog, null);

        builder.setView(view)
                .setTitle("Calm Your Mind")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        checkValue = true;
//                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("prefs",0);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
////                        dialogInterface.dismiss();
//                        editor.putBoolean("NotShow", checkValue);
//                        editor.commit();
                        doBoxBreathDialogListener.startBoxBreath(checkValue);
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        checkValue = false;
                        doBoxBreathDialogListener.startBoxBreath(checkValue);
                        dialogInterface.cancel();
//                        editor.putBoolean("NotShow", false);
//                        editor.apply();

                    }
                });

        confirmBoxbreath = view.findViewById(R.id.dialog_BoxBreathing);
        confirmBoxbreath.setText("Repeating breathing in, holding the breath, exhaling and holding it again for 4 seconds");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            doBoxBreathDialogListener = (DoBoxBreathDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DoBoxBreathDialogListener");
        }

    }

    public interface DoBoxBreathDialogListener{
        void startBoxBreath(boolean checkCancel);
    }
}
