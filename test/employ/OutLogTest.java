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
	// UT003-001と002を続けて実施する際に、001の内容を保持するのに使用する
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
	public void ファイル内の日付を比較する_String型_UT003_001() {
		System.out.println("**************** ファイル内の日付を比較する_String型_UT003_001 START");
		
		// 現在の時刻を取得
		final Date current = new Date();
		TestDate td = new TestDate(){
			Date newDate(){
				return current;
			}
		};
		String result = td.getTime();
		jikan = td.getTime();
	
		// 現在時刻+"sample:サンプル"をString型のexpectedに保持(ファイルの中身と比較するのに使用する)
		String expected  = result+":"+"sample：サンプル";
		
		// UT003-002でも使用するので、UT003-001の時刻と文字列を退避
		jikan = result+":"+"sample：サンプル";
		
		System.out.println("現在時刻に文字列を付加したものを出力↓");
		System.out.println(expected);

		// outLogDmpを起動
		OutLog.outLogDmp("sample：サンプル");
		
		try{
			// ファイルを開く
			File file = new File("C:/test/log/log.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// ファイルの中身をString型の変数に設定
			String str = br.readLine();
			br.close();
			
			System.out.println("ファイルの中身を表示↓");
			System.out.println(str);
			
			// expectedとファイルの中身(str)を比較
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
		//	System.out.println("終わり");
		}
		System.out.println("**************** ファイル内の日付を比較する_String型_UT003_001 END");
		System.out.println();
	}

	@Test
	public void ファイル内の日付を比較する_int型_UT003_002() {
		System.out.println("**************** ファイル内の日付を比較する_int型_UT003_002 START");
		
		// 現在の時刻を取得
		final Date current = new Date();
		TestDate td = new TestDate(){
			Date newDate(){
				return current;
			}
		};
		String result = td.getTime();
		
		// UT003-001の文字列＋現在時刻+"12345"をString型のjikanに保持(ファイルの中身と比較するのに使用する)
		jikan  = jikan+result+":"+"12345";
		System.out.println("UT003-001の内容+現在時刻に数字を付加したものを出力↓");
		System.out.println(jikan);
		
		// outLogDmpを起動
		OutLog.outLogDmp(12345);
		
		try{
			// ファイルを開く
			File file = new File("C:/test/log/log.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// ファイルの中身をString型の変数に設定
			String str = br.readLine();
			br.close();
			
			System.out.println("ファイルの中身を表示↓");
			System.out.println(str);
			
			// jikan(UT003-001,002が合わさったもの)とファイルの中身(str)を比較
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
		//	System.out.println("終わり");
		}
		System.out.println("**************** ファイル内の日付を比較する_int型_UT003_002 END");
		System.out.println();
	}
	
	@Test
	public void testOutLogDmpInteger() {
//		fail("まだ実装されていません");
	}

}
