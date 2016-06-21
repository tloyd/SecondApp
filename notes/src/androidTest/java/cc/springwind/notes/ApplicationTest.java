package cc.springwind.notes;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public  void test(){
        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");
        String result=df.format(new Date());
        System.out.println("-->>"+result);
    }
}