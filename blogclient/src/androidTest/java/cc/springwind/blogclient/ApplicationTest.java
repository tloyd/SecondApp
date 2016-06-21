package cc.springwind.blogclient;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import cc.springwind.beans.BeanTest;
import cc.springwind.utils.JsonTool;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void jsonTest() {

    }

    public void  testWhat(){

        System.out.println("-->>aa");
//        List<ArticleBean> list=JsonTool.jsonToBeanList("[{\"id\":\"1\",\"title\":\"百度地图API使用入门\",\"desc\":\"百度地图 Android SDK是一套基于Android 2.1及以上版本设备的应用程序接口...\",\"time\":\"2016-05-26 12:42:54.0\",\"content_url\":\"http://www.springwind.cc/2016/05/23/BAIDUMAP-API-GUIDE/\",\"pic_url\":\"http://ww2.sinaimg.cn/mw690/005JzrjDgw1f46zkg5suej30k00zktas.jpg\"},{\"id\":\"2\",\"title\":\"Genymotion使用遇到问题与解决方法\",\"desc\":\"记录一下使用Genymotion模拟器遇到的问题与解决方法,Genymotion确实是一个非常好的模拟器,快,轻巧,就是错误多,错误提示差,而且几乎无法得到官方的支持...\",\"time\":\"2016-05-26 12:43:02.0\",\"content_url\":\"http://www.springwind.cc/2016/05/18/GENYMOTION-ERROR/\",\"pic_url\":\"http://ww3.sinaimg.cn/mw690/005JzrjDgw1f41pvs4j7gj30dy0ajaby.jpg\"}]", ArticleBean.class);
        List<BeanTest> beanTests=JsonTool.jsonToBeanList("[{\"id\":\"1001\",\"title\":\"hello_world\"}]",BeanTest.class);
        System.out.println("-->>bb");

        BeanTest bean = beanTests.get(0);
        System.out.println("-->>cc");
        System.out.println("-->>"+bean.getId());
    }
}