package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
		
		List<Integer[]> array = new ArrayList<Integer[]>();
		array.add(arr1);
		array.add(arr2);
		array.add(arr3);
		array.add(arr4);
		array.add(arr5);
		
		
//		test1(array);
		
//		test2(array);
		
//		test3(array);
		
		// java 程序运行时，第一个参数指定对应的testId
		test4(Integer.valueOf(args[0]));
	}
	

	/**
	 * 通过标准输入(System.in)让用户输入5组共100个测试数据(每一组20个)
	 * 每一个数据之间用空格间隔，每一组数据都调用IntegerSort进行排序
	 * 输出排序结果(5行)到标注输出(System.out)
	 * 
	 * @param args
	 */
	private static void test1(List<Integer[]> array) {
		
		IntegerSort sort = new IntegerSort();
		
		for (Integer[] arr: array) {
			sort.sort(arr);
			printArray(arr);
		}
	}

	
	/**
	 * 通过JDBC记录测试过程，新增一行到TestMain表，记录使用的排序算法，并输出自增主键到控制台；
	 * @param args
	 * @throws SQLException 
	 */
	private static void test2(List<Integer[]> array) throws SQLException 
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
            conn.setAutoCommit(false);

			IntegerSort sort = new IntegerSort();
			
            // 执行SQL
            String sql = "INSERT INTO TestMain(testType) VALUES(?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, sort.getName());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
				System.out.println("自增主键是: " + rs.getObject(1));
            
            for (Integer[] arr: array) {
            	// 执行排序
        		sort.sort(arr);
        		printArray(arr);
    		}
			
            conn.commit();
            
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
	 * @throws SQLException 
	 */
	private static void test3(List<Integer[]> array) throws Exception 
	{	
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try
		{
			// 获取数据库连接
			conn = getConn();
            if(conn.isClosed())
                System.out.println("Failed connecting to the Database!");
            conn.setAutoCommit(false);
            
			IntegerSort sort = new IntegerSort();
			
            // 执行SQL
            String sql1 = "INSERT INTO TestMain(testType) VALUES(?)";
			ps1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, sort.getName());
			ps1.executeUpdate();
			rs1 = ps1.getGeneratedKeys();
			
			Integer key = 0;
			// 获取自增主键
			if (rs1.next()) {
			    key = rs1.getInt(1);
				System.out.println("自增主键是: " + key);
			}
            
            for (Integer[] arr: array) {
            	// MySQL 中的datetime 与java.sql.Timestamp; 对应
            	Timestamp begin = new Timestamp(System.currentTimeMillis());
            	
            	// 执行排序
        		sort.sort(arr);
        		printArray(arr);
        		
        		// 排序速度很快，这里加个睡眠帮助测试4()
        		Thread.currentThread().sleep(1000);
        		
        		Timestamp end = new Timestamp(System.currentTimeMillis());
        		
        		PreparedStatement ps2 = null;
        		ResultSet rs2 = null;
        		try {
	        		// 执行SQL
	                String sql2 = "INSERT INTO TestDetail(testId, testData, biginTime, endTime) VALUES(?, ?, ?, ?)";
	    			ps2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
	    			ps2.setInt(1, key);
	    			String s = getArrayString(arr);
	    			ps2.setString(2, s);
	    			ps2.setTimestamp(3, begin);
	    			ps2.setTimestamp(4, end);
	    			ps2.executeUpdate();
	    			rs2 = ps2.getGeneratedKeys();
        		} catch(Exception e) {   
                    e.printStackTrace();
                } finally {
                	rs2.close();
                	ps2.close();
                }
    		}
			
            conn.commit();
        } catch(Exception e) {   
            e.printStackTrace();
        } finally {
        	rs1.close();
        	ps1.close();
        	conn.close();
        }
	}
	
	
	/**
	 * 以TestMain的主键为查询条件，通过JDBC的查询输出每一组排序的排序消耗时间
	 * @param args
	 * @throws SQLException 
	 */
	private static void test4(Integer testId) throws SQLException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			// 获取数据库连接
			conn = getConn();
            if(conn.isClosed())
                System.out.println("Failed connecting to the Database!");
            conn.setAutoCommit(false);
			
            // 执行SQL
            String sql1 = "select * from TestDetail where testId = ?";
			ps = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, testId);
			rs = ps.executeQuery();

			// 每行
			Integer max = null;
			Integer min = null;
					
			Timestamp begin = null;
			Timestamp end = null;
			
			while (rs.next()) {
				Integer detailId = rs.getInt("detailId");
				Timestamp biginTime = rs.getTimestamp("biginTime");
				Timestamp endTime = rs.getTimestamp("endTime");
				
				if ((max == null) || (detailId > max)) {
					max = detailId;
					end = endTime;
				}
				
				if ((min == null) || (detailId < max)) {
					min = detailId;
					begin = biginTime;
				}
			}
			
			// 计算
			if ((null != begin) && (null != end)) {
				long diff = end.getTime() - begin.getTime();
				System.out.println("耗时(ms): " + diff);
			} else {
				System.out.println("该批次没有记录！！！");
			}
			
            conn.commit();
        } catch(Exception e) {   
            e.printStackTrace();
        } finally {
        	rs.close();
        	ps.close();
        	conn.close();
        }
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
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	private static String getArrayString(Integer[] arr) 
	{
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<arr.length; i++) {
			sb.append(arr[i]);
			sb.append(", ");
		}
		
		String s = sb.toString();
		
		return s;
	}
}
