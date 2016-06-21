package cc.springwind.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cc.springwind.beans.ArticleBean;

/**
 * Created by HeFan on 2016/5/27.
 */
public class JsonTool {

    /**
     * 从json对象集合表达式中得到一个java对象列表
     *
     * @param jsonString
     * @param beanClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToBeanList(String jsonString, Class<T> beanClass) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        T bean;
        int size = jsonArray.size();
        List<T> list = new ArrayList<T>(size);

        for (int i = 0; i < size; i++) {

            jsonObject = jsonArray.getJSONObject(i);
            bean= (T) JSONObject.toBean(jsonObject,beanClass);
            list.add(bean);

        }

        return list;

    }

    public static List<ArticleBean> jsonToArticleList(String jsonString) {
        System.out.println("-->>jsonstring:"+jsonString);
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        int size = jsonArray.size();
        if (size<=0){
            return null;
        }
        List<ArticleBean> list = new ArrayList<ArticleBean>(size);
        for (int i = 0; i < size; i++) {
            jsonObject = jsonArray.getJSONObject(i);
            String id=jsonObject.optString("id");
            String title=jsonObject.optString("title");
            String desc=jsonObject.optString("desc");
            String time=jsonObject.optString("time");
            String content_url=jsonObject.optString("content_url");
            String pic_url=jsonObject.optString("pic_url");
            ArticleBean mbean=new ArticleBean(id,title,desc,time,content_url,pic_url);
            list.add(mbean);
        }
        return list;
    }

    public static <T> List<T> toList(Object object, Class<T> objectClass)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return JSONArray.toList(jsonArray, objectClass);
    }

    public static List<ArticleBean> toWhat(String jsonString){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        ArticleBean bean;
        int size = jsonArray.size();
        List<ArticleBean> list = new ArrayList<ArticleBean>(size);

        for (int i = 0; i < size; i++) {

            jsonObject = jsonArray.getJSONObject(i);
            bean = (ArticleBean) JSONObject.toBean(jsonObject, ArticleBean.class);
            list.add(bean);

        }

        return list;
    }
}
