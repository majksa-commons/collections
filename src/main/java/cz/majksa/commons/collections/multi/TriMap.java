/*
 *  collections - cz.majksa.commons.collections.multi.TriMap
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

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p><b>Class {@link cz.majksa.commons.collections.multi.TriMap}</b></p>
 *
 * @author majksa
 * @version 1.0.0
 * @since 1.0.0
 */
public interface TriMap<K1, K2, V> {

    @NonNull V get(@NonNull K1 key1, @NonNull K2 key2);

    void put(@NonNull K1 key1, @NonNull K2 key2, @NonNull V value);

    @NonNull V replace(@NonNull K1 key1, @NonNull K2 key2, @NonNull V value);

    int size();

    boolean isEmpty();

    boolean containsKey1(K1 key);

    boolean containsKey2(K2 key);

    boolean containsKeys(K1 key1, K2 key2);

    boolean containsValue(V value);

    void forEach(@NonNull Consumer<Entry<K1, K2, V>> consumer);

    @NonNull Set<Entry<K1, K2, V>> entrySet();

    @NonNull Collection<V> values();

    interface Entry<K1, K2, V> {

        @NonNull K1 getKey1();

        @NonNull K2 getKey2();

        @NonNull V getValue();

        @Nullable V setValue(@NonNull V value);

    }

}
