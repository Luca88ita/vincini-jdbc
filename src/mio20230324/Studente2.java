package mio20230324;

import java.sql.*;
import java.util.ArrayList;

public class Studente2 {
  public String matr;
  public String nome;
  public String citta;
  public int anno;
  public ArrayList<Esame2> listaEsami;
  public boolean studenteEsiste;

  public Studente2() {
    matr = "";
    nome = "";
    citta = "";
    anno = 0;
    listaEsami = null;
    studenteEsiste = false;
  }

  public Studente2(Connection cn, String matricola) throws SQLException {

    Statement stmt = null;
    String sql;
    /*
    stmt = cn.createStatement();
    sql = "SELECT s.MATR, " +
            " s.SNOME, " +
            " s.CITTA, " +
            " s.ACORSO " +
            " FROM s  " +
            " WHERE s.MATR = '" + matricola + "'";
    ResultSet rs = stmt.executeQuery(sql);
    // */

    sql = "SELECT s.MATR, " +
            " s.SNOME, " +
            " s.CITTA, " +
            " s.ACORSO " +
            " FROM s  " +
            " WHERE s.MATR = ?";
    PreparedStatement prstmt = null;
    prstmt = cn.prepareStatement(sql);
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
    //STEP6: Clean-up environment
    prstmt.close();
    rs.close();

    sql = "SELECT e.matr, e.cc, e.data, e.voto, c.cnome "+
            "FROM e,c "+
            "WHERE c.cc = e.cc "+
            "AND e.matr = ?";
    prstmt = cn.prepareStatement(sql);
    prstmt.setString(1,matricola);
    rs = prstmt.executeQuery();


    this.listaEsami = new ArrayList<Esame2>();
    while(rs.next()){
      Esame2 e = new Esame2(rs.getString("matr"),rs.getString("cc"), rs.getString("data"),rs.getInt("voto"),rs.getString("cnome") );
      this.listaEsami.add(e);
    }
  }

  public void inserisciStudente(Connection cn) throws Exception {
    String sql = sql = "INSERT INTO s (MATR, SNOME, CITTA, ACORSO) VALUES " +
            "('" + this.matr + "', '" + this.nome + "', '" + this.citta + "', " + this.anno + ")";
    executeQuery(sql, cn);
  }
  public void aggiornaStudente(Connection cn) throws Exception {
    String sql = "UPDATE s SET SNOME = '" + this.nome + "', CITTA = '" + this.citta + "', ACORSO = "
            + this.anno + "  WHERE MATR = '" + this.matr + "';";
    executeQuery(sql, cn);
  }

  public void eliminaStudente(Connection cn) throws Exception {
    String sql = "DELETE FROM s WHERE s.MATR = '" + this.matr + "'";
    executeQuery(sql, cn);
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
    else
      System.out.println("Matricola non valida per l'aggiornamento...");
  }



  @Override
  public String toString() {
    return "Studente2{" +
            "matr='" + matr + '\'' +
            ", nome='" + nome + '\'' +
            ", citta='" + citta + '\'' +
            ", anno=" + anno +
            ", listaEsami=" + listaEsami +
            ", studenteEsiste=" + studenteEsiste +
            '}';
  }
}
