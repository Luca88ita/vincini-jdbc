package vendite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prodotto {
  String codp;
  String descrizione;
  int prezzo;

  public Prodotto(Connection cn, String codProdotto) throws SQLException{
    String sql = "SELECT prodotto.codp, " +
            " prodotto.descrizione, " +
            " prodotto.prezzo " +
            " FROM prodotto  " +
            " WHERE prodotto.codp = ?";
    PreparedStatement prstmt = cn.prepareStatement(sql);
    prstmt.setString(1,codProdotto);

    ResultSet rs = prstmt.executeQuery();

    if (rs.next()){
      this.codp = rs.getString("codp");
      this.descrizione = rs.getString("descrizione");
      this.prezzo = rs.getInt("prezzo");
    }

    prstmt.close();
    rs.close();
  }

  @Override
  public String toString() {
    return "Prodotto con codice " + codp +
            " sono dei " + descrizione +
            " e costano " + prezzo +
            " euro\n";
  }
}
