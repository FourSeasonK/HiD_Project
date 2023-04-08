package com.example.hid.created;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapClass {

    private static final String TAG = BitmapClass.class.getSimpleName();

    public static String insertImage(ContentResolver contentResolver, Bitmap source, String title, String description) throws IOException{
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, description);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());

        Uri uri = null;
        String stringurl = null;

        try{
            uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Log.d("URI1:", " " + uri);

            if(source != null){

                OutputStream outputStream = contentResolver.openOutputStream(uri);
                source.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);

                long id = ContentUris.parseId(uri);
                Bitmap mini = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                //storeThumnail(contentResolver, mini, id, 50f, 50f, MediaStore.Images.Thumbnails.MICRO_KIND);

            }else {
                contentResolver.delete(uri, null, null);
                uri = null;
            }
        }catch (FileNotFoundException ef) {

            if(uri != null){
                contentResolver.delete(uri, null, null);
                uri = null;
            }

            ef.printStackTrace();

        }

        if(uri != null){
            stringurl = uri.toString();
        }
        return stringurl;
    }

    private static final Bitmap storeThumnail(ContentResolver contentResolver, Bitmap source, long id, float width, float height, int microKind) {

        Matrix matrix = new Matrix();
        float X = width/source.getWidth();
        float Y = height/source.getHeight();

        matrix.setScale(X, Y);

        Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        ContentValues contentValues = new ContentValues(4);

        contentValues.put(MediaStore.Images.Thumbnails.KIND, microKind);
        contentValues.put(MediaStore.Images.Thumbnails.IMAGE_ID, id);
        contentValues.put(MediaStore.Images.Thumbnails.HEIGHT, height);
        contentValues.put(MediaStore.Images.Thumbnails.WIDTH, width);

        Uri uri = contentResolver.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, contentValues);
        Log.d("URI2:", " " + uri);

        try {
            OutputStream outputStream = contentResolver.openOutputStream(uri);
            Log.d(TAG, "OutputStream: " + outputStream);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
            return bitmap;

        } catch (FileNotFoundException ef){
            ef.printStackTrace();
            return null;

        } catch (IOException e){
            e.printStackTrace();
            return null;

        }
    }
}
