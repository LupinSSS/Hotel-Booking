package com.example.hotel_booking.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Result {
    private static String SUCCESS ="success";
    private static String DATA="data";
    private static String MESSAGE="message";
    private static Map<String,String> messageMap = new HashMap();
    private Result() {
    }

    static {
        messageMap.put("0", "");
        messageMap.put("400", "HTTP 错误 400.0 - 访问被拒绝：错误的请求");
        messageMap.put("401", "HTTP 错误 401.1 - 未经授权：访问由于凭据无效被拒绝");
        messageMap.put("405", "用来访问本页面的 HTTP 谓词不被允许（方法不被允许）");
        messageMap.put("406", "客户端浏览器不接受所请求页面的 MIME 类型");
        messageMap.put("500", "HTTP 错误 500.0 - 服务器出错：内部服务器出错");
        messageMap.put("1005", "[服务器]运行时异常");
        messageMap.put("1006", "[服务器]空值异常");
        messageMap.put("1007", "[服务器]数据类型转换异常");
        messageMap.put("1008", "[服务器]IO异常");
        messageMap.put("1009", "[服务器]未知方法异常");
        messageMap.put("1010", "[服务器]数组越界异常");
        messageMap.put("1011", "[服务器]网络异常");
    }
    private static ThreadLocal<JSONResultMap> threadLocal =new ThreadLocal<>();


    private static JSONResultMap getJSONResultMap(){
        JSONResultMap jsonResultMap = threadLocal.get();
        if(jsonResultMap==null){
            jsonResultMap = new JSONResultMap();
            threadLocal.set(jsonResultMap);
        }
        return jsonResultMap;
    }
    /*  成功的方法  */

    public static  JSONResultMap success(){
        return getJSONResultMap().put(SUCCESS,true).put("code",200);
    }
    public static  JSONResultMap success(Object data){
        return success().put(DATA,data);
    }
    public static  JSONResultMap success(Object data, String message){
        return success().put(DATA,data).put(MESSAGE,message);
    }
    public static  JSONResultMap success(Object data, String message, int statusCode){
        return success(data).put(MESSAGE,message).put("code",statusCode);
    }
    public static  JSONResultMap success(String message, int statusCode){
        return success(message).put("code",statusCode);
    }
    public static  JSONResultMap success(int statusCode){
        return getJSONResultMap().put(SUCCESS,true).put("code",statusCode);
    }
    public static JSONResultMap success(String message){
        return success().put(MESSAGE,message);
    }

    /* 服务器发生错误,异常对应的方法  */

    public static JSONResultMap error(int statusCode) {
        return getJSONResultMap().put(SUCCESS,false).put("code",statusCode).put(MESSAGE,messageMap.get(String.valueOf(statusCode)));
    }
    public static JSONResultMap error() {
        return error(500);
    }
    public static JSONResultMap error(String message) {
        return error(500).put(MESSAGE,message);
    }
    public static JSONResultMap error(String message,int statusCode) {
        return error(statusCode).put(MESSAGE,message);
    }

    /* 操作失败的对应方法  */
    public static  JSONResultMap fail(){
        return getJSONResultMap().put(SUCCESS,false).put("code",200);
    }
    /*
    public static  JSONResultMap fail(Object data){
        return fail().put(DATA,data);
    }
    public static  JSONResultMap fail(Object data,String message){
        return fail().put(DATA,data).put(MESSAGE,message);
    }
    public static  JSONResultMap fail(Object data,String message,int statusCode){
        return fail(data).put(MESSAGE,message).put("code",statusCode);
    }*/
    public static  JSONResultMap fail(String message,int statusCode){
        return fail(message).put("code",statusCode);
    }
    public static  JSONResultMap fail(int statusCode){
        return getJSONResultMap().put(SUCCESS,false).put("code",statusCode).put(MESSAGE,messageMap.get(String.valueOf(statusCode)));
    }
    public static JSONResultMap fail(String message){
        return fail().put(MESSAGE,message);
    }





    public static class JSONResultMap implements Map<String, Object> {
        private Map<String,Object> localMap =  new HashMap<>();
        private Map<String, Object> getLocalMap() {
            return localMap;
        }

        private JSONResultMap() {
        }

        @Override
        public int size() {
            return getLocalMap().size();
        }

        @Override
        public boolean isEmpty() {
            return getLocalMap().isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return getLocalMap().containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return getLocalMap().containsValue(value);
        }

        @Override
        public Object get(Object key) {
            return getLocalMap().get(key);
        }

        @Override
        public JSONResultMap put(String key, Object value) {
            getLocalMap().put(key, value);
            return this;
        }

        @Override
        public Object remove(Object key) {
            return getLocalMap().remove(key);
        }

        @Override
        public void putAll(Map<? extends String, ?> m) {
            getLocalMap().putAll(m);
        }


        @Override
        public void clear() {
            getLocalMap().clear();
        }

        @Override
        public Set<String> keySet() {
            return getLocalMap().keySet();
        }

        @Override
        public Collection<Object> values() {
            return getLocalMap().values();
        }

        @Override
        public Set<Entry<String, Object>> entrySet() {
            return getLocalMap().entrySet();
        }


    }


}

