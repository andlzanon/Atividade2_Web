/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.view;

import br.ufscar.dc.hotel.beans.Administrador;
import br.ufscar.dc.hotel.beans.Hotel;
import br.ufscar.dc.hotel.beans.Site;
import br.ufscar.dc.hotel.dao.AdministradorDAO;
import br.ufscar.dc.hotel.dao.HotelDAO;
import br.ufscar.dc.hotel.dao.SiteDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Andre
 */
@Named
@SessionScoped
public class NavigationController implements Serializable{
    
    @Inject
    AdministradorDAO administradorDAO;
    
    @Inject
    HotelDAO hotelDAO;
   
    @Inject
    SiteDAO siteDAO;
   
    String usuario;
    String senha;
    
    MensagemBootstrap mensagem;
    
    public NavigationController(){
        recomecar();
    }
    
    public MensagemBootstrap getMensagem() {
        return mensagem;
    }

    public void setMensagem(MensagemBootstrap mensagemBootstrap) {
        this.mensagem = mensagemBootstrap;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String direcionadorDeNavegacao(){
        try {
            //problema na classe adm ou admDAO
            Administrador administrador = administradorDAO.listarAdmPorNome(getUsuario());
            Hotel hotel = hotelDAO.listarHotelPorNome(getUsuario());
            Site site = siteDAO.listaSitePorUrl(getUsuario());
            
            System.out.println(getUsuario());
            if(administrador != null && administrador.getSenha().equals(getSenha())){
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionToken", "sessionAdm");
                return "menuAdm?faces-redirect=true&user="+getUsuario();
            }else if(hotel != null && hotel.getSenha().equals(getSenha())){
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionToken", "sessionHotel");
                return "menuHotel?faces-redirect=true&user="+hotel.getNome();
            }else if(site != null && site.getSenha().equals(getSenha())){
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionToken", "sessionSite");
                return "menuSite?faces-redirect=true&user="+site.getUrl();
            }
            else{
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                mensagem.setMensagem(true, "Usuário ou senha inválidos",
                MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "erro?faces-redirect=true";
        }
    }
    
    private void recomecar(){
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Para realizar o login digite seu usuário e senha",
        MensagemBootstrap.TipoMensagem.TIPO_INFO);
    }
}
    
