/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Fernando
 */
@Entity
public class ProdutoLista implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdutoLista;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ID_LISTA")
    private Lista lista;

    private int qtd = 1;

    public Long getIdProdutoLista() {
        return idProdutoLista;
    }

    public void setIdProdutoLista(Long idProdutoLista) {
        this.idProdutoLista = idProdutoLista;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "ProdutoLista{idProdutoLista=" + idProdutoLista + ", idLista=" + lista.getId() + ", descricaoLista=" + lista.getNome()
                + ", idProduto=" + produto.getId() + ", descricao=" + produto.getDescricao()
                + ", preco=" + produto.getPreco()+ ", qtd=" + qtd + ", vlrTotal=" + qtd * produto.getPreco()+ "}\n";
    }
}
