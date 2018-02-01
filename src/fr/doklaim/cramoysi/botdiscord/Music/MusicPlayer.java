/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;

/**
 *
 * @author CRamoysi
 */
public class MusicPlayer {
    
    private final AudioPlayer audioPlayer;
    private final AudioListener listener;
    private final Guild guild;
    
    public MusicPlayer(AudioPlayer audioPlayer, Guild guild){
        this.audioPlayer = audioPlayer;
        this.guild = guild;
        listener = new AudioListener(this);
        audioPlayer.addListener(listener);
    }
    
    public AudioPlayer getAudioPlayer(){
        return audioPlayer;
    }
    
    public Guild getGuild(){
        return guild;
    }
    
    public AudioListener getListener(){
        return listener;
    }
    
    public AudioHandler getAudioHandler(){
        return new AudioHandler(audioPlayer);
    }
    
    public synchronized void playTrack(AudioTrack track){
        listener.queue(track);
    }
    
   public synchronized void skipTrack(){
       listener.nextTrack();
   }
    
}
