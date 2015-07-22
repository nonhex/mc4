package ru.specialist.student.someapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by student on 21.07.2015.
 */
public class FFrag0 extends AFrag {

    @Override
    public int layout_id() {
        return R.layout.ffrag0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        root.findViewById(R.id.rlayout).setBackgroundResource(R.color.accent_material_light);
        return root;
    }
}
