/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Command.troll;

import fr.doklaim.cramoysi.botdiscord.Command.Command;
import java.util.Random;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 *
 * @author CRamoysi
 */
public class CommandTroll {
    
    
    @Command(name="cr",type = Command.ExecutorType.USER, description = "Permet de retourner la reponse à la vie, à l'univers et à tout ce qui existe.")
    public void cr(MessageChannel chan){
        
        String[] l = {
            "CRamoysi est trop beau",
            "CRamoysi est trop fort",
            "CRamoysi est le meilleur support au monde",
            "CRamoysi marry me!!!!!",
            "CRamoysi t'es tellement mieux que Shaolan!"
        };
        
        chan.sendMessage(""+l[new Random().nextInt(l.length)]).queue();   
    }
    
    @Command(name="rekt",type = Command.ExecutorType.USER, description = "GET REKT")
    public void rekt(MessageChannel chan){
        
        String[] l = {
            "Vos mères les chauves",
            "va sucer une commode",
            "Vu ta gueule, ça doit pas te gener qu'on flame ta mère",
            "pdsousrace",
            "Wallah que non",
            "Avoues, tu sais pas lequel est ton pere!",
            "Ramasse ta chaise"
        };
        
        chan.sendMessage(""+l[new Random().nextInt(l.length)]).queue();   
    }
    
    @Command(name="rektal",type = Command.ExecutorType.USER, description = "GET REKTALIZED")
    public void rektal(MessageChannel chan){
        rekt(chan);
    }
    
    @Command(name="42",type = Command.ExecutorType.USER, description = "42")
    public void get42(MessageChannel chan){
        chan.sendMessage("42").queue();   
    }
    
    @Command(name="alex",type = Command.ExecutorType.USER, description = "Donne des astuces à Alex")
    public void alex(MessageChannel chan){
         String[] l = {
            "Arretes de feed, putain!",
            "Joues pas adc",
            "Joues pas mid",
            "Joues pas support",
            "Joues pas top",
            "Joues pas jungle"
        };
        
        chan.sendMessage(""+l[new Random().nextInt(l.length)]).queue();
    }
    
    
    @Command(name="wesh",type = Command.ExecutorType.USER, description = "WESH")
    public void wesh(MessageChannel chan){
        chan.sendMessage("Wesh").queue();
    }
    
    @Command(name="monica?",type = Command.ExecutorType.USER, description = "WESH")
    public void monica(MessageChannel chan){
        chan.sendMessage("Monica le grizzli!").queue();
    }
    
}