package cc.springwind.secondapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by HeFan on 2016/5/24.
 */
public class TouchDialog extends Dialog {
    private ShuduView view;
    public TouchDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("选择数字");
        setContentView(R.layout.dialog_view);
        findKeysById();
        for (int i : usedNum) {
            keys[i-1].setVisibility(View.INVISIBLE);
        }
        setListener();
    }

    private int[] usedNum;

    public TouchDialog(Context context, int usedNum[],ShuduView view) {
        super(context);
        this.usedNum = usedNum;
        this.view=view;
    }

    private View[] keys = new View[9];

    private void findKeysById() {
        keys[0] = (Button) findViewById(R.id.bt1);
        keys[1] = (Button) findViewById(R.id.bt2);
        keys[2] = (Button) findViewById(R.id.bt3);
        keys[3] = (Button) findViewById(R.id.bt4);
        keys[4] = (Button) findViewById(R.id.bt5);
        keys[5] = (Button) findViewById(R.id.bt6);
        keys[6] = (Button) findViewById(R.id.bt7);
        keys[7] = (Button) findViewById(R.id.bt8);
        keys[8] = (Button) findViewById(R.id.bt9);
    }

    private void setListener(){
        for (int i = 0; i < 9; i++) {
            final int j=i+1;
            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnResult(j);
                }
            });
        }
    }

    private void returnResult(int j) {
        view.setSelectTile(j);
        dismiss();
    }
}
