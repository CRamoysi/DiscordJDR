/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Listeners;

import fr.doklaim.cramoysi.botdiscord.Command.CommandMap;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

/**
 *
 * @author CRamoysi
 */
public class BotListener implements EventListener{

    
    private final CommandMap commandMap;

    public BotListener(CommandMap commandMap) {
        this.commandMap = commandMap;
    }
    
    
    
    @Override
    public void onEvent(Event event) {
        
        System.out.println(event.getClass().getSimpleName());//recuperation du nom de l'event reçu
        
        
        if(event instanceof MessageReceivedEvent){
            onMessageReceivedEvent((MessageReceivedEvent)event);
        }
        
        
        
    }
    
    
    /**
     * Reception d'un message
     * @param mre 
     */
    private void onMessageReceivedEvent(MessageReceivedEvent mre){
        
        if(mre.getAuthor().equals(mre.getJDA().getSelfUser())) return;//si c'est le bot qui ecrit, on ne fait rien
        
        String message = mre.getMessage().getContent();
        
        if(message.startsWith(commandMap.getTag())){
            message = message.replaceFirst(commandMap.getTag(), "");
            if(commandMap.command(message, mre.getMessage())){
                System.out.println("Command success:"+message);
            }
        }
        
        
    }
    
    
    
    
    
    
}
