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

public class UpdateUserInfoDialog extends AppCompatDialogFragment {

    TextView userFirstName, userLastName, userEmail;
    private UpdateUserInforDialogListener updateUserInforDialogListener;
    String firstName, lastName, email;
    boolean check;

    public UpdateUserInfoDialog(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_updateuserinfo_dialog, null);

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

//                        updateUserInforDialogListener.displayTexts(firstName, lastName, email);
                        updateUserInforDialogListener.displayTexts();
                    }
                });

        userFirstName = view.findViewById(R.id.dialog_userFirstName);
        userLastName = view.findViewById(R.id.dialog_userLastName);
        userEmail = view.findViewById(R.id.dialog_userEmail);

        userFirstName.setText("First Name: " + firstName);
        userLastName.setText("Last Name: " + lastName);
        userEmail.setText("Email: " + email);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            updateUserInforDialogListener = (UpdateUserInforDialogListener) context;
        } catch (ClassCastException e) {
           throw  new ClassCastException(context.toString() +
                   "must implement UpdateUserInforDialogListener");
        }
    }

    public interface UpdateUserInforDialogListener{
//        void displayTexts(String firstName, String lastName, String email);
        void displayTexts();
    }
}
