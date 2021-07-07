/*
 *  collections - cz.majksa.commons.collections.multi.TriHashMap
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

package cz.majksa.commons.collections.multi;

import cz.majksa.commons.collections.exceptions.KeyAlreadyExistsException;
import cz.majksa.commons.collections.exceptions.KeyDoesNotExistException;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p><b>Class {@link cz.majksa.commons.collections.multi.TriHashMap}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
public class TriHashMap<K1, K2, V> implements TriMap<K1, K2, V> {

    private final Map<K1, Map<K2, V>> map = new HashMap<>();

    public TriHashMap() {
    }

    public TriHashMap(TriMap<K1, K2, V> map) {
        map.forEach(entry -> put(entry.getKey1(), entry.getKey2(), entry.getValue()));
    }

    @Override
    public @NonNull V get(@NonNull K1 key1, @NonNull K2 key2) {
        if (!containsKey1(key1)) {
            throw new KeyDoesNotExistException(key1.toString(), map);
        }
        final Map<K2, V> map2 = map.get(key1);
        if (!map2.containsKey(key2)) {
            throw new KeyDoesNotExistException(key2.toString(), map2);
        }
        return map2.get(key2);
    }

    @Override
    public void put(@NonNull K1 key1, @NonNull K2 key2, @NonNull V value) {
        if (!containsKey1(key1)) {
            map.put(key1, new HashMap<>());
        }
        final Map<K2, V> map2 = map.get(key1);
        if (map2.containsKey(key2)) {
            throw new KeyAlreadyExistsException(key2.toString(), map2);
        }
        map2.put(key2, value);
    }

    @Override
    public @NonNull V replace(@NonNull K1 key1, @NonNull K2 key2, @NonNull V value) {
        V oldValue = get(key1, key2);
        map.get(key1).replace(key2, value);
        return oldValue;
    }

    @Override
    public int size() {
        int size = 0;
        for (Map.Entry<K1, Map<K2, V>> entry : map.entrySet()) {
            size += entry.getValue().size();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey1(K1 key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsKey2(K2 key) {
        for (Map<K2, V> value : map.values()) {
            if (value.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsKeys(K1 key1, K2 key2) {
        return containsKey1(key1) && map.get(key1).containsKey(key2);
    }

    @Override
    public boolean containsValue(V value) {
        for (Map<K2, V> vMap : map.values()) {
            if (vMap.containsValue(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void forEach(@NonNull Consumer<Entry<K1, K2, V>> consumer) {
        entrySet().forEach(consumer);
    }

    @Override
    public @NonNull Set<Entry<K1, K2, V>> entrySet() {
        final Set<Entry<K1, K2, V>> set = new HashSet<>();
        for (Map.Entry<K1, Map<K2, V>> entry : map.entrySet()) {
            for (Map.Entry<K2, V> vEntry : entry.getValue().entrySet()) {
                set.add(new SimpleTriEntry<>(entry.getKey(), vEntry.getKey(), vEntry.getValue()));
            }
        }
        return set;
    }

    @Override
    public @NonNull Collection<V> values() {
        final Collection<V> collection = new HashSet<>();
        for (Map.Entry<K1, Map<K2, V>> entry : map.entrySet()) {
            for (Map.Entry<K2, V> vEntry : entry.getValue().entrySet()) {
                collection.add(vEntry.getValue());
            }
        }
        return collection;
    }

}
