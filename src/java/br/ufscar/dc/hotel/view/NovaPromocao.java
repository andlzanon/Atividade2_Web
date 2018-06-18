/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.view;
import br.ufscar.dc.hotel.beans.Promocao;
import br.ufscar.dc.hotel.dao.HotelDAO;
import br.ufscar.dc.hotel.dao.PromocaoDAO;
import br.ufscar.dc.hotel.dao.SiteDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author victor
 */
@Named
@SessionScoped
public class NovaPromocao implements Serializable{
    
    @Inject
    PromocaoDAO promocaoDAO;
    @Inject
    SiteDAO siteDAO;
    @Inject
    HotelDAO hotelDAO;
    
    Promocao dadosPromocao;
    
    boolean escondeBotao;
    MensagemBootstrap mensagem;
    
    UIInput dinicialInput;
 
    public void validarDatas(FacesContext context, UIComponent toValidate, Date data) {
        simularDemora();
        SimpleDateFormat out = new SimpleDateFormat("yyyy-mm-dd");
        String sDi = out.format(dinicialInput.getValue());
        String sDf = out.format(data);
        Date dinicial = java.sql.Date.valueOf(sDi);
        Date dfinal = java.sql.Date.valueOf(sDf);
        if (dfinal.before(dinicial)) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Data final não pode ser anterior a Data inicial");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
    
    public UIInput getdinicialInput() {
        return dinicialInput;
    }
    
    public void setdinicialInput(UIInput dinicialInput) {
        this.dinicialInput = dinicialInput;
    }
    
    public NovaPromocao(){
        recomecar();
    }
    
    public Promocao getDadosPromocao() {
        return dadosPromocao;
    }

    public void setDadosPromocao(Promocao dadosPromocao) {
        this.dadosPromocao = dadosPromocao;
    }
    
   /* public void procurarUrl(){
        simularDemora();
        try {
            Site siteEncontrado = siteDAO.listaSitePorUrl(dadosPromocao.getUrl().getUrl());
            if (siteEncontrado == null){
                mensagem.setMensagem(true, "Site ainda não cadastrado", MensagemBootstrap.TipoMensagem.TIPO_INFO);
            } else {
                mensagem.setMensagem(false, "Site cadastrado :)", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }*/

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
    
    public void enviarPromocao(AjaxBehaviorEvent event){
        simularDemora();
        mensagem.setMensagem(true, "Verifique os dados e confirme o cadastro da nova promoção",
        MensagemBootstrap.TipoMensagem.TIPO_AVISO);
        escondeBotao = true;
    }
    
    public void confirmarPromocao() throws SQLException, NamingException{
        simularDemora();
        
        try {
            Map<String, Object> sessionCnpj =FacesContext.getCurrentInstance().
                   getExternalContext().getSessionMap();
            dadosPromocao.getCnpj().setCnpj((String)sessionCnpj.get("cnpjHotel"));
            if(!promocaoDAO.verificarPromocaoHotel(dadosPromocao.getCnpj().getCnpj(), new java.sql.Date(dadosPromocao.getData_inicial().getTime()), new java.sql.Date(dadosPromocao.getData_final().getTime()))){
                promocaoDAO.gravarPromocao(dadosPromocao);
            };
            recomecar();
            mensagem.setMensagem(true, "Sua Promoção foi registrada com sucesso!", 
                    MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
        } catch (SQLException ex) {
            escondeBotao = false;
            Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!",
                    MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }
    
    private void recomecar(){
        escondeBotao = false;
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Digite os dados da nova promoção", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        dadosPromocao = new Promocao();
    }
    
    private void simularDemora() {
        // Para testar chamadas AJAX
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
}
