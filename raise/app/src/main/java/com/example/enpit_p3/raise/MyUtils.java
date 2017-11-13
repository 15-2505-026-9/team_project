package com.example.enpit_p3.raise;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import io.realm.internal.IOException;

/**
 * Created by enPiT-P9 on 2017/08/08.
 */

public class MyUtils {

    public static Bitmap getImageFromByte(byte[] bytes){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,opt);
        int bitmapSize = 1;
        if((opt.outHeight * opt.outWidth) > 500000){
            double outsize = (double) (opt.outHeight * opt.outWidth) / 500000;
            bitmapSize = (int)(Math.sqrt(outsize) + 1);
        }
        opt.inJustDecodeBounds = false;
        opt.inSampleSize = bitmapSize;
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.length,opt);
        return bmp;
    }
    public static byte[] getByteFromImage(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    public static Bitmap getImageFromStream(ContentResolver resolver,Uri uri) throws IOException, FileNotFoundException {
        InputStream in;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        in = resolver.openInputStream(uri);
        BitmapFactory.decodeStream(in,null,opt);
        assert in != null;
        try {
            in.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        int bitmapSize = 1;
        if((opt.outHeight * opt.outWidth)>500000){
            double outSize = (double) (opt.outHeight*opt.outWidth)/500000;
            bitmapSize = (int)(Math.sqrt(outSize) +1);
        }
        opt.inJustDecodeBounds = false;
        opt.inSampleSize = bitmapSize;
        in = resolver.openInputStream(uri);
        Bitmap bmp = BitmapFactory.decodeStream(in,null,opt);
        try {
            in.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color){
        Drawable normalDrawable = item.getIcon();
        Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
        DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(context,color));
        item.setIcon(wrapDrawable);
    }

}
