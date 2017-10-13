package lero.com.androidstudio.lero;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaPrincipalArtista extends Activity {

    private String idUsuario;
    private TextView nomeArtista;
    private String nome_display;

    private Toolbar mToolBar;

    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.tela_principal_artista);

        mToolBar = (Toolbar) findViewById(R.id.tb_main);
        mToolBar.setTitle("Lero");
        mToolBar.setSubtitle("Artista");
        mToolBar.setLogo(R.mipmap.ic_launcher);

//        nomeArtista = (TextView) findViewById(R.id.nomeArtista);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            nome_display = user.getDisplayName();
        }

        // as duas linhas abaixo sa um teste de criação de envento e salvamento desse evento no bd
        // aqui nao é o lugar delas, tem que ver onde o evento sera criado para instanciar a classe no lugar certo
        // ate aqui tudo funciona - Roney
        GerenciadorDeEventos ge = new GerenciadorDeEventos("EvenTALOUCO", "LEO", "UFF", "costazul","6/10/99", "17h");
        ge.setEvento();
//
//        // teste merge branch
//
//
//        nomeArtista.setText(nome_display);
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        idUsuario = bundle.getString("UID");
//
//        ProfilePictureView profilePictureView;
//        profilePictureView = (ProfilePictureView) findViewById(R.id.perfil_img);
//        profilePictureView.setProfileId(com.facebook.Profile.getCurrentProfile().getId());
    }
}
