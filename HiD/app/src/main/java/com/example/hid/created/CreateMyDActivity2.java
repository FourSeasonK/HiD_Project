package com.example.hid.created;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hid.R;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.created.fragment.ImageFragment;
import com.example.hid.databinding.ActivityCreateMyDactivityBinding;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.List;

import ja.burhanrashid52.photoeditor.OnSaveBitmap;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class CreateMyDActivity2 extends NavigationActivity implements AddImageListener {

    private static final String TAG = CreateMyDActivity2.class.getSimpleName();
    ActivityCreateMyDactivityBinding activityCreateMyDactivityBinding;

    ImageView imgEyes, imgNoses, imgLips, imgShapes, imgLines, imgBackgrounds, imgDTools;
    Button btnSaveD, btnShareD;
    PhotoEditorView photoEditorView;
    PhotoEditor photoEditor;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_my_dactivity);

        activityCreateMyDactivityBinding = ActivityCreateMyDactivityBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_create_my_dactivity, frameLayout);

        constraintLayout = rootView.findViewById(R.id.contraintlayout);

        imgEyes = rootView.findViewById(R.id.imgEyes);
        imgNoses = rootView.findViewById(R.id.imgNoses);
        imgLips = rootView.findViewById(R.id.imgLips);
        imgShapes = rootView.findViewById(R.id.imgShapes);
        imgLines = rootView.findViewById(R.id.imgLines);
        imgBackgrounds = rootView.findViewById(R.id.imgBackgrounds);
        imgDTools = rootView.findViewById(R.id.imgDTools);
        btnSaveD = rootView.findViewById(R.id.btnSaveCreateD);
        btnShareD = rootView.findViewById(R.id.btnShareCreateD);

       photoEditorView = findViewById(R.id.imgDrawBoard);
       photoEditorView.getSource().setImageURI(getIntent().getData());
       photoEditor = new PhotoEditor.Builder(this, photoEditorView).setPinchTextScalable(true).build();

        imgBackgrounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Bring the image
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);

            }
        });

        //add example Frame
        imgLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ImageFragment imageFragment = ImageFragment.getInstance();
                ImageFragment imageFragment = ImageFragment.getInstance();
                imageFragment.setListener(CreateMyDActivity2.this);
                imageFragment.show(getSupportFragmentManager(),imageFragment.getTag());
            }
        });

        //save
        btnSaveD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click here1");
                saveImage();
                Log.d(TAG, "Click here2");
            }
        });



    }

    private void saveImage() {
        Log.d(TAG, "Click here3");
        Dexter.withContext(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                if(multiplePermissionsReport.areAllPermissionsGranted()){
                    photoEditor.saveAsBitmap(new OnSaveBitmap() {
                        @Override
                        public void onBitmapReady(@Nullable Bitmap saveBitmap) {

                            photoEditorView.getSource().setImageBitmap(saveBitmap);

                            try {
                                final String path = BitmapClass.insertImage(getContentResolver(), saveBitmap, System.currentTimeMillis() + "_profile.jpg", null);
                                Log.d(TAG, "path Value: " + path);

                                if(!TextUtils.isEmpty(path)){
                                    Snackbar snackbar = Snackbar.make(constraintLayout, "Image saved successfully", Snackbar.LENGTH_LONG).setAction("OPEN", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            openImageLocation(path);
                                        }
                                    });

                                    snackbar.show();
                                }else {
                                    Snackbar snackbar = Snackbar.make(constraintLayout, "Unable to save image", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }

                            } catch (IOException e) {

                                e.printStackTrace();

                            }
                        }

                        @Override
                        public void onFailure(@Nullable Exception e) {

                        }
                    });
                }

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();

            }
        });
    }

    private void openImageLocation(String path) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path), "image/*");
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            if(data.getData() != null){
                Uri filepath = data.getData();
                Intent intent = new Intent(CreateMyDActivity2.this, CreateMyDActivity2.class);
                intent.setData(filepath);
                startActivity(intent);

            }
        }
    }

    @Override
    public void onAddImage(int image) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
        photoEditor.addImage(bitmap);
    }
}