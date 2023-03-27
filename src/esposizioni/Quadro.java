package esposizioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quadro {
  public String cq;
  public String autore;
  public String periodo;
  public String salaEsposizione;
  public boolean quadroEsiste;

  public Quadro(String cq, String autore, String periodo){
    this.cq = cq;
    this.autore = autore;
    this.periodo = periodo;
    this.salaEsposizione = null;
    this.quadroEsiste = true;
  }

  public Quadro(String cq, String autore, String periodo, String salaEsposizione) {
    this.cq = cq;
    this.autore = autore;
    this.periodo = periodo;
    this.salaEsposizione = salaEsposizione;
    this.quadroEsiste = true;
  }

  public Quadro(Connection cn, String codQuadro) throws SQLException {
    String sql = "SELECT quadro.cq, " +
            " quadro.autore, " +
            " quadro.periodo " +
            " FROM quadro " +
            " WHERE quadro.cq = ?";
    PreparedStatement prstmt = cn.prepareStatement(sql);
    prstmt.setString(1,codQuadro);

    ResultSet rs = prstmt.executeQuery();

    if (rs.next()){
      //Retrieve by column name
      this.cq = rs.getString("cq");
      this.autore = rs.getString("autore");
      this.periodo = rs.getString("periodo");
      this.quadroEsiste = true;
    }
    // Clean-up environment
    prstmt.close();
    rs.close();
  }

  public void inserisciQuadro(Connection cn) throws Exception {
    String sql = "INSERT INTO quadro (cq, autore, periodo) VALUES " +
            "('" + this.cq + "', '" + this.autore + "', '" + this.periodo + "')";
    executeQuery(sql, cn);
  }

  private void executeQuery(String query, Connection cn) throws SQLException {
    PreparedStatement prstmt = null;
    String sql = "SELECT * FROM quadro";
    if(this.cq.length()>0) {
      sql = sql + " WHERE cq = ?";
      prstmt = cn.prepareStatement(sql);
      prstmt.setString(1,this.cq);
      ResultSet rs = prstmt.executeQuery();
      prstmt = cn.prepareStatement(query);
      prstmt.executeUpdate();
    }
    else System.out.println("Codice quadro non valido per l'aggiornamento...");
  }

  @Override
  public String toString() {
    String output = " - "+cq +
            ", dipinto da " + autore +
            ", ed appartenente al periodo " + periodo;
    if(salaEsposizione != null){
      output += " e' stato esposto nella sala "+salaEsposizione;
    }
    output += "\n";
    return output;
  }
}
