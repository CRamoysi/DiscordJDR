/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Command;


import fr.doklaim.cramoysi.botdiscord.Command.Command.ExecutorType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.doklaim.cramoysi.botdiscord.Command.DiceRoller.CommandDiceRoller;
import fr.doklaim.cramoysi.botdiscord.Command.Music.CommandMusic;
import fr.doklaim.cramoysi.botdiscord.Command.standard.CommandHelp;
import fr.doklaim.cramoysi.botdiscord.Command.standard.CommandStandard;
import fr.doklaim.cramoysi.botdiscord.Command.troll.CommandTroll;
import fr.doklaim.cramoysi.botdiscord.jdrbot.JdrBot;
import fr.doklaim.cramoysi.botdiscord.jdrbot.KeyWords;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

/**
 *
 * @author CRamoysi
 */
public final class CommandMap {
    private final Map<String, SimpleCommand> commands = new HashMap<>();
    private final String tag = KeyWords.KEY;
    
    private final JdrBot jdrBot;
    
    
    public CommandMap(JdrBot jdrBot){
        this.jdrBot = jdrBot;
        
        registerCommand(new CommandStandard(jdrBot));
        registerCommand(new CommandDiceRoller(jdrBot));
        registerCommand(new CommandHelp(this));
        registerCommand(new CommandTroll());
        registerCommand(new CommandMusic());
    }
    
    /**
     * 
     * @return 
     */
    public String getTag() {
        return tag;
    }
    
    /**
     * 
     * @return 
     */
    public Collection<SimpleCommand> getCommands(){
        return commands.values();
    }
        

    public void registerCommands(Object...objects){
        for(Object object: objects){
            registerCommand(object);
        }
    }
    
    public void registerCommand(Object object){
        for(Method method: object.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(Command.class)){
                Command command = method.getAnnotation(Command.class);
                method.setAccessible(true);
                SimpleCommand simpleCommand = new SimpleCommand(command.name(), command.description(), command.type(), object, method);
                commands.put(command.name(), simpleCommand);
            }
        }
    }
    
    public boolean command(String command, Message message){
        Object[] object = getCommand(command);
        if(object[0] == null){
            return false;
        }
        
        try{
            if(((SimpleCommand)object[0]).getExecutorType() == ExecutorType.CONSOLE){
                execute(((SimpleCommand)object[0]), command, (String[])object[1], null);
            }else if(((SimpleCommand)object[0]).getExecutorType() == ExecutorType.USER){
                execute(((SimpleCommand)object[0]), command, (String[])object[1], message);
            }
        }catch(Exception e){
            System.err.println("Method "+((SimpleCommand)object[0]).getMethod().getName()+" init failed");
            System.err.println(e);
        }
        return true;
        
    }

    private Object[] getCommand(String command) {
        String[] commandSplit = command.split(" ");
        String[] args = new String[commandSplit.length-1];
        for(int i = 1; i < commandSplit.length; i++){
            args[i-1] = commandSplit[i];
        }
        SimpleCommand simpleCommand = this.commands.get(commandSplit[0]);
        
        return new Object[]{simpleCommand, args};
    }

    /**
     * 
     * @param String[] parameters of the command
     * @param User the User who type the command (Object User from JDA)
     * @param TextChannel the TextChannel in which the command has been typed
     * @param PrivateChannel the PrivateChannel in which the command has been typed
     * @param Guild the Guild in which the command has been typed
     * @param String the command typed
     * @param Message the Message typed (Object Message from JDA)
     * @param JDA the JDA object
     * @param MessageChannel the MessageChannel in which the command has been typed
     * 
     * @throws Exception 
     */
    private void execute(SimpleCommand simpleCommand, String command, String[] args, Message message) throws Exception {
        Parameter[] parameters = simpleCommand.getMethod().getParameters();
        Object[] objects = new Object[parameters.length];
        
        for(int i = 0; i < parameters.length; i++){
            if(parameters[i].getType() == String[].class) objects[i] = args;
            else if(parameters[i].getType() == User.class) objects[i] = message == null ? null : message.getAuthor();
            else if(parameters[i].getType() == TextChannel.class) objects[i] = message == null ? null : message.getTextChannel();
            else if(parameters[i].getType() == PrivateChannel.class) objects[i] = message == null ? null : message.getPrivateChannel();
            else if(parameters[i].getType() == Guild.class) objects[i] = message == null ? null : message.getGuild();
            else if(parameters[i].getType() == String.class) objects[i] = command;
            else if(parameters[i].getType() == Message.class) objects[i] = message;
            else if(parameters[i].getType() == JDA.class) objects[i] = jdrBot.getJda();
            else if(parameters[i].getType() == MessageChannel.class) objects[i] = message == null ? null : message.getChannel();
        }
        simpleCommand.getMethod().invoke(simpleCommand.getObject(), objects);
    }
    
    
}
