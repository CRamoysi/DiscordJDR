package fr.doklaim.cramoysi.botdiscord.jdrbot;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.net.UnknownHostException;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author CRamoysi
 */
public class BDDHelper {

    private static MongoClient mongoC = null;
    private static MongoDatabase db = null;
    
    public static void init()throws UnknownHostException, MongoException
, Exception{
        if(mongoC != null && db != null) return;
        mongoC = new MongoClient(Config.BDD_HOST, Config.BDD_PORT);
        db = mongoC.getDatabase(Config.BDD_BASENAME);
    }
    /**
     * Recupere une table
     * @param tblName
     * @return MongoCollection<Document>
     */
    public static MongoCollection<Document> getCollection(String tblName){  
        return db.getCollection(tblName);
    }
    
    /**
     * Ajouter un tuple à la table
     * @param d
     * @param tbl 
     */
    public static void add(Document d, String tbl){
        getCollection(tbl).insertOne(d);
    }
    /**
     * Ajouter plusieurs tuples à la table
     * @param ds
     * @param tbl 
     */
    public static void adds(List<Document> ds, String tbl){
        getCollection(tbl).insertMany(ds);
    }
    
    
    public static Document get(String cond, String tbl){
        
        return null;
    }
    
}
