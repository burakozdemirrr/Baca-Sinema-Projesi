package model;

public class Kullanici {
	private int KullaniciNo;
    private String AdSoyad;
    private String KullaniciAdi;
    private String eMail;
    private String Sifre;
	
    public int getKullaniciNo() {
		return KullaniciNo;
	}
	public void setKullaniciNo(int kullaniciNo) {
		KullaniciNo = kullaniciNo;
	}
	public String getAdSoyad() {
		return AdSoyad;
	}
	public void setAdSoyad(String adSoyad) {
		AdSoyad = adSoyad;
	}
	public String getKullaniciAdi() {
		return KullaniciAdi;
	}
	public void setKullaniciAdi(String kullaniciAdi) {
		KullaniciAdi = kullaniciAdi;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getSifre() {
		return Sifre;
	}
	public void setSifre(String sifre) {
		Sifre = sifre;
	}
}
