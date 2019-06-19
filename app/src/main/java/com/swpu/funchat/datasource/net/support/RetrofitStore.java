package com.swpu.funchat.datasource.net.support;

import androidx.core.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import retrofit2.Retrofit;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see RetrofitStore
 * @since 2019/6/17
 */
public class RetrofitStore {

    private final HashMap<String, Pair<Retrofit, HashMap<Class<?>, Object>>> mMap = new HashMap<>();

    public final void put(String key, Retrofit retrofit) {
        Pair<Retrofit, HashMap<Class<?>, Object>> newPair = Pair.create(retrofit, new HashMap<>());
        Pair<Retrofit, HashMap<Class<?>, Object>> old = mMap.put(key, newPair);
        if (old != null) {
            if (old.second != null) {
                old.second.clear();
            }
        }
    }

    public final Retrofit get(String key) {
        return Objects.requireNonNull(mMap.get(key), "retrofit by " + key + " is null").first;
    }

    public <I> I getServiceWithKey(String key, Class<I> clz) {
        Pair<Retrofit, HashMap<Class<?>, Object>> pair = mMap.get(key);
        Objects.requireNonNull(pair, "retrofit by " + key + " is null");
        Retrofit retrofit = Objects.requireNonNull(pair.first);
        HashMap<Class<?>, Object> holder = Objects.requireNonNull(pair.second);
        @SuppressWarnings("unchecked")
        I service = (I) holder.get(clz);
        if (service == null) {
            service = retrofit.create(clz);
            holder.put(clz, service);
        }
        return service;
    }

    public Set<String> keys() {
        return new HashSet<>(mMap.keySet());
    }

    public final void clear() {
        for (Pair<Retrofit, HashMap<Class<?>, Object>> pair : mMap.values()) {
            HashMap<Class<?>, Object> holder = pair.second;
            if (holder != null) {
                holder.clear();
            }
        }
        mMap.clear();
    }
}
