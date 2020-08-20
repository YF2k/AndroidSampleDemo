package com.joker.demo.opensourceframe.leakcanary;

import com.joker.demo.utils.Preconditions;
import com.joker.demo.utils.StringUtil;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import static androidx.core.util.Preconditions.checkNotNull;


public class KeyedWeakReference extends WeakReference<Object> {
    public final String key;
    public final String name;

    public KeyedWeakReference(Object referent, String key, String name,
                              ReferenceQueue<Object> referenceQueue) {
        super(Preconditions.checkNotNull(referent,name), Preconditions.checkNotNull(referenceQueue,name));
        this.key = checkNotNull(key, "key");
        this.name = checkNotNull(name, "name");
    }
}
