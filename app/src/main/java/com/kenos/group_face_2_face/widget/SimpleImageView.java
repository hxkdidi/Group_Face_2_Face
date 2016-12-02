package com.kenos.group_face_2_face.widget;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.kenos.group_face_2_face.R;
import com.kenos.group_face_2_face.costant.FilePathConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

public class SimpleImageView extends ImageView {
    // glide
    private static RequestManager requestManager;
    // application context
    private static Context applicationContext;
    // handler
    private static Handler handler;

    // image params
    private Shape shape;
    private Uri uri;

    /**
     * constructor
     */
    public SimpleImageView(Context context) {
        this(context, null);
    }

    public SimpleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        shape = Shape.RECTANGLE;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.image);
            shape = Shape.valueOf(a.getInt(R.styleable.image_shape, 0));
            a.recycle();
        }
    }

    /**
     * set image uri or resource id
     * resId can be drawable id, mipmap id, etc.
     * uri can be file uri, http uri, resource uri, etc.
     * it will not worry about NullPointerException.
     */
    public void setImageResource(final int resId) {
        into(requestManager
                .load(resId)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
        );
    }

    public void setImageURI(String uri) {
        setImageURI(parseURI(uri));
    }

    public void setImageURI(final Uri uri) {
        this.uri = uri;
        into(requestManager
                .load(uri)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.default_useravatar)
                .error(R.drawable.default_useravatar)
        );
    }

    public void setImageURI(String uri, String lowUri) {
        setImageURI(parseURI(uri), parseURI(lowUri));
    }

    public void setImageURI(final Uri uri, final Uri lowUri) {
        this.uri = uri;
        into(requestManager
                .load(uri)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .thumbnail(requestManager
                        .load(lowUri)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
        );
    }

    public void setImageURI(String uri, String lowUri, int width, int height) {
        setImageURI(parseURI(uri), parseURI(lowUri), width, height);
    }

    public void setImageURI(final Uri uri, final Uri lowUri, final int width, final int height) {
        this.uri = uri;
        into(requestManager
                .load(uri)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(requestManager
                        .load(lowUri)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .override(width, height)
                .centerCrop()
        );
    }

    public void setImageURI(String uri, String lowUri, int maxSize) {
        setImageURI(parseURI(uri), parseURI(lowUri), maxSize);
    }

    public void setImageURI(final Uri uri, final Uri lowUri, final int maxSize) {
        this.uri = uri;
        into(requestManager
                .load(uri)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(requestManager
                        .load(lowUri)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .override(maxSize, maxSize)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        );
    }

    private void into(final BitmapRequestBuilder builder) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                switch (shape) {
                    case RECTANGLE:
                        builder.into(SimpleImageView.this);
                        break;
                    case CIRCLE:
                        builder.into(new CircleTarget());
                        break;
                    case SQUARE:
                        builder.into(new SquareTarget());
                        break;
                }
            }
        });
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

    /**
     * save image to local
     *
     * @param l is the listener of save image.
     */
    public void saveToLocal(final SaveListener l) {
        saveToLocal(uri, l);
    }

    public static void initialize(Context context) {
        applicationContext = context.getApplicationContext();
        requestManager = Glide.with(applicationContext);
        handler = new Handler();
    }

    public static void getBitmapOnly(String uri, final LoadImage<Bitmap> l) {
        getBitmapOnly(parseURI(uri), l);
    }

    public static void getBitmapOnly(Uri uri, final LoadImage<Bitmap> l) {
        getBitmapOnly(uri, 0, 0, l);
    }

    public static void getBitmapOnly(String uri, int width, int height, final LoadImage<Bitmap> l) {
        getBitmapOnly(parseURI(uri), width, height, l);
    }

    public static void getBitmapOnly(Uri uri, Integer width, Integer height, final LoadImage<Bitmap> l) {
        Target<Bitmap> target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        l.onLoad(resource);
                    }
                });
            }
        };
        if (width != 0 && height != 0) {
            requestManager
                    .load(uri)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(width, height)
                    .into(target);
        } else {
            requestManager
                    .load(uri)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(target);
        }
    }

    public static void saveToLocal(String url, final SaveListener l) {
        saveToLocal(parseURI(url), l);
    }

    public static void saveToLocal(Uri uri, final SaveListener l) {
        getBitmapOnly(uri, new LoadImage<Bitmap>() {
            @Override
            public void onLoad(Bitmap bitmap) {
                if (bitmap == null) {
                    if (l != null)
                        l.onFailed();
                    return;
                }
                FileOutputStream fos = null;
                try {

                    // get file dir and auto create when it is not exists.
                    File dir = new File(FilePathConstant.PHOTO_SAVE_PATH);
                    if (!dir.exists())
                        dir.mkdir();
                    String fileName = System.currentTimeMillis() + ".jpg";
                    File photo = new File(dir, fileName);
                    fos = new FileOutputStream(photo);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    // input the photo to the system Photos
                    MediaStore.Images.Media.insertImage(applicationContext.getContentResolver(), photo.getAbsolutePath(), fileName, null);
                    applicationContext.sendBroadcast(new Intent(
                            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            parseURI(FilePathConstant.PHOTO_SAVE_PATH + File.separator + fileName)
                    ));
                    if (l != null)
                        l.onSucceed();
                } catch (IOException ignore) {
                    ignore.printStackTrace();
                    if (l != null)
                        l.onFailed();
                } finally {
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException ignore) {
                    }
                }
            }
        });
    }

    private static Uri parseURI(String uri) {
        if (uri == null)
            return null;
        Pattern p = Pattern.compile("((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|" +
                        "([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?",
                Pattern.CASE_INSENSITIVE);
        if (p.matcher(uri).matches())
            return Uri.parse(uri);
        return Uri.parse("file://" + uri);
    }

    private class CircleTarget extends BitmapImageViewTarget {

        CircleTarget() {
            super(SimpleImageView.this);
        }

        @Override
        protected void setResource(Bitmap resource) {
            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
            circularBitmapDrawable.setCircular(true);
            setImageDrawable(circularBitmapDrawable);
        }
    }

    private class SquareTarget extends BitmapImageViewTarget {

        SquareTarget() {
            super(SimpleImageView.this);
        }

        @Override
        protected void setResource(Bitmap resource) {
            ViewGroup.LayoutParams params = getLayoutParams();
            if (params.width > params.height)
                params.width = params.height;
            else
                params.height = params.width;
            setLayoutParams(params);
            setScaleType(ScaleType.CENTER_CROP);
            setImageBitmap(resource);
        }
    }

    public enum Shape {
        RECTANGLE,
        CIRCLE,
        SQUARE;

        public static Shape valueOf(int i) {
            if (i > 0 && i < Shape.values().length)
                return Shape.values()[i];
            return RECTANGLE;
        }

    }

    public interface LoadImage<T> {
        void onLoad(T t);
    }

    public interface SaveListener {
        void onSucceed();

        void onFailed();
    }
}
