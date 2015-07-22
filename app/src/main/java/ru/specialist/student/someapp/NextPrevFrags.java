package ru.specialist.student.someapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by student on 20.07.2015.
 */
public class NextPrevFrags extends AppCompatActivity {

    private Fragment[] frags = new Fragment[]{};
    private FragmentManager fm = null;
    private int fragment_place_id = 0;

    public void addFragmentsTo(int rid, Fragment... frags) {
        fragment_place_id = rid;
        this.frags = frags;
        this.fm = getFragmentManager();
        next_frag();
    }

    private byte frag_ind = -1;

    public void next_frag() {
        if (frags.length > frag_ind + 1) {
            frag_ind += 1;
        } else {
            frag_ind = 0;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.replace(fragment_place_id, frags[frag_ind]);
        ft.addToBackStack("ffrag" + frag_ind);
        ft.commit();
    }

    public void prev_frag() {
        if (frag_ind > 0) {
            frag_ind -= 1;
        } else {
            frag_ind = (byte) (frags.length - 1);
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        ft.replace(fragment_place_id, frags[frag_ind]);
        ft.addToBackStack("ffrag" + frag_ind);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
