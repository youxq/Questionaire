package org.ques.db;


import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

public class DBUtil {

	private static MongoClient mongoClient = null;
	private final static String HOST;
	private final static String PORT;
	
	static {
		PropertiesUtil.loadFile("dbconfig.properties");
		HOST = PropertiesUtil.getPropertyValue("host");
		PORT = PropertiesUtil.getPropertyValue("port");		
	}
	
	/**
	 * 构造函数，用来创建Spring Bean
	 */
	public DBUtil() {

	}
	
	

	/**
	 * 返回数据库实例
	 * @param databaseName
	 * @return
	 */
	public static MongoDatabase getDataBase(String databaseName){
		if(mongoClient == null){
			initConnection();
		}
		return mongoClient.getDatabase(databaseName);
	}
	
	/**
	 * 初始化mongodb连接
	 */
	private static void initConnection(){
		String host = HOST;// 主机名
		int port = new Integer(PORT);// 端口
		// 其他参数根据实际情况进行添加
		try {
			mongoClient = new MongoClient(host,port);
			
		} catch (MongoException e) {
			// log error
		}
	}
}
