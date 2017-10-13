package lero.com.androidstudio.lero;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.client.result.BookmarkDoCoMoResultParser;

import java.util.Date;

/**
 * Created by roney on 07/06/17.
 */

public class BancoDeDados {

    private String categoria;
    private String nomeArtisitco;
    private String nomeEstabelecimento;
    private String cache;
    private String bio;
    private String cnpj;
    private DatabaseReference raiz;
    private String idUsuario;
    private String endereco;



    public BancoDeDados(String idUsuario, String categoria, String nomeArtistico, String cache, String bio){
        this.categoria = categoria;
        this.nomeArtisitco = nomeArtistico;
        this.cache = cache;
        this.bio =bio;
        this.idUsuario = idUsuario;

        raiz = FirebaseDatabase.getInstance().getReference();

        raiz.child(idUsuario).removeValue();

        raiz.child(idUsuario).child("nome").setValue(nomeArtistico);
        raiz.child(idUsuario).child("categoria").setValue(categoria);
        raiz.child(idUsuario).child("bio").setValue(bio);
        raiz.child(idUsuario).child("cache").setValue(cache);

    }

    public BancoDeDados(String idUsuario, String categoria, String nomeEstabelecimento, String cnpj){

        raiz = FirebaseDatabase.getInstance().getReference();

        this.categoria = categoria;
        this.nomeEstabelecimento = nomeEstabelecimento;
//        this.endereco = endereco;
        this.cnpj = cnpj;
        this.idUsuario =idUsuario;

        raiz.child(idUsuario).removeValue();

        raiz.child(idUsuario).child("nome").setValue(nomeEstabelecimento);
        raiz.child(idUsuario).child("categoria").setValue(categoria);
        raiz.child(idUsuario).child("cnpj").setValue(cnpj);
//        raiz.child(idUsuario).child("endereco").setValue(endereco);

    }

    public BancoDeDados(String idUsuario, String categoria){

        raiz = FirebaseDatabase.getInstance().getReference();

        raiz.child(idUsuario).removeValue();

        this.idUsuario = idUsuario;
        this.categoria = categoria;

        raiz.child(idUsuario).child("categoria").setValue(categoria);
    }

    public BancoDeDados(Boolean evento, String nomeEvento, String nomeArtista, String nomePalco, String endereco, String data, String hora){
        raiz = FirebaseDatabase.getInstance().getReference();

        DatabaseReference raizEventos = raiz.child("Eventos").child(String.valueOf(new Date().getTime()));
        raizEventos.child("nomeEvento").setValue(nomeEvento);
        raizEventos.child("nomeArtista").setValue(nomeArtista);
        raizEventos.child("nomePalco").setValue(nomePalco);
        raizEventos.child("endereco").setValue(endereco);
        raizEventos.child("data").setValue(data);
        raizEventos.child("hora").setValue(hora);
    }




}
