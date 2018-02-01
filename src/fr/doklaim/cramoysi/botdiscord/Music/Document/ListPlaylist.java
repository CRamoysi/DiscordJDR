/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Music.Document;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CRamoysi
 */
public class ListPlaylist{
    
    public String author;
    final public List<PlayList> playlists = new ArrayList<>();
    
    
    
    
    public ListPlaylist(String author){
        this.author = author;
    }
    
    public ListPlaylist(String author, List<PlayList> list){
        this(author);
        list.forEach((pl) -> {
            this.playlists.add(pl);
        });
    }
    
    
    
    
    
    
    
    
    
    public String getAuthor(){
        return author;
    }
    
    public List<PlayList> getListPlayList(){
        return playlists;
    }


    
    
//    public MongoCollection get(){
//     
//    }
    
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("$$$$$$\n");
        sb.append("Author: ").append(author).append("\n");
        
        playlists.forEach((m) -> {
            sb.append(m.toString());
        });
        
        return sb.toString();
    }
    
    
    
    
    
    
}
