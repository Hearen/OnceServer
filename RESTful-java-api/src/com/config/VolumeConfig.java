package com.config;

import java.util.HashMap;
import java.util.Map;

/*******************************************
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2016-01-08 14:41
Description : Used to store basic profile to create a volume within a pool;
Additional  : These attributes with set* methods are optional;
*******************************************/
public class VolumeConfig {
    private String uuidString;
    private String volName;
    private String poolName;
    private int size;

    public VolumeConfig(String poolName, int size)
    {
        this.poolName = poolName;
        this.size = size;
    }

    public void setUuid(String uuidString)
    {
        this.uuidString = uuidString;
    }

    public void setVolName(String volName)
    {
        this.volName = volName;
    }

    public Map<String, String> toMap()
    {
        Map<String, String> data = new HashMap<String, String>();
        data.put("_id", uuidString);
        data.put("poolName", poolName);
        data.put("volName", volName);
        data.put("volSize", ""+size);
        return data;
    }
}
