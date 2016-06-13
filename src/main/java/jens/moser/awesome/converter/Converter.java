package jens.moser.awesome.converter;

import java.util.List;

public interface Converter<T, V> {

    T convert(V v);

    List<T> convert(List<V> v);
}