package dbcrew.highergrounds.web;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.core.lookup.MainMapLookup;

public class DBConnectionFactory {
	
	SqlSessionFactory sqlSessionFactory;
	
	public DBConnectionFactory(){
		
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	
	public void testConnection(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  Order order = session.selectOne("OrderMapper.selectOrder", 10248);
		  
		  System.out.println(order);
		} finally {
		  session.close();
		}
	}
	
	public static void main(String[] args) {
		DBConnectionFactory dbFactory = new DBConnectionFactory();
		dbFactory.testConnection();
	}
	

}
