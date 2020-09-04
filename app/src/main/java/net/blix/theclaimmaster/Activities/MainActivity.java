package net.blix.theclaimmaster.Activities;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import net.blix.theclaimmaster.Beans.Claim;
import net.blix.theclaimmaster.ClaimLab;
import net.blix.theclaimmaster.Fragments.ClaimListFragment;
import net.blix.theclaimmaster.Fragments.ProfileFragment;
import net.blix.theclaimmaster.R;
import net.blix.theclaimmaster.Fragments.ChatFragment;
import net.blix.theclaimmaster.Fragments.ClaimFragment;

//public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

public class MainActivity extends SingleFragmentActivity implements NavigationView.OnNavigationItemSelectedListener {

    //PLAY SERVICES REQUEST ERROR CODE
    private static final int REQUEST_ERROR = 0;

    //OJO ESTE METODO CREA EL FRAGMENT PRINCIPAL DE LA LISTA DE SINIESTROS
    @Override
    protected Fragment createFragment() {
        return new ClaimListFragment();
    }


    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawer  = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);//

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ClaimFragment()).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ClaimListFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_claim);// TO SET THE SELECTED ITEM BY DEFAULT
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){


            case R.id.nav_add_claim:
                Claim claim = new Claim();
                ClaimLab.get(MainActivity.this).addClaim(claim);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ClaimFragment()).commit();

                Intent intent = ClaimPagerActivity.newIntent(MainActivity.this, claim.getId());
                startActivity(intent);


                break;
            case R.id.nav_claim:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ClaimFragment()).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ClaimListActivity().createFragment()).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ChatFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int errorCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if(errorCode != ConnectionResult.SUCCESS){
            Dialog errorDialog = apiAvailability.getErrorDialog(this, errorCode, REQUEST_ERROR, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //LEAVE IF SERVICE IS UNAVAILABLE
                    finish();
                }
            });
            errorDialog.show();
        }
    }

}
