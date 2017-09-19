package lero.com.androidstudio.lero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by leona on 22/05/2017.
 */


public class EscolherPerfil extends Activity {



    private RadioGroup radioGroup;
    private RadioButton opcaoEscolhida;
    private RadioButton opcaoArtista;
    private RadioButton opcaoPalco;
    private RadioButton opcaoPlateia;


    private Button botao;


    //novo, acesso ao BD, referente a raiz do projeto
    //private DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();


    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.escolher_perfil);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);
        opcaoArtista = (RadioButton) findViewById(R.id.CheckBoxArtistaID);
        opcaoPalco = (RadioButton) findViewById(R.id.CheckBoxPalcoID);
        opcaoPlateia = (RadioButton) findViewById(R.id.CheckBoxPlateiaID);

    }

    /*
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        voltar();
    }*/


    public void escolherCategoriaUsuario(View view){
        // no banco de dados ta liberado pra qualquer um escrever no BD, rever isso

        int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();
        //Toast.makeText(EscolherPerfil.this, "ENTROU NO SALVAR BD: "+ idRadioButtonEscolhido, Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String idUsuario = bundle.getString("UID");

        //Toast.makeText(EscolherPerfil.this, "APOS RECEBER: "+ idUsuario, Toast.LENGTH_LONG).show();

        if(idRadioButtonEscolhido == opcaoArtista.getId()){
            //raiz.child(idUsuario).setValue("artista");
            //chamaria a proxima tela
            proximaTela(1, idUsuario); // 1 significa tela de artista

        }else {
            if (idRadioButtonEscolhido == opcaoPalco.getId()) {
                //raiz.child(idUsuario).setValue("palco");
                //chamar proxima tela
                Toast.makeText(EscolherPerfil.this, "escolheu perfil 2", Toast.LENGTH_LONG).show();
                proximaTela(2,idUsuario); //2 significa tela de palco


            } else {
                if (idRadioButtonEscolhido == opcaoPlateia.getId()) {
                    //raiz.child(idUsuario).setValue("plateia");
                    //chamar proxima tela
                    proximaTela(3,idUsuario); // 3 significa tela de plateia

                }
            }
        }


    }

    public void proximaTela(int numero, String idUsuario){
        Intent i = null;
        Bundle bundle = new Bundle();
        bundle.putString("UID",idUsuario);

        Toast.makeText(EscolherPerfil.this, "numero = "+ numero, Toast.LENGTH_LONG).show();
        switch (numero){
            case 1:
                i = new Intent(EscolherPerfil.this, CadastroArtista.class);
                bundle.putString("categoria", "artista");
                Toast.makeText(EscolherPerfil.this, "opção escolhida :"+ numero, Toast.LENGTH_LONG).show();
                i.putExtras(bundle);
                startActivity(i);
                break;
            case 2:

                Toast.makeText(EscolherPerfil.this, "opção escolhida :"+ numero, Toast.LENGTH_LONG).show();

                i = new Intent(EscolherPerfil.this, CadastroPalco.class);
                bundle.putString("categoria", "palco");

                i.putExtras(bundle);
                startActivity(i);
                break;
            case 3:
                i = new Intent(EscolherPerfil.this, CadastroPlateia.class);
                bundle.putString("categoria", "plateia");
                //Toast.makeText(EscolherPerfil.this, "opção escolhida :"+ numero, Toast.LENGTH_LONG).show();
                i.putExtras(bundle);
                startActivity(i);
                break;
        }
    }

    private void voltar() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
