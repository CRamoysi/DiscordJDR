/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Music.Document;

import org.bson.Document;



/**
 *
 * @author CRamoysi
 */
public class Music extends Document{
    private String URI;
    private String author;
    private String name = null;
    private String duration = null;
    

    public Music(String URI, String author){
        this.URI = URI;
        this.author = author;
    }
    
    public Music(String URI, String author, String name, String duration){
        this(URI, author);
        this.name = name;
        this.duration = duration;
    }
    
    
    /**
     * 
     * @return Document
     */
    public Document getDocument(){
        
        Document doc = new Document()
            .append("URI", URI)
            .append("author", author)
        ;
        
        if(name != null) doc.append("name", name);
        if(duration != null) doc.append("duration", duration);
        
        return doc;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder()
          .append("---------\n")
          .append("URI = ").append(URI).append("\n")
          .append("author = ").append(author).append("\n")
        ;
        if(name != null) sb.append("name = ").append(name).append("\n");
        if(duration != null) sb.append("duration = ").append(duration).append("\n");
        
        return sb.toString();
    }
    
    
    
    
    
}
