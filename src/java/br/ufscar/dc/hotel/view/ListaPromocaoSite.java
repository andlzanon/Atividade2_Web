/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.view;

import br.ufscar.dc.hotel.beans.Promocao;
import br.ufscar.dc.hotel.dao.PromocaoDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author victo
 */
@Named
@ViewScoped
public class ListaPromocaoSite implements Serializable{
    
    @Inject 
    PromocaoDAO promocaoDAO;
    
    private List<Promocao> promocoes;
    List<Promocao> promocaoFiltrados;
    private String url;


    @PostConstruct
    public void init(){
        try {
             Map<String, Object> sessionUrl =FacesContext.getCurrentInstance().
                   getExternalContext().getSessionMap();
            url = (String)sessionUrl.get("urlSite");
            promocoes = promocaoDAO.listarPromocaoSite(url);
        } catch (SQLException ex) {
            Logger.getLogger(ListaHoteis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Promocao> getPromocaoSite() {
        return promocoes;
    }
    
    public void setPromocaoSite(List<Promocao> promocoes) {
        this.promocoes = promocoes;
    }
    
        public List<Promocao> getPromocaoFiltrados() {
        return promocaoFiltrados;
    }

    public void setPromocaoFiltrados(List<Promocao> promocaoFiltrados) {
        this.promocaoFiltrados = promocaoFiltrados;
    }
}
