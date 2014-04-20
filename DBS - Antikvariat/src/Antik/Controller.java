package Antik;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	public static StringBuilder sb = new StringBuilder();

	@FXML
	Button btn;

	@FXML
	Button vlozTitul;

	@FXML
	Button vlozAutora;

	@FXML
	Button vlozZaner;

	@FXML
	Button zobrazAutor;

	@FXML
	Button zobrazZaner;

	@FXML
	Button zobrazTitul;

	@FXML
	Button naviazAutora;

	@FXML
	Button stat1;

	@FXML
	Button naviazTitul;
	
	@FXML
	Button vypKomplet;
	
	@FXML
	Button zmenTitul;

	@FXML
	Button vyhladajTitul;

	@FXML
	Button zmazTitul;

	@FXML
	Button zmazTitul1;

	@FXML
	TextField nazovTField;

	@FXML
	TextField idAutor;

	@FXML
	TextField zanerNazov;

	@FXML
	TextField idZaner;

	@FXML
	TextField idTitul1;

	@FXML
	TextField idTitul;

	@FXML
	TextField rokTField;

	@FXML
	TextField stavTField;

	@FXML
	TextField cenaTField;

	@FXML
	TextField nazovTField1;

	@FXML
	TextField rokTField1;

	@FXML
	TextField stavTField1;

	@FXML
	TextField cenaTField1;

	@FXML
	TextField menoAutora;

	@FXML
	TextField titulID;
	@FXML
	TextField titulID1;

	@FXML
	TextField rokField;

	@FXML
	TextField vyhNazovField;

	@FXML
	TextField vyhAutorField;

	@FXML
	static ComboBox<ComboItem> comboBox = new ComboBox<ComboItem>();

	@FXML
	static ComboBox<ComboItem> skladComboBox = new ComboBox<ComboItem>();

	@FXML
	static ComboBox<ComboItem> comboBox1 = new ComboBox<ComboItem>();

	@FXML
	static ComboBox<ComboItem> skladComboBox1 = new ComboBox<ComboItem>();

	@FXML
	TextArea textArea;

	public void vypisSB() {
		textArea.appendText(sb.toString());
		sb.setLength(0);
	}

	public boolean changed(TextField tf) {
		if (tf.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void insertTitul() throws SQLException {
		TitulManager tm = new TitulManager();
		String celyInsert = new String(
				"INSERT INTO titul(nazov, rok_vydania, stav, cena, id_typ, id_sklad) VALUES('");
		Titul novyTitul = new Titul();

		novyTitul.setNazov(nazovTField.getText());
		novyTitul.setCena(cenaTField.getText());
		novyTitul.setRok_vydania(rokTField.getText());
		novyTitul.setStav(stavTField.getText());
		novyTitul.setSklad(skladComboBox.getValue().getValue());
		novyTitul.setTyp(comboBox.getValue().getValue());

		celyInsert = celyInsert + novyTitul.getNazov() + "',"
				+ novyTitul.getRok_vydania() + "," + "'" + novyTitul.getStav()
				+ "'," + novyTitul.getCena() + "," + novyTitul.getTyp() + ","
				+ novyTitul.getSklad() + ")";

		tm.insertUpdateDelete(celyInsert);
		;

	}

	public void insertAutor() throws SQLException {
		TitulManager tm = new TitulManager();
		String celyInsert = new String("INSERT INTO autor(meno) VALUES('");
		celyInsert = celyInsert + menoAutora.getText() + "')";
		tm.insertUpdateDelete(celyInsert);
	}

	public void insertNaviazanieAutor() throws SQLException {
		TitulManager tm = new TitulManager();
		String celyInsert = new String(
				"INSERT INTO autorstvo(id_titul, id_autor) VALUES(");

		celyInsert = celyInsert + idTitul.getText() + "," + idAutor.getText()
				+ ")";

		tm.insertUpdateDelete(celyInsert);
	}

	public void insertZaner() throws SQLException {
		TitulManager tm = new TitulManager();
		String celyInsert = new String("INSERT INTO zaner(nazov) VALUES('");

		celyInsert = celyInsert + zanerNazov.getText() + ")";

		tm.insertUpdateDelete(celyInsert);
	}

	public void insertZanerstvo() throws SQLException {
		TitulManager tm = new TitulManager();
		String celyInsert = new String(
				"INSERT INTO zanerstvo(id_titul, id_zaner) VALUES(");

		celyInsert = celyInsert + idTitul1.getText() + ", " + idZaner.getText()
				+ ")";

		tm.insertUpdateDelete(celyInsert);
	}

	public void updateTitul() throws SQLException {
		TitulManager tm = new TitulManager();
		String celyUpdate = new String();

		Titul novyTitul = new Titul();

		if (changed(nazovTField1)) {
			novyTitul.setNazov(("'" + nazovTField1.getText() + "'"));
		} else {
			novyTitul.setNazov("'DEFAULT'");
		}

		if (changed(cenaTField1)) {
			novyTitul.setCena(cenaTField1.getText());
		} else {
			novyTitul.setCena("DEFAULT");
		}

		if (changed(rokTField1)) {
			novyTitul.setRok_vydania(rokTField1.getText());
		} else {
			novyTitul.setRok_vydania("DEFAULT");
		}

		if (changed(stavTField1)) {
			novyTitul.setStav(("'" + stavTField1.getText() + "'"));
		} else {
			novyTitul.setStav("'DEFAULT'");
		}

		novyTitul.setSklad(skladComboBox1.getValue().getValue());
		novyTitul.setTyp(comboBox1.getValue().getValue());
		novyTitul.setId(Integer.valueOf(titulID.getText()));

		celyUpdate = celyUpdate
				+ "UPDATE titul SET (nazov, rok_vydania, stav, cena, id_typ, id_sklad) =  ("
				+ novyTitul.getNazov() + "," + novyTitul.getRok_vydania() + ","
				+ novyTitul.getStav() + "," + novyTitul.getCena() + ","
				+ novyTitul.getTyp() + "," + novyTitul.getSklad()
				+ ") WHERE id = " + novyTitul.getId().toString();

		tm.insertUpdateDelete(celyUpdate);
	}

	public void zmazPodlaId() throws SQLException {
		TitulManager tm = new TitulManager();
		String celyUpdate = new String();

		celyUpdate = "DELETE FROM titul WHERE id = " + titulID1.getText();
		tm.insertUpdateDelete(celyUpdate);
	}

	public void zmazPodlaRoku() throws SQLException {
		TitulManager tm = new TitulManager();
		String celyUpdate = new String();

		celyUpdate = "DELETE FROM titul WHERE rok_vydania > "
				+ rokField.getText();
		tm.insertUpdateDelete(celyUpdate);
	}

	public void vyhladajPodlaAutora() throws SQLException {
		TitulManager tm = new TitulManager();
		String queryString = new String();
		ArrayList<NajdenyTitul> ntL = new ArrayList<NajdenyTitul>();
		NajdenyTitul nt = new NajdenyTitul();

		queryString = queryString
				+ "SELECT t.nazov, au.meno, t.rok_vydania, t.cena, t.stav, s.nazov AS nazovS FROM autorstvo a"
				+ " JOIN autor au ON au.id = a.id_autor"
				+ " JOIN titul t ON t.id = a.id_titul"
				+ " JOIN sklad s ON s.id = t.id_sklad" + " WHERE au.meno = '"
				+ vyhAutorField.getText() + "' AND t.nazov = '"
				+ vyhNazovField.getText() + "'";

		ntL = tm.vyhladajTitul(queryString);

		System.out.println(ntL.size());

		for (int i = 0; i < ntL.size(); i++) {
			nt = ntL.get(i);
			sb.append(("Nazov titulu: " + nt.getTitulNazov() + "\n"));
			sb.append(("Meno spisovatela: " + nt.getMeno() + "\n"));
			sb.append(("Rok vydania: " + nt.getRokVydania().toString() + "\n"));
			sb.append(("Cena: " + nt.getCena().toString() + "\n"));
			sb.append(("Stav: " + nt.getStav() + "\n"));
			sb.append(("Sklad: " + nt.getSkladNazov() + "\n\n"));
		}

		vypisSB();
	}

	public void vypisAutor() throws SQLException {
		TitulManager tm = new TitulManager();
		String queryString = new String("SELECT * FROM autor");
		ArrayList<AutorPocet> autorList = new ArrayList<AutorPocet>();
		AutorPocet ap = new AutorPocet();

		autorList = tm.getAutorZaner(queryString);

		for (int i = 0; i < autorList.size(); i++) {
			ap = autorList.get(i);
			sb.append(("ID: " + ap.getPocet() + "		"));
			sb.append(("Meno spisovatela: " + ap.getMeno() + "\n\n"));
		}
		vypisSB();
	}

	public void vypisTitul() throws SQLException {
		TitulManager tm = new TitulManager();
		String queryString = new String("SELECT * FROM titul ORDER BY id");
		ArrayList<NajdenyTitul> titulList = new ArrayList<NajdenyTitul>();
		NajdenyTitul nt = new NajdenyTitul();

		titulList = tm.getTitul(queryString);

		for (int i = 0; i < titulList.size(); i++) {
			nt = titulList.get(i);
			sb.append(("ID: " + nt.getId() + "\n"));
			sb.append(("Nazov titulu: " + nt.getTitulNazov() + "\n"));
			sb.append(("Rok vydania: " + nt.getRokVydania().toString() + "\n"));
			sb.append(("Cena: " + nt.getCena().toString() + "\n"));
			sb.append(("Stav: " + nt.getStav() + "\n\n\n"));
		}
		vypisSB();
	}

	public void vypisZaner() throws SQLException {
		TitulManager tm = new TitulManager();
		String queryString = new String("SELECT * FROM zaner");
		ArrayList<AutorPocet> zanerList = new ArrayList<AutorPocet>();
		AutorPocet ap = new AutorPocet();

		zanerList = tm.getAutorZaner(queryString);

		for (int i = 0; i < zanerList.size(); i++) {
			ap = zanerList.get(i);
			sb.append(("ID: " + ap.getPocet() + "		"));
			sb.append(("Zaner: " + ap.getMeno() + "\n\n"));
		}
		vypisSB();
	}

	public void vypisKomplet() throws SQLException {
		TitulManager tm = new TitulManager();
		String queryString = new String(
				("SELECT t.id, t.nazov, t. cena, t.rok_vydania, t.stav, "
						+ "au.meno, s.nazov AS nazovS, ty.nazov AS nazovT,"
						+ " za.nazov AS nazovZ FROM autorstvo a JOIN titul t ON a.id_titul "
						+ "= t.id JOIN autor au ON au.id = a.id_autor JOIN sklad s "
						+ "ON s.id = t.id_sklad join typ ty on ty.id = t.id_typ JOIN zanerstvo z"
						+ " ON z.id_titul = t.id JOIN zaner za ON za.id = z.id_zaner ORDER BY t.id"));
		
		ArrayList<NajdenyTitulKomplet> titulList = new ArrayList<NajdenyTitulKomplet>();
		NajdenyTitulKomplet ntk = new NajdenyTitulKomplet();

		titulList = tm.vypisKomplet(queryString);
		
		
		System.out.println(titulList.size());

		for (int i = 0; i < titulList.size(); i++) {
			ntk = titulList.get(i);
			sb.append(("ID: " + ntk.getId() + "\n"));
			sb.append(("Nazov titulu: " + ntk.getTitulNazov() + "\n"));
			sb.append(("Meno spisovatela: " + ntk.getMeno() + "\n"));
			sb.append(("Rok vydania: " + ntk.getRokVydania().toString() + "\n"));
			sb.append(("Cena: " + ntk.getCena().toString() + "\n"));
			sb.append(("Stav: " + ntk.getStav() + "\n"));
			sb.append(("Sklad: " + ntk.getSkladNazov() + "\n"));
			sb.append(("Typ: " + ntk.getNazovTypu() + "\n"));
			sb.append(("Nazov titulu: " + ntk.getNazovZanru() + "\n\n\n"));
		}

		vypisSB();
		
	}

	public void statistika1() throws SQLException {
		TitulManager tm = new TitulManager();
		String queryString = new String();
		ArrayList<AutorPocet> autorList = new ArrayList<AutorPocet>();
		AutorPocet ap = new AutorPocet();

		queryString = queryString
				+ "SELECT au.meno, count(t.id) FROM autorstvo a"
				+ " JOIN autor au ON au.id = a.id_autor"
				+ " JOIN titul t ON t.id = a.id_titul"
				+ " JOIN sklad s ON s.id = t.id_sklad" + " GROUP BY au.meno";

		autorList = tm.statistika1(queryString);

		for (int i = 0; i < autorList.size(); i++) {
			ap = autorList.get(i);
			sb.append(("Meno spisovatela: " + ap.getMeno() + "		"));
			sb.append(("Pocet knih v databaze: " + ap.getPocet() + "\n\n"));
		}
		vypisSB();
	}

	public static void initCombos() {
		ComboItem Typ1 = new ComboItem("1", "Kniha");
		ComboItem Typ2 = new ComboItem("2", "LP");
		ComboItem Typ3 = new ComboItem("3", "CD");
		ComboItem Typ4 = new ComboItem("4", "MC");

		ComboItem Sklad1 = new ComboItem("1", "Bratislava");
		ComboItem Sklad2 = new ComboItem("2", "Nieje na sklade");
		ComboItem Sklad3 = new ComboItem("3", "V predajni");

		comboBox.getItems().clear();
		comboBox.getItems().addAll(Typ1, Typ2, Typ3, Typ4);
		comboBox.setValue(Typ1);
		skladComboBox.getItems().clear();
		skladComboBox.getItems().addAll(Sklad1, Sklad2, Sklad3);
		skladComboBox.setValue(Sklad1);

		comboBox1.getItems().clear();
		comboBox1.getItems().addAll(Typ1, Typ2, Typ3, Typ4);
		comboBox1.setValue(Typ1);

		skladComboBox1.getItems().clear();
		skladComboBox1.getItems().addAll(Sklad1, Sklad2, Sklad3);
		skladComboBox1.setValue(Sklad1);
	}

	public void clear() {
		textArea.clear();
	}
}
