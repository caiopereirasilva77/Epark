package br.senai.sc.dao;

import java.util.List;

import javax.persistence.EntityManager;


import br.senai.sc.model.Cliente;
import br.senai.sc.util.JpaUtil;

/**
 *
 * @author Caio Pereira
 */
public class ClienteDao extends BaseDAO {

	private static ClienteDao instanciaRep;
	EntityManager em = JpaUtil.getEntityManager();

	// //SINGLETON//
	public static ClienteDao obterInstancia() {
		if (instanciaRep == null) {
			instanciaRep = new ClienteDao();
		}
		return instanciaRep;
	}

	public void insere(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}

	public List<Cliente> buscarTodos() {
		return em.createQuery("select d from Cliente d").getResultList();
	}

	public void atualizar(Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
	}

	public Cliente clientegetId(Integer id) {
		return (Cliente) em.find(Cliente.class, id);
	}

	public Cliente burcarPorPlaca(String placa) {
		return (Cliente) em
				.createQuery("select c from Cliente c where c.placa= :placa")
				.setParameter("placa", placa).getSingleResult();
	}

	public void excluir(Cliente cliente) throws Exception {
		cliente = em.find(Cliente.class, cliente.getId());
		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
	}

	public Cliente consultaExistenciaCliente(Cliente cliente) {
		return (Cliente) em
				.createQuery("select c from Cliente c where c.placa= :placa")
				.setParameter("placa", cliente.getPlaca()).getSingleResult();
	}
	    
    //SINGLETON//
//    public static ClienteDao obterInstancia() {
//        
//        if ( instanciaRep == null ) {
//            instanciaRep = new ClienteDao();
//        }
//        return instanciaRep;
//    }
//    
//    public ClienteDao() {
//       this.listaCliente = new ArrayList<Cliente>();
//        con = new ConnectionUtil();
//    }
//    
//    public  void inserir(Cliente cliente){
//       try {
//            String query = "INSERT INTO CLIENTE ( NOME, CPF, PLACA, MODELO, TIPOCLIENTE ) VALUES ( ?,?,?,?,?)";
//            PreparedStatement pst = con.getConnection().prepareStatement(query);
//            //pst.setString(5, cliente.getTipocliente());
//        //      if (vaga.isStatus() == true ){
//        //        pst.setInt(5, 1);
//        //    } else {
//        //        pst.setInt(5, 0);
//           
//            pst.setString(1, cliente.getNome());
//            pst.setString(2, cliente.getCpf());
//            pst.setString(3, cliente.getPlaca());
//            pst.setString(4, cliente.getModelo());
//            pst.setString(5, cliente.getTipocliente());
//            pst.execute();
//            con.closeConnection();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//     public void atualizarTabelaClientes(Cliente cliente){
//        
//        try {
//            Statement st = con.getConnection().createStatement();
//            String query = "SELECT * FROM CLIENTE";
//            ResultSet rs = st.executeQuery(query);
//            while( rs.next() ){
//                Cliente c = new Cliente();
//                c.setId (rs.getInt("id"));
//                c.setCartaopark(rs.getInt("NumeroCartão"));
//                c.setNome(rs.getString("nome"));
//                c.setCpf(rs.getString("cpf"));
//                c.setPlaca(rs.getString("Placa"));
//                c.setModelo(rs.getString("Modelo"));
//                
//                this.listaCliente.add( c );
//            }
//            con.closeConnection();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }  
//         
//         
//     }
//            
//    public List<Cliente> listarTodos(){
//         this.listaCliente = new ArrayList<Cliente>();
//        try {
//            Statement st = con.getConnection().createStatement();
//            String query = "SELECT * FROM CLIENTE";
//            ResultSet rs = st.executeQuery(query);
//            while( rs.next() ){
//                Cliente c = new Cliente();
//                c.setId (rs.getInt("id"));
//                c.setCartaopark(rs.getInt("Numerocartao"));
//                c.setNome(rs.getString("nome"));
//                c.setCpf(rs.getString("cpf"));
//                c.setPlaca(rs.getString("Placa"));
//                c.setModelo(rs.getString("modelo"));
//                c.setTipocliente(rs.getString("Tipocliente"));
//                
//                this.listaCliente.add( c );
//            }
//            con.closeConnection();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        
//        return this.listaCliente;
//    }
//    
//    
//    public void alterar (Cliente cliente){
//        try{
//                           
//            String query = "UPDATE CLIENTE SET NOME=?, CPF=?, PLACA=?, MODELO=?, TIPOCLIENTE=?  WHERE ID=?";
//            PreparedStatement st = con.getConnection().prepareStatement(query);
//            st.setString(1,cliente.getNome());
//            st.setString(2,cliente.getCpf());
//            st.setString(3,cliente.getPlaca());
//            st.setString(4,cliente.getModelo());
//            st.setString(5,cliente.getTipocliente());
//            st.setInt(6,cliente.getId());
//            st.executeUpdate();
//            System.out.println(st);
//            
//           
//            con.closeConnection();
//            
//        }catch (SQLException ex){
//            ex.printStackTrace();
//        }
//    }
//    
//   public void excluir(Cliente cliente) throws Exception{
//          try{
//            String query = "DELETE FROM CLIENTE WHERE ID=?";
//            PreparedStatement st = con.getConnection().prepareStatement(query);
//            st.setInt(1,cliente.getId());
//            
//            st.executeUpdate();
//            con.closeConnection();
//            
//        }catch (SQLException ex){
//            ex.printStackTrace();
//        }
//    }   
//    
//    public Integer verificaExistencia(Cliente cliente){
//        for ( int i=0; i < this.listaCliente.size(); i++){
//            if ( this.listaCliente.get(i).equals(cliente) ) {
//                return i;
//            }
//        }
//        return null;
//    }
//    public Cliente clientegetId(Integer id){
//            Cliente c = new Cliente();
//        try {
//            Statement st = con.getConnection().createStatement();
//            String query = "SELECT * FROM CLIENTE WHERE ID="+id;
//            ResultSet rs = st.executeQuery(query);
//            rs.next();
//                
//            c.setId(rs.getInt("id"));
//            c.setCartaopark(rs.getInt("Numerocartao"));
//            c.setNome(rs.getString("nome"));
//            c.setCpf(rs.getString("cpf"));
//            c.setPlaca(rs.getString("Placa"));
//            c.setModelo(rs.getString("modelo"));
//            c.setTipocliente(rs.getString("Tipocliente"));
//            System.out.println(rs);    
//            
//            con.closeConnection();
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        
//        return c;
//    }
//    
//    public Cliente clientegetPlaca(String placa){
//        Cliente c = new Cliente();
//        try {
//            Statement st = con.getConnection().createStatement();
//            String query = "SELECT * FROM CLIENTE WHERE Placa='"+placa+"'" ;
//            ResultSet rs = st.executeQuery(query);
//            rs.next();
//            
//            c.setId (rs.getInt("id"));
//            c.setCartaopark(rs.getInt("Numerocartao"));
//            c.setNome(rs.getString("nome"));
//            c.setCpf(rs.getString("cpf"));
//            c.setPlaca(rs.getString("Placa"));
//            c.setModelo(rs.getString("modelo"));
//            c.setTipocliente(rs.getString("Tipocliente"));
//                
//            con.closeConnection();
//        } catch (SQLException ex) {
//            //ex.getErrorCode();
//            //ex.printStackTrace();
//        }
//        
//        return c;
//        
//    }
//
//    public Cliente consultaExistenciaCliente(Cliente cliente) {
//        Cliente c = new Cliente();
//        try{
//            Statement st = con.getConnection().createStatement();
//            String query = "SELECT * FROM CLIENTE WHERE PLACA=";
//            ResultSet rs = st.executeQuery(query);
//            rs.next();
//            
//            c.setId (rs.getInt("id"));
//            c.setCartaopark(rs.getInt("Numerocartao"));
//            c.setNome(rs.getString("nome"));
//            c.setCpf(rs.getString("cpf"));
//            c.setPlaca(rs.getString("Placa"));
//            c.setModelo(rs.getString("modelo"));
//            c.setTipocliente(rs.getString("Tipocliente"));
//            System.out.println(rs);
//            
//            
//           con.closeConnection();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return c;
//         
//        }
//       
        
}
