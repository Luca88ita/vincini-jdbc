package mio20230324;

import vinciniUniversita.Studente;

import java.io.FileWriter;
import java.io.IOException;
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
      info.put( "user", "fanni92" );
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
      System.out.println("Tentativo di connessione al database...");
      conn = DriverManager.getConnection(URL, info);
      System.out.println("Connessione stabilita\n");
      Studente2 stud = new Studente2(conn, "m1");
      System.out.println("Lo "+stud);

      // dichiaro i dati dello studente ricercato (potrei ricevere i dati in input)
      String matr = "M14";
      String nome = "Panco Pinco";
      String citta = "RO";
      int anno = 2;

      // dichiaro se voglio eliminare lo studente trovato, o se voglio solo modificarne i dati
      boolean elimina = true;

      // qui vado a creare l'oggetto Studente2 stud2
      Studente2 stud2 = new Studente2(conn, matr);

      // ne verifico l'esistenza e procedo con le operazioni d'inserimento || modifica || eliminazione
      if(stud2.studenteEsiste){
        System.out.println("Trovato lo "+stud2);
        if (elimina){
          System.out.println("Procedo con la sua eliminazione...");
          boolean eliminationResult = stud2.eliminaStudente(conn);
          if(eliminationResult){
            System.out.print("Eliminato lo ");
          }else{
            System.out.print("ERRORE!!! Con degli esami registrati e' impossibile eliminare lo ");
          }
        }else{
          System.out.println("Procedo con l'aggiornamento dei dati...");
          stud2.matr = matr;
          stud2.nome = nome;
          stud2.citta = citta;
          stud2.anno = anno;
          stud2.aggiornaStudente(conn);
          System.out.print("Aggiornati i dati dello ");
        }
      }else {
        System.out.println("Studente non trovato: procedo con l'inserimento dei dati");
        stud2.matr = matr;
        stud2.nome = nome;
        stud2.citta = citta;
        stud2.anno = anno;
        stud2.inserisciStudente(conn);
        System.out.print("Inserito il nuovo ");
      }
      System.out.println(stud2);

    } catch (Exception e) {
      System.out.println("Errore: "+e);
    }
  }

}
