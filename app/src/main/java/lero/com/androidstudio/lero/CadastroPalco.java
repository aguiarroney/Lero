package lero.com.androidstudio.lero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by roney on 06/06/17.
 */

public class CadastroPalco extends Activity {

    private EditText nomeEstabelecimentoEditText;
    private EditText enderecoEditText;
    private EditText cnpjEditText;
    private BancoDeDados bancoDeDados;
    private String idUsuario;
    private String categoria;

    private Button botao;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_palco);

        Toast.makeText(CadastroPalco.this, "entrou na onCrate", Toast.LENGTH_LONG).show();

        botao = (Button) findViewById(R.id.botaoPalcoId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                idUsuario = bundle.getString("UID");
                categoria = bundle.getString("categoria");
//
                nomeEstabelecimentoEditText = (EditText) findViewById(R.id.nomeEstabelecimentoId);
////                enderecoEditText = (EditText) findViewById(R.id.enderecoId);
                cnpjEditText = (EditText) findViewById(R.id.cnpjId);
//
                String nomeEstabelecimento = nomeEstabelecimentoEditText.getText().toString();
////                String endereco = enderecoEditText.getText().toString();
                String cnpj = cnpjEditText.getText().toString();

                Toast.makeText(CadastroPalco.this, "salvar no bd agora", Toast.LENGTH_LONG).show();

                bancoDeDados = new BancoDeDados(idUsuario, categoria, nomeEstabelecimento, cnpj);
            }
        });

    }

}
