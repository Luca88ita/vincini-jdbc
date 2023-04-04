package vendite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryB {
  private class QB{
    String codf;
    int anno;
    int qtyTotale;
    public QB(String codf, int anno, int qtyTotale) {
      this.codf = codf;
      this.anno = anno;
      this.qtyTotale = qtyTotale;
    }
    @Override
    public String toString() {
      return " - Cod Fornitore: " + codf + " --- " +
              " anno: " + anno + " --- " +
              " quantita' totale: " + qtyTotale;
    }
  }
  ArrayList<QB> queryB;

  public QueryB(Connection cn) throws SQLException {
    String sql = "SELECT fornisce.codf, fornisce.anno, SUM(fornisce.qty) AS 'qty totale'" +
            " FROM fornisce" +
            " GROUP BY fornisce.codf, fornisce.anno;";
    PreparedStatement prstmt = cn.prepareStatement(sql);

    ResultSet rs = prstmt.executeQuery();

    this.queryB = new ArrayList<>();

    while(rs.next()){
      QB query = new QB(rs.getString("codf"),rs.getInt("anno"),
              rs.getInt("qty totale"));
      this.queryB.add(query);
    }

    prstmt.close();
    rs.close();
  }

  @Override
  public String toString() {
    String output = "Per ogni anno la quantita' totale fornita da ciascun fornitore e' la seguente:\n";
    for (QB query : this.queryB) {
      output += query.toString();
      output += "\n";
    }
    return output;
  }
}
