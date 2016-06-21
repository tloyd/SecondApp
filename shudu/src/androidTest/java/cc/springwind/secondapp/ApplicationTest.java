package cc.springwind.secondapp;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test(){
        Game game=new Game();
        /*for (int i = 0; i < game.numbers.length; i++) {
            System.out.println("---->>"+game.numbers[i]);
        }*/
        /*int [] n=game.calcTileUsedNum(2,1);
        for (int m : n){
            System.out.println("-->>"+m);
        }*/
        /*for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int n=game.usedNum[i][j].length;
                for (int k = 0; k < n; k++) {
                    System.out.println("-->>usedNum["+i+"]["+j+"]:"+game.usedNum[i][j][k]);
                }
            }
        }*/
        int n []=game.getUsedNumByCoor(3,5);
        for (int x:n){
            System.out.println("-->>x:"+x);
        }
    }
}