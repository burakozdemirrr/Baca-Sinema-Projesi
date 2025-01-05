package model;

public class SeansParametre {
	int SeansNo;
	String FilmAdi;
	String FilmTarihi;
	String FilmSaati;
	int SalonNo;
	int Kapasite;
	double BiletFiyati;
	int BiletSayisi;
	public int getBiletAdedi() {
		return BiletSayisi;
	}
	public void setBiletAdedi(int biletAdedi) {
		BiletSayisi = biletAdedi;
	}
	public int getSeansNo() {
		return SeansNo;
	}
	public void setSeansNo(int seansNo) {
		SeansNo = seansNo;
	}
	public String getFilmAdi() {
		return FilmAdi;
	}
	public void setFilmAdi(String filmAdi) {
		FilmAdi = filmAdi;
	}
	public String getFilmTarihi() {
		return FilmTarihi;
	}
	public void setFilmTarihi(String filmTarihi) {
		FilmTarihi = filmTarihi;
	}
	public String getFilmSaati() {
		return FilmSaati;
	}
	public void setFilmSaati(String filmSaati) {
		FilmSaati = filmSaati;
	}
	public int getSalonNo() {
		return SalonNo;
	}
	public void setSalonNo(int salonNo) {
		SalonNo = salonNo;
	}
	public int getKapasite() {
		return Kapasite;
	}
	public void setKapasite(int kapasite) {
		Kapasite = kapasite;
	}
	public double getBiletFiyati() {
		return BiletFiyati;
	}
	public void setBiletFiyati(double biletFiyati) {
		BiletFiyati = biletFiyati;
	}
}
