/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Command.standard;

import fr.doklaim.cramoysi.botdiscord.Command.Command;
import fr.doklaim.cramoysi.botdiscord.Command.Command.ExecutorType;
import fr.doklaim.cramoysi.botdiscord.Command.CommandMap;
import fr.doklaim.cramoysi.botdiscord.Command.SimpleCommand;
import java.awt.Color;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 *
 * @author CRamoysi
 */
public class CommandHelp {
    
    private final CommandMap commandMap;
    
    public CommandHelp(CommandMap commandMap){
        this.commandMap = commandMap;
    }
    
    
    
    @Command(name="help", type=ExecutorType.USER, description = "Liste des commandes disponible")
    private void help(MessageChannel chan) throws NoSuchMethodException{
        System.out.println(getClass().getName()+"");
        EmbedBuilder eb = new EmbedBuilder();
        //eb.setTitle(getClass().getMethod("help").getAnnotation(Command.class).description());
        eb.setTitle("HELP\n");
        eb.setColor(Color.yellow);
        
        for(SimpleCommand sc : commandMap.getCommands()){
            if(!(sc.getExecutorType()== Command.ExecutorType.USER )) continue;
            String desc = sc.getDescription();
            
            eb.addField(sc.getName(), desc, false);
        }
        
        chan.sendMessage(eb.build()).queue();
        
        
    }
    
    
    
    
}
