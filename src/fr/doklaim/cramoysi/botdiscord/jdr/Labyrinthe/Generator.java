/*
 
 */
package fr.doklaim.cramoysi.botdiscord.jdr.Labyrinthe;

import fr.doklaim.cramoysi.botdiscord.Exception.NotYetException;
import fr.doklaim.cramoysi.botdiscord.Exception.TryAgainException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Genere le labyrinthe (graphe/arbre) representant le donjon. Il faut considerer le donjon comme etant un arbre partant de l'entrée. Une des feuilles est la sortie
 * @author CRamoysi
 */
public final class Generator {
    
    private static final int MAXTURNRATIO = 10;
    private int m = 0,n = 0;//ne sert que pour la generation et verification
    
    public ArrayList<Integer>[] laby = null;
    public int in, out;
    
    
    /**
     * Generation d'un labyrinthe de m sur n cases.
     * @param m
     * @param n
     */
    public void generate(int m, int n){
        this.m = m;
        this.n = n;
        //Les transitions dans le Generator
        //Tableau de m*n arrayList
        ArrayList<Integer>[] laby = new ArrayList[m*n];
        
       //création des m*n cases du tableau
        for(int i =0; i<m*n; i++){
            laby[i] = new ArrayList<>();
        }
        
        int turn = 0;//nombre de tour
        int brokenWall = 0;//nombre de mur cassé
        
        Random r = new Random(System.currentTimeMillis());
        //variable de tour
        int currCaseID; //ID de la case courrante
        int currCaseIDx, currCaseIDy;
        int nextCaseID; //ID de la case à lier
        List<Integer> l = new ArrayList<>(); //liste des cases autour de la courante

        
        /*
        On boucle tant que l'on a pas cassé m*n-1 mur ou que l'on a trop bouclé
        */
        while((turn++ < m*n*MAXTURNRATIO) & brokenWall < m*n){
            l.clear();//clear de la list des cases autour
            
            currCaseIDx = r.nextInt(m);
            currCaseIDy = r.nextInt(n);
            currCaseID = currCaseIDy*m+currCaseIDx;
            
//            System.out.println("Courant: "+currCaseID);
            
            //case gauche
            if(currCaseIDx > 0){ //pas à gauche
                if(!laby[currCaseID].contains(currCaseID-1)){//si la case n'est pas deja lié
                    l.add(currCaseID-1);//on l'ajoute à la liste des possibles cases à lier
                }
            }
            //Case droite
            if(currCaseIDx < m-1){//pas à droite
                if(!laby[currCaseID].contains(currCaseID+1)){//si la case n'est pas deja lié
                    l.add(currCaseID+1);//on l'ajoute à la liste des possibles cases à lier
                }
            }
            //Case haut
            if(currCaseIDy >0){ //pas en haut
                if(!laby[currCaseID].contains(currCaseID-m)){//si la case n'est pas deja lié
                    l.add(currCaseID-m);//on l'ajoute à la liste des possibles cases à lier
                }
            }
            //case bas
            if(currCaseIDy < n-1){//pas en bas
                if(!laby[currCaseID].contains(currCaseID+m)){//si la case n'est pas deja lié
                    l.add(currCaseID+m);//on l'ajoute à la liste des possibles cases à lier
                }
            }
            if(l.size()<1)continue;
            nextCaseID = l.get(r.nextInt(l.size()));//choix de la case à lier
            
            
//            System.out.println("next: "+nextCaseID);
            //ajout dans le laby
            brokenWall++;
            laby[currCaseID].add(nextCaseID);
            laby[nextCaseID].add(currCaseID);            
            
        }
        
        this.laby = laby;
    }
    
    
    public void addINOUT() throws NotYetException, TryAgainException{
        if(this.laby == null) throw new NotYetException("generate() must be called before");
        /*
        choisir de façon aleatoire une entrée et une sortie puis verifier que l'on peux aller de l'un à l'autre via un algorithme de parcours
        
        DFS iterative
        utilisation d'une pile (LIFO), le but est uniquement de savoir si la sortie est accessible
        on va partir de l'entrée, si aucun de ses adjacents ne sont la sortie, on les empile.
        on depile le premier et on le considere comme etant la nouvelle entrée
        On va ranger dans une autre liste les salles que l'on a deja depilé afin de verifier que l'on ne l'a pas deja verifié
        
        */
        
        Stack<Integer> pileRestant = new Stack<>();
        Stack<Integer> pileVerifie = new Stack<>();
        
        Random r = new Random(System.currentTimeMillis());
        int in = r.nextInt(m*n);
        int out = r.nextInt(m*n);
        if(in == out) throw new TryAgainException("input and output are randomly generated as the same.");


        int current = in;
        int tmp;
        pileRestant.push(in);
       
        while(!pileRestant.empty()){
           current = pileRestant.pop();
           System.out.println("current = "+current);
           if(current == out){//c'est ok, on arrete la boucle
               System.out.println("current == out");
               current = -1;
               pileRestant.clear();
               break;
           }
           
           pileVerifie.push(current);
           
            if(!this.laby[current].isEmpty()){
                System.out.println("not empty");
                for (Iterator<Integer> it = this.laby[current].iterator(); it.hasNext();) {
                    tmp = it.next();
                    if(!pileVerifie.contains(tmp)){
                        pileRestant.push(tmp);
                    }
                }
            }
        }
        
        if(current != -1) throw new TryAgainException("input and output are not connected.");
        this.in = in;
        this.out = out;
        
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < m*n; i++){
            
            sb.append("case(").append(i).append(")=>");
            for(int j=0; j<laby[i].size(); j++){
                sb.append("[").append(laby[i].get(j)).append("]");
            }
            sb.append("\n");    
        }
        
        sb.append("INPUT: ").append(in).append("\n");
        sb.append("OUTPUT: ").append(out).append("\n");
        
        return sb.toString();
    }
    
            
            
    public static void main(String[] args){
        System.out.println("Test gen lab");
        
        int m = 20, n = 20;
        
        long t = System.currentTimeMillis();
        Generator l = new Generator();
        l.generate(m,n);
        try {
            l.addINOUT();
        } catch (NotYetException | TryAgainException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        long t2 = System.currentTimeMillis();
        
        System.out.printf("Temps d'execution: %dms\n", t2-t);
        
        System.out.println(l);

    }
    
    
    
    
    
    
    
    
}
