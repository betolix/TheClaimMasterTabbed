package net.blix.theclaimmaster.Contract;
//ACTUALIZAR CON LOS DATOS DEL REGISTER ACTIVITY
import android.provider.BaseColumns;

public class UserContract {

    public static abstract class UserEntry implements BaseColumns{

        public static final String TABLE_NAME = "usuario";

        public static final String IDCLIENTE ="id";
        public static final String NOMBRE ="nombre";
        public static final String APELLIDOPATERNO ="apellido_paterno";
        public static final String APELLIDOMATERNO ="apellido_materno";
        public static final String DNI ="dni";
        public static final String USUARIO ="usuario";
        public static final String PASSWORD ="password";

    }
}
