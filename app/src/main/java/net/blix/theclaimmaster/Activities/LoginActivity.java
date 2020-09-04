package net.blix.theclaimmaster.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.blix.theclaimmaster.R;

public class LoginActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    //ConstraintLayout constraintLayout;
    //AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        //animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        //animationDrawable.setEnterFadeDuration(3000);
        //animationDrawable.setExitFadeDuration(3000);


        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.btnIngresar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                //Intent intent = new Intent(LoginActivity.this, ClaimListActivity.class);//OJO ESTE CARGA SOLO CLAIM LIST ACTIVITY
                startActivity(intent);
            }
        });


    }
}
