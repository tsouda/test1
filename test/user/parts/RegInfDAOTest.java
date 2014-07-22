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

	// INSERT����(UT002-001)�Ŏg�p
	static String expected_UT002_001="004�����H����28";
		
	// UPDATE����(UT002-002)�Ŏg�p
	static String expected_UT002_002="002Michael29";
	
	// DELETE����(UT002-003)�Ŏg�p
	static String expected_UT002_003="";

	// getReglist����(UT002-004)�Ŏg�p
	static String Informations [] = {"001��ؑ��Y35","002Tommy25","003�R�c�Ԏq30"};
	
	// getNextId����(UT002-005)�Ŏg�p
	static String expected_UT002_005="001";	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// �����ݒ�
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
			System.out.println("���s");
		}
		finally{
			System.out.println("�����ݒ�I���");
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		RegInfDAO rid = new RegInfDAO();
		
		// �S�������ڏI����J�n�O�ɁADB�����̏�Ԃɖ߂��B
		// delete�����s
		rid.delete("001");
		rid.delete("002");
		rid.delete("003");
		rid.delete("004");

		// insert�����s
		rid.insert("001", "��ؑ��Y", "35");
		rid.insert("002", "Tommy", "25");
		rid.insert("003", "�R�c�Ԏq", "30");
	
	}

	@Before
	public void setUp() throws Exception {
		RegInfDAO rid = new RegInfDAO();
		
		// �����J�n�O�ɁADB�����̏�Ԃɖ߂��B
		// delete�����s
		rid.delete("001");
		rid.delete("002");
		rid.delete("003");
		rid.delete("004");

		// insert�����s
		rid.insert("001", "��ؑ��Y", "35");
		rid.insert("002", "Tommy", "25");
		rid.insert("003", "�R�c�Ԏq", "30");
	}

	@After
	public void tearDown() throws Exception {
		// ������ɂ��̎���DB�̒��g��\��
		System.out.println("�������DB�̒��g��");
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
	public void DB�Ɍٗp�ҏ���INSERT����() {
		System.out.println("**************** DB�Ɍٗp�ҏ���INSERT����_UT002_001 START");
		
		// ����������
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		
		try{
			// DB�֐ڑ�
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// insert�����s
			rid.insert("004", "�����H����", "28");
			
			// INSERT�ŏ������񂾓��e���w�肵�ēǂݏo��
			ps = db.prepareStatement("SELECT * FROM registrants WHERE registrant_id = '004'");
			rs = ps.executeQuery();
			String str;
			while(rs.next()){
				str = rs.getString("registrant_id");
				str = str+rs.getString("registrant_name");
				str = str+rs.getString("registrant_age");
				System.out.println("���Ғl��"+expected_UT002_001);
				System.out.println("DB����擾�����������"+str);
				System.out.println("assrtThat�����s!");
				assertThat(str,is(expected_UT002_001));
			}		
		}
		catch(Exception e){
			System.out.println("���s");
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
		System.out.println("**************** DB�Ɍٗp�ҏ���INSERT����_UT002_001 END");
		System.out.println();
	}
	
	// -------------------------------------------------------------------------------------------

	@Test
	//@Order(order=4)
	public void DB�̌ٗp�ҏ���UPDATE����() {
		System.out.println("**************** DB�̌ٗp�ҏ���UPDATE����_UT002_002 START");
			
		// ����������
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		
		try{
			// DB�֐ڑ�
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// update�����s
			rid.update("002", "Michael", "29");
			
			// INSERT�ŏ������񂾓��e���w�肵�ēǂݏo��
			ps = db.prepareStatement("SELECT * FROM registrants WHERE registrant_id = '002'");
			rs = ps.executeQuery();
			String str;
			while(rs.next()){
				str = rs.getString("registrant_id");
				str = str+rs.getString("registrant_name");
				str = str+rs.getString("registrant_age");
				System.out.println("���Ғl��"+expected_UT002_002);
				System.out.println("DB����擾�����������"+str);
				System.out.println("assrtThat�����s!");
				assertThat(str,is(expected_UT002_002));
			}		
		}
		catch(Exception e){
			System.out.println("���s");
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
		System.out.println("**************** DB�̌ٗp�ҏ���UPDATE����_UT002_002 END");
		System.out.println();
	}

	// -------------------------------------------------------------------------------------------
	@Test
//	@Order(order=3)
	public void DB�̌ٗp�ҏ���DELETE����() {
		System.out.println("**************** DB�̌ٗp�ҏ���DELETE����_UT002_003 START");
		
		// ����������
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		
		try{
			// DB�֐ڑ�
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// delete�����s
			rid.delete("001");
			
			// INSERT�ŏ������񂾓��e���w�肵�ēǂݏo��
			ps = db.prepareStatement("SELECT * FROM registrants WHERE registrant_id = '001'");
			rs = ps.executeQuery();
			boolean i = rs.next();
			System.out.println("assrtThat�����s!");
			assertThat(i,is(false));
	//		String str;
	//		while(rs.next()){
	//			str = rs.getString("registrant_id");
	//			str = str+rs.getString("registrant_name");
	//			str = str+rs.getString("registrant_age");
	//			System.out.println(str);
	//			System.out.println("assrtThat�����s!");
	//			assertThat(str,is(expected_UT003_003));
	//		}
	
		}
		catch(Exception e){
			System.out.println("���s");
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
		System.out.println("**************** DB�̌ٗp�ҏ���DELETE����_UT002_003 END");
		System.out.println();
	} 
	// -------------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------------
	@Test
	//@Order(order=2)
	public void DB�̌ٗp�ҏ����o�͂���() {
		System.out.println("**************** DB�̌ٗp�ҏ����o�͂���_UT002_004 START");
		
		// ����������
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		ArrayList<RegistrantInfo> list = new ArrayList<RegistrantInfo>();
		
		try{
			// DB�֐ڑ�
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// getReglist�����s
			list = rid.getReglist();
			for(int i=0;i<list.size();i++){
				RegistrantInfo ri = list.get(i);
				String actual = ri.getrId();
				actual = actual+ri.getrName();
				actual = actual+ri.getrAge();
				System.out.println("���Ғl��"+Informations[i]);
				System.out.println("DB����擾�����������"+actual);
				System.out.println("ASSERTTHAT���N��");
				assertThat(actual,is(Informations[i]));
			}
		}
		catch(Exception e){
			System.out.println("���s");
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
		System.out.println("**************** DB�̌ٗp�ҏ����o�͂���_UT002_004 END");
		System.out.println();
	}

	// -------------------------------------------------------------------------------------------
	
	// -------------------------------------------------------------------------------------------
	@Test
	//@Order(order=1)
	public void DB�̌ٗp�ҏ�񂪋�̂Ƃ��ɏ����l���擾����() {
		System.out.println("**************** DB�̌ٗp�ҏ�񂪋�̂Ƃ��ɏ����l���擾����_UT002_005 START");
		
		// ����������
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegInfDAO rid = new RegInfDAO();
		
		// delete�����s
		rid.delete("001");
		rid.delete("002");
		rid.delete("003");
		rid.delete("004");
		
		try{
			// DB�֐ڑ�
			Context contex = new InitialContext();
			DataSource ds = (DataSource)contex.lookup("java:comp/env/jdbc/Task");
			db = ds.getConnection();
			
			// getNextId�����s
			String actual = rid.getNextId();
			
			// INSERT�ŏ������񂾓��e���w�肵�ēǂݏo��
			System.out.println("assertThat���N����");
			System.out.println("���Ғl��"+expected_UT002_005);
			System.out.println("DB����擾�����������"+actual);
			assertThat(actual,is(expected_UT002_005));
					
		}
		catch(Exception e){
			System.out.println("���s");
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
		System.out.println("**************** DB�̌ٗp�ҏ�񂪋�̂Ƃ��ɏ����l���擾����_UT002_005 END");
		System.out.println();
	}
	// -------------------------------------------------------------------------------------------
}
