package com.bank.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.*;

import com.bank.user.bean.UserBean;
import com.bank.user.bean.TransactionBean;


public class DaoClass implements DaoInterface {
	
	EntityManager con;
	
	DaoDatabaseConnection dbc = new DaoDatabaseConnection();
	
	PreparedStatement ps = null;
	Connection conn = null;
	static Random rand = new Random();
	
	 HashMap<String, UserBean> UserAccountData;
	 public DaoClass() {
		 UserAccountData = new HashMap<String, UserBean>();
		}
	
	UserBean a1; // Object of Userbean class.
	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sd1 = new SimpleDateFormat("HH:mm:ss");

	
	public int generateRandomTrnasactionID() {
		int n = rand.nextInt(100);
		return n;
	}
	//this method create user account and data in map collection.
	@Override
	public int userAccountCreate(UserBean userbean) {
		
		con = dbc.getConnection();
		con.getTransaction().begin();
		
		con.persist(userbean);
		con.getTransaction().commit();
		return 1;

	}
	
	//this method return balance .
	@Override
	public String showBalance(int accountId) {
		
		con = dbc.getConnection();
		con.getTransaction().begin();
		
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		con.getTransaction().commit();
		
		return "balance is "+ub.getBalance();


	}
	
	
	@Override
	public int SignIn(int accountId, String accountPassword) {
		int flag = 0;
		con = dbc.getConnection();
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		if(ub.getAccountPassword().equals(accountPassword)) {
			flag = 1;
		}
		else {
			flag = 0;
		}
		return flag;
//		Query query = con.createQuery("select b from bank b where accountid = ? and userpassword = ?");
//		query.setParameter(1, accountId);
//		query.setParameter(2, accountPassword);
//		List<UserBean> resultList = query.getResultList();
//		if(resultList.size() != 0) {
//			flag = 1;
//		}
//		else {
//			flag = 0;
//		}
//		return flag;
//		try {
//			conn = DaoDatabaseConnection.getConnection();
//			String show_str = "select * from bank where accountid = ? and userpassword = ?";
//			
//			ps = conn.prepareStatement(show_str);
//			
//			ps.setString(1, accountId);
//			ps.setString(2, accountPassword);
//			ResultSet resultSet = ps.executeQuery();
//			
//			if(resultSet.next()) {
//				flag = 1;
//			}
//	
//			return flag;
//		}
//		catch(Exception e) {
//			System.out.println("error");
//			System.out.println(e.getStackTrace());
//			return 0;
//		}
		
	}
	
	//this method use for deposit amount. 
	@Override
	public String Deposit(int accountId, int amount) {
		
		con = dbc.getConnection();
		con.getTransaction().begin();
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		int updatetBalance = ub.getBalance() + amount;
		ub.setBalance(updatetBalance);
		
		
		//con.getTransaction().commit();
		
//		con = dbc.getConnection();
//		con.getTransaction().begin();
		
		TransactionBean tb = new TransactionBean();
		//Timestamp getDate = rs.getTimestamp("transactiondate");
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		//tb.setAccountId(accountId);
		tb.setAmount(amount);
		tb.setTransactionType("Deposit");
		tb.setTransactionDate(ts);
		tb.setUserbean(ub);
		//tb.setUserbean(ub);
		//ub.getTransactions().add(tb);
		
		con.merge(ub);
		con.persist(tb);
		con.getTransaction().commit();
		//System.out.println(ub.getTransactions());
		
		return "Deposit Successfull";

	}
	
	//this method use for withdraw amount.
	@Override
	public String withDraw(int accountId, int amount) {
		con = dbc.getConnection();
		con.getTransaction().begin();
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		int updatetBalance = ub.getBalance() - amount;
		ub.setBalance(updatetBalance);
		//con.getTransaction().commit();
		
//		con = dbc.getConnection();
//		con.getTransaction().begin();
		
		TransactionBean tb = new TransactionBean();
		//Timestamp getDate = rs.getTimestamp("transactiondate");
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		//tb.setAccountId(accountId);
		tb.setAmount(amount);
		tb.setTransactionType("Withdraw");
		tb.setTransactionDate(ts);
		tb.setUserbean(ub);
		
		con.merge(ub);
		con.persist(tb);
		con.getTransaction().commit();
		
		
		return "Withdraw Successfull";

	}
	
	//this method use to sign in into account.
	//return 1 if valid ,else return 0.


	//this method use for fund transfer data.
	//sourceAccountId = user one who want to transfer amount.
	//destrinationAccountId = user whom to transfer amount.
	@Override
	public String fundTransfer(int sourceAccountId, int destinationAccountId, int amount) {
		
		con = dbc.getConnection();
		con.getTransaction().begin();
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(sourceAccountId));
		int updatetBalance = ub.getBalance() - amount;
		ub.setBalance(updatetBalance);
//		con.getTransaction().commit();
//		
//		con = dbc.getConnection();
//		con.getTransaction().begin();
		
		TransactionBean tb = new TransactionBean();
		//Timestamp getDate = rs.getTimestamp("transactiondate");
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		//tb.setAccountId(sourceAccountId);
		tb.setToAccountId(destinationAccountId);
		tb.setAmount(amount);
		tb.setTransactionType("fund transfer to");
		tb.setTransactionDate(ts);
		tb.setUserbean(ub);
		
//		con.persist(tb);
//		con.getTransaction().commit();
//		
//		
//		con = dbc.getConnection();
//		con.getTransaction().begin();
		
		UserBean ub2 = (UserBean) con.find(UserBean.class, new Integer(destinationAccountId));
		int updatetBalance2 = ub2.getBalance() + amount;
		ub2.setBalance(updatetBalance2);
//		con.getTransaction().commit();
//		
//		
//		con = dbc.getConnection();
//		con.getTransaction().begin();
		
		TransactionBean tb2 = new TransactionBean();
		//Timestamp getDate = rs.getTimestamp("transactiondate");
		Date date2= new Date();
		long time2 = date.getTime();
		Timestamp ts2 = new Timestamp(time2);
		//tb2.setAccountId(destinationAccountId);
		tb2.setToAccountId(sourceAccountId);
		tb2.setAmount(amount);
		tb2.setTransactionType("fund transfer from");
		tb2.setTransactionDate(ts2);
		tb2.setUserbean(ub2);
		
		
		con.merge(ub);
		con.merge(ub2);
		con.persist(tb);
		con.persist(tb2);
		
		
		con.getTransaction().commit();
		
		return "Transfer Successfull";
		

		
	}
//	@Override
//	public UserBean printTransactions(int accountId) {
//		//HashMap<Integer,UserBean> hm = new HashMap<Integer,UserBean>();
//		con = dbc.getConnection();
//		con.getTransaction().begin();
//		UserBean tb = (UserBean) con.find(UserBean.class, new Integer(accountId));
//		
//		con.getTransaction().commit();
//		
//		//hm.put(accountId, tb);
//		
//		return tb;
//		
//	}
	
	//this method print the all transaction of logged in user.
	@Override
	public HashMap<Integer,UserBean> printTransactions(int accountId){
		HashMap<Integer,UserBean> hm = new HashMap<Integer,UserBean>();
		
		con = dbc.getConnection();
		con.getTransaction().begin();
		UserBean tb = (UserBean) con.find(UserBean.class, new Integer(accountId));
		System.out.println(tb.getTransactions());
		con.getTransaction().commit();
		
		hm.put(accountId, tb);
		
		return hm;
		//a1 = UserAccountData.get(accountId);
//		HashMap<Integer,TransactionBean> hm = new HashMap<Integer,TransactionBean>();
//		
//		//System.out.println(a1.getTransaction());
//		try {
//			
//				
//			String print_transaction = "select * from transaction where accountid = ?";
//			ps = conn.prepareStatement(print_transaction);
//			
//			ps.setString(1, accountId);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				TransactionBean tb = new TransactionBean();
//				int tid = rs.getInt("tid");
//				String transactionType = rs.getString("transactiontype");
//				
//				String toAccountId = rs.getString("toaccountid");
//				Timestamp getDate = rs.getTimestamp("transactiondate");
//				int amount = rs.getInt("amount");
//				String accountId1 = rs.getString("accountId");
//				
//				tb.setTransactionId(tid);
//				tb.setTransactionType(transactionType);
//				tb.setToAccountId(toAccountId);
//				tb.setAmount(amount);
//				tb.setAccountId(accountId1);
//				tb.setTransactionDate(getDate);
//				
//				hm.put(tid, tb);
//			}
//			//conn.close();
//			return  hm;
//		
//		}
//		catch(Exception e) {
//			System.out.println("this error i got "+e.getMessage());
//			return  hm;
//		}
		
		

	}

	
	
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////validation/////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	



	
	//this method check account id is in collection or not.
	@Override
	public boolean validAccountId(int accountId) {
//		if (UserAccountData.containsKey(accountId)) {
//			return true;
//		} else {
//			return false;
//		}
		
		con = dbc.getConnection();
		UserBean ub = (UserBean) con.find(UserBean.class, new Integer(accountId));
		if(ub != null) {
			return true;
		}
		else {
			return false;
		}
//		try {
//			conn = DaoDatabaseConnection.getConnection();
//			String val_str = "select * from bank where accountid = ?";
//			
//			ps = conn.prepareStatement(val_str);
//			
//			ps.setString(1,accountId);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			if(rs.next()) {
//				return true;
//			}
//			
//			//conn.close();
//			return false;
//			
//			
//			
//		}
//		catch(Exception e) {
//			System.out.println(e);
//			System.out.println(e.getMessage());
//			return false;
//		}
	}
	
	//this method check user have insufficient balance or not.
	@Override
	public boolean checkBalance(int accountId, int amount) {
		
		con = dbc.getConnection();
		UserBean ub = (UserBean) con.find(UserBean.class,accountId);
		if(ub.getBalance() >= amount) {
			return true;
		}
		else {
			return false;
		}
//		try {
//			conn = DaoDatabaseConnection.getConnection();
//			String chk_str = "select balance from bank where accountid = ? and balance >= ?";
//			
//			ps = conn.prepareStatement(chk_str);
//			
//			ps.setString(1, accountId);
//			ps.setInt(2, amount);
//			
//			ResultSet resultSet = ps.executeQuery();
//			if(resultSet.next()) {
//				return true;
//			}
//			
//			//conn.close();
//			return false;
//		}
//		catch(Exception e){
//			System.out.println(e);
//			System.out.println(e.getMessage());
//			return false;
//		}
	}
	


}
