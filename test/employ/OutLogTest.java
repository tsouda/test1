package employ;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class OutLogTest {
	// UT003-001��002�𑱂��Ď��{����ۂɁA001�̓��e��ێ�����̂Ɏg�p����
	static String jikan;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void �t�@�C�����̓��t���r����_String�^_UT003_001() {
		System.out.println("**************** �t�@�C�����̓��t���r����_String�^_UT003_001 START");
		
		// ���݂̎������擾
		final Date current = new Date();
		TestDate td = new TestDate(){
			Date newDate(){
				return current;
			}
		};
		String result = td.getTime();
		jikan = td.getTime();
	
		// ���ݎ���+"sample:�T���v��"��String�^��expected�ɕێ�(�t�@�C���̒��g�Ɣ�r����̂Ɏg�p����)
		String expected  = result+":"+"sample�F�T���v��";
		
		// UT003-002�ł��g�p����̂ŁAUT003-001�̎����ƕ������ޔ�
		jikan = result+":"+"sample�F�T���v��";
		
		System.out.println("���ݎ����ɕ������t���������̂��o�́�");
		System.out.println(expected);

		// outLogDmp���N��
		OutLog.outLogDmp("sample�F�T���v��");
		
		try{
			// �t�@�C�����J��
			File file = new File("C:/test/log/log.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// �t�@�C���̒��g��String�^�̕ϐ��ɐݒ�
			String str = br.readLine();
			br.close();
			
			System.out.println("�t�@�C���̒��g��\����");
			System.out.println(str);
			
			// expected�ƃt�@�C���̒��g(str)���r
			assertThat(str,is(expected));
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
		//	System.out.println("�I���");
		}
		System.out.println("**************** �t�@�C�����̓��t���r����_String�^_UT003_001 END");
		System.out.println();
	}

	@Test
	public void �t�@�C�����̓��t���r����_int�^_UT003_002() {
		System.out.println("**************** �t�@�C�����̓��t���r����_int�^_UT003_002 START");
		
		// ���݂̎������擾
		final Date current = new Date();
		TestDate td = new TestDate(){
			Date newDate(){
				return current;
			}
		};
		String result = td.getTime();
		
		// UT003-001�̕�����{���ݎ���+"12345"��String�^��jikan�ɕێ�(�t�@�C���̒��g�Ɣ�r����̂Ɏg�p����)
		jikan  = jikan+result+":"+"12345";
		System.out.println("UT003-001�̓��e+���ݎ����ɐ�����t���������̂��o�́�");
		System.out.println(jikan);
		
		// outLogDmp���N��
		OutLog.outLogDmp(12345);
		
		try{
			// �t�@�C�����J��
			File file = new File("C:/test/log/log.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// �t�@�C���̒��g��String�^�̕ϐ��ɐݒ�
			String str = br.readLine();
			br.close();
			
			System.out.println("�t�@�C���̒��g��\����");
			System.out.println(str);
			
			// jikan(UT003-001,002�����킳��������)�ƃt�@�C���̒��g(str)���r
			assertThat(str,is(jikan));
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
		//	System.out.println("�I���");
		}
		System.out.println("**************** �t�@�C�����̓��t���r����_int�^_UT003_002 END");
		System.out.println();
	}
	
	@Test
	public void testOutLogDmpInteger() {
//		fail("�܂���������Ă��܂���");
	}

}
