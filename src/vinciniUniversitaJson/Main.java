package vinciniUniversitaJson;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.Properties;
import java.io.*;

import org.json.JSONArray;
public class Main {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	public static void main(String[] args) throws Exception {
		try {
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

			PrintWriter pw = new PrintWriter(new FileWriter(file,false));
			PrintWriter rw_json = new PrintWriter(new FileWriter(filejson,false));

			Convertor ClassConvert = new  Convertor();
			JSONArray jsonConvert;

			//STEP 2: Register JDBC driver
	    Class.forName(JDBC_DRIVER);
	    //Class.forName("com.mysql.cj.jdbc.Driver");

	    //STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(URL, info);


			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql;
			String m = "M2";
			sql = "SELECT s.MATR, " +
							" s.SNOME, " +
							" s.CITTA, " +
							" s.ACORSO " +
							" FROM s " +
							" WHERE MATR = '" + m + "' " ;
			ResultSet rs = stmt.executeQuery(sql);
		  jsonConvert = ClassConvert.convertToJSON(rs);
			System.out.println ("Generato il seguente file JSON: " +jsonConvert.toString());
			jsonConvert.write(rw_json);

			//rs = stmt.executeQuery(sql);
			rs.beforeFirst();

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
			 	String  matr  = rs.getString("MATR");
				String  nome = rs.getString("SNOME");
				String citta = rs.getString("CITTA");
				int anno = rs.getInt("ACORSO");
				//Display values
				System.out.print("MATR: " + matr);
				System.out.print(", NOME: " + nome);
				System.out.print(", Città: " + citta);
				System.out.println(", Anno: " + anno);
				pw.println ("MATR: " + matr + ", NOME: " + nome + ", Città: " + citta + ", Anno: " + anno);
	      }

			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			// crea un nuovo oggetto Studente
			Studente stud = new Studente(conn, m);
			if (stud.studenteEsiste == true) System.out.println(" Trovato lo " + stud.toString());
			else System.out.println("Studente con matricola " + m + " non trovato");
			stud.matr = "M11";
			stud.nome = "Oronzo Amato";
			stud.citta = "Ferrara";
			stud.anno = 3;

			stud.inserisciStudente(conn);

			Studente stud1 = new Studente(conn, stud.matr);
			if (stud1.studenteEsiste)	System.out.println(" Trovato lo " + stud1.toString());
			else System.out.println("Studente con matricola " + stud1.matr + " non trovato");

			pw.close();
			rw_json.close ();

			//String url_php = "http://localhost/ElencoStudenti.php";
			//String url_php = "http://localhost/ElencoStudenti.php?m=M1000";
		   
		   /*
		   String url_php = "http://localhost/ElencoStudenti.php?m=M11";
		   
		   URL url = new URL(url_php);

		   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		   BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		   //BufferedReader read = new BufferedReader(new FileReader(filejson));

		   
		   String line = read.readLine();
		   String html = "";
		   while(line!=null) {
		     html += line;
		     line = read.readLine();
		   }
		   
		   System.out.println("Letto: " + html);
		            
	        JSONArray jsonArray = new JSONArray(html);
	        ArrayList<Studente> listaStudenti = new ArrayList<Studente>(); 
	        
	        // looping through all item nodes <item>    
	        for (int i = 0; i < jsonArray.length(); i++) {
	        	String c = "";
	        	System.out.print("JSON\nMatr: " + jsonArray.getJSONObject(i).getString("MATR"));
	        	System.out.print(" Nome: " + jsonArray.getJSONObject(i).getString("SNOME"));
	        	if (jsonArray.getJSONObject(i).has("citta")){
	        		System.out.print(" Città: " + jsonArray.getJSONObject(i).getString("CITTA"));
	        		c = jsonArray.getJSONObject(i).getString("CITTA");
	        	}	
	        	System.out.println(" Anno: " + jsonArray.getJSONObject(i).getInt("ACORSO"));
	        	Studente s = new Studente();
	        	s.inserisciStudenteJSON(jsonArray.getJSONObject(i).getString("MATR"), jsonArray.getJSONObject(i).getString("SNOME"), c, jsonArray.getJSONObject(i).getInt("ACORSO"));
	        	System.out.println("Aggiunto studente ");
	        	listaStudenti.add(s);
	        }		            
		    
	        System.out.print("Visualizzo ArrayList Studenti");
	        
	    	for(Studente s : listaStudenti){
	    		System.out.print("\n" + s.toString());
	    	}	

	        System.out.println("\n");
	        
	        */
	        
			stud.matr = "M11";
			stud.eliminaStudente(conn);
			conn.close();
	        
	    System.out.println("Goodbye!");
		}
		catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		}
	}
}
