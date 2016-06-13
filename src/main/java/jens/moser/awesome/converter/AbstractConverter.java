package jens.moser.awesome.converter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<T, V> implements Converter<T, V> {

    @Override
    public T convert(V v) {

        if (v == null) {
            return null;
        }

        return convertItem(v);

    }

    @Override
    public List<T> convert(List<V> vList) {

        if (vList == null) {
            return null;
        }

        List<T> tList = new ArrayList<>();

        for (V v : vList) {
            T t = convertItem(v);
            if (t != null) {
                tList.add(t);
            }
        }

        return tList;
    }

    protected abstract T convertItem(V v);
}