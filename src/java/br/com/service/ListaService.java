package br.com.service;

import br.com.entities.Lista;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Fernando
 */
public class ListaService {

    private EntityManager entity;

    public ListaService(EntityManager entity) {
        this.entity = entity;
    }

    public List<Lista> BuscarTodas() {
        return entity.createQuery("FROM Lista l", Lista.class).getResultList();
    }

    public Double valorTotal(Long id) {
        String sql = "SELECT SUM(P.preco * PL.qtd) FROM ProdutoLista PL, Produto P"
                + "    WHERE PL.produto.id = P.id"
                + "      AND PL.lista.id = :id";

        return (Double) (entity.createQuery(sql).setParameter("id", id).getSingleResult());
    }

    public Lista BuscarPorId(Long id) {
        return entity.find(Lista.class, id);
    }

    public void remover(Lista lista) {
        lista = entity.find(Lista.class, lista.getId());
        entity.remove(lista);
    }

    public void salvar(Lista lista) {
        if (lista.getId() == null) {
            entity.persist(lista);
        } else {
            entity.merge(lista);
        }
    }
}
