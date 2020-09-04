package net.blix.theclaimmaster.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import net.blix.theclaimmaster.Activities.ClaimActivity;
import net.blix.theclaimmaster.Activities.ClaimPagerActivity;
import net.blix.theclaimmaster.Activities.MainActivity;
import net.blix.theclaimmaster.ClaimLab;
import net.blix.theclaimmaster.R;
import net.blix.theclaimmaster.Beans.Claim;
import java.util.List;
import java.util.UUID;


public class ClaimListFragment extends Fragment  {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mClaimRecyclerView;

    private ClaimAdapter mAdapter;

    private boolean mSubtitleVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_claim_list, container, false);

        mClaimRecyclerView = (RecyclerView) view.findViewById(R.id.claim_recycler_view);

        mClaimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_claim_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if(mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_claim:
                Claim claim = new Claim();
                ClaimLab.get(getActivity()).addClaim(claim);
                Intent intent = ClaimPagerActivity.newIntent(getActivity(), claim.getId());
                startActivity(intent);
                return true;
            //case R.id.delete_claim:
              //  UUID crimeId = mClaim.getId();

            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void updateUI(){
        ClaimLab claimLab = ClaimLab.get(getActivity());
        List<Claim> claims = claimLab.getClaims();

        if(mAdapter == null){
            mAdapter = new ClaimAdapter(claims);
            mClaimRecyclerView.setAdapter(mAdapter);
        } else{

            mAdapter.setClaims(claims);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }

    private void updateSubtitle(){
        ClaimLab claimLab = ClaimLab.get(getActivity());
        int claimCount = claimLab.getClaims().size();
        String subtitle = getString(R.string.subtitle_format, claimCount);

        if(!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }


    private class ClaimHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextViewSiniestro;
        private TextView mNombreAsegurado;
        private TextView mHeadString;
        private TextView mTags;

        private Claim mClaim;


        public ClaimHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_claim, parent, false));

            itemView.setOnClickListener(this);

            mTextViewSiniestro = (TextView) itemView.findViewById(R.id.textViewSiniestro);
            mNombreAsegurado = (TextView) itemView.findViewById(R.id.textViewNombreAsegurado);
            mHeadString = (TextView) itemView.findViewById(R.id.textViewHeadString);
            mTags = (TextView) itemView.findViewById(R.id.textViewTags);
        }

        public void bind(Claim claim){
            mClaim = claim;
            mTextViewSiniestro.setText(mClaim.getNumeroSiniestro());
            mNombreAsegurado.setText(mClaim.getNombreAsegurado());
            mHeadString.setText(mClaim.getHeadString());
            mTags.setText(mClaim.getTag());
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), mClaim.getNumeroSiniestro() + " Clicked", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(), ClaimActivity.class);

            //Intent intent = ClaimActivity.newIntent(getActivity(), mClaim.getId());

            Intent intent = ClaimPagerActivity.newIntent(getActivity(),mClaim.getId());

            startActivity(intent);

        }
    }


    private class ClaimAdapter extends RecyclerView.Adapter<ClaimHolder>
    {
        private List<Claim> mClaims;

        public ClaimAdapter(List<Claim> claims){
            mClaims = claims;
        }


        @NonNull
        @Override
        public ClaimHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ClaimHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull ClaimHolder claimHolder, int i) {
            Claim claim = mClaims.get(i);
            claimHolder.bind(claim);

        }

        @Override
        public int getItemCount() {
            return mClaims.size();
        }

        public void setClaims(List<Claim> claims) {
            mClaims = claims;
        }
    }
}
