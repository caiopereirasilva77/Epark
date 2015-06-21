/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.dao;

import br.senai.sc.model.Vaga;
import br.senai.sc.util.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Caio Pereira
 */
public class VagaDao {

    private ArrayList<Vaga> listaVaga;
    
    private static VagaDao instanciaRep;
    private ConnectionUtil con;
    
    //SINGLETON//
    public static VagaDao obterInstancia() {
        
        if ( instanciaRep == null ) {
            instanciaRep = new VagaDao();
        }
        return instanciaRep;
    }
    
    public VagaDao() {
       this.listaVaga = new ArrayList<Vaga>();
        con = new ConnectionUtil();
    }

    
    public  void inserir(Vaga vaga){
       try {
            String query = "INSERT INTO VAGA ( NUMERO_VAGA, TIPO_VAGA, TIPO_VALOR, VALOR_UNIT, STATUS ) VALUES ( ?,?,?,?,?)";
            PreparedStatement pst = con.getConnection().prepareStatement(query);
            pst.setInt(1, vaga.getNumeroVaga());
            pst.setString(2,  vaga.getTipoVaga());
            pst.setString(3,  vaga.getTipoValor());
            pst.setDouble(4, vaga.getValorUnit());
            pst.setInt(5, vaga.getStatus());
        //    if (vaga.isStatus() == true ){
        //        pst.setInt(5, 1);
        //    } else {
        //        pst.setInt(5, 0);
        //   }
            pst.execute();
            con.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void atualizarTabelaVagas(Vaga vaga){
        
        try {
            Statement st = con.getConnection().createStatement();
            String query = "SELECT * FROM VAGA";
            ResultSet rs = st.executeQuery(query);
            while( rs.next() ){
                Vaga v = new Vaga();
                v.setId(rs.getInt("id"));
                v.setNumeroVaga(rs.getInt("Numero_vaga"));
                v.setTipoVaga(rs.getString("Tipo_vaga"));
                v.setTipovalor(rs.getString("Tipo_valor"));
                v.setValorUnit(rs.getDouble("Valor_unit"));
                
                this.listaVaga.add( v );
            }
            con.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
     }
    
    public ArrayList<Vaga> listarTodos(){
        this.listaVaga = new ArrayList<Vaga>();
        try {
            Statement st = con.getConnection().createStatement();
            String query = "SELECT * FROM VAGA";
            ResultSet rs = st.executeQuery(query);
            while( rs.next() ){
                Vaga v = new Vaga();
                v.setId (rs.getInt("id"));
                v.setNumeroVaga(rs.getInt("Numero_vaga"));
                v.setTipoVaga(rs.getString("Tipo_vaga"));
                v.setTipovalor(rs.getString("Tipo_valor"));
                v.setValorUnit(rs.getDouble("Valor_unit"));
                v.setStatus(rs.getInt("Status"));
              //  if ( rs.getInt("Status") == 1) {
              //      v.setStatus(true);
              //  } else {
              //      v.setStatus(false);
              //  }
                
               // alterar o status para integer
                this.listaVaga.add( v );
            }
            con.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return this.listaVaga;
    }
    
    public ArrayList<Vaga> listarVagasLivresAvulso(){
        this.listaVaga = new ArrayList<Vaga>();
        try {
            Statement st = con.getConnection().createStatement();
         
            String query = "SELECT * FROM VAGA WHERE STATUS=0 and TIPO_VAGA='Avulso'";
                    //+vaga.getNumeroVaga()+"'";
             
            ResultSet rs = st.executeQuery(query);
            while( rs.next() ){
                Vaga v = new Vaga();
                v.setId (rs.getInt("id"));
                v.setNumeroVaga(rs.getInt("Numero_vaga"));
                v.setTipoVaga(rs.getString("Tipo_vaga"));
                v.setTipovalor(rs.getString("Tipo_valor"));
                v.setValorUnit(rs.getDouble("Valor_unit"));
                v.setStatus(rs.getInt("Status"));
           
                this.listaVaga.add( v );
            }
            con.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return this.listaVaga;
    }
    
    public ArrayList<Vaga> listarVagasLivresMensalista(){
        this.listaVaga = new ArrayList<Vaga>();
        try {
            Statement st = con.getConnection().createStatement();
         
            String query = "SELECT * FROM VAGA WHERE STATUS=0 and TIPO_VAGA='Mensalista'";
                    //+vaga.getNumeroVaga()+"'";
             
            ResultSet rs = st.executeQuery(query);
            while( rs.next() ){
                Vaga v = new Vaga();
                v.setId (rs.getInt("id"));
                v.setNumeroVaga(rs.getInt("Numero_vaga"));
                v.setTipoVaga(rs.getString("Tipo_vaga"));
                v.setTipovalor(rs.getString("Tipo_valor"));
                v.setValorUnit(rs.getDouble("Valor_unit"));
                v.setStatus(rs.getInt("Status"));
           
                this.listaVaga.add( v );
            }
            con.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return this.listaVaga;
    }    
    
     public void alterarStatus(Vaga vaga){
     try{
    
     String queryVaga = "UPDATE VAGA SET STATUS=? WHERE ID=?";
            PreparedStatement st = con.getConnection().prepareStatement(queryVaga);
            st.setInt(1,vaga.getId());
           // st.setInt(2,vaga.getStatus());
          //  if ( vaga.getStatus() == 0) {
                    vaga.setStatus(1);
            
            st.executeUpdate();
    }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
     public Vaga vagagetId(Integer id){
        Vaga v = new Vaga();
        try {
            Statement st = con.getConnection().createStatement();
            String query = "SELECT * FROM VAGA WHERE ID="+id;
            ResultSet rs = st.executeQuery(query);
            rs.next();
                
            v.setId(rs.getInt("id"));
            v.setNumeroVaga(rs.getInt("Numero_vaga"));
            v.setTipoVaga(rs.getString("Tipo_vaga"));
            v.setTipovalor(rs.getString("Tipo_valor"));
            v.setValorUnit(rs.getDouble("Valor_Unit"));
            
            System.out.println(rs);    
            
            con.closeConnection(); } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return v;
    }
    
    
    public void alterar (Vaga vaga){
        
         try{
            String query = "UPDATE VAGA SET NUMERO_VAGA=?, TIPO_VAGA=?, TIPO_VALOR=?, VALOR_UNIT=? WHERE ID=?";
            PreparedStatement st = con.getConnection().prepareStatement(query);
            st.setInt(1,vaga.getNumeroVaga());
            st.setString(2,vaga.getTipoVaga());
            st.setString(3,vaga.getTipoValor());
            st.setDouble(4,vaga.getValorUnit());
            st.setInt(5,vaga.getId());
            st.executeUpdate();
            con.closeConnection();
            
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
     
   public void remover(Vaga vaga) throws Exception{
          try{
            String query = "DELETE FROM VAGA WHERE ID=?";
            PreparedStatement st = con.getConnection().prepareStatement(query);
            st.setInt(1,vaga.getId());
            
            st.executeUpdate();
            con.closeConnection();
            
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }   
    
    public Integer verificaExistencia(Vaga vaga){
        for ( int i=0; i < this.listaVaga.size(); i++){
            if ( this.listaVaga.get(i).equals(vaga) ) {
                return i;
            }
        }
        return null;
    }
}
    

