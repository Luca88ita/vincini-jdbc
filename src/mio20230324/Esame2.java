package mio20230324;

import java.sql.Connection;
import java.sql.Statement;

public class Esame2 {

  public String matricola;
  public String codiceCorso;
  public String nomeCorso;
  public String data;
  public int voto;

  public Esame2(String matricola, String codiceCorso, String data, int voto, String nomeCorso) {
    this.matricola = matricola;
    this.codiceCorso = codiceCorso;
    this.nomeCorso = nomeCorso;
    this.data = data;
    this.voto = voto;
  }

  /*public mio20230324.Esame2(Connection cn, String esame){
    Statement stmt = null;
    String sql;
  }*/

  @Override
  public String toString() {
    return "Esame2{" +
            "matricola='" + matricola + '\'' +
            ", codiceCorso='" + codiceCorso + '\'' +
            ", nomeCorso='" + nomeCorso + '\'' +
            ", data='" + data + '\'' +
            ", voto=" + voto +
            '}';
  }
}
