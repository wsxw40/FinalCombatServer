package com.pearl.fcw.info.core.persistence.serializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.esotericsoftware.kryo.Kryo;

import de.javakaffee.kryoserializers.DateSerializer;

public class KryoFactory extends BasePooledObjectFactory<Kryo> {

    private Collection<Class<?>> entities;

    public void setEntities(Collection<Class<?>> entities) {
        this.entities = entities;
    }

    public KryoFactory() {
        this(Collections.emptySet());
    }

    public KryoFactory(Class<?>... registerClasses) {
        this(Stream.of(registerClasses).collect(Collectors.toSet()));
    }

    public KryoFactory(Set<Class<?>> registerClasses) {
        if (registerClasses != null) {
            entities = registerClasses.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                    .collect(Collectors.toList());
        } else {
            entities = Collections.emptyList();
        }
    }

    @Override
    public Kryo create() {
        Kryo k = new Kryo();
        k.register(java.util.Date.class, new DateSerializer(java.util.Date.class));
        k.register(java.sql.Date.class, new DateSerializer(java.sql.Date.class));
        k.register(ArrayList.class);
        k.register(HashMap.class);
        entities.forEach(k::register);
        return k;
    }

    @Override
    public PooledObject<Kryo> wrap(Kryo obj) {
        return new DefaultPooledObject<Kryo>(obj);
    }

}
