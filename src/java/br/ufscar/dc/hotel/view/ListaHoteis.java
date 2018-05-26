/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.view;

import br.ufscar.dc.hotel.beans.Hotel;
import br.ufscar.dc.hotel.dao.HotelDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author Andre
 */
@Named
@ViewScoped
public class ListaHoteis implements Serializable{
    
    @Inject 
    HotelDAO hotelDAO;
    
    private List<Hotel> hoteis;
    private List<Hotel> hoteisFiltrados;
    private List<String> cnpjs;
    private List<String> cidades;

    @PostConstruct
    public void init(){
        try {
            hoteis = hotelDAO.listarTodosHoteis();
            cnpjs = hotelDAO.listarCNPJs();
            cidades = hotelDAO.listarCidades();
        } catch (SQLException ex) {
            Logger.getLogger(ListaHoteis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Hotel> getHoteis() {
        return hoteis;
    }

    public List<Hotel> getHoteisFiltrados() {
        return hoteisFiltrados;
    }

    public List<String> getCnpjs() {
        return cnpjs;
    }

    public List<String> getCidades() {
        return cidades;
    }

    public void setHoteisFiltrados(List<Hotel> hoteisFiltrados) {
        this.hoteisFiltrados = hoteisFiltrados;
    }
    
}
