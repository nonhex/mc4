package ru.specialist.student.someapp;

import java.util.Objects;

/**
 * Created by xema on 22.07.15.
 */
public class Group<K extends Object, V extends Object> {
    public K k;
    public V v;

    public Group(K k, V v) {
        this.k = k;
        this.v = v;
    }
}
