package com.config;

import java.util.HashMap;
import java.util.Map;

/*******************************************
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2016-01-08 14:32
Description : Used to store profile of storage pool;
Additional  : These with set* methods are optional
*******************************************/
public class StoragePoolConfig {
    private String uuidString;
    private String name;
    private String target;

    public StoragePoolConfig(String target)
    {
        super();
        this.target = target;
    }

    public void setUuid(String uuidString)
    {
        this.uuidString = uuidString;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Map<String, String> toMap()
    {
        Map<String, String> data = new HashMap<String, String>();
        data.put("_id", uuidString);
        data.put("target", target);
        data.put("name", name);
        return data;
    }
}
