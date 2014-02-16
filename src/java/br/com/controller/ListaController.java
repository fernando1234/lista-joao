package br.com.controller;

import br.com.entities.Lista;
import br.com.service.ListaService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fernando
 */
@ManagedBean
public class ListaController {

    private Lista lista = new Lista();

    @ManagedProperty("#{param.idLista}")
    private Long idLista;

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }

    public List<Lista> getBuscarTodas() {
        EntityManager entityManager = getEntityManager();
        return new ListaService(entityManager).BuscarTodas();
    }

    public String salvar() {
        EntityManager manager = getEntityManager();
        new ListaService(manager).salvar(lista);

        return "index?faces-redirect=true";
    }

    @PostConstruct
    public void atualiza() {
        if (idLista != null) {
            EntityManager manager = getEntityManager();
            this.lista = new ListaService(manager).BuscarPorId(idLista);
        }
    }

    public void removerLista(Lista lista) {
        EntityManager entityManager = getEntityManager();
        new ListaService(entityManager).remover(lista);
    }

    public Double getValorTotal(Long idLista) {
        EntityManager entityManager = getEntityManager();
        return new ListaService(entityManager).valorTotal(idLista);
    }
}
