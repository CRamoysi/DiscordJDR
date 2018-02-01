/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Command.standard;

import fr.doklaim.cramoysi.botdiscord.Command.Command;
import fr.doklaim.cramoysi.botdiscord.Command.Command.ExecutorType;
import java.awt.Color;
import fr.doklaim.cramoysi.botdiscord.jdrbot.JdrBot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

/**
 *
 * @author CRamoysi
 */
public class CommandStandard {
    private final JdrBot jdrBot;

    public CommandStandard(JdrBot jdrBot) {
        this.jdrBot = jdrBot;
    }
    
    @Command(name="stop",type=ExecutorType.CONSOLE)
    private void Stop(){
        System.out.println("stop");
        jdrBot.setRunning(false);
    }
    
    @Command(name = "ping", type = ExecutorType.USER, description = "Retourne pong")
    private void Ping(MessageChannel chan){
        chan.sendMessage("pong!").queue();
    }
    
    @Command(name = "info", type = ExecutorType.USER, description = "Affiche les informations sur le serveur.")
    private void Info(MessageChannel chan, User user, Guild guild){
        
        if(chan instanceof TextChannel){
            TextChannel textChannel = (TextChannel)chan;
            if(!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS)) return;
        }
        
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(user.getName(), null, user.getAvatarUrl()+"?size=256");
        eb.setTitle("Informations");
        
        if(guild != null){
            eb.setDescription("[>](1) Guild: "+ guild.getName()
                    + "\n[>](2) Channel: " + chan.getName()
            );
        }else{
            eb.setDescription("[>](1) Channel: " + chan.getName());
        }
        eb.setColor(Color.green);
        
        chan.sendMessage(eb.build()).queue();
        
         
    }
}
