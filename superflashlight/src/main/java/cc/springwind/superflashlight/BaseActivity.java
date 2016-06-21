package cc.springwind.superflashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HeFan on 2016/5/31.
 */
public class BaseActivity extends Activity {

    @InjectView(R.id.iv_flashlight_controller)
    ImageView ivFlashlightController;
    @InjectView(R.id.iv_flashlight)
    ImageView ivFlashlight;

    protected Camera camera;
    protected Camera.Parameters parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

}
