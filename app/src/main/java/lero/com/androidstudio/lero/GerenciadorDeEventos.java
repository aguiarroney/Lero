package lero.com.androidstudio.lero;

/**
 * Created by roney on 12/10/17.
 */

public class GerenciadorDeEventos {
    private String nomeArtista;
    private String nomePalco;
    private String endereco;
    private String data;
    private String hora;
    private String nomeEvento;

    public GerenciadorDeEventos(String nomeEvento, String nomeArtista, String nomePalco, String endereco, String data, String hora){
        this.nomeArtista = nomeArtista;
        this.nomeEvento= nomeEvento;
        this.nomePalco = nomePalco;
        this.data = data;
        this.endereco = endereco;
        this.hora = hora;

    }

    public void setEvento(){
        BancoDeDados bd = new BancoDeDados(true, this.nomeEvento, this.nomeArtista, this.nomePalco,
                this.endereco, this.data, this.hora);
    }
}
