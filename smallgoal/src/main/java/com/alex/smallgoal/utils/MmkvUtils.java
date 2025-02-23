package com.alex.smallgoal.utils;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.mmkv.MMKV;

public class MmkvUtils {

    public static MmkvUtils getInstance() {
        return Instance.instance;
    }

    private static class Instance {
        public static MmkvUtils instance = new MmkvUtils();
    }

    public static void initialize(Context context) {
        MMKV.initialize(context);
    }

    private MmkvUtils() {
        // 迁移旧的数据
        migrateData();
    }

    private void migrateData() {
        // 针对spUtils的数据迁移可以删除了
    }

    private static MMKV getMMKV() {
        return MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null);
    }

    public boolean put(String key, Object o) {
        MMKV mmkv = getMMKV();
        boolean result = false;
        if (o instanceof Integer) {
            result = mmkv.encode(key, (Integer) o);
        } else if (o instanceof Long) {
            result = mmkv.encode(key, (Long) o);
        } else if (o instanceof Float) {
            result = mmkv.encode(key, (Float) o);
        } else if (o instanceof Double) {
            result = mmkv.encode(key, (Double) o);
        } else if (o instanceof Boolean) {
            result = mmkv.encode(key, (Boolean) o);
        } else if (o instanceof String) {
            result = mmkv.encode(key, (String) o);
        } else if (o instanceof byte[]) {
            result = mmkv.encode(key, (byte[]) o);
        }  else if (o instanceof Parcelable) {
            result = mmkv.encode(key, (Parcelable) o);
        }
        return result;
    }

    public <T> T getValue(String key, T o) {

        MMKV mmkv = getMMKV();
        Object result = null;
        if (o instanceof Integer) {
            result = mmkv.decodeInt(key, (Integer) o);
        } else if (o instanceof Long) {
            result = mmkv.decodeLong(key, (Long) o);
        } else if (o instanceof Float) {
            result = mmkv.decodeFloat(key, (Float) o);
        } else if (o instanceof Double) {
            result = mmkv.decodeDouble(key, (Double) o);
        } else if (o instanceof Boolean) {
            result = mmkv.decodeBool(key, (Boolean) o);
        } else if (o instanceof String) {
            result = mmkv.decodeString(key, (String) o);
        } else if (o instanceof byte[]) {
            result = mmkv.decodeBytes(key, (byte[]) o);
        }
        return (T) result;
    }

    public int getInt(String key) {
        return getMMKV().decodeInt(key, 0);
    }

    public int getInt(String key, int value) {
        return getMMKV().decodeInt(key, value);
    }


    public long getLong(String key) {
        return getMMKV().decodeLong(key, 0L);
    }

    public long getLong(String key, long value) {
        return getMMKV().decodeLong(key, value);
    }

    public void clear() {
        MMKV mmkv = getMMKV();
        mmkv.clearAll();
    }

    public void remove(String key) {
        MMKV mmkv = getMMKV();
        if (mmkv.containsKey(key)) {
            mmkv.remove(key);
        }
    }

    public boolean containsKey(String key) {
        if (TextUtils.isEmpty(key)) {
            // 为空的时候不包含
            return false;
        }
        MMKV mmkv = getMMKV();
        return mmkv.containsKey(key);
    }

    public String getString(String key) {
        return getMMKV().decodeString(key, "");
    }

    public String getString(String key, String value) {
        return getMMKV().decodeString(key, value);
    }

    public boolean getBoolean(String key) {
        return getMMKV().decodeBool(key);
    }

    public boolean getBoolean(String key, boolean flag) {
        return getMMKV().decodeBool(key, flag);
    }

    public <T extends Parcelable> T getParcelable(String key, Class<T> tClass, T defaultValue) {
        return getMMKV().decodeParcelable(key, tClass, defaultValue);
    }

}
