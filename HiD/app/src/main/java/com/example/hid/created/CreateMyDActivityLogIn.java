package com.example.hid.created;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.TooltipCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hid.R;
import com.example.hid.activities.HomeActivity;
import com.example.hid.activities.HomeActivityLogInD;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.activities.NavigationActivityLogIn;
import com.example.hid.created.fragment.EyeFragment;
import com.example.hid.created.fragment.ImageFragment;
import com.example.hid.created.fragment.LineFragment;
import com.example.hid.created.fragment.LipFragment;
import com.example.hid.created.fragment.NoseFragment;
import com.example.hid.created.fragment.ShapeFragment;
import com.example.hid.databinding.ActivityCreateMyDactivityBinding;
import com.example.hid.dialog.CreateMydDialog;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import ja.burhanrashid52.photoeditor.OnSaveBitmap;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class CreateMyDActivityLogIn extends NavigationActivityLogIn implements AddImageListener {
//public class CreateMyDActivityLogIn extends NavigationActivityLogIn implements AddImageListener, CreateMydDialog.DoCreateMyDDialogListener {

    private static final String TAG = CreateMyDActivityLogIn.class.getSimpleName();
    ActivityCreateMyDactivityBinding activityCreateMyDactivityBinding;
    private static int REQUEST_CODE = 100;
    OutputStream outputStream;

    ImageView imgEyes, imgNoses, imgLips, imgShapes, imgLines, imgFrames, imgBackgrounds, imgDTools;
    Button btnSaveD, btnShareD;
    PhotoEditorView photoEditorView;
    PhotoEditor photoEditor;
    ConstraintLayout constraintLayout;
    String path;
    boolean doNotShowAgian;

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
        imgLines = rootView.findViewById(R.id.imgLines);
        imgShapes = rootView.findViewById(R.id.imgShapes);
        imgFrames = rootView.findViewById(R.id.imgFrames);
        imgBackgrounds = rootView.findViewById(R.id.imgBackgrounds);
//        imgDTools = rootView.findViewById(R.id.imgDTools);
        btnSaveD = rootView.findViewById(R.id.btnSaveCreateD);
        btnShareD = rootView.findViewById(R.id.btnShareCreateD);

       photoEditorView = findViewById(R.id.imgDrawBoard);
       photoEditorView.getSource().setImageURI(getIntent().getData());
       photoEditor = new PhotoEditor.Builder(this, photoEditorView).setPinchTextScalable(true).build();

       photoEditorView.setVisibility(View.VISIBLE);

       /////To Test the App/////
//       removeDataFromPref(CreateMyDActivityLogIn.this);
       openCreateMyDDialog();

       imgEyes.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               imgEyes.setImageResource(R.drawable.eyeselectgreen);
               imgNoses.setImageResource(R.drawable.nosegray);
               imgLips.setImageResource(R.drawable.lipgray);
               imgLines.setImageResource(R.drawable.linegray);
               imgShapes.setImageResource(R.drawable.shapegray);
               imgFrames.setImageResource(R.drawable.framegray);
               imgBackgrounds.setImageResource(R.drawable.backgroundgray);
//               imgDTools.setImageResource(R.drawable.drawgray);

               EyeFragment eyeFragment = EyeFragment.getInstance();
               eyeFragment.setListener(CreateMyDActivityLogIn.this);
               eyeFragment.show(getSupportFragmentManager(),eyeFragment.getTag());
           }
       });

       imgNoses.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               imgEyes.setImageResource(R.drawable.eyegray);
               imgNoses.setImageResource(R.drawable.noseselectgreen);
               imgLips.setImageResource(R.drawable.lipgray);
               imgLines.setImageResource(R.drawable.linegray);
               imgShapes.setImageResource(R.drawable.shapegray);
               imgFrames.setImageResource(R.drawable.framegray);
               imgBackgrounds.setImageResource(R.drawable.backgroundgray);
//               imgDTools.setImageResource(R.drawable.drawgray);

               NoseFragment noseFragment = NoseFragment.getInstance();
               noseFragment.setListener(CreateMyDActivityLogIn.this);
               noseFragment.show(getSupportFragmentManager(), noseFragment.getTag());
           }
       });

       imgLips.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               imgEyes.setImageResource(R.drawable.eyegray);
               imgNoses.setImageResource(R.drawable.nosegray);
               imgLips.setImageResource(R.drawable.lipselectgreen);
               imgLines.setImageResource(R.drawable.linegray);
               imgShapes.setImageResource(R.drawable.shapegray);
               imgFrames.setImageResource(R.drawable.framegray);
               imgBackgrounds.setImageResource(R.drawable.backgroundgray);
//               imgDTools.setImageResource(R.drawable.drawgray);

               LipFragment lipFragment = LipFragment.getInstance();
               lipFragment.setListener(CreateMyDActivityLogIn.this);
               lipFragment.show(getSupportFragmentManager(), lipFragment.getTag());
           }
       });

       imgLines.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               imgEyes.setImageResource(R.drawable.eyegray);
               imgNoses.setImageResource(R.drawable.nosegray);
               imgLips.setImageResource(R.drawable.lipgray);
               imgLines.setImageResource(R.drawable.lineselectgreen);
               imgShapes.setImageResource(R.drawable.shapegray);
               imgBackgrounds.setImageResource(R.drawable.backgroundgray);
//               imgDTools.setImageResource(R.drawable.drawgray);

               LineFragment lineFragment = LineFragment.getInstance();
               lineFragment.setListener(CreateMyDActivityLogIn.this);
               lineFragment.show(getSupportFragmentManager(), lineFragment.getTag());
           }
       });

       imgShapes.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               imgEyes.setImageResource(R.drawable.eyegray);
               imgNoses.setImageResource(R.drawable.nosegray);
               imgLips.setImageResource(R.drawable.lipgray);
               imgLines.setImageResource(R.drawable.linegray);
               imgShapes.setImageResource(R.drawable.shapeselectgreen);
               imgFrames.setImageResource(R.drawable.framegray);
               imgBackgrounds.setImageResource(R.drawable.backgroundgray);
//               imgDTools.setImageResource(R.drawable.drawgray);

               ShapeFragment shapeFragment = ShapeFragment.getInstance();
               shapeFragment.setListener(CreateMyDActivityLogIn.this);
               shapeFragment.show(getSupportFragmentManager(), shapeFragment.getTag());
           }
       });

        imgFrames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgEyes.setImageResource(R.drawable.eyegray);
                imgNoses.setImageResource(R.drawable.nosegray);
                imgLips.setImageResource(R.drawable.lipgray);
               imgLines.setImageResource(R.drawable.linegray);
                imgShapes.setImageResource(R.drawable.shapegray);
                imgFrames.setImageResource(R.drawable.frameselectgreen);
                imgBackgrounds.setImageResource(R.drawable.backgroundgray);
//                imgDTools.setImageResource(R.drawable.drawgray);

                ImageFragment imageFragment = ImageFragment.getInstance();
                imageFragment.setListener(CreateMyDActivityLogIn.this);
                imageFragment.show(getSupportFragmentManager(),imageFragment.getTag());

            }
        });

        imgBackgrounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgEyes.setImageResource(R.drawable.eyegray);
                imgNoses.setImageResource(R.drawable.nosegray);
                imgLips.setImageResource(R.drawable.lipgray);
                imgLines.setImageResource(R.drawable.linegray);
                imgShapes.setImageResource(R.drawable.shapegray);
                imgFrames.setImageResource(R.drawable.framegray);
                imgBackgrounds.setImageResource(R.drawable.imageselectgreen);
//                imgDTools.setImageResource(R.drawable.drawgray);

                //Bring the image
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);

            }
        });

//        imgDTools.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imgEyes.setImageResource(R.drawable.eyegray);
//                imgNoses.setImageResource(R.drawable.nosegray);
//                imgLips.setImageResource(R.drawable.lipgray);
////                imgLines.setImageResource(R.drawable.linegray);
//                imgShapes.setImageResource(R.drawable.shapegray);
//                imgFrames.setImageResource(R.drawable.framegray);
//                imgBackgrounds.setImageResource(R.drawable.backgroundgray);
//                imgDTools.setImageResource(R.drawable.toolselectgreen);
//
//                DrawFragment drawFragment = DrawFragment.getInstance();
//                drawFragment.setListener(CreateMyDActivity.this);
//                drawFragment.show(getSupportFragmentManager(),drawFragment.getTag());
//            }
//        });


        //save
        btnSaveD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(path == null) {
//                    Toast.makeText(CreateMyDActivityLogIn.this, "Please load the image from device first", Toast.LENGTH_SHORT).show();
//                } else {
//                    saveImage();
//                }
                saveImage();

            }
        });

        btnShareD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getAndShareImage();

                if(path == null){
                    photoEditorView.setVisibility(View.VISIBLE);
                } else {
                    photoEditorView.setVisibility(View.INVISIBLE);
                }
            }
        });
        
    }


    private void saveImage() {
        Log.d(TAG, "Click here3");

        Dexter.withActivity(this).withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                Log.d(TAG, "Click here4");
                if(multiplePermissionsReport.areAllPermissionsGranted()){
                    photoEditor.saveAsBitmap(new OnSaveBitmap() {
                        @Override
                        public void onBitmapReady(@Nullable Bitmap saveBitmap) {

                            photoEditorView.getSource().setImageBitmap(saveBitmap);

                            try {
//                               final String path = BitmapClass.insertImage(getContentResolver(), saveBitmap, System.currentTimeMillis() + "_profile.jpg", null);
                                path = BitmapClass.insertImage(getContentResolver(), saveBitmap, System.currentTimeMillis() + "_profile.jpg", null);
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
                            Toast.makeText(CreateMyDActivityLogIn.this, "fail to the save process" ,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();

            }
        }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError dexterError) {
                Toast.makeText(CreateMyDActivityLogIn.this, "Error occur to save image" ,Toast.LENGTH_SHORT).show();
            }
        }).check();
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
                Intent intent = new Intent(CreateMyDActivityLogIn.this, CreateMyDActivityLogIn.class);
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

    private void getAndShareImage(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

//        BitmapDrawable drawable = (BitmapDrawable)photoEditorView.getSource().getDrawable();
//        Log.d(TAG,  "drawable !!!: " +  drawable);
//        Bitmap bitmap = drawable.getBitmap();

        shareImage(this);
    }

    public void shareImage(Context context) {
//    public static void shareImage(Bitmap bmp, Context context) {

//        String sImageUrl = MediaStore.Images.Media.insertImage(context.getContentResolver(), bmp, "title" , "description");
        if(path == null){
            Toast.makeText(CreateMyDActivityLogIn.this, "Please load & save the image first", Toast.LENGTH_SHORT).show();

        } else {
            Uri savedImageURI = Uri.parse(path);

            if (savedImageURI != null) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/png");
                shareIntent.putExtra(Intent.EXTRA_STREAM, savedImageURI);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(Intent.createChooser(shareIntent, "Share Image"));
            }
        }
    }

    public void openCreateMyDDialog(){
//        CreateMydDialog createMydDialog = new CreateMydDialog();
//        createMydDialog.show(getSupportFragmentManager(), "Start Create My D");
        final AlertDialog.Builder adb = new AlertDialog.Builder(CreateMyDActivityLogIn.this);
        LayoutInflater adbInflater = LayoutInflater.from(CreateMyDActivityLogIn.this);
        View eulaLayout = adbInflater.inflate(R.layout.layout_creatd_dialog, null);

        adb.setView(eulaLayout);
        adb.setTitle("Express a Portrait of Depression");
        adb.setMessage("\nCreate a portrait of your depression as it may help you create a psychological distance from it. \n\n\n" +
                        "1) Click on the leftmost round icon at the bottom to load the image you want.\n\n" +
                        "2) Click on each icon, select a color, and designate a shape.\n\n" +
                        "3) Resizing it and place it where you want.\n\n\n" +
                        "Enjoy!");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        adb.setNegativeButton("Do not show again", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                doNotShowAgian = true;
                SharedPreferences settings = getSharedPreferences("CreateMyDLogIn", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("skipMessage", doNotShowAgian);
                editor.commit();
                dialog.cancel();
            }
        });
        SharedPreferences settings = getSharedPreferences("CreateMyDLogIn", 0);
        Boolean skipMessage = settings.getBoolean("skipMessage", false);

        if (skipMessage.equals(false)) {
            adb.show();
        }
    }

    //to test the app
    public static void removeDataFromPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("CreateMyDLogIn", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("skipMessage");
        editor.commit();
    }

//    @Override
//    public void startCreateMyD(boolean checkCancel) {
//        if(checkCancel){
//            Intent intent = new Intent(CreateMyDActivityLogIn.this, HomeActivityLogInD.class);
//            startActivity(intent);
//        }
//    }
}