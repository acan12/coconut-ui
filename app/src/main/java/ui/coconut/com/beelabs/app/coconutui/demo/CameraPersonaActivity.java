//package ui.coconut.com.beelabs.app.coconutui.demo;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.hardware.Camera;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import java.io.File;
//
//import app.beelabs.com.codebase.base.BaseActivity;
//import app.coconut.ui.com.beelabs.ui.camera.CocoCameraPersona;
//import app.coconut.ui.com.beelabs.ui.camera.CocoPhotoHandler;
//import butterknife.ButterKnife;
//import pl.tajchert.nammu.Nammu;
//import pl.tajchert.nammu.PermissionCallback;
//import ui.coconut.com.beelabs.app.coconutui.IConfig;
//import ui.coconut.com.beelabs.app.coconutui.R;
//
//public class CameraPersonaActivity extends BaseActivity {
//
//    private static int cameraFacing;
//    private Camera camera;
//    private CocoCameraPersona cameraPreview;
//    private FrameLayout previewContainer;
//    private ImageView captureButton;
//    private boolean permissionsGranted = false;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera_persona);
//        ButterKnife.bind(this);
//
//        Nammu.init(this);
//
//        cameraFacing = getIntent().getIntExtra(IConfig.KEY_CAMERA_FACING, Camera.CameraInfo.CAMERA_FACING_FRONT);
//
//        try {
//
//            initializeCamera();
//        } catch (Exception e) {
//            Log.e("PHOTO ERROR:", e.getMessage());
//        }
//
//    }
//
//    protected void onResume() {
//        super.onResume();
//        reloadCamera();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (camera != null) {
//            camera.stopPreview();
//            camera.release();
//            camera = null;
//        }
//        if (cameraPreview != null) {
//            previewContainer.removeView(cameraPreview);
//            cameraPreview = null;
//        }
//    }
//
//    /**
//     * Checking device has camera hardware or not
//     */
//    private boolean isDeviceSupportCamera() {
//        return getApplicationContext().getPackageManager().hasSystemFeature(
//                PackageManager.FEATURE_CAMERA);
//    }
//
//    /**
//     * Capturing Camera Image will lauch camera app requrest image capture
//     */
//    private void captureImage() throws Exception {
//
//
//        String rootPath = Environment.getExternalStorageDirectory() + File.separator
//                + getResources().getString(R.string.app_name);
//        String photoDir = "photos";
//        String pictureDirPath = rootPath + File.separator + photoDir;
//        // Create Directory App
//        final File pictureDir = new File(pictureDirPath);
//        pictureDir.mkdirs();
//        if (!pictureDir.exists() && !pictureDir.mkdirs()) {
//            Log.d("", "Can't create directory to save image.");
//            return;
//        }
//        final Activity self = this;
//
//        boolean hasAutoFocus = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS);
//
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
//            hasAutoFocus = false;
//        }
//
//        if (hasAutoFocus) {
//
//            camera.autoFocus(new Camera.AutoFocusCallback() {
//
//                @Override
//                public void onAutoFocus(boolean b, Camera camera) {
//                    camera.takePicture(null, null, new CocoPhotoHandler(self, pictureDir, 10, cameraFacing));
//                    camera.setPreviewCallback(null);
//                }
//            });
//        } else {
//            camera.takePicture(null, null, new CocoPhotoHandler(self, pictureDir, 12, cameraFacing));
//            camera.setPreviewCallback(null);
//        }
//    }
//
//    private void initializeCamera() throws Exception {
//        Nammu.askForPermission(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionCallback() {
//            @Override
//            public void permissionGranted() {
//                permissionsGranted = true;
//                // Create an instance of Camera
//                camera = getCameraInstance(getPackageManager());
//                // Checking camera availability
//                if (!isDeviceSupportCamera() || camera == null)
//                    finish();
//                // Create our Preview view and set it as the content of our activity.
//                boolean hasFrontCamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
//                cameraPreview = new CocoCameraPersona(CameraPersonaActivity.this, camera, true);
//                previewContainer = (FrameLayout) findViewById(R.id.camera_preview);
//                previewContainer.addView(cameraPreview);
//                previewContainer.setBackgroundColor(Color.parseColor("#80005959"));
//
//                DisplayMetrics metrics = new DisplayMetrics();
//                getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//                cameraViewPort.setMarginTop(metrics.heightPixels / 2);
//                captureButton = (ImageView) findViewById(R.id.capture_button);
//                captureButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        captureButton.setClickable(false);
//                        // capture picture,
//                        try {
//                            captureImage();
//                        } catch (Exception e) {
//                            new Exception("Photo failed!");
//                        }
//                    }
//                });
//
//                switchCameraButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int targetFacing = (cameraFacing == Camera.CameraInfo.CAMERA_FACING_FRONT) ? Camera.CameraInfo.CAMERA_FACING_BACK : Camera.CameraInfo.CAMERA_FACING_FRONT;
//                        Intent intent = new Intent(CameraPersonaActivity.this, CameraPersonaActivity.class);
//                        intent.putExtra(IConfig.KEY_CAMERA_FACING, targetFacing);
//                        startActivityForResult(intent, 12);
//                        finish();
//                    }
//                });
//
//            }
//
//            @Override
//            public void permissionRefused() {
//                Toast.makeText(CameraPersonaActivity.this, "Not allowed to use camera", Toast.LENGTH_SHORT).show();
////                Notificator.INSTANCE.showError(getResources().getString(R.string.error_grant_permissions), CameraKtpActivity.this);
//            }
//        });
//    }
//
//
//    private void reloadCamera() {
//        if (!permissionsGranted)
//            return;
//        if (camera == null) {
//            camera = getCameraInstance(getPackageManager());
//        }
//        if (camera != null && cameraPreview == null) {
//            boolean hasFrontCamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
//            cameraPreview = new CocoCameraPersona(this, camera, hasFrontCamera);
//            previewContainer.addView(cameraPreview);
//        }
//    }
//
//    /**
//     * A safe way to get an instance of the Camera object.
//     */
//    private static Camera getCameraInstance(PackageManager pm) {
//        Camera c = null;
//        try {
//            c = Camera.open(cameraFacing);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Camera is not available (in use or does not exist)
//        }
//        return c; // returns null if camera is unavailable
//    }
//
//    // Android 6+ permissions management
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//
//    @OnClick(R.id.back_button)
//    public void onBack(View view) {
//        super.onBackPressed();
//    }
//}
