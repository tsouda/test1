package user.parts;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegInfCheckTest {

	// ���O�̃`�F�b�N���Ɏg�p
	static String message ;
	static String brank;
	
	// �N��̃`�F�b�N���Ɏg�p
	static String hankaku;
	static String hanni;
	static String hanni_hankaku;
	
	// ID�̃`�F�b�N���Ɏg�p
	static String touroku;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// �G���[���b�Z�[�W��ݒ肵�Ă���
		brank = "";
		message = "���O��10���ȓ��œ��͂��Ă��������B<br />";
		hankaku = "�N��͐��l(���p)�œ��͂��Ă��������B<br />";
		hanni = "�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />";
		hanni_hankaku = "�N��͐��l(���p)�œ��͂��Ă��������B<br />�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />";
		touroku = "�o�^�\��ID�i999�j�𒴂��Ă��܂��B�Ǘ��҂ɖ₢���킹�Ă��������B<br />";
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
	public void ���O�̃`�F�b�N_UT001_001() {

		System.out.println(" **************** ���O�̃`�F�b�N_UT001_001 START");
		RegInfCheck check = new RegInfCheck();
		
		check.checkName("0123456789");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** ���O�̃`�F�b�N_UT001_001 END");
	}	
	
	@Test
	public void ���O�̃`�F�b�N_UT001_002() {
	
		System.out.println(" **************** ���O�̃`�F�b�N_UT001_002 START");
		RegInfCheck check = new RegInfCheck();
		
		check.checkName("��������������������");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** ���O�̃`�F�b�N_UT001_002 END");
	}		
	
	@Test
	public void ���O�̃`�F�b�N_UT001_003() {
		System.out.println(" **************** ���O�̃`�F�b�N_UT001_003 START");
		RegInfCheck check = new RegInfCheck();

		check.checkName("01234567890");
		String str = check.getErrMsg();
		assertThat(message,is(str));
		System.out.println(" **************** ���O�̃`�F�b�N_UT001_003 END");
	}

	@Test
	public void ���O�̃`�F�b�N_UT001_004() {
		System.out.println(" **************** ���O�̃`�F�b�N_UT001_004 START");
		RegInfCheck check = new RegInfCheck();

		check.checkName("����������������������");
		String str = check.getErrMsg();
		assertThat(message,is(str));
		System.out.println(" **************** ���O�̃`�F�b�N_UT001_004 END");
	}
	
	@Test
	public void �N��̃`�F�b�N_UT001_005() {
		System.out.println(" **************** �N��̃`�F�b�N_UT001_005 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("16");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** �N��̃`�F�b�N_UT001_005 END");
	}

	@Test
	public void �N��̃`�F�b�N_UT001_006() {
		System.out.println(" **************** �N��̃`�F�b�N_UT001_006 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("60");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** �N��̃`�F�b�N_UT001_006 END");
	}

	@Test
	public void �N��̃`�F�b�N_UT001_007() {
		System.out.println(" **************** �N��̃`�F�b�N_UT001_007 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("15");
		String str = check.getErrMsg();
		assertThat(hanni,is(str));
		System.out.println(" **************** �N��̃`�F�b�N_UT001_007 END");
	}
	
	@Test
	public void �N��̃`�F�b�N_UT001_008() {
		System.out.println(" **************** �N��̃`�F�b�N_UT001_008 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("61");
		String str = check.getErrMsg();
		assertThat(hanni,is(str));
		System.out.println(" **************** �N��̃`�F�b�N_UT001_008 END");
	}
	
	@Test
	public void �N��̃`�F�b�N_UT001_009() {
		System.out.println(" **************** �N��̃`�F�b�N_UT001_009 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("�P�U");
		String str = check.getErrMsg();
		assertThat(hankaku,is(str));
		System.out.println(" **************** �N��̃`�F�b�N_UT001_009 END");
	}
	
	@Test
	public void �N��̃`�F�b�N_UT001_010() {
		System.out.println(" **************** �N��̃`�F�b�N_UT001_010 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("����������");
		String str = check.getErrMsg();
		assertThat(hanni_hankaku,is(str));
		System.out.println(" **************** �N��̃`�F�b�N_UT001_010 END");
	}

	@Test
	public void �N��̃`�F�b�N_UT001_011() {
		System.out.println(" **************** �N��̃`�F�b�N_UT001_011 START");
		RegInfCheck check = new RegInfCheck();

		check.checkAge("16����������");
		String str = check.getErrMsg();
		assertThat(hanni_hankaku,is(str));
		System.out.println(" **************** �N��̃`�F�b�N_UT001_011 END");
	}
	
	@Test
	public void ID�̃`�F�b�N_UT001_012() {
		System.out.println(" **************** ID�̃`�F�b�N_UT001_012 START");
		RegInfCheck check = new RegInfCheck();

		check.checkId("999");
		String str = check.getErrMsg();
		assertThat(brank,is(str));
		System.out.println(" **************** ID�̃`�F�b�N_UT001_012 END");
	}
	
	@Test
	public void ID�̃`�F�b�N_UT001_013() {
		System.out.println(" **************** ID�̃`�F�b�N_UT001_013 START");
		RegInfCheck check = new RegInfCheck();

		check.checkId("1000");
		String str = check.getErrMsg();
		assertThat(touroku,is(str));
		System.out.println(" **************** ID�̃`�F�b�N_UT001_013 END");
	}
}
