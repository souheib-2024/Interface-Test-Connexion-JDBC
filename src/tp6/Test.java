package tp6;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Test extends JDBCTest{

	Connection cnx = null; 
	 Test() { this("", ""); } 
	  
	 Test(String driver, String url) { 
	  nomDriver.setText(driver); 
	  urlConnection.setText(url); 
	  motPasse.setEchoChar('*'); 
	 } 
	 
	 public void actionPerformed(ActionEvent e) { 
	  Button b = (Button)(e.getSource()); 
	  try { 
	   if((b==boutonConnection) && (cnx == null)) { 
	    System.out.println("dans CONNECT"); 
	    String driver=new String(nomDriver.getText()); 
	    String url=new String(urlConnection.getText()); 
	    String login=new String(nomLogin.getText()); 
	    String passwd=new String(motPasse.getText()); 
	     
	    Class.forName(driver); 
	    cnx=DriverManager.getConnection(url,login,passwd); 
	   } 
	   else if ((b == boutonConnection) && (cnx !=null)) 
	    System.out.println("connection deja etablie"); 
	   else if ((b == boutonExecuter) && (cnx != null)) { 
	    System.out.println("dans EXECUTE"); 
	    Statement stmt = cnx.createStatement(); 
	    resultatRequete.removeAll(); 
	    String requete=new String(requeteSQL.getText()); 
	    ResultSet rs = stmt.executeQuery(requete); 
	    ResultSetMetaData rsmd=rs.getMetaData(); 
	 
	    int nbcol = rsmd.getColumnCount(); 
	    String titre=""; 
	    String resultat; 
	    for(int i = 0; i < nbcol; i++)  
	     titre+=" : "+rsmd.getColumnName(i+1); 
	    resultatRequete.add(titre); 
	    while(rs.next()) { 
	     resultat = new String(); 
	     for(int i=0; i < nbcol; i++) 
	     { 
	       resultat+=" : "+rs.getString(i+1); 
	       System.out.println(rs.getString(i+1)); 
	     } 
	     resultatRequete.add(resultat); 
	     System.out.println("requete = " + resultat); 
	 
	    } 
	    rs.close(); 
	    stmt.close(); 
	   } 
	   else System.out.println("connection non etablie ou cassee"); 
	  } 
	  catch(SQLException ei) { 
	   ei.printStackTrace(); 
	  } 
	  catch(ClassNotFoundException ei2) { 
	   ei2.printStackTrace(); 
	  } } 
	  public static void main(String[] arg) 
	  { 
	          Test test; 
	   Frame f = new Frame(); 
	   f.setSize(500, 400); 
	   String driver = "com.mysql.cj.jdbc.Driver";
	   String url = "jdbc:mysql://localhost:3306/ParcInformatique";
	   test = new Test(driver, url);
	   f.add(test, BorderLayout.CENTER); 
	   f.addWindowListener(new WindowAdapter() { 
	   public void windowClosing(WindowEvent e) 
	   { 
	    System.exit(0); 
	   }}); 
	  f.setVisible(true); 
	 } 

}
