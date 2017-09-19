package lero.com.androidstudio.lero;

/**
 * Created by roney on 06/06/17.
 */

public class Usuario {

    private String categoriaUsuario;
    private String nomeArtistico;
    private String bio;
    private String cache;

    public Usuario (/*int categoriaUsuario,*/ String nomeArtistico, String bio, String cache){
       // this.categoriaUsuario = categoriaUsuario;
        this.bio = bio;
        this.cache = cache;
        this.nomeArtistico = nomeArtistico;
    }
}
