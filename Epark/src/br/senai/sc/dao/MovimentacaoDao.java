/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.dao;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;

import br.senai.sc.model.Movimentacao;
import br.senai.sc.util.JpaUtil;

/**
 *
 * @author Caio Pereira
 */
public class MovimentacaoDao extends BaseDAO {

	private static MovimentacaoDao instanciaRep;

	public static MovimentacaoDao obterInstancia() {
		if (instanciaRep == null) {
			instanciaRep = new MovimentacaoDao();
		}
		return instanciaRep;
	}


	public MovimentacaoDao() {

	}

	EntityManager em = JpaUtil.getEntityManager();

	public void insere(Movimentacao movimentacao) {
		em.getTransaction().begin();
		em.persist(movimentacao);
		em.getTransaction().commit();
	}

	public void inserirSaida(Movimentacao movimentacao) {
		em.getTransaction().begin();
		em.merge(movimentacao);
		em.getTransaction().commit();
	}
	
	public Movimentacao verStatusCliente(int id){
		return (Movimentacao) em.createQuery("select m from Movimentacao m where m.cliente.id = :id order by m.id desc limit 1").setParameter("id", id).getSingleResult();
	}
	
	public List<Movimentacao> listarTodos() {
		return em.createQuery("select m from Movimentacao m").getResultList();
	}

	public List<Movimentacao> relacionarmovimentacaocliente(Integer idcliente) throws ParseException {
		return em.createQuery("select mov from Movimentacao mov where mov.cliente.id = :idCliente "
								+ "and dtentrada is not null and dtsaida is null ")
				.setParameter("idCliente", idcliente).getResultList();
	}
	
	public Movimentacao buscar(int id) {
		return em.find(Movimentacao.class, id);
	}

	public Movimentacao buscarPorPlaca(String placa){
		return (Movimentacao) em
				.createQuery(
						"select m from movimentacao m "
								+ "where m.ciente.placa = :placa and m.dtsaida is null")
				.setParameter("placa", placa).getSingleResult();
	}
}


// }
// }}

// public void inserir(Movimentacao movimentacao){
// try {
// //Alocar vaga
// String query1 = "UPDATE VAGA SET STATUS=1 WHERE ID=?";
// PreparedStatement pst1 = con.getConnection().prepareStatement(query1);
// pst1.setInt(1, movimentacao.getVaga().getId());
// pst1.execute();
// con.closeConnection();
//
// //Inserir movimentação
// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
// String dataEntrada = sdf.format(movimentacao.getDataentrada());
// String query2 =
// "INSERT INTO MOVIMENTACAO (VAGA_ID ,CLIENTE_ID, DTENTRADA ) VALUES ( ?,?,? )";
// PreparedStatement pst2 = con.getConnection().prepareStatement(query2);
// pst2.setInt(1, movimentacao.getVaga().getId());
// pst2.setInt(2, movimentacao.getCliente().getId());
// pst2.setString(3, dataEntrada);
// pst2.execute();
//
// con.closeConnection();
// } catch (SQLException ex) {
// ex.printStackTrace();
// }
// }
// public void inserirSaida(Movimentacao movimentacao){
// try {
// //Desalocar vaga
// String query1 = "UPDATE VAGA SET STATUS=0 WHERE ID=?";
// PreparedStatement pst1 = con.getConnection().prepareStatement(query1);
// pst1.setInt(1, movimentacao.getVaga().getId());
// pst1.execute();
// con.closeConnection();
//
// //Calcular número de horas e valor se for avulso
// Date dataSaidaDate = new Date();
// double valor = movimentacao.calcularSaida(dataSaidaDate);
//
// //Desetacionar
// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
// String dataSaida = sdf.format(dataSaidaDate);
// String query =
// "UPDATE MOVIMENTACAO SET DTSAIDA=?,vlrtotal=? WHERE IDMOVIMENTACAO=?";
// PreparedStatement pst = con.getConnection().prepareStatement(query);
// pst.setString(1, dataSaida);
// pst.setDouble(2, valor);
// pst.setInt(3, movimentacao.getId());
// pst.executeUpdate();
// con.closeConnection();
// } catch (SQLException ex) {
// ex.printStackTrace();
// }
// }
//
// //implementar movimentacao para resgatar se o cliente existe
//
// public Movimentacao movimentacaoget(Integer id) throws ParseException{
// Movimentacao m = new Movimentacao();
// try {
// Statement st = con.getConnection().createStatement();
// String query = "SELECT * FROM MOVIMENTACAO WHERE ID="+id;
// ResultSet rs = st.executeQuery(query);
// rs.next();
//
// m.setId (rs.getInt("idmovimentacao"));
// m.setPlaca(rs.getString("placa"));
// m.setTipocliente(rs.getString("Tipocliente"));
// m.setDataentrada((new
// SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).parse(rs.getString("dtentrada")));
// m.setDatasaida((new
// SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).parse(rs.getString("dtsaida")));
// m.setCliente(new Cliente(rs.getInt("cliente_id")));
// m.setVaga(new Vaga(rs.getInt("vaga_id")));
// m.setVlrtotal(rs.getDouble("vlrtotal"));
// System.out.println(m);
//
// con.closeConnection();
// } catch (SQLException ex) {
// ex.printStackTrace();
// }
//
// return m;
// }
//
// public Movimentacao movimentacaoSaidaGet(String placa) throws ParseException{
// Movimentacao m = new Movimentacao();
// try {
// Statement st = con.getConnection().createStatement();
// String query = "SELECT * FROM MOVIMENTACAO m " +
// "inner join CLIENTE c on m.cliente_id=c.id " +
// "WHERE Placa='"+placa+"' and dtsaida is null";
//
// ResultSet rs = st.executeQuery(query);
// rs.next();
//
// m.setId (rs.getInt("idmovimentacao"));
// m.setPlaca(rs.getString("placa"));
// m.setTipocliente(rs.getString("Tipocliente"));
// m.setDataentrada((new
// SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).parse(rs.getString("dtentrada")));
// if(rs.getString("dtsaida") != null){
// m.setDatasaida((new
// SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).parse(rs.getString("dtsaida")));
// }
// m.setVaga(new Vaga(rs.getInt("vaga_id")));
// m.setCliente(new Cliente(rs.getInt("cliente_id")));
// m.setVlrtotal(rs.getDouble("vlrtotal"));
// System.out.println(m);
//
// con.closeConnection();
// } catch (SQLException ex) {
// ex.printStackTrace();
// }
//
// return m;
// }
//
// public ArrayList<Movimentacao> relacionarmovimentacaocliente(Integer
// idcliente) throws ParseException{
// listaMovimentacao.clear();
//
// try {
// Statement st = con.getConnection().createStatement();
// String query =
// "SELECT * FROM MOVIMENTACAO WHERE CLIENTE_ID="+idcliente+" and dtentrada is not null and dtsaida is null ";
// ResultSet rs = st.executeQuery(query);
//
// while( rs.next() ){
// Movimentacao m = new Movimentacao();
// m.setId (rs.getInt("idmovimentacao"));
// m.setCliente(new Cliente(rs.getInt("cliente_id")));
// m.setVlrtotal(rs.getDouble("vlrtotal"));
// listaMovimentacao.add(m);
// }
//
// con.closeConnection();
// } catch (SQLException ex) {
// ex.printStackTrace();
// }
//
// return listaMovimentacao;
// }

