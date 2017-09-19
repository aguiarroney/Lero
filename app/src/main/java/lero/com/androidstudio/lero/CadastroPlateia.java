package lero.com.androidstudio.lero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by roney on 06/06/17.
 */

public class CadastroPlateia extends Activity {

    private Button botao;
    private String idUsuario;
    private String categoria;
    private BancoDeDados bancoDeDados;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_plateia);

        botao =(Button) findViewById(R.id.botaoPlateiaaId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                idUsuario = bundle.getString("UID");
                categoria = bundle.getString("categoria");

                bancoDeDados = new BancoDeDados(idUsuario, categoria);
            }
        });
    }
}
