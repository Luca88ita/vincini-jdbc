package vinciniUniversitaJson;

import java.io.*;
import java.util.*;

public class JsonStudente  {
	
	public String  matr ;
	public String  nome;
	public String citta;
	public int anno;
	
	
	public JsonStudente (String  matr, String  nome, String  citta, int anno  ) {
		this.matr = matr;
		this.nome = nome;
		this.citta = citta;
		this.anno = anno;
	}

	
	 public String toString() {
	        
	    	String out="";
	    	
	    	out = "Studente{ " +
	                "Matricola= " + matr +
	                ", Nome= " + nome+ " " +
	                ", Citt�= " + citta + " " +
	                ", Anno di corso=" + anno +
	                " }";

	    	return out;
	    }
}
