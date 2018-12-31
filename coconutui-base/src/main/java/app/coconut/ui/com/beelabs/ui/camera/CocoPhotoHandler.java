package app.coconut.ui.com.beelabs.ui.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.coconut.ui.com.beelabs.IConfig;


public class CocoPhotoHandler implements Camera.PictureCallback {

    private final Activity activity;
    private final int cameraType;
    private final int cameraFacing;
    private File pictureDir;

    public CocoPhotoHandler(Activity activity, File pictureDir, int cameraType) {
        this.activity = activity;
        this.pictureDir = pictureDir;
        this.cameraType = cameraType;
        this.cameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;
    }

    public CocoPhotoHandler(Activity activity, File pictureDir, int cameraType, int cameraFacing) {
        this.activity = activity;
        this.pictureDir = pictureDir;
        this.cameraType = cameraType;
        this.cameraFacing = cameraFacing;
    }


    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);

        Bitmap bitmap = null;
        if (cameraType == IConfig.CAMERA_PERSONA_TYPE) {
            PhotoResolution photo = calculateResolutionPhotoCrop(bmp);
            bitmap = Bitmap.createBitmap(photo.getBmp(), photo.getLeft(), photo.getTop(), photo.getWidth(), photo.getHeight(), photo.getMtx(), true);

        } else if (cameraType == IConfig.CAMERA_IDCARD_TYPE) {
            PhotoResolution photo = calculateResolutionPhotoFull(bmp, cameraFacing);
            bitmap = Bitmap.createBitmap(photo.getBmp(), 0, 0, photo.getWidth(), photo.getHeight(), photo.getMtx(), true);
        }


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bmdata = bos.toByteArray();

        File picturePath = getPicturePath(pictureDir);

        try {
            FileOutputStream fos = new FileOutputStream(picturePath);
            fos.write(bmdata);
            fos.close();

            Intent cameraIntent = new Intent("com.android.camera.action.CROP");
            cameraIntent.setClassName("com.android.camera", "com.android.camera.CropImage");

            cameraIntent.setData(Uri.fromFile(picturePath));
            cameraIntent.putExtra("outputX", 200);
            cameraIntent.putExtra("outputY", 200);
            cameraIntent.putExtra("aspectX", 1);
            cameraIntent.putExtra("aspectY", 1);
            cameraIntent.putExtra("scale", true);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(picturePath));
            cameraIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

            activity.setResult(Activity.RESULT_OK, cameraIntent);

            activity.finish();

        } catch (Exception error) {
            Log.d("", "File" + pictureDir + "not saved: "
                    + error.getMessage());
        }
    }

    private PhotoResolution calculateResolutionPhotoFull(Bitmap bitmap, int cameraFacing) {

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();

        if (cameraFacing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            mtx.setScale(-1, 1);
            mtx.postRotate(90);
        } else
            mtx.postRotate(90);

        PhotoResolution photo = new PhotoResolution();
        photo.setBmp(bitmap);
        photo.setMtx(mtx);
        photo.setWidth(w);
        photo.setHeight(h);
        photo.setTop(0);
        photo.setLeft(0);

        return photo;
    }

    private PhotoResolution calculateResolutionPhotoCrop(Bitmap bitmap) {

        int x = bitmap.getWidth();
        int y = bitmap.getHeight();


        Matrix mtx = new Matrix();
        mtx.postRotate(90);
        int left = x / 4;//(w - 320)>0? 320:50;
        int top = 0;//(h - 320)>0? 320:50 ;

        int w = x / 2;
        int h = y;

        int w2 = (w > 0) ? w - 50 : x;
        int h2 = (h > 0) ? h - 50 : y;

        PhotoResolution photo = new PhotoResolution();
        photo.setBmp(bitmap);
        photo.setMtx(mtx);
        photo.setWidth(w2);
        photo.setHeight(h2);
        photo.setLeft(left);
        photo.setTop(top);

        return photo;
    }


    private File getPicturePath(File photoFolder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String filename = photoFolder.getPath() + File.separator + photoFile;

        return new File(filename);
    }


    public class PhotoResolution {
        private Bitmap bmp;
        private Matrix mtx;
        private int width;
        private int height;
        private int left;
        private int top;

        public Bitmap getBmp() {
            return bmp;
        }

        public void setBmp(Bitmap bmp) {
            this.bmp = bmp;
        }

        public Matrix getMtx() {
            return mtx;
        }

        public void setMtx(Matrix mtx) {
            this.mtx = mtx;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }
    }
}