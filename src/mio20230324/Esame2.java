package mio20230324;

import java.sql.Connection;
import java.sql.Statement;

public class Esame2 {

  public String matricola;
  public String codiceCorso;
  public String nomeCorso;
  public String data;
  public int voto;
  public String docente;

  public Esame2(String matricola, String codiceCorso, String data, int voto, String nomeCorso, String docente) {
    this.matricola = matricola;
    this.codiceCorso = codiceCorso;
    this.nomeCorso = nomeCorso;
    this.data = data;
    this.voto = voto;
    this.docente = docente;
  }

  /*public mio20230324.Esame2(Connection cn, String esame){
    Statement stmt = null;
    String sql;
  }*/

  @Override
  public String toString() {
    String output = " - "+nomeCorso +
            ", codice corso '" + codiceCorso + "'" +
            ", tenuto dal prof. " + docente +
            ", e' stato superato con voto " + voto +
            " dalla matricola '" + matricola + "'" +
            " in data " + data+"\n";
    return output;
  }
}
