package lhs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import lhs.oracle.uitl.oracleJDBC;

public class userDao {
	
	public int  updataflagport(int port,int id){
		String sql1=" update chatuser set port="+port+" ,flag=1 where userid='"+id+"'";
		 Connection conn;
		 conn=oracleJDBC.getcon();
		 Statement stm=null;
		 try {
			stm=conn.createStatement();
		int i=0;
		i=stm.executeUpdate(sql1);
		if(i!=0){
			return 1;
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		return 0;
	}

}
