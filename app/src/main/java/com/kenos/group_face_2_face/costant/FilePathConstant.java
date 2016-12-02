package com.kenos.group_face_2_face.costant;



import android.os.Environment;


import java.io.File;

public class FilePathConstant {

    public static final String BASE_PATH = Environment.getExternalStorageDirectory() + File.separator + "Demo";
    // 照片保存路径
    public static final String PHOTO_SAVE_PATH = BASE_PATH + File.separator + "photo";

}
