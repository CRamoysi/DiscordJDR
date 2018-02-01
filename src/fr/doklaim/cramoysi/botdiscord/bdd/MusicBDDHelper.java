/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.bdd;

import com.mongodb.MongoException;
import fr.doklaim.cramoysi.botdiscord.jdrbot.BDDHelper;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CRamoysi
 */
public class MusicBDDHelper {
    
    
    
    
    /**
     * Si la connexion n'existe pas, on la créé
     */
    private int createConnection(){
        try {
            BDDHelper.init();
            return 0;
        } catch(UnknownHostException ex){
            Logger.getLogger(MusicBDDHelper.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (MongoException ex) {
            Logger.getLogger(MusicBDDHelper.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        } catch (Exception ex) {
            Logger.getLogger(MusicBDDHelper.class.getName()).log(Level.SEVERE, null, ex);
            return -3;
        }
    }
    
    
    public void createPlaylist(String name, String author){
        
    }
    
    
    
}
