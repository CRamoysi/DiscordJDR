/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author CRamoysi
 */
public class AudioListener extends AudioEventAdapter{
    
    private final BlockingQueue<AudioTrack> tracks = new LinkedBlockingQueue<>();
    private final MusicPlayer player;
    
    public AudioListener(MusicPlayer player){
        this.player = player;
    }
    
    public BlockingQueue<AudioTrack> getTracks(){
        return tracks;
    }
    
    public int getTrackSize(){
        return tracks.size();
    }
    
    public void nextTrack(){
        if(tracks.isEmpty()){ //cas où la liste de musques est vide
            if(player.getGuild().getAudioManager().getConnectedChannel() != null){ //si le player est connecté au moment où la liste est vide
                player.getGuild().getAudioManager().closeAudioConnection();//on deco le bot
            }
            return;
        }
        //liste pas vide
        player.getAudioPlayer().startTrack(tracks.poll(), false);//false pour forcer le passage à la musique suivante
    }
    
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason){
        if(endReason.mayStartNext) nextTrack();
    }

    public void queue(AudioTrack track) {
        if(!player.getAudioPlayer().startTrack(track, true)) tracks.offer(track);
    }
    
    
    
    
    
}
