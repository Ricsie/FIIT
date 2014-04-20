package Antik;

public class NajdenyTitul {
	private String titulNazov;
	private String skladNazov;
	private String meno;
	private Double cena;
	private String stav;
	private Integer rokVydania;
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulNazov() {
		return titulNazov;
	}
	public void setTitulNazov(String titulNazov) {
		this.titulNazov = titulNazov;
	}
	public String getSkladNazov() {
		return skladNazov;
	}
	public void setSkladNazov(String skladNazov) {
		this.skladNazov = skladNazov;
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public String getStav() {
		return stav;
	}
	public void setStav(String stav) {
		this.stav = stav;
	}
	public Integer getRokVydania() {
		return rokVydania;
	}
	public void setRokVydania(Integer rokVydania) {
		this.rokVydania = rokVydania;
	}
}
