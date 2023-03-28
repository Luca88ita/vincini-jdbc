package esposizioni;

import mio20230324.Studente2;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Main {
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

  public static void main(String[] args) {

    try{
      Connection conn = null;
      Statement stmt = null;
      //String file = "esposzioni.txt";
      //String filejson = "esposizioni.json";

      String URL = "jdbc:mysql://localhost/ifts_2023_esposizioni";
      Properties info = new Properties( );
      info.put( "user", "fanni92" );
      info.put( "password", "root" );
      info.put( "autoReconnect", "true" );
      info.put( "useSSL", "false" );
      info.put( "serverTimezone", "Europe/Amsterdam" );

      //PrintWriter pw  = new PrintWriter(new FileWriter(file, false));
      //PrintWriter rw_json = new PrintWriter(new FileWriter(filejson, false));

      //Convertor ClassConvert = new Convertor ();
      //JSONArray jsonConvert;

      //STEP2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP3: Open a connection
      System.out.println("Tentativo di connessione al database...");
      conn = DriverManager.getConnection(URL, info);
      System.out.println("Connessione stabilita\n");

      Mostra mostra = new Mostra(conn, "M5");
      System.out.println("La "+mostra);

      // dichiaro i dati dello studente ricercato (potrei ricevere i dati in input)
      String cm = "M9";
      String nome = "Mostra9";
      int anno = 1998;
      String organizzatore = "Organizzatore 1";

      // dichiaro se voglio eliminare la mostra trovata, o se voglio solo modificarne i dati
      boolean eliminaMostra = false;

      // qui vado a creare l'oggetto Mostra mostra
      Mostra mostra2 = new Mostra(conn, cm);

      // ne verifico l'esistenza e procedo con le operazioni d'inserimento || modifica || eliminazione
      if(mostra2.mostraEsiste){
        System.out.println("Trovata la "+mostra2);
        if (eliminaMostra){
          System.out.println("Procedo con la sua eliminazione...");
          boolean eliminationResult = mostra2.eliminaMostra(conn);
          if(eliminationResult){
            System.out.print("Eliminata la ");
          }else{
            System.out.print("ERRORE!!! Con dei quadri esposti e' impossibile eliminare la ");
          }
        }else{
          System.out.println("Procedo con l'aggiornamento dei dati...");
          mostra2.cm = cm;
          mostra2.nome = nome;
          mostra2.anno = anno;
          mostra2.organizzatore = organizzatore;
          mostra2.aggiornaMostra(conn);
          System.out.print("Aggiornati i dati della ");
        }
      }else {
        System.out.println("Mostra non trovata: procedo con l'inserimento dei dati");
        mostra2.cm = cm;
        mostra2.nome = nome;
        mostra2.anno = anno;
        mostra2.organizzatore = organizzatore;
        mostra2.inserisciMostra(conn);
        System.out.print("Inserita la nuova ");
      }
      System.out.println(mostra2);

      String cq = "Q12";
      String autore = "Autore2";
      String periodo = "Periodo5";

      // dichiaro se voglio eliminare la mostra trovata, o se voglio solo modificarne i dati
      boolean eliminaQuadro = false;

      // qui vado a creare l'oggetto Quadro quadro
      Quadro quadro = new Quadro(conn, cq);

      if(quadro.quadroEsiste){
        System.out.println("Trovato il "+quadro);
        if (eliminaQuadro){
          /*
          System.out.println("Procedo con la sua eliminazione...");
          boolean eliminationResult = quadro.eliminaQuadro(conn);
          if(eliminationResult){
            System.out.print("Eliminato il ");
          }else{
            System.out.print("ERRORE!!! Impossibile eliminare il quadro ");
          }*/
          System.out.print("ERRORE!!! Impossibile eliminare il quadro ");
        }else{
          System.out.print("ERRORE!!! Impossibile inserire il quadro perché già esistente");
          /*
          System.out.println("Procedo con l'aggiornamento dei dati...");
          quadro.cq = cm;
          quadro.autore = autore;
          quadro.periodo = periodo;
          quadro.aggiornaQuadro(conn);
          System.out.print("Aggiornati i dati del ");*/
        }
      }else {
        System.out.println("Quadro non trovato: procedo con l'inserimento dei dati");
        quadro.cq = cq;
        quadro.autore = autore;
        quadro.periodo = periodo;
        quadro.inserisciQuadro(conn);
        System.out.print("Inserito il nuovo ");
      }
      System.out.println(quadro);

    } catch (Exception e) {
      System.out.println("Errore: "+e);
    }
  }
}
