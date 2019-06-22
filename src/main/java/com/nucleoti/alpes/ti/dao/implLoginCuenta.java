package com.nucleoti.alpes.ti.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.nucleoti.alpes.ti.constantes.UtilsConsultas;
import com.nucleoti.alpes.ti.model.Cliente;
import com.nucleoti.alpes.ti.model.Cuenta;
import com.nucleoti.alpes.ti.model.ProductBean;
import com.nucleoti.alpes.ti.model.ResponCod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class implLoginCuenta implements LoginCuenta{
	@Autowired
	private DataSource dataSource;	
	Connection con=null;
	@Override
	public ResponCod AccesoUser(Cuenta cuenta) {
		ResponCod cod=null;
		try {
			cod=new ResponCod();
			con=dataSource.getConnection();
			CallableStatement cStmt = con.prepareCall(UtilsConsultas.CALL_LIST_PRODUCT);
			cStmt.setString(1, cuenta.getUsuario());
			cStmt.registerOutParameter(3, java.sql.Types.DOUBLE);
			cStmt.registerOutParameter(4, java.sql.Types.VARCHAR);			
			cStmt.execute();  
			int indice= cStmt.getInt(3);
			String mensaje=cStmt.getString(4);
			System.out.println("resultado: "+String.valueOf(indice)+"mens:"+mensaje);
			switch(indice){
			case 0:
				cod.setId(String.valueOf(indice));
				cod.setMensaje(mensaje);
				break;
			case 1:
				cod.setId(String.valueOf(indice));
				cod.setMensaje(mensaje);
				break;
				
			case 2:
				cod.setId(String.valueOf(indice));
				cod.setMensaje(mensaje);
				break;
				
					}
		
		}catch(Exception e){
			cod=new ResponCod();
			cod.setId("0");
			cod.setMensaje("Error!!!");
		}

		return cod;
	}

	@Override
	public ResponCod CreateCliente(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		ResponCod cod=null;

			cod=new ResponCod();
			con=dataSource.getConnection();
			try{
				CallableStatement cStmt = con.prepareCall(UtilsConsultas.CALL_CREATE_CUENTA);  
				cStmt.setString(1,cliente.getUsrCuenta());
				cStmt.setString(2,cliente.getPassword());
				cStmt.setString(3,cliente.getName());
				cStmt.setString(4,cliente.getApPaterno());
				cStmt.setString(5, cliente.getApPaterno());
				cStmt.registerOutParameter(6, java.sql.Types.INTEGER);	
				cStmt.registerOutParameter(7, java.sql.Types.VARCHAR);	
				cStmt.execute();
				cod.setId(String.valueOf(cStmt.getInt(6)));
				cod.setMensaje(cStmt.getString(7));
				System.out.println("codigo-->"+cod);
			}catch(Exception e){
				cod.setId("3");
				cod.setMensaje(e.getMessage());
			}
		
		return cod;
	}
	@Override
	public int ExistenciaEmail(String email) throws Exception {
		int cod=0;
		con=dataSource.getConnection();
		try{
			CallableStatement cStmt = con.prepareCall(UtilsConsultas.CALL_EXISTENCIA_EMAIL);  
			cStmt.setString(1,email);
			cStmt.registerOutParameter(2, java.sql.Types.INTEGER);		
			cStmt.execute();
			cod=cStmt.getInt(2);
			System.out.println("codigo-->"+cod);
		}catch(Exception e){
			
		}
		return cod;
	}

	@Override
	public List<ProductBean> listProduct(int codEmpresa) {
		List<ProductBean> list=new ArrayList();
		try {
			con=dataSource.getConnection();
			CallableStatement st= con.prepareCall(UtilsConsultas.CALL_LIST_PRODUCT);
			st.setInt(1,codEmpresa);
			ResultSet rs=st.executeQuery();
			ProductBean bean;
			while (rs.next()){
				bean=new ProductBean();
				bean.setCodProducto(rs.getInt(1));
				bean.setDescription(rs.getString(2));
				list.add(bean);
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductBean listProductById(int codCompany,int codProduct) {
		ProductBean bean=null;
		try {
			con=dataSource.getConnection();
			CallableStatement st=con.prepareCall(UtilsConsultas.CALL_LIST_PRODUCT_BY_ID);
			st.setInt(1,codCompany);
			st.setInt(2,codProduct);
			ResultSet rs=st.executeQuery();
			while (rs.next()){
				bean=new ProductBean();
				bean.setCodProducto(rs.getInt(1));
				bean.setDescription(rs.getString(2));
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return bean;
	}

}
