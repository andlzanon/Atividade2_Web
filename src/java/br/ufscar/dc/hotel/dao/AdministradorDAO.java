/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.dao;

import br.ufscar.dc.hotel.beans.Administrador;
import br.ufscar.dc.hotel.beans.Hotel;
import br.ufscar.dc.hotel.beans.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Andre
 */
@RequestScoped
public class AdministradorDAO {
    
    private final static String CADASTRAR_ADM_SQL = 
            "insert into Administrador(usuario, senha) values(?,?)";
    
    private final static String ADM_NOME_SQL = 
            "select usuario, senha from Administrador where usuario = ?";
    
    @Resource(name = "jdbc/HotelDBLocal")
    private DataSource dataSource;
    
    public Administrador gravarAdm(Administrador a) throws SQLException, NamingException{
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CADASTRAR_ADM_SQL);) {
            ps.setString(1, a.getUsuario());
            ps.setString(2, a.getSenha());
            ps.execute();
        }
        
        return a;
    }
    
    public Administrador listarAdmPorNome(String usuario) throws SQLException, NamingException{
        Administrador ret = null;
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(ADM_NOME_SQL)){
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret = new Administrador();
                    ret.setUsuario(rs.getString("usuario"));
                    ret.setSenha(rs.getString("senha"));
                }
            }
        }
        return ret;
    }
}
