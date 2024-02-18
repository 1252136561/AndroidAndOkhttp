package com.hbw.data;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbw.entity.Result;
import com.hbw.http.HttpUrl;
import com.hbw.server.OKHttpUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 增删改查
 * 增：post请求
 * 删：delete请求
 * 改：put请求
 * 查：get请求
 */
public class DatabaseUtil {
    final private static String baseUrl= HttpUrl.getBaseUrl();
    //final private static Gson gson= JsonBean.getGson();
    final private static Gson gson=new GsonBuilder().serializeNulls().create();
    private static String jsonForm=null;

    private static String jsonResult=null;
    private static Result result=null;
    /**
     * 通过异步post请求
     * 数据库插入操作----增        --例子
     * @param bean bean对象       user
     * @param args 请求地址参数   {"user","insert"}
     * @return
     */
    public static Result insert(Object bean,String... args){
        try{
            jsonForm=gson.toJson(bean);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        jsonResult= OKHttpUtil.postAsyncRequest(baseUrl,jsonForm,args);
        if(jsonResult==null){//请求失败
            result.setInfo("发送请求错误！",null);
        }else{//请求成功
            result=gson.fromJson(jsonResult,Result.class);
            if(result.getCode()==200){//插入失败

            }else if(result.getCode()==600){//插入成功

            }
        }
        return result;
    }

    /**
     * 通过异步delete请求
     * 数据库插入操作----通过 id 删除
     * @param args 请求地址参数 args[]={"user","delete",id}
     * @return
     */
    public static Result deleteById(String... args){
        jsonForm= "";
        jsonResult= OKHttpUtil.deleteAsyncRequest(baseUrl,jsonForm,args);
        if(jsonResult==null){//请求失败
            result.setInfo("发送请求错误！",null);;
        }else{//请求成功
            result=gson.fromJson(jsonResult,Result.class);
            if(result.getCode()==200){//删除失败

            }else if(result.getCode()==400){//删除成功

            }
        }
        return result;
    }
    /**
     * 通过异步put请求
     * 数据库更新操作----通过 id 改    ---例子
     * @param bean                     user
     * @param args                    {"user","update"}
     * @return
     */
    public static Result updateById(Object bean,String... args){
        try{
            jsonForm=gson.toJson(bean);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        jsonResult= OKHttpUtil.postAsyncRequest(baseUrl,jsonForm,args);
        if(jsonResult==null){//请求失败
            result.setInfo("发送请求错误！",null);;
        }else{//请求成功
            result=gson.fromJson(jsonResult,Result.class);
            if(result.getCode()==200){//插入失败

            }else if(result.getCode()==400){//插入成功

            }
        }
        return result;
    }

    /**
     * 数据库操作--查询，查询单个对象
     * @param args 请求地址参数 {"user","line",id}
     * @return
     */
    public static Result selectLineById(Object bean,String... args){
        try{
            jsonForm=gson.toJson(bean);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        jsonResult= OKHttpUtil.postAsyncRequest(baseUrl,jsonForm,args);
        if(jsonResult==null){//请求失败
            result.setMsg("发送请求错误！");
        }else{//请求成功
            result=gson.fromJson(jsonResult,Result.class);
            if(result.getCode()==200){//查询失败

            }else if(result.getCode()==400){//查询成功

            }
        }
        return result;
    }

    /**
     * 数据库操作--查询，查询对象集合
     * @param args 请求地址参数 {"user","list"}
     * @return
     */
    public static Result selectList(Object bean,String... args){
        try{
            jsonForm=gson.toJson(bean);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        jsonResult= OKHttpUtil.postAsyncRequest(baseUrl,jsonForm,args);
        if(jsonResult==null){//请求失败
            result.setMsg("发送请求错误！");
        }else{//请求成功
            result=gson.fromJson(jsonResult,Result.class);
            if(result.getCode()==200){//查询失败

            }else if(result.getCode()==400){//查询成功

            }
        }
        return result;
    }
    /**
     * 通过Result获取bean对象
     * @param result
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getEntity(Result result,Class<?> cls){
        T entity=null;
        try {
            entity= (T) gson.fromJson(result.getResult(),cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 通过Result获取对象集合
     * @param result
     * @return
     */
    public static <T> List<T> getList(Result result,Class<T> clazz){
        List list=new ArrayList<>();
        try{
            Type listType = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, ArrayList.class, clazz);
            list= (List) gson.fromJson(result.getResult(), listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}

