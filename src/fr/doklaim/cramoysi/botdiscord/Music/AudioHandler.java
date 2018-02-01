/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

/**
 *
 * @author CRamoysi
 */
public class AudioHandler implements AudioSendHandler{

    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;
    
    public AudioHandler(AudioPlayer audioPlayer){
        this.audioPlayer = audioPlayer;
    }
    
    @Override
    public boolean canProvide() {
        if(lastFrame == null){
            lastFrame = audioPlayer.provide();
        }
        return lastFrame != null;
    }

    @Override
    public byte[] provide20MsAudio() {
        byte[] data = canProvide() ? lastFrame.data : null;
        lastFrame = null;
        
        return data;
    }
    
    @Override
    public boolean isOpus(){
        return true;
    }
    
}
