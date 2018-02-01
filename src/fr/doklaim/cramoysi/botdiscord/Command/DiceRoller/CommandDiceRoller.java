/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doklaim.cramoysi.botdiscord.Command.DiceRoller;

import fr.doklaim.cramoysi.botdiscord.Command.Command;
import fr.doklaim.cramoysi.botdiscord.Command.Command.ExecutorType;
import java.util.Random;
import fr.doklaim.cramoysi.botdiscord.jdrbot.KeyWords;
import fr.doklaim.cramoysi.botdiscord.jdrbot.JdrBot;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 *
 * @author CRamoysi
 */
public class CommandDiceRoller {
    private final JdrBot jdrBot;
    
    private final static Random RAND = new Random(System.currentTimeMillis());

    public CommandDiceRoller(JdrBot jdrBot) {
        this.jdrBot = jdrBot;
    }
    
    @Command(name="d",type = ExecutorType.USER, description = "[>](1)d X pour retourner un lancé de dé à X faces.\n[>](2)d YdX pour retourner les lancés de Y dés à X faces.")
    private void rollDice(String[] args, MessageChannel channel) throws Exception{
        executeRollDice(args[0], channel);
    }
    
    
    
    
    
    
    
    
    
    
    /**
        * 
        * @param i nombre de face du dé à lancer
        * @return le resultat du dé
        */
       private static int roll(int i){
           return RAND.nextInt(i+1);
       }

       private String executeRollText(int resultDice, int valueDice){
           String mf = "";
               if(valueDice==100){ // Le cas d'un dé à 100 faces
                   if(resultDice >= 95){
                       mf = "ECHEC CRITIQUE ("+resultDice+")";
                   }else if(resultDice<=5){
                       mf = "REUSSITE CRITIQUE ("+resultDice+")";
                   }else{
                       mf = "Dé = "+resultDice;
                   }
               }else{ // un dé indéfini
                   mf = "Dé = "+resultDice;
               }
           return mf;
       }

       /**
        * 
        * @param cmd la commande envoyé par le client
        * @param chan le channel où ecrire la reponse
        * @throws java.lang.Exception
        */
       public void executeRollDice(String cmd, MessageChannel chan) throws Exception{
           /**
            * on decoupe la chaine
            * cmd = integer ex; 100, integer'd'integer ex: 2d100
            */
           if(cmd.contains(KeyWords.KEY_MULTI_DICE)){// cas d'un lancé multiple de dé
               /**
                * On decoupe la chaine
                * 
                * diceParts[0] = le nombre de dé à lancer
                * diceParts[1] = la valeur des dé à lancer
                */
               String[] diceParts = cmd.split(KeyWords.KEY_MULTI_DICE);

               int nd = Integer.parseInt(diceParts[0]); // nombre de dé
               int d = Integer.parseInt(diceParts[1]); // nombre de face des dés
               int id = 0; // valeur du dé courrant
               int td = 0; // valeur total

               String outStr = ""; // la chaine de sortie
               for(int i = 0; i<nd; i++){
                   id = roll(d); // le resultat du lancé de dé
                   td += id; // on rajoute à la somme totale

                   outStr += executeRollText(id, d);
                   outStr += "\n";
               }
               outStr += "Valeur totale = "+td;

               chan.sendMessage(outStr).queue();

           }else{ // lancé d'un dé directement
               int d = Integer.parseInt(cmd); // le nombre de face du dé
               int id = roll(d); // le resultat du lancé de dé

               chan.sendMessage(executeRollText(id, d)).queue();
           }
       }
}
