package com.justinzyh.film.mvp.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.net.Uri;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.justinzyh.film.R;

import java.lang.ref.SoftReference;
import java.util.Hashtable;

/**
 * Created by justinzyh on 2016/11/11.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class ImageUtil {

    private static final String                                    TAG         = "ImageUtil";
    /**
     * 缓存集合
     */
    private static       Hashtable<Integer, SoftReference<Bitmap>> mImageCache = new Hashtable<Integer, SoftReference<Bitmap>>();

    /**
     * 根据id返回一个处理后的图片
     *
     * @param res
     * @param resID
     * @return
     */
    public static Bitmap getImageBitmap(Resources res, int resID) {

        // 先去集合中取当前resID是否已经拿过图片，如果集合中有，说明已经拿过，直接使用集合中的图片返回
        SoftReference<Bitmap> reference = mImageCache.get(resID);
        if (reference != null) {
            Bitmap bitmap = reference.get();
            if (bitmap != null) {// 从内存中取
                Log.i(TAG, "从内存中取");
                return bitmap;
            }
        }
        // 如果集合中没有，就调用getInvertImage得到一个图片，需要向集合中保留一张，最后返回当前图片
        Log.i(TAG, "重新加载");
        Bitmap invertBitmap = getInvertBitmap(res, resID);
        // 在集合中保存一份，便于下次获取时直接在集合中获取
        mImageCache.put(resID, new SoftReference<Bitmap>(invertBitmap));
        return invertBitmap;
    }

    /**
     * 根据图片的id，获取到处理之后的图片
     *
     * @param resID
     * @return
     */
    public static Bitmap getInvertBitmap(Resources res, int resID) {
        // 1.获取原图
        Bitmap sourceBitmap = BitmapFactory.decodeResource(res, resID);

        // 2.生成倒影图片
        Matrix m = new Matrix(); // 图片矩阵
        m.setScale(1.0f, -1.0f); // 让图片按照矩阵进行反转
        Bitmap invertBitmap = Bitmap.createBitmap(sourceBitmap, 0,
                sourceBitmap.getHeight() / 2, sourceBitmap.getWidth(),
                sourceBitmap.getHeight() / 2, m, false);

        // 3.两张图片合成一张图片
        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(),
                (int) (sourceBitmap.getHeight() * 1.5 + 5), Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap); // 为合成图片指定一个画板
        canvas.drawBitmap(sourceBitmap, 0f, 0f, null); // 将原图片画在画布的上方
        canvas.drawBitmap(invertBitmap, 0f, sourceBitmap.getHeight() + 5, null); // 将倒影图片画在画布的下方

        // 4.添加遮罩效果
        Paint paint = new Paint();
        // 设置遮罩的颜色，这里使用的是线性梯度
        LinearGradient shader = new LinearGradient(0,
                sourceBitmap.getHeight() + 5, 0, resultBitmap.getHeight(),
                0x70ffffff, 0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        // 设置模式为：遮罩，取交集
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        canvas.drawRect(0, sourceBitmap.getHeight() + 5,
                sourceBitmap.getWidth(), resultBitmap.getHeight(), paint);

        return resultBitmap;
    }

    /**
     * @param context   上下文
     * @param url       图片url(直接传从服务器获取的url即可,方法内会自动把baseurl拼接上)
     * @param imageView 显示在哪个imageview上
     */
    public static void showImage(Context context, String url, ImageView imageView) {
        Glide
                .with(context) // 指定Context,同时接受Activity、Fragment
                .load(url)// 指定图片的URL
                .placeholder(R.mipmap.image1)// 指定图片未成功加载前显示的图片
                .error(R.mipmap.image2)// 指定图片加载失败显示的图片
                .override(120, 120)//指定图片的尺寸
                .centerCrop()// 指定图片缩放类型为centerCrop
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//仅仅缓存最终的图像
                .into(imageView);//指定显示图片的ImageView
    }
    public static String getUri2Url( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String url = null;
        if ( scheme == null )
            url = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            url = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( ImageColumns.DATA );
                    if ( index > -1 ) {
                        url = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return url;
    }
}
