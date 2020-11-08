package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Homework {
	
	/**
	 * 测试数据
	 * 1 2 3 4 5 6 7 8 9 10 100 99 98 97 96 95 94 93 92 91 20 19 18 17 16 15 14 13 12 11 21 22 23 24 25 26 27 28 29 30 40 39 38 37 36 35 34 33 32 31 41 42 43 44 45 46 47 48 49 50 60 59 58 57 56 55 54 53 52 51 71 72 73 74 75 76 77 78 79 80 90 89 88 87 86 85 84 83 82 81 51 52 53 54 55 56 57 58 59 60
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception {
		
		if (args.length < 100) {
			System.out.println("输入的参数少于100个");
		}
		
		Integer[] arr1 = new Integer[20];
		Integer[] arr2 = new Integer[20];
		Integer[] arr3 = new Integer[20];
		Integer[] arr4 = new Integer[20];
		Integer[] arr5 = new Integer[20];
		
		// 第一组
		for (int i=0; i<20; i++) 
			arr1[i] = Integer.valueOf(args[i]);
		// 第二组
		for (int i=0; i<20; i++) 
			arr2[i] = Integer.valueOf(args[20 + i]);
		// 第三组
		for (int i=0; i<20; i++) 
			arr3[i] = Integer.valueOf(args[40 + i]);
		// 第四组
		for (int i=0; i<20; i++) 
			arr4[i] = Integer.valueOf(args[60 + i]);
		// 第五组
		for (int i=0; i<20; i++) 
			arr5[i] = Integer.valueOf(args[80 + i]);
		
		
//		test1(arr1, arr2, arr3, arr4, arr5);
		
		test2(arr1, arr2, arr3, arr4, arr5);
//		
//		test3(arr1, arr2, arr3, arr4, arr5);
//		
//		test4(arr1, arr2, arr3, arr4, arr5);
	}
	

	/**
	 * 通过标准输入(System.in)让用户输入5组共100个测试数据(每一组20个)
	 * 每一个数据之间用空格间隔，每一组数据都调用IntegerSort进行排序
	 * 输出排序结果(5行)到标注输出(System.out)
	 * 
	 * @param args
	 */
	private static void test1(Integer[] arr1, Integer[] arr2, Integer[] arr3, Integer[] arr4, Integer[] arr5) {
		
		IntegerSort sort = new IntegerSort();
		
		sort.sort(arr1);
		printArray(arr1);
		
		sort.sort(arr2);
		printArray(arr2);
		
		sort.sort(arr3);
		printArray(arr3);
		
		sort.sort(arr4);
		printArray(arr4);
		
		sort.sort(arr5);
		printArray(arr5);
	}

	
	/**
	 * 通过JDBC记录测试过程，新增一行到TestMain表，记录使用的排序算法，并输出自增主键到控制台；
	 * @param args
	 * @throws SQLException 
	 */
	private static void test2(Integer[] arr1, Integer[] arr2, Integer[] arr3, Integer[] arr4, Integer[] arr5) throws SQLException 
	{	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			// 获取数据库连接
			conn = getConn();
            if(conn.isClosed())
                System.out.println("Failed connecting to the Database!");
            
            // 执行排序
            IntegerSort sort = new IntegerSort();
    		sort.sort(arr1);
    		printArray(arr1);
            
            // 执行SQL
            String sql = "INSERT INTO TestMain(testType) VALUES(?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, sort.getName());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			
			if(rs.next())
				System.out.println("自增主键是: " + rs.getObject(1));
			
        } catch(Exception e) {   
            e.printStackTrace();
        } finally {
        	rs.close();
        	ps.close();
        	conn.close();
        }
    }
	
	
	/**
	 * 每一组测试数据新增一行到TestDetail表，记录TestMain的自增主键
	 * 每一组的测试数据(用,间隔)、测试开始时间和测试结束时间
	 * @param args
	 */
	private static void test3(Integer[] arr1, Integer[] arr2, Integer[] arr3, Integer[] arr4, Integer[] arr5) {
		
		
	}
	
	
	/**
	 * 以TestMain的主键为查询条件，通过JDBC的查询输出每一组排序的排序消耗时间
	 * @param args
	 */
	private static void test4(Integer[] arr1, Integer[] arr2, Integer[] arr3, Integer[] arr4, Integer[] arr5) {
		
		
	}
	
	
	/**
	 * 给定一个数组，输出到控制台
	 * 
	 * @param arr
	 */
	private static void printArray(Integer[] arr) 
	{
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i]);
			System.out.print(" ");
		}
		System.out.println(" ");
	}
	
	/** 
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private static Connection getConn() throws SQLException, ClassNotFoundException
	{
		//声明Connection对象
        Connection conn;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/SortTest";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "";
        
        //加载驱动程序
        Class.forName(driver);
        //1.getConnection()方法，连接MySQL数据库！！
        conn = DriverManager.getConnection(url,user,password);
        
        return conn;
	}
}
