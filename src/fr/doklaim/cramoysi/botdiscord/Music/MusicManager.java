/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import java.util.HashMap;
import java.util.Map;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 *
 * @author CRamoysi
 */
public class MusicManager {
    
    private final AudioPlayerManager manager = new DefaultAudioPlayerManager();
    private final Map<String, MusicPlayer> players = new HashMap<>();

    public MusicManager() {
        AudioSourceManagers.registerRemoteSources(manager);
        AudioSourceManagers.registerLocalSource(manager);
    }
    
    public synchronized MusicPlayer getPlayer(Guild guild){
        if(!players.containsKey(guild.getId())){//Si le musicManager de la Guild n'est pas dans la liste
            //On creer et rajoute dans la liste un MusicManager pour la Guild
            players.put(guild.getId(), new MusicPlayer(manager.createPlayer(), guild));
        }
        return players.get(guild.getId());
    }
    
    public void loadTrack(final TextChannel channel, final String source){
        MusicPlayer player = getPlayer(channel.getGuild());
        channel.getGuild().getAudioManager().setSendingHandler(player.getAudioHandler());
        manager.loadItemOrdered(player, source, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack at) {
                channel.sendMessage("Ajout de la musique: "+at.getInfo().title).queue();
                player.playTrack(at);
            }

            @Override
            public void playlistLoaded(AudioPlaylist ap) {
                StringBuilder builder = new StringBuilder();
                builder.append("Ajout de la playlist **").append(ap.getName()).append("**\n");
                
                for(int i = 0; i < ap.getTracks().size() && i < 5; i++){
                    AudioTrack track = ap.getTracks().get(i);
                    builder.append("\n **->** ").append(track.getInfo().title);
                    player.playTrack(track);
                }
                
                channel.sendMessage(builder.toString()).queue();
                
            }

            @Override
            public void noMatches() {
                channel.sendMessage("La musique "+source+" n'a pas été trouvée").queue();
            }

            @Override
            public void loadFailed(FriendlyException fe) {
                channel.sendMessage("Impossible de jouer la musique "+source+ " (raison: " +fe.getMessage()+")").queue();
            }
        });
        
    }
}
