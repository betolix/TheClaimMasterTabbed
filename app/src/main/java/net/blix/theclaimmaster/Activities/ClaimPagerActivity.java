package net.blix.theclaimmaster.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import net.blix.theclaimmaster.Beans.Claim;
import net.blix.theclaimmaster.ClaimLab;
import net.blix.theclaimmaster.Fragments.ClaimFragment;
import net.blix.theclaimmaster.R;

import java.util.List;
import java.util.UUID;

public class ClaimPagerActivity extends AppCompatActivity {

    private static final String EXTRA_CLAIM_ID = "net.blix.theclaimmaster.claim_id";

    private ViewPager mViewPager;
    private List<Claim> mClaims;

    public static Intent newIntent (Context packageContext, UUID claimId){

        Intent intent = new Intent(packageContext, ClaimPagerActivity.class);
        intent.putExtra(EXTRA_CLAIM_ID, claimId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_pager);

        UUID claimId = (UUID) getIntent().getSerializableExtra(EXTRA_CLAIM_ID);

        mViewPager = (ViewPager) findViewById(R.id.claim_view_pager);

        mClaims = ClaimLab.get(this).getClaims();
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Claim claim = mClaims.get(i);
                return ClaimFragment.newInstance(claim.getId());
            }

            @Override
            public int getCount() {
                return mClaims.size();
            }
        });

         for(int i = 0; i < mClaims.size(); i++){
            if(mClaims.get(i).getId().equals(claimId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
