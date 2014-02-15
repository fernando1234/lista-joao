package br.com.controller;

import br.com.entities.Lista;
import br.com.entities.Produto;
import br.com.entities.ProdutoLista;
import br.com.service.ListaService;
import br.com.service.ProdutoListaService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fernando
 */
@ManagedBean
public class ProdutoListaController {

    private ProdutoLista produtoLista = new ProdutoLista();
    private Produto produto = new Produto();
    private Lista lista = new Lista();

    public ProdutoLista getProdutoLista() {
        return produtoLista;
    }

    public void setProdutoLista(ProdutoLista produtoLista) {
        this.produtoLista = produtoLista;
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

    @PostConstruct
    public void atualiza() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String idPParam = facesContext.getExternalContext().getRequestParameterMap().get("idProduto");
        String idLParam = facesContext.getExternalContext().getRequestParameterMap().get("idLista");

        if (idPParam != null) {
            EntityManager manager = getEntityManager();
            this.produtoLista = new ProdutoListaService(manager).BuscarPorId(Long.valueOf(idPParam));
        } else {
            if (idLParam != null) {
                EntityManager manager = getEntityManager();
                this.lista = new ListaService(manager).BuscarPorId(Long.valueOf(idLParam));
            }
        }
    }

    public String salvar() {
           if (produto.getId()!= null) {
            this.produtoLista.setProduto(produto);
        }

        if (lista.getId() != null) {
            this.produtoLista.setLista(lista);
        }
        
        EntityManager manager = getEntityManager();

        new ProdutoListaService(manager).salvar(produtoLista);

        return "produtoDaLista?faces-redirect=true&idLista=" + produtoLista.getLista().getId();
    }

    public List<ProdutoLista> getListaProdutoLista(Long idLista) {
        EntityManager entityManager = getEntityManager();

        return new ProdutoListaService(entityManager).buscar(idLista);
    }

    public void removerProdutoLista(ProdutoLista produtoLista) {
        EntityManager entityManager = getEntityManager();

        new ProdutoListaService(entityManager).remove(produtoLista);
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
}
