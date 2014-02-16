package br.com.controller;

import br.com.entities.Produto;
import br.com.service.ProdutoService;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import sun.misc.IOUtils;

/**
 *
 * @author Fernando
 */
@ManagedBean
public class ProdutoController {

    private Produto produto = new Produto();

    @ManagedProperty("#{param.idProduto}")
    private Long idProduto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }

    public List<Produto> getListaProduto() {
        EntityManager entityManager = getEntityManager();

        return new ProdutoService(entityManager).BuscarTodos();
    }

    public String salvar() {
        EntityManager manager = getEntityManager();
        new ProdutoService(manager).salvar(produto);

        return "listaProduto?faces-redirect=true";
    }

    @PostConstruct
    public void atualiza() {
        if (idProduto != null) {
            EntityManager manager = getEntityManager();
            this.produto = new ProdutoService(manager).buscarPorId(idProduto);
        }
    }

    public void removerProduto(Produto produto) {
        EntityManager entityManager = getEntityManager();

        new ProdutoService(entityManager).remover(produto);
    }
}
