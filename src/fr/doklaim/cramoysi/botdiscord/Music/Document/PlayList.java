/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Music.Document;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author CRamoysi
 */
public class PlayList extends Document{
    private String name;
    private String author;
    private final List<Music> playlist = new ArrayList<>();
    
    
    public PlayList(String name, String author){
        this.name = name;
        this.author = author;
    }
    
    public PlayList(String name, String author, final List<Music> plist){
        this(name, author);
        
        plist.forEach((m) -> {
            this.playlist.add(m);
        });
    }
    
    
    public void add(Music m){
        this.playlist.add(m);
    }
    
    public Document getDocument(){
        Document doc = new Document()
            .append("name", name)
            .append("author", author)
            .append("list", playlist)
        ;
        
        return doc;
    }
    

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("==========\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("\n");
        playlist.forEach((m)-> {
           sb.append(m.toString()).append("\n");
        });
        
        return sb.toString();
    }
    
    
    
}
