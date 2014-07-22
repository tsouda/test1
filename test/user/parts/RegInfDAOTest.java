package user.parts;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import user.bean.RegistrantInfo;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import employ.OrderedRunner;
import employ.Order;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

@RunWith(OrderedRunner.class)
public class RegInfDAOTest {

	// INSERT試験(UT002-001)で使用
	static String expected_UT002_001="004佐藤路未央28";
		
	// UPDATE試験(UT002-002)で使用
	static String expected_UT002_002="002Michael29";
	
	// DELETE試験(UT002-003)で使用
	static String expected_UT002_003="";

	// getReglist試験(UT002-004)で使用
	static String Informations [] = {"001鈴木太郎35","002Tommy25","003山田花子30"};
	
	// getNextId試験(UT002-005)で使用
	static String expected_UT002_005="001";	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 初期設定
		try{
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
			InitialContext ic = new InitialContext();
			ic.createSubcontext("java:");
			ic.createSubcontext("java:comp");
			ic.createSubcontext("java:comp/env");
			ic.createSubcontext("java:comp/env/jdbc");
			MysqlDataSource ds = new MysqlDataSource();
			ds.setUser("root");
			ds.setPassword("root");
			ds.setURL("jdbc:mysql://localhost/Task");
			ic.bind("java:comp/env/jdbc/Task", ds);

		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("失敗");
		}
		finally{
			System.out.println("初期設定終わり");
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		RegInfDAO rid = new RegInfDAO();
		
		// 全試験項目終了後開始前に、DBを元の状態に戻す。
		// deleteを実行
		rid.delete("001");
		rid.delete("002");
		rid.delete("003");
		rid.delete("004");

		// insertを実行
		rid.insert("001", "鈴木太郎", "35");
		rid.insert("002", "Tommy", "25");
		rid.insert("003", "山田花子", "30");
	
	}

	@Before
	public void setUp() throws Exception {
		RegInfDAO rid = new RegInfDAO();
		
		// 試験開始前に、DBを元の状態に戻す。
		// deleteを実行
		rid.delete("001");
		rid.delete("002");
		rid.delete("003");
		rid.delete("004");

		// insertを実行
		rid.insert("001", "鈴木太郎", "35");
		rid.insert("002", "Tommy", "25");
		rid.insert("003", "山田花子", "30");
	}

	@After
	public void tearDown() throws Exception {
		// 試験後にその時のDBの中身を表示
		System.out.println("試験後のDBの中身↓");
		ArrayList<RegistrantInfo> list = new ArrayList<RegistrantInfo>();
		RegInfDAO rid = new RegInfDAO();
		
		list = rid.getReglist();
		for(int i=0;i<list.size();i++){
			RegistrantInfo ri = list.get(i);
			String Str = ri.getrId();
			Str = Str + ',' + ri.getrName();
			Str = Str + ',' + ri.getrAge();
			System.out.println(Str);
		}
		System.out.println();
	}

	// -------------------------------------------------------------------------------------------
	
	@Test
	//@Order(order=5)
	public void DBに雇用者情報をINSERTする() {
		System.out.println("**************** DBに雇用者情報をINSERTする_UT002_001 START");
		
		// 初期化処理
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		
		try{
			// DBへ接続
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// insertを実行
			rid.insert("004", "佐藤路未央", "28");
			
			// INSERTで書き込んだ内容を指定して読み出す
			ps = db.prepareStatement("SELECT * FROM registrants WHERE registrant_id = '004'");
			rs = ps.executeQuery();
			String str;
			while(rs.next()){
				str = rs.getString("registrant_id");
				str = str+rs.getString("registrant_name");
				str = str+rs.getString("registrant_age");
				System.out.println("期待値⇒"+expected_UT002_001);
				System.out.println("DBから取得した文字列⇒"+str);
				System.out.println("assrtThatを実行!");
				assertThat(str,is(expected_UT002_001));
			}		
		}
		catch(Exception e){
			System.out.println("失敗");
			e.printStackTrace();
		}
		finally{
			try{
				if(db!=null){
					db.close();
				}
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}		
		}
		System.out.println("**************** DBに雇用者情報をINSERTする_UT002_001 END");
		System.out.println();
	}
	
	// -------------------------------------------------------------------------------------------

	@Test
	//@Order(order=4)
	public void DBの雇用者情報をUPDATEする() {
		System.out.println("**************** DBの雇用者情報をUPDATEする_UT002_002 START");
			
		// 初期化処理
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		
		try{
			// DBへ接続
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// updateを実行
			rid.update("002", "Michael", "29");
			
			// INSERTで書き込んだ内容を指定して読み出す
			ps = db.prepareStatement("SELECT * FROM registrants WHERE registrant_id = '002'");
			rs = ps.executeQuery();
			String str;
			while(rs.next()){
				str = rs.getString("registrant_id");
				str = str+rs.getString("registrant_name");
				str = str+rs.getString("registrant_age");
				System.out.println("期待値⇒"+expected_UT002_002);
				System.out.println("DBから取得した文字列⇒"+str);
				System.out.println("assrtThatを実行!");
				assertThat(str,is(expected_UT002_002));
			}		
		}
		catch(Exception e){
			System.out.println("失敗");
			e.printStackTrace();
		}
		finally{
			try{
				if(db!=null){
					db.close();
				}
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}		
		}
		System.out.println("**************** DBの雇用者情報をUPDATEする_UT002_002 END");
		System.out.println();
	}

	// -------------------------------------------------------------------------------------------
	@Test
//	@Order(order=3)
	public void DBの雇用者情報をDELETEする() {
		System.out.println("**************** DBの雇用者情報をDELETEする_UT002_003 START");
		
		// 初期化処理
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		
		try{
			// DBへ接続
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// deleteを実行
			rid.delete("001");
			
			// INSERTで書き込んだ内容を指定して読み出す
			ps = db.prepareStatement("SELECT * FROM registrants WHERE registrant_id = '001'");
			rs = ps.executeQuery();
			boolean i = rs.next();
			System.out.println("assrtThatを実行!");
			assertThat(i,is(false));
	//		String str;
	//		while(rs.next()){
	//			str = rs.getString("registrant_id");
	//			str = str+rs.getString("registrant_name");
	//			str = str+rs.getString("registrant_age");
	//			System.out.println(str);
	//			System.out.println("assrtThatを実行!");
	//			assertThat(str,is(expected_UT003_003));
	//		}
	
		}
		catch(Exception e){
			System.out.println("失敗");
			e.printStackTrace();
		}
		finally{
			try{
				if(db!=null){
					db.close();
				}
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}		
		}
		System.out.println("**************** DBの雇用者情報をDELETEする_UT002_003 END");
		System.out.println();
	} 
	// -------------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------------
	@Test
	//@Order(order=2)
	public void DBの雇用者情報を出力する() {
		System.out.println("**************** DBの雇用者情報を出力する_UT002_004 START");
		
		// 初期化処理
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		ArrayList<RegistrantInfo> list = new ArrayList<RegistrantInfo>();
		
		try{
			// DBへ接続
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// getReglistを実行
			list = rid.getReglist();
			for(int i=0;i<list.size();i++){
				RegistrantInfo ri = list.get(i);
				String actual = ri.getrId();
				actual = actual+ri.getrName();
				actual = actual+ri.getrAge();
				System.out.println("期待値⇒"+Informations[i]);
				System.out.println("DBから取得した文字列⇒"+actual);
				System.out.println("ASSERTTHATを起動");
				assertThat(actual,is(Informations[i]));
			}
		}
		catch(Exception e){
			System.out.println("失敗");
			e.printStackTrace();
		}
		finally{
			try{
				if(db!=null){
					db.close();
				}
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}		
		}
		System.out.println("**************** DBの雇用者情報を出力する_UT002_004 END");
		System.out.println();
	}

	// -------------------------------------------------------------------------------------------
	
	// -------------------------------------------------------------------------------------------
	@Test
	//@Order(order=1)
	public void DBの雇用者情報が空のときに初期値を取得する() {
		System.out.println("**************** DBの雇用者情報が空のときに初期値を取得する_UT002_005 START");
		
		// 初期化処理
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		
		// deleteを実行
		rid.delete("001");
		rid.delete("002");
		rid.delete("003");
		rid.delete("004");
		
		try{
			// DBへ接続
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// getNextIdを実行
			String actual = rid.getNextId();
			
			// INSERTで書き込んだ内容を指定して読み出す
			System.out.println("assertThatを起動↓");
			System.out.println("期待値⇒"+expected_UT002_005);
			System.out.println("DBから取得した文字列⇒"+actual);
			assertThat(actual,is(expected_UT002_005));
					
		}
		catch(Exception e){
			System.out.println("失敗");
			e.printStackTrace();
		}
		finally{
			try{
				if(db!=null){
					db.close();
				}
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}		
		}
		System.out.println("**************** DBの雇用者情報が空のときに初期値を取得する_UT002_005 END");
		System.out.println();
	}
	// -------------------------------------------------------------------------------------------
}
