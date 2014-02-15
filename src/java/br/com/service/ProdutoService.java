package br.com.service;

import br.com.entities.Produto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Fernando
 */
public class ProdutoService {

    private EntityManager entity;

    public ProdutoService(EntityManager entity) {
        this.entity = entity;
    }

    public void salvar(Produto produto) {
        if (produto.getId() == null) {
            entity.persist(produto);
        } else {
            entity.merge(produto);
        }
    }

    public void remover(Produto produto) {
        produto = entity.find(Produto.class, produto.getId());
        entity.remove(produto);
    }

    public Produto buscarPorId(Long id) {
        return entity.find(Produto.class, id);
    }

    public List<Produto> BuscarTodos() {
        return entity.createQuery("FROM Produto p", Produto.class).getResultList();
    }
}
