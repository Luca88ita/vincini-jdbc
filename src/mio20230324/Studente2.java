package mio20230324;

import vinciniUniversita.Esame;

import java.sql.*;
import java.util.ArrayList;

public class Studente2 {
  public String matr;
  public String nome;
  public String citta;
  public int anno;
  public ArrayList<Esame2> listaEsami;
  public float mediaEsami;
  public boolean studenteEsiste;

  public Studente2() {
    matr = "";
    nome = "";
    citta = "";
    anno = 0;
    listaEsami = null;
    mediaEsami = 0;
    studenteEsiste = false;
  }

  public Studente2(Connection cn, String matricola) throws SQLException {

    String sql = "SELECT s.MATR, " +
            " s.SNOME, " +
            " s.CITTA, " +
            " s.ACORSO " +
            " FROM s  " +
            " WHERE s.MATR = ?";
    PreparedStatement prstmt = cn.prepareStatement(sql);
    prstmt.setString(1,matricola);

    ResultSet rs = prstmt.executeQuery();

    if (rs.next()){
      //Retrieve by column name
      this.matr = rs.getString("matr");
      this.nome = rs.getString("snome");
      this.citta = rs.getString("citta");
      this.anno = rs.getInt("acorso");
      this.studenteEsiste = true;
    }
    // Clean-up environment
    prstmt.close();
    rs.close();

    sql = "SELECT e.matr, e.cc, e.data, e.voto, c.cnome, d.dnome "+
            "FROM e,c,d "+
            "WHERE c.cc = e.cc "+
            "AND c.cd = d.cd "+
            "AND e.matr = ?";
    prstmt = cn.prepareStatement(sql);
    prstmt.setString(1,matricola);
    rs = prstmt.executeQuery();

    this.listaEsami = new ArrayList<>();
    while(rs.next()){
      Esame2 e = new Esame2(rs.getString("matr"),rs.getString("cc"), rs.getString("data"),rs.getInt("voto"),rs.getString("cnome"), rs.getString("dnome") );
      this.listaEsami.add(e);
    }

    // Clean-up environment
    prstmt.close();
    rs.close();

    sql = "SELECT AVG(e.voto) AS 'media'"+
            "FROM e,s "+
            "WHERE e.matr = s.matr "+
            "AND e.matr = ?";
    prstmt = cn.prepareStatement(sql);
    prstmt.setString(1,matricola);
    rs = prstmt.executeQuery();
    if (rs.next()){
      this.mediaEsami = rs.getFloat("media");
    }

  }

  public void inserisciStudente(Connection cn) throws Exception {
    String sql = "INSERT INTO s (MATR, SNOME, CITTA, ACORSO) VALUES " +
            "('" + this.matr + "', '" + this.nome + "', '" + this.citta + "', " + this.anno + ")";
    executeQuery(sql, cn);
  }
  public void aggiornaStudente(Connection cn) throws Exception {
    String sql = "UPDATE s SET SNOME = '" + this.nome + "', CITTA = '" + this.citta + "', ACORSO = "
            + this.anno + "  WHERE MATR = '" + this.matr + "';";
    executeQuery(sql, cn);
  }

  public boolean eliminaStudente(Connection cn) throws Exception {
    String sql = "DELETE FROM s WHERE s.MATR = '" + this.matr + "'";
    if(checkGivenExams(cn)){
      executeQuery(sql, cn);
      return true;
    }
    return false;
  }

  private boolean checkGivenExams(Connection cn) throws SQLException{
    PreparedStatement prstmt = null;
    String sql = "SELECT matr FROM e ";
    if(this.matr.length()>0) {
      sql = sql + " WHERE MATR = ?";
      prstmt = cn.prepareStatement(sql);
      prstmt.setString(1,this.matr);
      ResultSet rs = prstmt.executeQuery();
      return !rs.isBeforeFirst();
    }
    return false;
  }

  private void executeQuery(String query, Connection cn) throws SQLException {
    PreparedStatement prstmt = null;
    String sql = "SELECT * FROM s ";
    if(this.matr.length()>0) {
      sql = sql + " WHERE MATR = ?";
      prstmt = cn.prepareStatement(sql);
      prstmt.setString(1,this.matr);
      ResultSet rs = prstmt.executeQuery();
      prstmt = cn.prepareStatement(query);
      prstmt.executeUpdate();
    }
    else System.out.println("Matricola non valida per l'aggiornamento...");
  }

  @Override
  public String toString() {
    String output = "studente " + nome+
            ", registrato con matricola '" + matr + "'" +
            ", residente a " + citta +
            " e frequentante il " + anno + "o anno";
    if (listaEsami.isEmpty()){
      output = output + ", non ha ancora sostenuto esami: \n";
    }else{
      output = output + ", ha sostenuto i seguenti esami:\n";
      for(Esame2 e : this.listaEsami){
        output += e.toString();
      }
      output += " ed ha una media di "+mediaEsami;
    }
    return output;
  }
}
