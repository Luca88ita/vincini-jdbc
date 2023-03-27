package esposizioni;

import mio20230324.Esame2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mostra {
  public String cm;
  public String nome;
  public int anno;
  public String organizzatore;
  public ArrayList<Quadro> listaQuadri;
  public boolean mostraEsiste;

  public Mostra() {
    cm = "";
    nome = "";
    anno = 0;
    organizzatore = "";
    listaQuadri = null;
    mostraEsiste = false;
  }

  public Mostra(Connection cn, String codMostra) throws SQLException {

    String sql = "SELECT mostra.cm, " +
            " mostra.nome, " +
            " mostra.anno, " +
            " mostra.organizzatore " +
            " FROM mostra  " +
            " WHERE mostra.cm = ?";
    PreparedStatement prstmt = cn.prepareStatement(sql);
    prstmt.setString(1,codMostra);

    ResultSet rs = prstmt.executeQuery();

    if (rs.next()){
      //Retrieve by column name
      this.cm = rs.getString("cm");
      this.nome = rs.getString("nome");
      this.anno = rs.getInt("anno");
      this.organizzatore = rs.getString("organizzatore");
      this.mostraEsiste = true;
    }
    // Clean-up environment
    prstmt.close();
    rs.close();

    sql = "SELECT quadro.cq, quadro.autore, quadro.periodo, espone.sala "+
            "FROM quadro, espone, mostra "+
            "WHERE quadro.cq = espone.cq "+
            "AND espone.cm = mostra.cm "+
            "AND espone.cm = ?";
    prstmt = cn.prepareStatement(sql);
    prstmt.setString(1,codMostra);
    rs = prstmt.executeQuery();

    this.listaQuadri = new ArrayList<>();
    while(rs.next()){
      Quadro quadro = new Quadro(rs.getString("cq"),rs.getString("autore"),
              rs.getString("periodo"), rs.getString("sala"));
      this.listaQuadri.add(quadro);
    }
  }

  public void inserisciMostra(Connection cn) throws Exception {
    String sql = "INSERT INTO mostra (cm, nome, anno, organizzatore) VALUES " +
            "('" + this.cm + "', '" + this.nome + "', " + this.anno + ", '" + this.organizzatore + "')";
    executeQuery(sql, cn);
  }
  public void aggiornaMostra(Connection cn) throws Exception {
    String sql = "UPDATE mostra SET nome = '" + this.nome + "', anno = " + this.anno + ", organizzatore = '"
            + this.organizzatore + "'  WHERE cm = '" + this.cm + "';";
    executeQuery(sql, cn);
  }

  public boolean eliminaMostra(Connection cn) throws Exception {
    String sql = "DELETE FROM s WHERE mostra.cm = '" + this.cm + "'";
    if(checkPaintings(cn)){
      executeQuery(sql, cn);
      return true;
    }
    return false;
  }

  private boolean checkPaintings(Connection cn) throws SQLException{
    PreparedStatement prstmt = null;
    String sql = "SELECT cm FROM espone ";
    if(this.cm.length()>0) {
      sql = sql + " WHERE cm = ?";
      prstmt = cn.prepareStatement(sql);
      prstmt.setString(1,this.cm);
      ResultSet rs = prstmt.executeQuery();
      return !rs.isBeforeFirst();
    }
    return false;
  }

  private void executeQuery(String query, Connection cn) throws SQLException {
    PreparedStatement prstmt = null;
    String sql = "SELECT * FROM mostra";
    if(this.cm.length()>0) {
      sql = sql + " WHERE cm = ?";
      prstmt = cn.prepareStatement(sql);
      prstmt.setString(1,this.cm);
      ResultSet rs = prstmt.executeQuery();
      prstmt = cn.prepareStatement(query);
      prstmt.executeUpdate();
    }
    else System.out.println("Codice mostra non valida per l'aggiornamento...");
  }

  @Override
  public String toString() {
    String output = "mostra " + nome+
            ", registrato con codice '" + cm + "'" +
            ", organizzata da " + organizzatore +
            " e' stata svolta nel " + anno;
    if (listaQuadri.isEmpty()){
      output = output + "; non sono stati esposti quadri\n";
    }else{
      output = output + "; sono stati esposti i seguenti quadri:\n";
      for(Quadro quadro : this.listaQuadri){
        output += quadro.toString();
      }
    }
    return output;
  }
}
