package net.blix.theclaimmaster.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import net.blix.theclaimmaster.Fragments.ClaimFragment;

import java.util.UUID;

//public class ClaimActivity extends AppCompatActivity {
public class ClaimActivity extends SingleFragmentActivity {

    //public static final String EXTRA_CLAIM_ID =  "net.blix.theclaimmaster.claim_id";
    private static final String EXTRA_CLAIM_ID =  "net.blix.theclaimmaster.claim_id";

    public static Intent newIntent(Context packageContext, UUID claimId){
        Intent intent = new Intent (packageContext, ClaimActivity.class);
        intent.putExtra(EXTRA_CLAIM_ID, claimId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        //return new ClaimFragment();
        UUID claimId = (UUID) getIntent().getSerializableExtra(EXTRA_CLAIM_ID);
        return ClaimFragment.newInstance(claimId);
    }

}
