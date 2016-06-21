package cc.springwind.a2dturnover;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @butterknife.InjectView(R.id.girl1)
    ImageView girl1;
    @butterknife.InjectView(R.id.girl2)
    ImageView girl2;

    private ScaleAnimation animation1 = new ScaleAnimation(1, 0, 1, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
    private ScaleAnimation animation2 = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.inject(this);
        initView();
        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (girl1.getVisibility() == View.VISIBLE) {
                    girl1.startAnimation(animation1);
                } else if (girl2.getVisibility() == View.VISIBLE) {
                    girl2.startAnimation(animation1);
                }
            }
        });
    }

    private void initView() {

        show1();
        animation1.setDuration(700);
        animation2.setDuration(700);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (girl1.getVisibility() == View.VISIBLE) {
                    show2();
                    girl2.startAnimation(animation2);
                } else if (girl2.getVisibility() == View.VISIBLE) {
                    show1();
                    girl1.startAnimation(animation2);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void show1() {
        girl1.setVisibility(View.VISIBLE);
        girl2.setVisibility(View.INVISIBLE);
    }

    private void show2() {
        girl2.setVisibility(View.VISIBLE);
        girl1.setVisibility(View.INVISIBLE);
    }


}
