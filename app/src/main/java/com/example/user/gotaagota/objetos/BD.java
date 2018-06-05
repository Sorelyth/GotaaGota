package com.example.user.gotaagota.objetos;



import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




/**
 * Created by User on 3/06/2018.
 */

public class BD {
    private static String dbnombre = "gota";
    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private static String id;

    public static int hola;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        BD.id = id;
    }

    public static String obtenerID(){
        return db.push().getKey();
    }
    public static void guardar(prestador p){
        db.child(dbnombre).child(p.getId()).setValue(p);
    }

    public static boolean login(String user,String contra){

        return true;
    }
}
