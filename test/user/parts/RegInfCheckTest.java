package user.parts;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegInfCheckTest {

	// 名前のチェック時に使用
	static String message ;
	static String brank;
	
	// 年齢のチェック時に使用
	static String hankaku;
	static String hanni;
	static String hanni_hankaku;
	
	// IDのチェック時に使用
	static String touroku;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// エラーメッセージを設定しておく
		brank = "";
		message = "名前は10桁以内で入力してください。<br />";
		hankaku = "年齢は数値(半角)で入力してください。<br />";
		hanni = "年齢は(16-60)の範囲で入力してください。<br />";
		hanni_hankaku = "年齢は数値(半角)で入力してください。<br />年齢は(16-60)の範囲で入力してください。<br />";
		touroku = "登録可能なID（999）を超えています。管理者に問い合わせてください。<br />";
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
	public void 名前のチェック_UT001_001() {

		System.out.println(" **************** 名前のチェック_UT001_001 START");
		RegInfCheck check = new RegInfCheck();
		
		check.checkName("0123456789");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** 名前のチェック_UT001_001 END");
	}	
	
	@Test
	public void 名前のチェック_UT001_002() {
	
		System.out.println(" **************** 名前のチェック_UT001_002 START");
		RegInfCheck check = new RegInfCheck();
		
		check.checkName("あいうえおかきくけこ");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** 名前のチェック_UT001_002 END");
	}		
	
	@Test
	public void 名前のチェック_UT001_003() {
		System.out.println(" **************** 名前のチェック_UT001_003 START");
		RegInfCheck check = new RegInfCheck();

		check.checkName("01234567890");
		String str = check.getErrMsg();
		assertThat(message,is(str));
		System.out.println(" **************** 名前のチェック_UT001_003 END");
	}

	@Test
	public void 名前のチェック_UT001_004() {
		System.out.println(" **************** 名前のチェック_UT001_004 START");
		RegInfCheck check = new RegInfCheck();

		check.checkName("あいうえおかきくけこさ");
		String str = check.getErrMsg();
		assertThat(message,is(str));
		System.out.println(" **************** 名前のチェック_UT001_004 END");
	}
	
	@Test
	public void 年齢のチェック_UT001_005() {
		System.out.println(" **************** 年齢のチェック_UT001_005 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("16");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** 年齢のチェック_UT001_005 END");
	}

	@Test
	public void 年齢のチェック_UT001_006() {
		System.out.println(" **************** 年齢のチェック_UT001_006 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("60");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** 年齢のチェック_UT001_006 END");
	}

	@Test
	public void 年齢のチェック_UT001_007() {
		System.out.println(" **************** 年齢のチェック_UT001_007 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("15");
		String str = check.getErrMsg();
		assertThat(hanni,is(str));
		System.out.println(" **************** 年齢のチェック_UT001_007 END");
	}
	
	@Test
	public void 年齢のチェック_UT001_008() {
		System.out.println(" **************** 年齢のチェック_UT001_008 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("61");
		String str = check.getErrMsg();
		assertThat(hanni,is(str));
		System.out.println(" **************** 年齢のチェック_UT001_008 END");
	}
	
	@Test
	public void 年齢のチェック_UT001_009() {
		System.out.println(" **************** 年齢のチェック_UT001_009 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("１６");
		String str = check.getErrMsg();
		assertThat(hankaku,is(str));
		System.out.println(" **************** 年齢のチェック_UT001_009 END");
	}
	
	@Test
	public void 年齢のチェック_UT001_010() {
		System.out.println(" **************** 年齢のチェック_UT001_010 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("あいうえお");
		String str = check.getErrMsg();
		assertThat(hanni_hankaku,is(str));
		System.out.println(" **************** 年齢のチェック_UT001_010 END");
	}

	@Test
	public void 年齢のチェック_UT001_011() {
		System.out.println(" **************** 年齢のチェック_UT001_011 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("16あいうえお");
		String str = check.getErrMsg();
		assertThat(hanni_hankaku,is(str));
		System.out.println(" **************** 年齢のチェック_UT001_011 END");
	}
	
	@Test
	public void IDのチェック_UT001_012() {
		System.out.println(" **************** IDのチェック_UT001_012 START");
		RegInfCheck check = new RegInfCheck();

		check.checkId("999");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** IDのチェック_UT001_012 END");
	}
	
	@Test
	public void IDのチェック_UT001_013() {
		System.out.println(" **************** IDのチェック_UT001_013 START");
		RegInfCheck check = new RegInfCheck();

		check.checkId("1000");
		String str = check.getErrMsg();
		assertThat(touroku,is(str));
		System.out.println(" **************** IDのチェック_UT001_013 END");
	}
}
