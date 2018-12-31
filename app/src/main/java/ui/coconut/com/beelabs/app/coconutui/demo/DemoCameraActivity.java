package ui.coconut.com.beelabs.app.coconutui.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import app.beelabs.com.codebase.base.BaseActivity;
import app.coconut.ui.com.beelabs.ui.camera.CameraUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ui.coconut.com.beelabs.app.coconutui.R;

public class DemoCameraActivity extends BaseActivity {
    @BindView(R.id.photo_view)
    ImageView photoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_camera);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.photo_button)
    public void onUploadPhoto(View view){
        Intent intent = new Intent(this, CameraPersonaActivity.class);
        startActivityForResult(intent, 10);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;

        Bitmap photo = null;
        switch (requestCode) {
            case 10:

                photo = CameraUtil.getPhotoCameraResult(data, "BASE64_CAMERA_PERSONA_KEY", this);

                photoView.setImageBitmap(photo);
                break;
        }
    }

}
