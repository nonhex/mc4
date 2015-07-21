package ru.specialist.student.someapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

/**
 * Created by student on 21.07.2015.
 */
public abstract class AFrag extends Fragment {
    public void l(String m) {
        Log.d("MC4", hashCode() + ":" + getClass().getSimpleName() + ": " + m);
    }

    public abstract int layout_id();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(layout_id(), container, false);
        l("onCreateView");
        ((TextView) root.findViewById(R.id.ff_text_view)).setText(
                "Класс: " + getClass().getSimpleName());
        root.findViewById(R.id.ff_btn_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnClick(v);
            }
        });
        return root;
    }

    public void onBtnClick(View r) {
        EventBus.getDefault().post(new OnResumeInFragment(this.getClass()));
    }
}
