package vendite;

import java.sql.*;
import java.util.Properties;

public class Main {
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

  public static void main(String[] args) {
    try{
      Connection conn = null;
      Statement stmt = null;

      String URL = "jdbc:mysql://localhost/vendite";
      Properties info = new Properties( );
      info.put( "user", "root" );
      info.put( "password", "root" );
      info.put( "autoReconnect", "true" );
      info.put( "useSSL", "false" );
      info.put( "serverTimezone", "Europe/Amsterdam" );

      Class.forName(JDBC_DRIVER);

      System.out.println("Tentativo di connessione al database...");
      conn = DriverManager.getConnection(URL, info);
      System.out.println("Connessione stabilita\n");

      /* stampa del prodotto P1 */
      System.out.println("------------------------------");
      Prodotto p1 =  new Prodotto(conn, "P1");
      System.out.println("Il "+p1);

      /* stampa della lista di prodotti per fornitore per anno */
      System.out.println("------------------------------");
      QueryB queryB = new QueryB(conn);
      System.out.println(queryB);


    }catch (Exception e){
      System.out.println("Errore: "+e);
    }
  }
}
