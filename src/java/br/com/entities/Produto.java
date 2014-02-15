package br.com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fernando
 */
@Entity
public class Produto{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String descricao;
    private Double preco;
   // @Lob
 //   private byte[] imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

  //  public byte[] getImagem() {
//        return imagem;
  //  }

 //   public void setImagem(byte[] imagem) {
  //      this.imagem = imagem;
   // }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id 
                + ", descricao=" + descricao 
                + ", preco=" + preco 
           //     + ", imagem=" + imagem                 
                + '}';
    }
    
    
    
}
