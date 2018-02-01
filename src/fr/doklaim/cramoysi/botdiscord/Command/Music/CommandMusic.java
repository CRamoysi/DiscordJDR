/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Command.Music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import fr.doklaim.cramoysi.botdiscord.Command.Command;
import fr.doklaim.cramoysi.botdiscord.Music.MusicManager;
import fr.doklaim.cramoysi.botdiscord.Music.MusicPlayer;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;

/**
 *
 * @author CRamoysi
 */
public class CommandMusic {
    private final MusicManager manager = new MusicManager();
    
    @Command(name="play", type = Command.ExecutorType.USER, description = "play LAMUSIC pour jouer LAMUSIC")
    private void play(Guild guild, TextChannel textChannel, User user, String command){
        if(guild == null) return;
        
        if(!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect()){
            VoiceChannel voiceChannel = guild.getMember(user).getVoiceState().getChannel();
            if(voiceChannel == null){
                textChannel.sendMessage("Vous devez etre connecté au channel pour commander de la musique").queue();
                return;
            }
            guild.getAudioManager().openAudioConnection(voiceChannel);
        }
        
        manager.loadTrack(textChannel, command.replaceFirst("play ", ""));
       // if(manager.getPlayer(guild).getAudioPlayer().getPlayingTrack().) guild.getAudioManager().closeAudioConnection();
    }
    
    @Command(name = "skip", type = Command.ExecutorType.USER, description = "skip  pour passer la musique courrante")
    private void skip(Guild guild, TextChannel textChannel){
        if(!guild.getAudioManager().isAutoReconnect() && !guild.getAudioManager().isAttemptingToConnect()){
            textChannel.sendMessage("Le bot ne joue plus de musique").queue();
            return;
        }
        
        manager.getPlayer(guild).skipTrack();
        textChannel.sendMessage("Musique suivante: "+manager.getPlayer(guild).getAudioPlayer().getPlayingTrack().getInfo().title).queue();
    }
    
    @Command(name = "clear", type = Command.ExecutorType.USER, description = "clear  pour clear la playlist")
    private void clear(TextChannel textChannel){
        MusicPlayer player = manager.getPlayer(textChannel.getGuild());
        
        if(player.getListener().getTracks().isEmpty()){
            textChannel.sendMessage("Playlist deja vide").queue();
            return;
        }
        
        player.getListener().getTracks().clear();
        textChannel.sendMessage("Playlist vidée").queue();
    }
    
    @Command(name="current", type = Command.ExecutorType.USER, description = "Pour afficher la musique en cours")
    public void current(Guild guild, TextChannel textChannel){
        if(guild == null) return;
        if(!guild.getAudioManager().isConnected()) return;
        /**
         Music en cours: TITLE
         Durée: DURATION
         SOURCE: URI
         */
        
        EmbedBuilder eb = new EmbedBuilder();
        
        eb.setTitle(manager.getPlayer(guild).getAudioPlayer().getPlayingTrack().getInfo().title, manager.getPlayer(guild).getAudioPlayer().getPlayingTrack().getInfo().uri);
        eb.setColor(Color.WHITE);
        
        Date date = new Date(manager.getPlayer(guild).getAudioPlayer().getPlayingTrack().getInfo().length);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateFormatted = formatter.format(date);
        
        eb.addField("Durée: ", dateFormatted, false);
        eb.addField("URI: ", manager.getPlayer(guild).getAudioPlayer().getPlayingTrack().getInfo().uri, false);
        
        textChannel.sendMessage(eb.build()).queue();
    }
    
    
    @Command(name = "playlist", type = Command.ExecutorType.USER,description = "Affiche la playlist en cours")
    public void playlist(Guild guild, TextChannel textChannel){
        if(guild == null) return;
        if(!guild.getAudioManager().isConnected()) return;
        
        MusicPlayer player = manager.getPlayer(guild);
        if(player.getListener().getTracks().isEmpty()){
            textChannel.sendMessage("Playlist vide").queue();
            return;
        }
        
        EmbedBuilder eb = new EmbedBuilder();
        
        eb.setTitle("Playlist en cours!");
        eb.setColor(Color.WHITE);
        
        Date date = null;
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateFormatted = null;
        
        for(AudioTrack at : player.getListener().getTracks()){
            date = new Date(at.getInfo().length);
            dateFormatted = formatter.format(date);
            
            eb.addField(at.getInfo().title, "Durée: "+dateFormatted+"\nURI: "+at.getInfo().uri, false);
        }
        
        textChannel.sendMessage(eb.build()).queue();
    }
    
    @Command(name = "pause", type = Command.ExecutorType.USER,description = "Met en pause la musique")
    public void pause(Guild guild, TextChannel textChannel){
        if(guild == null) return;
        if(!guild.getAudioManager().isConnected()) return;
        
        MusicPlayer player = manager.getPlayer(guild);
        if(player.getAudioPlayer().isPaused()) return;
        player.getAudioPlayer().setPaused(true);
        
        
    }
    
    @Command(name = "resume", type = Command.ExecutorType.USER,description = "Enleve la pause")
    public void resume(Guild guild, TextChannel textChannel){
        if(guild == null) return;
        if(!guild.getAudioManager().isConnected()) return;
        
        MusicPlayer player = manager.getPlayer(guild);
        if(!player.getAudioPlayer().isPaused()) return;
        player.getAudioPlayer().setPaused(false);
        
    }
    
    public void loop(Guild guild){
        if(guild == null) return;
        if(!guild.getAudioManager().isConnected()) return;
        
        MusicPlayer player = manager.getPlayer(guild);
        boolean loop = true;
        if(player.getAudioPlayer().getPlayingTrack() != null && loop){
            AudioTrack at = player.getAudioPlayer().getPlayingTrack();
            player.getListener().onTrackEnd(player.getAudioPlayer(), player.getAudioPlayer().getPlayingTrack(), AudioTrackEndReason.FINISHED);
            
            player.getAudioPlayer().playTrack(at);
            
        }
        
        
        
    }
    
}
