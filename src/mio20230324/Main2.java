package mio20230324;

import vinciniUniversita.Studente;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

public class Main2 {

  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

  public static void main(String[] args) {

    try{
      Connection conn = null;
      Statement stmt = null;
      String file = "studente.txt";
      String filejson = "studente.json";

      String URL = "jdbc:mysql://localhost/ifts_universita";
      Properties info = new Properties( );
      info.put( "user", "root" );
      info.put( "password", "root" );
      info.put( "autoReconnect", "true" );
      info.put( "useSSL", "false" );
      info.put( "serverTimezone", "Europe/Amsterdam" );

      PrintWriter pw  = new PrintWriter(new FileWriter(file, false));
      PrintWriter rw_json = new PrintWriter(new FileWriter(filejson, false));

      //Convertor ClassConvert = new Convertor ();
      //JSONArray jsonConvert;

      //STEP2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP3: Open a connection
      System.out.println("Connecting to the database");
      conn = DriverManager.getConnection(URL, info);

      Studente2 stud = new Studente2(conn, "m1");
      System.out.println(stud.toString());

      String m = "M14";
      boolean elimina = true;
      Studente2 stud2 = new Studente2(conn, m);
      if(stud2.studenteEsiste == true){
        if (elimina){
          System.out.println("Trovato lo studente "+stud2.toString());
          System.out.println("Procedo con la sua eliminazione");
          stud2.eliminaStudente(conn);
          System.out.println("Eliminato lo studente "+stud2.toString());
        }else{
          System.out.println("Trovato lo studente "+stud2.toString());
          System.out.println("Procedo con l'aggiornamento dei dati");
          stud2.matr = m;
          stud2.nome = "Panco Pinco";
          stud2.citta = "RO";
          stud2.anno = 2;
          stud2.aggiornaStudente(conn);
          System.out.println("Aggiornati i dati dello studente "+stud2.toString());
        }
      }else {
        stud2.matr = m;
        stud2.nome = "Pinco Pallino";
        stud2.citta = "FE";
        stud2.anno = 3;
        stud2.inserisciStudente(conn);
        System.out.println("Inserito il nuovo studente "+stud2.toString());
      }


    }catch(Exception e){
      System.out.println("Errore: "+e);
    }
  }

}
