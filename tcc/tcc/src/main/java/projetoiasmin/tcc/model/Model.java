package projetoiasmin.tcc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "moda")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String ocasiao;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String imagURL;



    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOcasiao() {
        return ocasiao;
    }

    public void setOcasiao(String ocasiao) {
        this.ocasiao = ocasiao;
    }

    public String getTitulo() {
        return titulo;
    }

    // Getter
    public String getImagURL() {
        return imagURL;
    }

    // Setter
    public void setImagURL(String imagURL) {
        this.imagURL = imagURL;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
