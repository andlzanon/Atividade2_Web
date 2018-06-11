/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author victo
 */
public class Promocao implements Serializable{

    private int id;
    private Site url;
    private Hotel cnpj;
    private Double preco;
    private Date data_inicial;
    private Date data_final;
    
    public Promocao(){
        url = new Site();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Site getUrl() {
        return url;
    }

    public void setUrl(Site url) {
        this.url = url;
    }

    public Hotel getCnpj() {
        return cnpj;
    }

    public void setCnpj(Hotel cnpj) {
        this.cnpj = cnpj;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }
}
