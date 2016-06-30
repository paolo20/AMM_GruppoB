/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author paolo
 */
public class UtentiFactory {
    
    // Attributi
    private static UtentiFactory singleton;
    String connectionString;
    
    public static UtentiFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiFactory();
        }
        return singleton;
    }
    
    //costruttore
    private UtentiFactory(){
        
    }

    
    /* Metodi */
    
    public Utente getUtente(String username, String password) {
       
        try{             
             //connetto il mio database
            Connection conn= DriverManager.getConnection(connectionString,"paolo","1234");
            // sql command
            String query= "select * from Utente where username= ? and password= ? "; 
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            /// eseguo query
            ResultSet res = stmt.executeQuery();
            
            //controllo dei risultati
            if(res.next())
            {   // qui tutti gli attributi del professore gli imposto
                boolean identita = res.getBoolean("identita");
                if(identita == false){
                    Venditore venditore=new Venditore();
                    venditore.setId(res.getInt("id_Utente"));
                    venditore.setNome(res.getString("nome"));
                    venditore.setCognome(res.getString("cognome"));
                    venditore.setUsername(res.getString("username"));
                    venditore.setPassword(res.getString("password"));
                    venditore.setSaldo(res.getDouble("saldo"));

                    stmt.close();
                    conn.close();
                    return venditore;
                }
                else{
                    Cliente cliente = new Cliente();
                    cliente.setId(res.getInt("id_Utente"));
                    cliente.setNome(res.getString("nome"));
                    cliente.setCognome(res.getString("cognome"));
                    cliente.setUsername(res.getString("username"));
                    cliente.setPassword(res.getString("password"));
                    cliente.setSaldo(res.getDouble("saldo"));
               
                    // Oggetti assegnati
                    query= "select * from Oggetto";
                    Statement st= conn.createStatement();
                    ResultSet res2=st.executeQuery(query);
                    //controllo dei ruisultati restituiti dalla query
                    while(res2.next()){
                        OggettoInVendita oggetto=new OggettoInVendita();
                        oggetto.setIdOggetto(res2.getInt("id_Oggetto"));
                        oggetto.setNomeEAutore(res2.getString("nomeEAutore"));
                        oggetto.setImage(res2.getString("image"));
                        oggetto.setDescrizione(res2.getString("descrizione"));
                        oggetto.setPrezzo(res2.getDouble("prezzo"));
                        oggetto.setQuantita(res2.getInt("quantita"));
                        cliente.getListaOggetti().add(oggetto);
                    }

                    // chiudo tutto e faccio il ritorno
                    st.close();               

                    stmt.close();
                    conn.close();
                    return cliente;
                }
                
            }
            
            stmt.close();
            conn.close();
               
        }
        catch(SQLException e){
           // errore 
           e.printStackTrace(); 
           
        }
        
        return null;   // ritorna nulla se non c'è corrispondenza
    }
    
    public Utente getCliente(int id)
    {
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
            // Query
            String query = "select * from Utente "+
                           "where id_Utente = ? "+
                           "and identita = true";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, id);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
             // ciclo sulle righe restituite
            if(res.next()) 
            {
                Cliente cliente = new Cliente();
                cliente.setId(res.getInt("id_Utente"));
                cliente.setNome(res.getString("nome"));
                cliente.setCognome(res.getString("cognome"));
                cliente.setUsername(res.getString("username"));
                cliente.setPassword(res.getString("password"));
                cliente.setSaldo(res.getDouble("saldo"));
                // Oggetti assegnati 
                query= "select * from Oggetto "+
                       "join Oggetto_Utente on Oggetto_Utente.id_Oggetto = Oggetto.id_Oggetto ";
                Statement st= conn.createStatement();
                ResultSet res2=st.executeQuery(query);
                //controllo dei ruisultati restituiti dalla query
                while(res2.next()){
                    OggettoInVendita oggetto=new OggettoInVendita();
                    oggetto.setIdOggetto(res2.getInt("id_Oggetto"));
                    oggetto.setNomeEAutore(res2.getString("nomeEAutore"));
                    oggetto.setImage(res2.getString("image"));
                    oggetto.setDescrizione(res2.getString("descrizione"));
                    oggetto.setPrezzo(res2.getDouble("prezzo"));
                    oggetto.setQuantita(res2.getInt("quantita"));
                    cliente.getListaOggetti().add(oggetto);
                }
                
                // chiudo tutto e faccio il ritorno
                st.close();               
                
                stmt.close();
                conn.close();
                return cliente;
            }   
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
     public Utente getVenditore(int id)
    {
        try 
        {
            // path, username, password
            //connetto il mio database
            Connection conn= DriverManager.getConnection(connectionString,"paolo","1234"); 
           
            // sql command
            String query= "select * from Utente where id_Utente= ? and identita= false"; 
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, id);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            //controllo dei risultati
            if(res.next())
            {   // qui tutti gli attributi del professore gli imposto
                Venditore venditore=new Venditore();
                venditore.setId(res.getInt("id_Utente"));
                venditore.setNome(res.getString("nome"));
                venditore.setCognome(res.getString("cognome"));
                venditore.setUsername(res.getString("username"));
                venditore.setPassword(res.getString("password"));
                venditore.setSaldo(res.getDouble("saldo"));
                
                // Oggetti assegnati 
                query= "select * from Oggetto "+
                       "join Oggetto_Utente on Oggetto_Utente.id_Oggetto = Oggetto.id_Oggetto where Oggetto_Utente.id_Utente= "+id;
                Statement st= conn.createStatement();
                ResultSet res2=st.executeQuery(query);
                //controllo dei ruisultati restituiti dalla query
                while(res2.next()){
                    OggettoInVendita oggetto=new OggettoInVendita();
                    oggetto.setIdOggetto(res2.getInt("id_Oggetto"));
                    oggetto.setNomeEAutore(res2.getString("nomeEAutore"));
                    oggetto.setImage(res2.getString("image"));
                    oggetto.setDescrizione(res2.getString("descrizione"));
                    oggetto.setPrezzo(res2.getDouble("prezzo"));
                    oggetto.setQuantita(res2.getInt("quantita"));
                    venditore.getListaOggetti().add(oggetto);
                }
                
                st.close();
                stmt.close();
                conn.close();
                return venditore;
                
            }
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
     
     // Dato un id restituisce il relativo oggetto
    public OggettoInVendita getOggetto(int id)
    {
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
            String query = "select * from Oggetto "+
                           "where id_Oggetto = ?";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, id);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
             // ciclo sulle righe restituite
            if(res.next()) 
            {
                OggettoInVendita oggetto = new OggettoInVendita();
                oggetto.setIdOggetto(res.getInt("id_Oggetto"));
                    oggetto.setNomeEAutore(res.getString("nomeEAutore"));
                    oggetto.setImage(res.getString("image"));
                    oggetto.setDescrizione(res.getString("descrizione"));
                    oggetto.setPrezzo(res.getDouble("prezzo"));
                    oggetto.setQuantita(res.getInt("quantita"));
   
                stmt.close();
                conn.close();
                return oggetto;
            } 
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Utente> getVenditori()
    {
        ArrayList<Utente> listaVenditori = new ArrayList<Utente>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
            Statement stmt = conn.createStatement();
            String query = "select * from Utente "+
                           "where identita= false";
            ResultSet set = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(set.next()) 
            {
                Venditore venditore=new Venditore();
                venditore.setId(set.getInt("id_Utente"));
                venditore.setNome(set.getString("nome"));
                venditore.setCognome(set.getString("cognome"));
                venditore.setUsername(set.getString("username"));
                venditore.setPassword(set.getString("password"));
                venditore.setSaldo(set.getDouble("saldo"));
                listaVenditori.add(venditore);
            }     
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return listaVenditori;
    }
    

    public ArrayList<Utente> getClienti()
    {
        ArrayList<Utente> listaClienti = new ArrayList<Utente>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
            Statement stmt = conn.createStatement();
            String query = "select * from Utente where identita= true";
            ResultSet set = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(set.next()) 
            {
                Cliente cliente = new Cliente();
                cliente.setId(set.getInt("id_Utente"));
                cliente.setNome(set.getString("nome"));
                cliente.setCognome(set.getString("cognome"));
                cliente.setUsername(set.getString("username"));
                cliente.setPassword(set.getString("password"));
                cliente.setSaldo(set.getDouble("saldo"));
                listaClienti.add(cliente);
            } 
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return listaClienti;
    } 
      
    /**
     * @return the listaOggetti
     */
    public ArrayList<OggettoInVendita> getOggetti() {
        
        ArrayList<OggettoInVendita> listaOggetti = new ArrayList<OggettoInVendita>();
        
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
            Statement stmt = conn.createStatement();
            String query = "select * from Oggetto";
            ResultSet set = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(set.next()) 
            {
                OggettoInVendita oggetto=new OggettoInVendita();
                oggetto.setIdOggetto(set.getInt("id_Oggetto"));
                oggetto.setNomeEAutore(set.getString("nomeEAutore"));
                oggetto.setImage(set.getString("image"));
                oggetto.setDescrizione(set.getString("descrizione"));
                oggetto.setPrezzo(set.getDouble("prezzo"));
                oggetto.setQuantita(set.getInt("quantita"));
                listaOggetti.add(oggetto);
            }
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return listaOggetti;
    }
    
    // Restituisce la lista di tutti gli oggetti il cui nome e descrizione contiene la stringa in input
    public ArrayList<OggettoInVendita> getOggetti(String text)
    {
        ArrayList<OggettoInVendita> lista = new ArrayList<OggettoInVendita>();
        
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
            String query = "select * from Oggetto " + 
                           "where LOWER(Oggetto.nomeEAutore) LIKE ? OR LOWER(Oggetto.descrizione) LIKE ?";         
            PreparedStatement stmt = conn.prepareStatement(query);
            // Assegna dati
            text = text.toLowerCase();
            text = "%"+text+"%";
            stmt.setString(1, text);
            stmt.setString(2, text);
            ResultSet res = stmt.executeQuery();
            
            while(res.next())
            {
                OggettoInVendita oggetto = new OggettoInVendita();
                oggetto.setIdOggetto(res.getInt("ID_OGGETTO"));
                oggetto.setNomeEAutore(res.getString("nomeEAutore"));
                oggetto.setImage(res.getString("image"));
                oggetto.setDescrizione(res.getString("descrizione"));
                oggetto.setPrezzo(res.getDouble("prezzo"));
                oggetto.setQuantita(res.getInt("quantita"));
                lista.add(oggetto);
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {}
        
        return lista;
    }
    
    public void OggettiVenditore(int idVenditore){
        
        ArrayList<OggettoInVendita> listaOggetti = new ArrayList<OggettoInVendita>();     
        Venditore venditore=new Venditore();
            
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");             
           
            String query = "select * from Oggetto join Oggetto_Utente on Oggetto.id_Oggetto=Oggetto_Utente.ID_OGGETTO where Oggetto_Utente.ID_UTENTE= "+idVenditore;
            
            Statement stmt = conn.createStatement();
            
            ResultSet set = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(set.next()) 
            {
                OggettoInVendita oggetto=new OggettoInVendita();
                oggetto.setIdOggetto(set.getInt("id_Oggetto"));
                oggetto.setNomeEAutore(set.getString("nomeEAutore"));
                oggetto.setImage(set.getString("image"));
                oggetto.setDescrizione(set.getString("descrizione"));
                oggetto.setPrezzo(set.getDouble("prezzo"));
                oggetto.setQuantita(set.getInt("quantita"));
                listaOggetti.add(oggetto);
                
            }
            
            venditore.setListaOggetti(listaOggetti);
            
            
            stmt.close();           
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void creaOggetto(int id_Venditore, String nome ,String image, String descrizione, double prezzo, int quantita) throws SQLException{
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
        PreparedStatement creaOggetto= null;
        PreparedStatement creaOggettoUtente= null;
        Statement stmt = conn.createStatement();
        
        OggettoInVendita ogg=new OggettoInVendita();
               
        ArrayList<OggettoInVendita> listaOggetti = UtentiFactory.getInstance().getOggetti();
        
        try{
            int res1,res2;
            conn.setAutoCommit(false); 
            
            String select= "select id_Oggetto from Oggetto";
            ResultSet resSelect = stmt.executeQuery(select);
            while(resSelect.next()) { ogg.setIdOggetto(resSelect.getInt("id_Oggetto")); }
            int idOggetto= ogg.getIdOggetto()+1;
            for(OggettoInVendita oggetto : listaOggetti){
               
                if( oggetto.getNomeEAutore().equals(nome) && oggetto.getPrezzo() == prezzo ){
                    
                    int q = oggetto.getQuantita() +1;
                    
                    String queryOggetto = "insert into Oggetto(id_Oggetto,nomeEAutore,image,descrizione,prezzo,quantita) "+
                       "values( "+idOggetto+" , ? , ? , ? , ? , ? )";
                    String queryOggettoUtente = "insert into Oggetto_Utente(id_Oggetto,id_Utente) "+
                       "values( "+idOggetto+" , ? )"; 
                    creaOggetto = conn.prepareStatement(queryOggetto);
                    creaOggettoUtente = conn.prepareStatement(queryOggettoUtente);
                    
                    creaOggetto.setString(1,nome);
                    creaOggetto.setString(2,image);
                    creaOggetto.setString(3,descrizione);
                    creaOggetto.setDouble(4,prezzo);
                    creaOggetto.setInt(5,quantita);
            
                    creaOggettoUtente.setInt(1,id_Venditore); 
                    
                    res1= creaOggetto.executeUpdate();
                    res2= creaOggettoUtente.executeUpdate();
            
                    if(res1 != 1 || res2 != 1 ){
                        conn.rollback();
                    }
                                       
                }else if( (idOggetto-1) == oggetto.getIdOggetto() ){       
                    
                    String queryOggetto = "insert into Oggetto(id_Oggetto,nomeEAutore,image,descrizione,prezzo,quantita) values("+idOggetto+", ? , ? , ? , ? , ? )";
                    String queryOggettoUtente = "insert into Oggetto_Utente (id_Oggetto,id_Utente) values("+idOggetto+", ? )"; 
                    creaOggetto = conn.prepareStatement(queryOggetto);
                    creaOggettoUtente = conn.prepareStatement(queryOggettoUtente);
                    
                    creaOggetto.setString(1,nome);
                    creaOggetto.setString(2,image);
                    creaOggetto.setString(3,descrizione);
                    creaOggetto.setDouble(4,prezzo);
                    creaOggetto.setInt(5,quantita);
            
                    creaOggettoUtente.setInt(1,id_Venditore); 
                    
                    res1= creaOggetto.executeUpdate();
                    res2= creaOggettoUtente.executeUpdate();
            
                    if(res1 != 1 || res2 != 1 ){
                        conn.rollback();
                    }
                }  
            }
            
                    
                    
                    //stmt.close();
            
            
        }catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
            if(creaOggetto != null){
                creaOggetto.close();
            }
            
            if(creaOggettoUtente != null){
                creaOggettoUtente.close();
            }
            if(stmt != null){
                stmt.close();
            }
            conn.setAutoCommit(true);
            conn.close();
        }
        
    }
    
    public void modificaOggetto(int id_Oggetto, String nome, String descrizione, double prezzo, int quantita) throws SQLException{
        
        // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
                      
            PreparedStatement update = null;
            
            String query = "update Oggetto set nomeEAutore = ? , descrizione = ? , prezzo = ? , quantita = ? where id_Oggetto = ? ";
            
            
        try{
            
            conn.setAutoCommit(false);
            
            update = conn.prepareStatement(query);
            update.setString(1,nome);
            update.setString(2,descrizione);
            update.setDouble(3,prezzo);
            update.setInt(4,quantita);
            update.setInt(5,id_Oggetto);
            int res = update.executeUpdate();
            
            if(res != 1 ){
                conn.rollback();
            }           
            //conn.commit();

        }catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
                       
            if(update != null){
                update.close();
            }        
                
            conn.setAutoCommit(true);
            conn.close();
        }
        
        
    }
 
    public void modificaImage(int id_Oggetto, String image) throws SQLException{
        
        // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
            
            PreparedStatement update = null;
            
        try{
            conn.setAutoCommit(false);
            
            String imageQuery= "update Oggetto set image = ?  where id_Oggetto = ? ";
            update= conn.prepareStatement(imageQuery);
            update.setString(1, image);
            update.setInt(2, id_Oggetto);
            int r = update.executeUpdate();
            
            if(r != 1){
              conn.rollback();
            }
            
        }
        catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
                        
            if(update != null){
                update.close();
            }
                        
            conn.setAutoCommit(true);
            conn.close();
        }
    }
    
    public void eliminaOggetto(int id_Oggetto, String nome, double prezzo) throws SQLException{
        
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
        PreparedStatement deleteOgg = null;
        PreparedStatement deleteOggUtente= null;
        
        ArrayList<OggettoInVendita> listaOggetti= UtentiFactory.getInstance().getOggetti();
        
        try{           
            int res1,res2;
            conn.setAutoCommit(false); 
            
            for(OggettoInVendita oggetto : listaOggetti){
                if(oggetto.getIdOggetto() == id_Oggetto && oggetto.getNomeEAutore().equals(nome) && oggetto.getPrezzo() == prezzo ){
                 
                    if(oggetto.getQuantita() == 1){
                    
                    String deleteOggetto = "delete from Oggetto where id_Oggetto= ? "; 
                    String deleteOggettoUtente = "delete from Oggetto_Utente where id_Oggetto= ? ";  
                    
                    deleteOgg = conn.prepareStatement(deleteOggetto);
                    deleteOggUtente = conn.prepareStatement(deleteOggettoUtente);
                    
                    deleteOgg.setInt(1,id_Oggetto);
                    deleteOggUtente.setInt(1,id_Oggetto);
                    
                    res2 = deleteOggUtente.executeUpdate();
                    res1 = deleteOgg.executeUpdate();
                    
                    
                    if(res1 != 1 || res2 != 1 ){
                       conn.rollback();
                    }
                    
                    oggetto.setNomeEAutore(null);
                    oggetto.setImage(null);
                    oggetto.setDescrizione(null);
                    oggetto.setPrezzo(0.0);
                    oggetto.setQuantita(0);
                    
                  }else if(oggetto.getQuantita() > 1){
                    
                    int q = oggetto.getQuantita() -1;
                    
                    String updateOggetto = "update Oggetto set quantita= "+q+"where id_Oggetto = ? "; 
                    
                    deleteOgg = conn.prepareStatement(updateOggetto);
                    
                    deleteOgg.setInt(1,id_Oggetto);
                    
                    res1 = deleteOgg.executeUpdate();
                    
                    if(res1 != 1 ){
                        conn.rollback();
                    }
                    
                    oggetto.setQuantita(q);
                  }  
                }
            }                 
            
        }catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
            if(deleteOgg != null){
                deleteOgg.close();
            }
            if(deleteOggUtente != null){
                deleteOggUtente.close();
            }
            
            conn.setAutoCommit(true);
            conn.close();
        }
    }
    
    public void compraOggetto(int id_Oggetto, String nome , double prezzo,int quantita,int numeroQuantita,int id_Cliente) throws SQLException, Exception{
        
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
        
        PreparedStatement clienteStmt= null;
        PreparedStatement venditoreStmt= null;
        PreparedStatement selectStmt= null;
        PreparedStatement aggiornaStmt= null;
        PreparedStatement eliminaStmt= null;
        PreparedStatement eliminaOggettoStmt= null;
        
        double nuovoSaldo=0.0;
        int id_Venditore=0;
        
        try{
            conn.setAutoCommit(false);
            
            Cliente cliente = new Cliente();
            cliente=(Cliente) getCliente(id_Cliente);
            
            if(cliente.getSaldo() >= prezzo){
                
                nuovoSaldo = cliente.getSaldo()- prezzo;
                String clienteQuery = "update Utente set saldo = "+nuovoSaldo+"where id_Utente= ? and identita = true ";
                clienteStmt = conn.prepareStatement(clienteQuery);
                clienteStmt.setInt(1, id_Cliente);
                int r1 = clienteStmt.executeUpdate();
                
                String select = "select * from Oggetto join Oggetto_Utente on Oggetto_Utente.id_Oggetto=Oggetto.id_Oggetto where Oggetto_Utente.id_Oggetto= ? ";
                selectStmt = conn.prepareStatement(select);
                selectStmt.setInt(1, id_Oggetto);
                ResultSet result = selectStmt.executeQuery();                
                if(result.next()){ id_Venditore = result.getInt("id_Utente");}
                
                Venditore venditore = new Venditore();
                venditore= (Venditore)getVenditore(id_Venditore);
                nuovoSaldo = venditore.getSaldo() + prezzo;
                
                String venditoreQuery = "update Utente set saldo = "+nuovoSaldo+"where id_Utente= ? and identita = false ";
                venditoreStmt = conn.prepareStatement(venditoreQuery);
                venditoreStmt.setInt(1, id_Venditore);
                int r2 = venditoreStmt.executeUpdate();
                
               
                if(numeroQuantita < quantita){
                    //AGGIORNA quantita
                    String aggiorna="update Oggetto set quantita= "+numeroQuantita+" where id_Oggetto= ? ";
                    aggiornaStmt = conn.prepareStatement(aggiorna);
                    aggiornaStmt.setInt(1, id_Oggetto);
                    int r3=aggiornaStmt.executeUpdate();
                    
                    if(r3 != 1 ){
                        conn.rollback();
                    }
                    
                }else if(numeroQuantita == quantita){
                   // cancella oggetto
                   String eliminaOggetto = "delete from Oggetto_Utente where id_Oggetto= ? ";
                   eliminaOggettoStmt = conn.prepareStatement(eliminaOggetto);
                   eliminaOggettoStmt.setInt(1, id_Oggetto);
                   int r4 = eliminaOggettoStmt.executeUpdate();                  
                   
                   String elimina="delete from Oggetto where id_Oggetto= ? ";
                   eliminaStmt = conn.prepareStatement(elimina);
                   eliminaStmt.setInt(1, id_Oggetto);
                   int r5 = eliminaStmt.executeUpdate();
                   
                   
                   
                   if(r4 != 1 || r5 != 1 ){
                        conn.rollback();
                    }
                }
                
                if(r1 != 1 || r2 != 1 ){
                    conn.rollback();
                }
                
                
            }else{
                throw new Exception("non hai abbastanza soldi");
            }
            
        }catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
            if(clienteStmt != null){
                clienteStmt.close();
            } 
            
            if(venditoreStmt != null){
                venditoreStmt.close();
            }
            
            if(selectStmt != null){
                selectStmt.close();
            }
            
            if(aggiornaStmt != null){
                aggiornaStmt.close();
            }
            
            if(eliminaStmt != null){
                eliminaStmt.close();
            }
            
            if(eliminaOggettoStmt != null){
                eliminaOggettoStmt.close();
            }
            
            conn.setAutoCommit(true);
            conn.close();
        }
    }
    
    public void ricaricaSaldo(int id_Cliente, double credito) throws SQLException{
        
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "paolo", "1234");
        
        PreparedStatement update= null;
        
        double nuovoSaldo=0.0;
        
        try{
            conn.setAutoCommit(false);
            
            Cliente cliente = new Cliente();
            cliente=(Cliente) getCliente(id_Cliente);
                
            nuovoSaldo = cliente.getSaldo()+credito;
            String query = "update Utente set saldo = "+nuovoSaldo+"where id_Utente= ? and identita = true ";
            update = conn.prepareStatement(query);
            update.setInt(1, id_Cliente);
            int r1 = update.executeUpdate();
            
            if(r1 != 1){
                conn.rollback();
            }
           
            
        }catch(SQLException e){
            conn.rollback();
            throw e;
        }finally{
            if(update != null){
                update.close();
            } 
                        
            conn.setAutoCommit(true);
            conn.close();
        }
    }
    
    // ConnectionString
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
	return this.connectionString;
    }
    
}
