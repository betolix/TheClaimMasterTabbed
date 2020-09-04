package net.blix.theclaimmaster.Activities;

import android.support.v4.app.Fragment;

import net.blix.theclaimmaster.Fragments.ClaimListFragment;

public class ClaimListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ClaimListFragment();
    }
}
