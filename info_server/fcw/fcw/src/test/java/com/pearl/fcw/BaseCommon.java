package com.pearl.fcw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pearl.fcw.info.core.network.Response;

public class BaseCommon {
    public final Map<Integer, Response> promises = new ConcurrentHashMap<>(1024);

}
