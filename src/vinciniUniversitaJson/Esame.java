package vinciniUniversitaJson;

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

		
      	//System.out.println("\nMatricola Esame " + matricola);
    	this.matr  = matricola;
      	//System.out.println("\nCC Esame " + cc);
    	this.CodiceCorso= cc;
      	//System.out.println("\nData Esame " + dataEsame);
    	this.dataEsame = dataEsame;
      	//System.out.println("\nData Esame " + voto);
    	this.voto = voto;
      	//System.out.println("\nFinito inserimento esame");
    	this.NomeCorso = NomeCorso;
	}
	

	
    @Override
    public String toString() {
        
    	String out="";
    	
        	out += "\n Esame " +
                  " Matricola: " + this.matr +
                  " CC: " + this.CodiceCorso +
                  " Nome Corso: " + this.NomeCorso +
                  " Data: " + this.dataEsame +
                  " Voto: " + this.voto;
    		
    	return out;
    }	
}
