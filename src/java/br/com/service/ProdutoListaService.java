package br.com.service;

import br.com.entities.ProdutoLista;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Fernando
 */
public class ProdutoListaService {

    private EntityManager entity;

    public ProdutoListaService(EntityManager entity) {
        this.entity = entity;
    }

    public List<ProdutoLista> buscar(Long idLista) {
        String sql = "FROM ProdutoLista AS PL WHERE PL.lista.id = " + String.valueOf(idLista);
        return entity.createQuery(sql, ProdutoLista.class).getResultList();
    }

    public ProdutoLista BuscarPorId(Long idProdutoLista) {
        return entity.find(ProdutoLista.class, idProdutoLista);
    }

    public void salvar(ProdutoLista produtoLista) {
        if (produtoLista.getIdProdutoLista() == null) {
            entity.persist(produtoLista);
        } else {
            entity.merge(produtoLista);
        }
    }

    public void remove(ProdutoLista produtoLista) {
        produtoLista = entity.find(ProdutoLista.class, produtoLista.getIdProdutoLista());
        entity.remove(produtoLista);
    }
}
