package Antik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ArrayList;

import GUI.Controller;

public class TitulManager {
	static Connection conn = null;
	static Properties connectionProps = new Properties();
	
	
	//	Pripoj sa k databaze
	public static void pripojDB(){
		connectionProps.put("user", "postgres");
		connectionProps.put("password", "cocker");
		String connectionString = "jdbc:postgresql://localhost:5432/Antik";
		
		try {
			conn = DriverManager.getConnection(connectionString,
					connectionProps);
			conn.setAutoCommit(false);
			Controller.sb.append("Pripojené na databázu...\n");
		} catch (SQLException e) {
			Controller.sb.append(e.getMessage());
		}		
	}
	
	// Odpoj databazu
	public static void odpojDB() {
		try {
			conn.close();
		} catch (SQLException e) {
			Controller.sb.append(e.getMessage());
		}
	}

	//	Vykonaj zmenu v datbaze bez vratenia
	public void insertUpdateDelete(String statementString) throws SQLException {		
		PreparedStatement stmt = null;
		try {
			conn.setAutoCommit(false);
			stmt = (PreparedStatement) conn.prepareStatement(statementString);
			stmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.println(e.getMessage());
					System.err.print("Transaction sa roluje back\n");
					conn.rollback();
				} catch (SQLException excep) {
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}		
		System.out.println(statementString);
	}
	
	public NajdenyTitul jedenRiadok(ResultSet rs) throws SQLException{
		NajdenyTitul novyT = new NajdenyTitul();
		novyT.setCena(rs.getDouble("cena"));
		novyT.setSkladNazov(rs.getString("nazovS"));
		novyT.setTitulNazov(rs.getString("nazov"));
		novyT.setMeno(rs.getString("meno"));
		novyT.setStav(rs.getString("stav"));
		novyT.setRokVydania(rs.getInt("rok_vydania"));
		
		return novyT;		
	}
	
	@SuppressWarnings("finally")
	public ArrayList<NajdenyTitul> vyhladajTitul(String statementString) throws SQLException {
		Statement stmt = null;
		ArrayList<NajdenyTitul> resultList = new ArrayList<NajdenyTitul>();
		System.out.println(statementString);
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementString);
			while (rs.next()) {
				resultList.add(jedenRiadok(rs));
			}
			
		} catch(SQLException e) {
			Controller.sb.append(e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			return resultList;
		}
	}
	
	public AutorPocet jedenRiadokAutorZaner(ResultSet rs) throws SQLException {
		AutorPocet ap = new AutorPocet();
		ap.setMeno(rs.getString(2));
		ap.setPocet(rs.getInt(1));
		
		return ap;
	}
	
	@SuppressWarnings("finally")
	public ArrayList<AutorPocet> getAutorZaner(String statementString) throws SQLException {
		Statement stmt = null;
		ArrayList<AutorPocet> resultList = new ArrayList<AutorPocet>();
		System.out.println(statementString);
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementString);
			while (rs.next()) {
				resultList.add(jedenRiadokAutorZaner(rs));
			}
			
		} catch(SQLException e) {
			Controller.sb.append(e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			return resultList;
		}
	}
	
	public NajdenyTitul jedenRiadokTitul(ResultSet rs) throws SQLException{
		NajdenyTitul novyT = new NajdenyTitul();
		novyT.setCena(rs.getDouble("cena"));
		novyT.setTitulNazov(rs.getString("nazov"));
		novyT.setStav(rs.getString("stav"));
		novyT.setRokVydania(rs.getInt("rok_vydania"));
		novyT.setId(rs.getInt("id"));
		
		return novyT;		
	}
	
	@SuppressWarnings("finally")
	public ArrayList<NajdenyTitul> getTitul(String statementString) throws SQLException {
		Statement stmt = null;
		ArrayList<NajdenyTitul> resultList = new ArrayList<NajdenyTitul>();
		System.out.println(statementString);
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementString);
			while (rs.next()) {
				resultList.add(jedenRiadokTitul(rs));
			}
			
		} catch(SQLException e) {
			Controller.sb.append(e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			return resultList;
		}
	}
	
	public AutorPocet jedenRiadokStat(ResultSet rs) throws SQLException {
		AutorPocet ap = new AutorPocet();
		ap.setMeno(rs.getString("meno"));
		ap.setPocet(rs.getInt("count"));
		
		return ap;
	}
	
	@SuppressWarnings("finally")
	public ArrayList<AutorPocet> statistika1(String statementString) throws SQLException {
		Statement stmt = null;
		ArrayList<AutorPocet> resultList = new ArrayList<AutorPocet>();
		System.out.println(statementString);
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementString);
			while (rs.next()) {
				resultList.add(jedenRiadokStat(rs));
			}
			
		} catch(SQLException e) {
			Controller.sb.append(e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			return resultList;
		}
	}
	
	public NajdenyTitulKomplet jedenRiadokKomplet(ResultSet rs) throws SQLException{
		NajdenyTitulKomplet novyT = new NajdenyTitulKomplet();
		novyT.setCena(rs.getDouble("cena"));
		novyT.setSkladNazov(rs.getString("nazovs"));
		novyT.setTitulNazov(rs.getString("nazov"));
		novyT.setMeno(rs.getString("meno"));
		novyT.setStav(rs.getString("stav"));
		novyT.setRokVydania(rs.getInt("rok_vydania"));
		novyT.setId(rs.getInt("id"));
		novyT.setNazovZanru(rs.getString("nazovz"));
		novyT.setNazovTypu(rs.getString("Nazovt"));
		
		return novyT;		
	}
	
	@SuppressWarnings("finally")
	public ArrayList<NajdenyTitulKomplet> vypisKomplet(String statementString) throws SQLException {
		Statement stmt = null;
		ArrayList<NajdenyTitulKomplet> resultList = new ArrayList<NajdenyTitulKomplet>();
		System.out.println(statementString);
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statementString);
			while (rs.next()) {
				resultList.add(jedenRiadokKomplet(rs));
			}
			
		} catch(SQLException e) {
			Controller.sb.append(e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			return resultList;
		}
	}

}
