package vinciniUniversita;

import java.sql.Date;



public class Esame {

	public String  matr ;
	public String  CodiceCorso;
	public String  NomeCorso;	
	public String dataEsame;
	public int voto;
	
	public Esame() {

		matr = "";
		CodiceCorso = "";
		NomeCorso = "";
		dataEsame = "";
		voto = 0;
	}

	public Esame(String matricola, String cc, String dataEsame, int voto, String NomeCorso) throws Exception {

    	this.matr  = matricola;
    	this.CodiceCorso= cc;
    	this.dataEsame = dataEsame;
    	this.voto = voto;
    	this.NomeCorso = NomeCorso;
	}


	@Override
	public String toString() {
		
		String out="";
		
		out = "vinciniUniversita.Esame [NomeCorso=" + NomeCorso + ", dataEsame=" + dataEsame
				+ ", voto=" + voto + "]";
		return out;
	}
	

	
	
}
