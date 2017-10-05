package lero.com.androidstudio.lero;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

/**
 * Created by roney on 06/06/17.
 */

public class CadastroArtista extends Activity{

    private EditText nomeArtistico;
    private String idUsuario;
    private EditText bio;
   // private DatabaseReference raiz;
//    private Usuario usuario;
    private String categoria;
    private EditText cache;
    private BancoDeDados bancoDeDados;

    private Button botao;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_artista);

        botao = (Button) findViewById(R.id.botaoArtistaId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CadastroArtista.this, "entrou na funçao salvar dados", Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                idUsuario = bundle.getString("UID");
                categoria = bundle.getString("categoria");

                //raiz = FirebaseDatabase.getInstance().getReference();

//                Toast.makeText(CadastroArtista.this, "antes nome artistico", Toast.LENGTH_LONG).show();
////
                nomeArtistico =  (EditText) findViewById(R.id.nomeArtistaId);
                bio =  (EditText) findViewById(R.id.bioId);
                cache = (EditText) findViewById(R.id.cacheId);

                String nomeArtisticoString = nomeArtistico.getText().toString();
                String bioString = bio.getText().toString();
                String cacheString = cache.getText().toString();

                bancoDeDados = new BancoDeDados(idUsuario, categoria, nomeArtisticoString, cacheString, bioString);
////
//        Toast.makeText(CadastroArtista.this, "dps nome artistico : "+nomeArtisticoString , Toast.LENGTH_LONG).show();
////
////        //int categoriaUsuario, String nomeArtistico, String bio, Long cache
//                usuario = new Usuario(nomeArtisticoString, bioString, cacheString);
//                Toast.makeText(CadastroArtista.this, "vai salvar no bd agrrrrr", Toast.LENGTH_LONG).show();
//                raiz.child(idUsuario).child("nome").setValue(nomeArtisticoString);
//                raiz.child(idUsuario).child("categoria").setValue(categoria);
//                raiz.child(idUsuario).child("bio").setValue(bioString);
//                raiz.child(idUsuario).child("cache").setValue(cacheString);

                telaPrincipal(idUsuario);
            }
        });
    }

    public void telaPrincipal(String idUsuario){
        Intent i = null;
        Bundle bundle = new Bundle();
        bundle.putString("UID",idUsuario);
        i = new Intent(CadastroArtista.this,TelaPrincipal.class);
        i.putExtras(bundle);
        startActivity(i);
    }

}
