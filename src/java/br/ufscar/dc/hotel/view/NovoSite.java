/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.view;

import br.ufscar.dc.hotel.beans.Site;
import br.ufscar.dc.hotel.dao.SiteDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;


@Named
@SessionScoped
public class NovoSite implements Serializable{
    
    @Inject
    SiteDAO siteDAO;
    
    Site dadosSite;
    
    boolean escondeBotao;
    MensagemBootstrap mensagem;
    String senhaConfirmada;
    
    public NovoSite(){
        recomecar();
    }

    public String getSenhaConfirmada() {
        return senhaConfirmada;
    }

    public void setSenhaConfirmada(String senhaConfirmada) {
        this.senhaConfirmada = senhaConfirmada;
    }

    public Site getDadosSite() {
        return dadosSite;
    }

    
    public void setDadosSite(Site dadosSite) {
        this.dadosSite = dadosSite;
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
        if(getSenhaConfirmada().equals(dadosSite.getSenha())){
            return true;
        }
        else{
            mensagem.setMensagem(true, "Senha digitada é diferente da conferida!",
            MensagemBootstrap.TipoMensagem.TIPO_ERRO);
            return false;
        }
    }
    
    public void enviarSite(AjaxBehaviorEvent event){
        simularDemora();
        if(conferirSenha()){
            mensagem.setMensagem(true, "Verifique os dados e confirme o cadastro do novo site",
            MensagemBootstrap.TipoMensagem.TIPO_AVISO);
            escondeBotao = true;
        }
    }
    
    public void confirmarSite() throws NamingException{
        simularDemora();
        
        try {
            siteDAO.gravarSite(dadosSite);
            recomecar();
            mensagem.setMensagem(true, "Seu site foi registrado com sucesso!", 
                    MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
        } catch (SQLException ex) {
            escondeBotao = false;
            Logger.getLogger(NovoSite.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!",
                    MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }
    
    private void recomecar(){
        escondeBotao = false;
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Digite os dados do novo site", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        dadosSite = new Site();
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
