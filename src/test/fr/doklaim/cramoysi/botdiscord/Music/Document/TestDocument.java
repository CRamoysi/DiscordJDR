/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.fr.doklaim.cramoysi.botdiscord.Music.Document;

import fr.doklaim.cramoysi.botdiscord.Music.Document.ListPlaylist;
import fr.doklaim.cramoysi.botdiscord.Music.Document.Music;
import fr.doklaim.cramoysi.botdiscord.Music.Document.PlayList;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CRamoysi
 */
public class TestDocument {
    
    public TestDocument() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void testMusicDocument(){
        List<Music> listmusic = new ArrayList<>();
        listmusic.add(new Music("http://music1", "CRamoysi#7241"));
        listmusic.add(new Music("http://music2", "CRamoysi#7241"));
        listmusic.add(new Music("http://music3", "CRamoysi#7241"));
        listmusic.add(new Music("http://music4", "CRamoysi#7241"));
        listmusic.add(new Music("http://music5", "CRamoysi#7241"));
        listmusic.add(new Music("http://music6", "CRamoysi#7241"));
        
        
        PlayList pl = new PlayList("Ma playlist", "CRamoysi#7241", listmusic);
        
        List<PlayList> playlists = new ArrayList<>();
        playlists.add(pl);
        
        ListPlaylist lpl = new ListPlaylist("CRamoysi#7241", playlists);
        
        
        System.out.println(lpl);
    }
    
    public static void main(String[] args) {
        TestDocument td = new TestDocument();
        td.testMusicDocument();
    }
    
}
