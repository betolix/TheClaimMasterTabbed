package net.blix.theclaimmaster.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import net.blix.theclaimmaster.Beans.UserBean;
import net.blix.theclaimmaster.R;
import net.blix.theclaimmaster.Sqlite.AppBdHelper;

public class RegisterActivity extends AppCompatActivity {
//ACTUALIZAR CON REGISTER ACTIVITY
    public EditText etNombre;
    public EditText etApePat;
    public EditText etApeMat;
    public EditText etDNI;
    public EditText etUsuario;
    public EditText etPassword;

    public Button btnRegistrar;

    public AppBdHelper mAppBdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //TECLADO OCULTO POR DEFECTO
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        mAppBdHelper = new AppBdHelper(RegisterActivity.this);

        etNombre = (EditText) findViewById(R.id.editTextNombre);
        etApePat= (EditText) findViewById(R.id.editTextApellidoPaterno);
        etApeMat= (EditText) findViewById(R.id.editTextApellidoMaterno);
        etDNI= (EditText) findViewById(R.id.editTextDNI);
        etUsuario= (EditText) findViewById(R.id.editTextUsuario);
        etPassword= (EditText) findViewById(R.id.editTextClave);
        //ACTUALIZAR CAMPOS


        btnRegistrar = (Button) findViewById(R.id.btnRegister);
/*
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apepat = etApePat.getText().toString();
                //IGUALAR CAMPO DEL REGISTER ACTIVITY




                UserBean usuario = new UserBean(
                        "1",
                        nombre,
                        apepat,
                        apemat,
                        dni,
                        usuario,
                        //actalizar con register activity

                        password);

                registrarUsuario(usuatio);
                )
            }
        });*/

    }

    /*
    public void registrarUsuario(UserBean user){
        new CrearUsuarioTask().execute(usuario);
    }

    */


    private class CrearUsuarioTask extends AsyncTask<UserBean,Void, Boolean>{

        public void resultadoUsuer(Boolean resultado){
            //Log.e(resultado), String.valueOf(resultado)
            if(resultado){
                Log.e("resultado", "Biemvenido Sr.");
                //Mandar al main activity
                //Shared preferences
            }else{
                Log.e("resultado", "Error al insertar IUsuario");
            }
        }


        @Override
        protected Boolean doInBackground(UserBean... userBeans) {
            return mAppBdHelper.agregarUsuario(userBeans[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //super.onPostExecute(aBoolean);
            resultadoUsuer(aBoolean);
        }
    }
}
