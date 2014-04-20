package Antik;

public class Titul {



	private String nazov;
	private String rok_vydania;
	private String stav;
	private String cena;
	private String typ;
	private String sklad;	
	private Integer id;

	public String getNazov() {
		return nazov;
	}

	public String getCena() {
		return cena;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRok_vydania() {
		return rok_vydania;
	}

	public void setRok_vydania(String rok_vydania) {
		this.rok_vydania = rok_vydania;
	}

	public String getStav() {
		return stav;
	}

	public void setStav(String stav) {
		this.stav = stav;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String string) {
		this.typ = string;
	}

	public String getSklad() {
		return sklad;
	}

	public void setSklad(String sklad) {
		this.sklad = sklad;
	}

	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	public void setCena(String string) {
		this.cena = string;
	}


	public Titul(){

	}


	public Titul(String nazov, String rok_vydania, String stav, String cena)
	{
		this.nazov = nazov;
		this.rok_vydania = rok_vydania;
		this.stav = stav;
		this.cena = cena;
	}
}
