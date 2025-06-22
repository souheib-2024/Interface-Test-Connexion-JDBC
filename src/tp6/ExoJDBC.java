package tp6;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExoJDBC {

	public static Connection cx; 
	 public static ResultSet rs, rs2; 
	 public static Statement etat; 
	 public static Statement etatModifiable; 
	 public static CallableStatement cetat; 
	  
	 public static ArrayList getSalles()  
	 { 
	   ArrayList tableauRésultat = new ArrayList(); 
	   try { 
	     etat = cx.createStatement(); 
	    rs = etat.executeQuery("SELECT * FROM Salle"); 
	    String [] ligne = null; 
	   while (rs.next()) { 
	    ligne = new String[4]; 
	    ligne[0] = rs.getString(1); 
	    ligne[1] = rs.getString(2); 
	    ligne[2] = (new Integer(rs.getInt(3))).toString(); 
	    ligne[3] = rs.getString(4); 
	    tableauRésultat.add(ligne); 
	    } 
	   rs.close(); 
	   etat.close(); 
	   } 
	  catch (SQLException ex) { 
	      while (ex != null) { 
	       System.out.println ("Statut SQL  : "+ex.getSQLState()); 
	     System.out.println ("Message     : "+ex.getMessage()); 
	     System.out.println ("Code erreur : "+ex.getErrorCode()); 
	     ex = ex.getNextException(); 
	     } 
	   } 
	  return tableauRésultat; 
	  } 
	   
	 public static void deleteSalle(int nl)  
	 {     
	  try { 
	   etatModifiable = 
	cx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
	            ResultSet.CONCUR_UPDATABLE); 
	  cx.setAutoCommit(false); 
	  rs2 = etatModifiable.executeQuery("SELECT s.* FROM Salle s"); 
	  if (rs2.absolute(nl))  
	    { rs2.deleteRow(); cx.commit(); 
	    System.out.println("Salle supprimée");}  
	  else System.out.println("Désolé, pas de "+ nl +" ème salle !"); 
	  rs2.close(); 
	  etatModifiable.close(); 
	      } 
	  catch (SQLException ex) { 
	      while (ex != null) { 
	       System.out.println ("Statut SQL  : "+ex.getSQLState()); 
	     System.out.println ("Message     : "+ex.getMessage()); 
	     System.out.println ("Code erreur : "+ex.getErrorCode()); 
	     ex = ex.getNextException(); 
	     } 
	    } 
	  } 
	  
	 public static void main(String args[]) throws SQLException, Exception 
	 { 
	 try { 
	     System.out.println("Initialisation de la connexion"); 
	  Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	   } catch (ClassNotFoundException ex) 
	             { System.out.println ("Problème au chargement"+ex.toString()); 
	} 
	    try  
	     { 
	     cx = 
	DriverManager.getConnection("jdbc:mysql://localhost:3306/ParcInformatique", "root", "motdepasse"); 
	  
	 ArrayList lignes = getSalles(); 
	 System.out.println("Liste des salles :\n"); 
	 System.out.println("nSalle\tnomSalle  \tnbPoste\tindIP"); 
	 System.out.println("------------------------------------------"); 
	 String[] lig; 
	 for (int i=0;i<lignes.size();i++)  
	 { lig=(String [])lignes.get(i); 
	  System.out.println(lig[0]+"  \t"+lig[1]+"  \t"+lig[2]+"  \t"+lig[3]);} 
	 System.out.println(); 
	 
	//Pb intégrité 
	// System.out.println("Salle 21 à supprimer"); 
	// deleteSalle(6); 
	   
	// dernère nouvelle salle pas rattachée 
	// System.out.println("Salle 77 à supprimer"); 
	// deleteSalle(7); 
	 
	 
	  
	 } 
	 catch (SQLException ex) { 
	    while (ex != null) { 
	     System.out.println ("Statut SQL  : "+ex.getSQLState()); 
	  System.out.println ("Message     : "+ex.getMessage()); 
	  System.out.println ("Code erreur : "+ex.getErrorCode()); 
	  ex = ex.getNextException(); } } 
	 }

}
