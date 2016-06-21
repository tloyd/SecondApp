package cc.springwind.superflashlight;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by HeFan on 2016/5/31.
 */
public class FlashlightActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        ViewGroup.LayoutParams lp_flashlight_controller = ivFlashlightController.getLayoutParams();
        lp_flashlight_controller.height = point.y * 3 / 4;
        lp_flashlight_controller.width = point.x / 3;
        ivFlashlightController.setLayoutParams(lp_flashlight_controller);

        ivFlashlight.setTag(false);
    }

    @butterknife.OnClick(R.id.iv_flashlight_controller)
    public void onClick() {

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(this, "没有闪光灯呵呵", Toast.LENGTH_SHORT).show();
        }

        if ((Boolean) ivFlashlight.getTag()) {
            closeFlashlight();
        } else {
            openFlashlight();
        }
    }

    protected void openFlashlight() {
        TransitionDrawable drawable = (TransitionDrawable) ivFlashlight.getDrawable();
        drawable.startTransition(200);
        ivFlashlight.setTag(true);

        try {
            camera = Camera.open();
            camera.setPreviewTexture(new SurfaceTexture(1));
            camera.startPreview();

            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

            camera.setParameters(parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void closeFlashlight() {
        TransitionDrawable drawable = (TransitionDrawable) ivFlashlight.getDrawable();
        drawable.reverseTransition(200);
        ivFlashlight.setTag(false);

        if (camera != null) {
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

            camera.setParameters(parameters);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeFlashlight();
    }
}
