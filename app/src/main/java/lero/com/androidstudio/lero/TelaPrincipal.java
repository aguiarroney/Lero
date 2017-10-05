package lero.com.androidstudio.lero;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaPrincipal extends Activity {

    private String idUsuario;
    private TextView nomeArtista;
    private String nome_display;

    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.tela_principal);

        nomeArtista = (TextView) findViewById(R.id.nomeArtista);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            nome_display = user.getDisplayName();
        }


        nomeArtista.setText(nome_display);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idUsuario = bundle.getString("UID");

        ProfilePictureView profilePictureView;
        profilePictureView = (ProfilePictureView) findViewById(R.id.perfil_img);
        profilePictureView.setProfileId(com.facebook.Profile.getCurrentProfile().getId());
    }
}
