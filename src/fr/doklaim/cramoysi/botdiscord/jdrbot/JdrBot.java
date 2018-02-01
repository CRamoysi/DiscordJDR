/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.





*


lancer la bdd: ./mongod.exe -dbpath H:\\BOULOT\\bdd\\mongo

 */
package fr.doklaim.cramoysi.botdiscord.jdrbot;

import fr.doklaim.cramoysi.botdiscord.Command.CommandMap;
import javax.security.auth.login.LoginException;
import fr.doklaim.cramoysi.botdiscord.Listeners.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

/**
 *
 * @author CRamoysi
 */
public class JdrBot implements Runnable{

    
private final String tokenBot = "YOURTOKENDISCORD";
    private final JDA jda;
    private final CommandMap commandMap = new CommandMap(this);
    
    private boolean running;

    
//    private Scanner scanner = new Scanner(System.in);
    
    

    public JdrBot() throws LoginException, IllegalArgumentException, RateLimitedException {
        jda = new JDABuilder(AccountType.BOT).setToken(tokenBot).buildAsync();
        jda.addEventListener(new BotListener(commandMap));
        
        jda.getPresence().setGame(Game.of("Raler !"));
    }

    public JDA getJda() {
        return jda;
    }

    
    public void setRunning(boolean running) {
        System.out.println("setRunning("+running+")");
        this.running = running;
        System.out.println("this.running = "+this.running);
    }
    
    @Override
    public void run() {
        this.running = true;
        System.out.println("Bot started");
        while(this.running){
            
//            if(scanner.hasNextLine()){
//                commandMap.command(scanner.nextLine(), null);
//            }
        }
        
//        scanner.close();
        System.out.println("Bot stopped");
        jda.shutdown();
        System.exit(0);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
            try{
                JdrBot jdrBot = new JdrBot();
                new Thread(jdrBot, "JdrBot").start();
            }catch(LoginException | RateLimitedException e){
                e.printStackTrace();
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        //On va forcer a utiliser un token en parametre
       
            
            //Instanciation de la BDD
            //BDDHelper.init();
            
            
            
            /**
            * 
            */
            //JDA jda = null; // instance de la lib jda
//            String token = "";
//            token = args[0]; // le token du bot discord
//
//            System.out.println("Kikoo hentai world , token = "+token);


//            try{
//                MongoClient mongoC = new MongoClient("localhost", 27017); //connection à MongoDB
//                MongoDatabase db = mongoC.getDatabase("jdr"); // utilisation de la base de donnée de test
//                System.out.println("bdd connecté");
//                
//                MongoCollection<Document> collection = db.getCollection("test"); //recuperation/creation de la collection(table)
//                Document doc = new Document("name","Toto") //creation d'un tuple dans la collection
//                                .append("surname","Labulle")
//                                .append("age",69)
//                                .append("list", Arrays.asList("item1","item2","item3"))
//                                .append("innerObject",new Document("x",42).append("y","la reponse universelle"));
//                
//                collection.insertOne(doc); //insertion du tuple, pour inserer plusieurs ne meme temps: ArrayList<Document> et add avec insertMany
//                
//                
//                
//            }catch(Exception e){
//                System.out.println("fckfckfck");
//            }
            
//            System.out.println("Connecte avec: " + jda.getSelfUser().getName());
            
            
            /**
             * Initialisation des fonctions
             */
            
            //FuncMap.initFuncMap();
            
            /**
             * 
             * Ajout du listener
             * 
             */
            
//            jda.addEventListener(new DiceListener());
//            jda.addEventListener(new ImgListener());
//            
//            jda.addEventListener(new TestListener());
            
            
//            jda.addEventListener(new BotListener());
        
        
    }

    
    
}
