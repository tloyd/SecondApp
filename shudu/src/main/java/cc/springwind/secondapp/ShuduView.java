package cc.springwind.secondapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by HeFan on 2016/5/23.
 */
public class ShuduView extends View {
    public ShuduView(Context context) {
        super(context);
        game = new Game();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(ContextCompat.getColor(getContext(), R.color.shudu_background));
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);

        /*Paint testPaint=new Paint();
        testPaint.setColor(Color.BLACK);
        canvas.drawText("aa",100,100,testPaint);*/

        Paint lightPaint = new Paint();
        lightPaint.setColor(ContextCompat.getColor(getContext(), R.color.shudu_light));
        Paint hilitePaint = new Paint();
        hilitePaint.setColor(ContextCompat.getColor(getContext(), R.color.shudu_hilite));
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, height * i, getWidth(), height * i, lightPaint);//横线
            canvas.drawLine(0, height * i + 1, getWidth(), height * i + 1, hilitePaint);

            canvas.drawLine(width * i, 0, width * i, getHeight(), lightPaint);//竖线
            canvas.drawLine(width * i + 1, 0, width * i + 1, getHeight(), hilitePaint);
        }

        Paint darkPaint = new Paint();
        darkPaint.setColor(ContextCompat.getColor(getContext(), R.color.shudu_dark));

        for (int i = 0; i < 9; i++) {
            if (i % 3 != 0) {
                continue;
            }
            canvas.drawLine(0, height * i, getWidth(), height * i, darkPaint);
            canvas.drawLine(width * i, 0, width * i, getHeight(), darkPaint);

        }

        Paint blackPaint = new Paint();

        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextAlign(Paint.Align.CENTER);
        blackPaint.setTextSize(height * 0.75f);
        blackPaint.setStyle(Paint.Style.STROKE);

        Paint.FontMetrics fm = blackPaint.getFontMetrics();

        float h = width / 2;
        float v = height / 2 - (fm.descent + fm.ascent) / 2;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                canvas.drawText(game.getTileString(i, j), h + i * width, v + j * height, blackPaint);
            }
        }
    }

    private int width;
    private int height;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = w / 9;
        this.height = h / 9;
    }

    private int coorX;
    private int coorY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(event);
        }
        float rawX = event.getX();
        float rawY = event.getY();
        coorX = (int) (rawX / width);
        coorY = (int) (rawY / height);
        int n[] = game.getUsedNumByCoor(coorX, coorY);
        TouchDialog touchDialog = new TouchDialog(getContext(), n, this);
        touchDialog.show();
        return true;
    }

    private Game game ;

    public void setSelectTile(int n) {

        if (game.setTileIfValid(coorX, coorY, n)) {
            invalidate();
        }
    }
}
