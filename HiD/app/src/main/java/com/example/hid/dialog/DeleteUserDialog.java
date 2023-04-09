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

public class DeleteUserDialog extends AppCompatDialogFragment {

    TextView deleteConfirmMessage;
    private DeleteUserInfoDialogListener deleteUserInfoDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_deleteuser_dialog, null);

        builder.setView(view)
                .setTitle("Confirm the information")
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteUserInfoDialogListener.deleteConfirmMessage();
                    }
                });

        deleteConfirmMessage = view.findViewById(R.id.dialog_ConfirmMessage);
        deleteConfirmMessage.setText("Are you sure you want to delete user information?");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            deleteUserInfoDialogListener = (DeleteUserInfoDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() +
                    "must implement DeleteUserInfoDialogListener");
        }

    }

    public interface DeleteUserInfoDialogListener{
        void deleteConfirmMessage();
    }
}
