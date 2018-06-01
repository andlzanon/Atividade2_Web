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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Andre
 */
@Named
@SessionScoped
public class NovoHotel implements Serializable{
       
    @Inject
    HotelDAO hotelDAO;
    
    Hotel dadosHotel;
    
    boolean escondeBotao;
    MensagemBootstrap mensagem;
    String senhaConfirmada;
    
    public NovoHotel(){
        recomecar();
    }

    public String getSenhaConfirmada() {
        return senhaConfirmada;
    }

    public void setSenhaConfirmada(String senhaConfirmada) {
        this.senhaConfirmada = senhaConfirmada;
    }

    public Hotel getDadosHotel() {
        return dadosHotel;
    }

    
    public void setDadosHotel(Hotel dadosHotel) {
        this.dadosHotel = dadosHotel;
    }

    public boolean isEscondeBotao() {
        return escondeBotao;
    }

    public void setEscondeBotao(boolean escondeBotao) {
        this.escondeBotao = escondeBotao;
    }

    public MensagemBootstrap getMensagem() {
        return mensagem;
    }

    public void setMensagem(MensagemBootstrap mensagem) {
        this.mensagem = mensagem;
    }
    
    public boolean conferirSenha(){
        if(getSenhaConfirmada().equals(dadosHotel.getSenha())){
            return true;
        }
        else{
            mensagem.setMensagem(true, "Senha digita é diferente da conferida!",
            MensagemBootstrap.TipoMensagem.TIPO_ERRO);
            return false;
        }
    }
    
    public void enviarHotel(AjaxBehaviorEvent event){
        simularDemora();
        if(conferirSenha()){
            mensagem.setMensagem(true, "Verifique os dados e confirme o cadastro do novo hotel",
            MensagemBootstrap.TipoMensagem.TIPO_AVISO);
            escondeBotao = true;
        }
    }
    
    public void confirmarHotel(){
        simularDemora();
        
        try {
            hotelDAO.gravarHotel(dadosHotel);
            recomecar();
            mensagem.setMensagem(true, "Seu hotel foi registrado com sucesso!", 
                    MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
        } catch (SQLException ex) {
            escondeBotao = false;
            Logger.getLogger(NovoHotel.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!",
                    MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }
    
    private void recomecar(){
        escondeBotao = false;
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Digite os dados do novo hotel", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        dadosHotel = new Hotel();
        senhaConfirmada = null;
    }
    
    private void simularDemora() {
        // Para testar chamadas AJAX
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(NovoHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
}
