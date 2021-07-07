/*
 *  collections - cz.majksa.commons.collections.map.CheckedMap
 *  Copyright (C) 2021  Majksa
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cz.majksa.commons.collections.map;

import cz.majksa.commons.collections.exceptions.KeyAlreadyExistsException;
import cz.majksa.commons.collections.exceptions.KeyDoesNotExistException;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p><b>Class {@link CheckedMap}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
public class CheckedMap<K, V> implements Map<K, V> {

    private final @NonNull Map<K, V> map;

    public CheckedMap() {
        this.map = new HashMap<>();
    }

    public CheckedMap(@NonNull Map<K, V> map) {
        this.map = new HashMap<>(map);
    }

    public CheckedMap(int initialCapacity, float loadFactor) {
        this.map = new HashMap<>(initialCapacity, loadFactor);
    }

    /**
     * Constructs an empty {@code HashMap} with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public CheckedMap(int initialCapacity) {
        this.map = new HashMap<>(initialCapacity);
    }

    /**
     * {@inheritDoc}
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * {@inheritDoc}
     *
     * @return true if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * {@inheritDoc}
     *
     * @param key key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     *         key
     * @throws java.lang.ClassCastException if the key is of an inappropriate type for
     *         this map
     * (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified key is null
     */
    @Override
    public boolean containsKey(@NonNull Object key) {
        return map.containsKey(key);
    }

    /**
     * {@inheritDoc}
     *
     * @param value value whose presence in this map is to be tested
     * @return {@code true} if this map maps one or more keys to the
     *         specified value
     * @throws java.lang.ClassCastException if the value is of an inappropriate type for
     *         this map
     * (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified value is null
     */
    @Override
    public boolean containsValue(@NonNull Object value) {
        return map.containsValue(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws java.lang.ClassCastException if the key is of an inappropriate type for
     *         this map
     * (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified key is null
     * @throws cz.majksa.commons.collections.exceptions.KeyDoesNotExistException if the specified key does not exist
     */
    @Override
    public V get(@NonNull Object key) throws KeyDoesNotExistException {
        if (!containsKey(key)) {
            throw new KeyDoesNotExistException(key.toString(), this);
        }
        return map.get(key);
    }

    /**
     * {@inheritDoc}
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key},
     *         if the implementation supports {@code null} values.)
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     * @throws NullPointerException if the specified key or value is null
     *         and this map does not permit null keys or values
     * @throws IllegalArgumentException if some property of the specified key
     *         or value prevents it from being stored in this map
     * @throws cz.majksa.commons.collections.exceptions.KeyAlreadyExistsException if the specified key already exists
     */
    @Override
    public V put(@NonNull K key, V value) throws KeyAlreadyExistsException {
        if (containsKey(key)) {
            throw new KeyAlreadyExistsException(key.toString(), this);
        }
        return map.put(key, value);
    }

    /**
     * {@inheritDoc}
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     * @throws java.lang.UnsupportedOperationException if the {@code remove} operation
     *         is not supported by this map
     * @throws java.lang.ClassCastException if the key is of an inappropriate type for
     *         this map
     * (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified key is null
     * @throws cz.majksa.commons.collections.exceptions.KeyDoesNotExistException if the specified key does not exist
     */
    @Override
    public V remove(@NonNull Object key) {
        if (!containsKey(key)) {
            throw new KeyDoesNotExistException(key.toString(), this);
        }
        return map.remove(key);
    }

    /**
     * {@inheritDoc}
     *
     * @param m mappings to be stored in this map
     * @throws java.lang.UnsupportedOperationException if the {@code putAll} operation
     *         is not supported by this map
     * @throws java.lang.ClassCastException if the class of a key or value in the
     *         specified map prevents it from being stored in this map
     * @throws java.lang.NullPointerException if the specified map is null, or if
     *         this map does not permit null keys or values
     * @throws java.lang.IllegalArgumentException if some property of a key or value in
     *         the specified map prevents it from being stored in this map
     * @throws cz.majksa.commons.collections.exceptions.KeyAlreadyExistsException if the specified key already exists
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws java.lang.UnsupportedOperationException if the {@code clear} operation
     *         is not supported by this map
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * {@inheritDoc}
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * {@inheritDoc}
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        return map.values();
    }

    /**
     * {@inheritDoc}
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    /**
     * {@inheritDoc}
     *
     * @param key key with which the specified value is associated
     * @param oldValue value expected to be associated with the specified key
     * @param newValue value to be associated with the specified key
     * @return {@code true} if the value was replaced
     * @throws java.lang.UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.ClassCastException if the class of a specified key or value
     *         prevents it from being stored in this map
     * @throws java.lang.NullPointerException if a specified key or newValue is null,
     *         and this map does not permit null keys or values
     * @throws java.lang.NullPointerException if oldValue is null and this map does not
     *         permit null values
     *         (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.IllegalArgumentException if some property of a specified key
     *         or value prevents it from being stored in this map
     * @throws cz.majksa.commons.collections.exceptions.KeyDoesNotExistException if the specified key does not exist
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if (!containsKey(key)) {
            throw new KeyDoesNotExistException(key.toString(), this);
        }
        return map.replace(key, oldValue, newValue);
    }

    /**
     * {@inheritDoc}
     *
     * @param key key with which the specified value is associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or
     *         {@code null} if there was no mapping for the key.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with the key,
     *         if the implementation supports null values.)
     * @throws java.lang.UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws java.lang.NullPointerException if the specified key or value is null,
     *         and this map does not permit null keys or values
     * @throws java.lang.IllegalArgumentException if some property of the specified key
     *         or value prevents it from being stored in this map
     * @throws cz.majksa.commons.collections.exceptions.KeyDoesNotExistException if the specified key does not exist
     */
    @Override
    public V replace(K key, V value) {
        if (!containsKey(key)) {
            throw new KeyDoesNotExistException(key.toString(), this);
        }
        return map.replace(key, value);
    }

}
