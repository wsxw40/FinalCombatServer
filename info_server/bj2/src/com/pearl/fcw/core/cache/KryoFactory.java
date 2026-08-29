package com.pearl.fcw.core.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.esotericsoftware.kryo.Kryo;

import de.javakaffee.kryoserializers.DateSerializer;

public class KryoFactory implements com.esotericsoftware.kryo.pool.KryoFactory {

    private Collection<Class<?>> classes;

    public KryoFactory() {
        this(Collections.emptySet());
    }

    public KryoFactory(Class<?>... registerClasses) {
        this(Stream.of(registerClasses).collect(Collectors.toSet()));
    }

    public KryoFactory(Set<Class<?>> registerClasses) {
        if (registerClasses != null) {
            classes = registerClasses.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                    .collect(Collectors.toList());
        } else {
            classes = Collections.emptyList();
        }
    }

    @Override
    public Kryo create() {
        Kryo k = new Kryo();
        k.register(java.util.Date.class, new DateSerializer(java.util.Date.class));
        k.register(java.sql.Date.class, new DateSerializer(java.sql.Date.class));
        k.register(ArrayList.class);
        k.register(HashMap.class);
        classes.forEach(k::register);
        return k;
    }

}
