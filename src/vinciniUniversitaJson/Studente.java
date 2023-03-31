package vinciniUniversitaJson;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Studente {
	
	public String  matr ;
	public String  nome;
	public String citta;
	public int anno;
	public ArrayList<Esame> listaEsami;
	public boolean studenteEsiste;
	
	public Studente() {

		matr = "";
		nome = "";
		citta = "";
		anno = 0;
		listaEsami = null;
		studenteEsiste = false;
	}
	
	public Studente(Connection cn, String matricola) throws Exception {

		Statement stmt = null;
		//PreparedStatement Prstmt = null;
		
		stmt = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String sql;
	    sql = "SELECT s.MATR, " +
              " s.SNOME, " +
              " s.CITTA, " +
              " s.ACORSO " +
              " FROM s  " +
              " WHERE s.MATR = '" + matricola + "'";
		
/*	    
	    sql = "SELECT s.MATR, " +
	              " s.SNOME, " +
	              " s.CITTA, " +
	              " s.ACORSO " +
	              " FROM s  " +
	              " WHERE s.MATR = ?";
			
	    Prstmt = cn.prepareStatement(sql);
	    Prstmt.setString(1,matricola);
*/	    
	    ResultSet rs = stmt.executeQuery(sql);
	    //ResultSet rs = Prstmt.executeQuery();
		
	    if(rs.next()){
		         //Retrieve by column name
	    	this.matr  = rs.getString("MATR");
	    	this.nome = rs.getString("SNOME");
	    	this.citta = rs.getString("CITTA");
	    	this.anno = rs.getInt("ACORSO");
			this.studenteEsiste = true;
	    		
		      //STEP 6: Clean-up environment
		    rs.close();
	
		    sql = "SELECT e.MATR, " +
		              " e.CC, " +
		              " e.DATA, " +
		              " e.VOTO, " +
		              " c.CNOME " +
		              " FROM e, c  " +
		              " WHERE c.CC = e.CC AND " + 
		              " e.MATR = '" + matricola + "'";
				
		    rs = stmt.executeQuery(sql);
	
		    this.listaEsami = new ArrayList<Esame>();
		    while(rs.next()){
		         //Retrieve by column name
		    	
		      	//System.out.println("\nEsame " + rs.getString("MATR"));
		      	Esame e = new Esame(rs.getString("MATR"),rs.getString("CC"), rs.getString("DATA"),rs.getInt("VOTO"),rs.getString("CNOME") );
		      	//System.out.println("\nMi appresto ad inserire esame ");
			   	this.listaEsami.add(e);  
		      	//System.out.println("\nInserito esame");
		    }
		    
		    
	
	    }
	    else
	    {

			this.matr = "";
			this.nome = "";
			this.citta = "";
			this.anno = 0;
			this.listaEsami = null;
			this.studenteEsiste = false;
		}

	    rs.close();
	    
	    stmt.close();
	    
	}

	public void inserisciStudente(Connection cn) throws Exception {

		Statement stmt = null;

		stmt = cn.createStatement();

		String sql;
	    sql = "SELECT s.MATR " +
              " FROM s  " +
              " WHERE s.MATR = '" + this.matr + "'";
		
	    ResultSet rs = stmt.executeQuery(sql);
		
	    if(!rs.next()){
	    	
	    	cn.setAutoCommit(false);
		    sql = "INSERT INTO s(MATR, SNOME, CITTA, ACORSO) " +
		              " VALUES ('" + this.matr + "','" + this.nome + "','" +this.citta + "'," + this.anno + ")";        
				
		    stmt.executeUpdate(sql);
		    sql = "DELETE FROM e " +
		          "WHERE MATR = '" + this.matr + "'";
		    stmt.executeUpdate(sql);

        	for(Esame e : this.listaEsami){
    		    sql = "INSERT INTO e(MATR, CC, DATA, VOTO) " +
  		              " VALUES ('" + this.matr + "','" + e.CodiceCorso + "','" + e.dataEsame + "'," + e.voto + ")";        
  				
    		    stmt.executeUpdate(sql);
        		
        	}
        	cn.commit();
        	cn.setAutoCommit(true);
	    }
	    else {
	    	System.out.println("Studente gi� esistente " + this.matr );
	    	
		    sql = "UPDATE s SET " +
		    	  " SNOME = '" + this.nome + "' ,"+
		    	  " CITTA = '" + this.citta + "' , " +
		    	  " ACORSO = " + this.anno +
				  " WHERE MATR = '"  + this.matr + "'";

		    stmt.executeUpdate(sql);

		    stmt.executeUpdate(sql);
		    sql = "DELETE FROM e " +
		          "WHERE MATR = '" + this.matr + "'";
		    stmt.executeUpdate(sql);

        	for(Esame e : this.listaEsami){
    		    sql = "INSERT INTO e(MATR, CC, DATA, VOTO) " +
  		              " VALUES ('" + this.matr + "','" + e.CodiceCorso + "','" + e.dataEsame + "'," + e.voto + ")";        
  				
    		    stmt.executeUpdate(sql);
        		
        	}	

        	System.out.println("Ho aggiornato lo Studente");

	    }

	    rs.close();
	    stmt.close();
	    
	}

	public void inserisciStudenteJSON(String  matr, String  nome, String citta, Integer anno) throws Exception {

		this.matr = matr;
		this.nome = nome;
		this.citta = citta;
		//this.anno = Integer.parseInt(anno);
		this.anno = (anno);
		this.listaEsami = null;
		this.studenteEsiste = true;

    
	}
	

	
	public void eliminaStudente(Connection cn) throws Exception {

		Statement stmt = null;
		stmt = cn.createStatement();
		String sql;
	    sql = "SELECT COUNT(*) AS NumEsami " +
              " FROM e  " +
              " WHERE e.MATR = '" + this.matr + "'";
		
	    ResultSet rs = stmt.executeQuery(sql);		
    	
	    if(rs.next()){

	    	Integer contaEsami = rs.getInt("NumEsami");	 
	    	
	    	if (contaEsami == 0) {
			    sql = "DELETE FROM s  " +
			          " WHERE s.MATR = '" + this.matr + "'";
					
			    stmt.executeUpdate(sql);
		    	System.out.println("Studente eliminato con successo");
		    }
		    else {
		    	System.out.println("Lo studente ha sostenuto " + contaEsami  + " esami, non lo posso eliminare");

		    }
	    }


	    rs.close();
	    stmt.close();
	    
	}

	
    @Override
    public String toString() {
        
    	String out="";
    	
    	out = "Studente{ " +
                "Matricola= " + matr +
                ", Nome= " + nome+ " " +
                ", Citt�= " + citta + " " +
                ", Anno di corso=" + anno +
                " }";

    	if (this.listaEsami != null)
    	for(Esame e : this.listaEsami){
        	out += e.toString();
    	}	
    	return out;
    }
	
}
