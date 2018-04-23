package com.woodys.devicelib.location;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lisiwei on 2018/2/17.
 */

public class SetterAndGetter {
    public static final int DEFAULTINT = -1;
    public static final String DEFAULTSTRING = "";
    public static final double DEFAULTDOUBLE = -1;
    public static final long DEFAULTLONG = -1;
    public static boolean DEFAULTBoolean = false;

    protected static int getHideMethodInt(Object instance, String method){
        try {
            Class cls = instance.getClass();
            Method m = cls.getMethod(method);
            Object obj = m.invoke(instance);
            if (null != obj && obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
        }catch (Exception e){
            e.getMessage();
        }
        return DEFAULTINT;
    }

    protected static void setHideMethod(Object instance, String method, Object value)  {
        try {
            Class cls = instance.getClass();
            Method m = cls.getMethod(method);
            Object obj = m.invoke(instance, value);
        }catch (Exception e){
            e.getMessage();
        }
    }

    protected static String getHideMethodString(Object instance, String method)  {
        try {
            Class cls = instance.getClass();
            Method m = cls.getMethod(method);
            Object obj = m.invoke(instance);
            if (null != obj && obj instanceof String) {
                return (String) obj;
            }
        }catch (Exception e){
            e.getMessage();
        }
        return DEFAULTSTRING;
    }
    protected static int getFieldValueByNameInt(Object instance, String fName)  {
        try {
            Field field = instance.getClass().getField(fName);
            Object obj = field.get(instance);
            if(null != obj && obj instanceof Integer){
                return ((Integer) obj).intValue();
            }
        }catch (Exception e){
            e.getMessage();
        }
        return DEFAULTINT;
    }
    protected static double getFieldValueByNameDouble(Object instance, String fName)  {
        try {
            Field field = instance.getClass().getField(fName);
            Object obj = field.get(instance);
            if (null != obj && obj instanceof Integer) {
                return ((Double) obj).doubleValue();
            }
        }catch (Exception e){
            e.getMessage();
        }
        return DEFAULTDOUBLE;
    }
    protected static long getFieldValueByNameLong(Object instance, String fName)  {
        try {
            Field field = instance.getClass().getField(fName);
            Object obj = field.get(instance);
            if (null != obj && obj instanceof Integer) {
                return ((Long) obj).longValue();
            }
        }catch (Exception e){
            e.getMessage();
        }
        return DEFAULTLONG;
    }
    protected static boolean getFieldValueByNameBoolean(Object instance, String fName) {
        try {
            Field field = instance.getClass().getField(fName);
            Object obj = field.get(instance);
            if (null != obj && obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
        }catch (Exception e){
            e.getMessage();
        }
        return DEFAULTBoolean;
    }
    protected static String getFieldValueByNameString(Object instance, String fName)  {
        try {
            Field field = instance.getClass().getField(fName);
            Object obj = field.get(instance);
            if (null != obj && obj instanceof String) {
                return ((String) obj);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return DEFAULTSTRING;
    }
}
