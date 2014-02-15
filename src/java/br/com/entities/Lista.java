package br.com.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fernando
 */
@Entity
public class Lista {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;  
 @NotNull
 private String nome;
 @Temporal(javax.persistence.TemporalType.DATE)
 private Date dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
 @Override
    public String toString() {
        return "Lista{" + "id=" + id 
                + ", nome=" + nome 
                + ", dataCadastro=" + dataCadastro 
                + '}';
    }
 
 
}
