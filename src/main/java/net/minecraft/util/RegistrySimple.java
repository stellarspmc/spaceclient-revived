package net.minecraft.util;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class RegistrySimple<K, V> implements IRegistry<K, V> {
    private static final Logger logger = LogManager.getLogger();
    protected final Map<K, V> registryObjects = this.createUnderlyingMap();

    protected Map<K, V> createUnderlyingMap() {
        return Maps.newHashMap();
    }

    public V getObject(K name) {
        return this.registryObjects.get(name);
    }

    /**
     * Register an object on this registry.
     */
    public void putObject(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        if (this.registryObjects.containsKey(key)) {
            logger.debug("Adding duplicate key '{}' to registry", key);
        }

        this.registryObjects.put(key, value);
    }

    public Set<K> getKeys() {
        return Collections.unmodifiableSet(this.registryObjects.keySet());
    }

    /**
     * Does this registry contain an entry for the given key?
     */
    public boolean containsKey(K key) {
        return this.registryObjects.containsKey(key);
    }

    public Iterator<V> iterator() {
        return this.registryObjects.values().iterator();
    }
}
