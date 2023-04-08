package com.example.hid.model;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.hid.R;

public class ResetpasswordDialog extends AppCompatDialogFragment {

    private EditText editTextEmail;
    private ResetPasswordDialogListener resetPasswordDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_resetpasswordemail_dialog, null);

        builder.setView(view)
                .setTitle("Send email to Reset Password")
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userEmail = editTextEmail.getText().toString().trim();
                        resetPasswordDialogListener.applyTexts(userEmail);
                    }
                });

        editTextEmail = view.findViewById(R.id.dialog_resetpasswordEmail);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            resetPasswordDialogListener = (ResetPasswordDialogListener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString() +
                   "must implement ResetPasswordDialogListener");
        }
    }

    public interface ResetPasswordDialogListener{
        void applyTexts(String email);
    }
}
