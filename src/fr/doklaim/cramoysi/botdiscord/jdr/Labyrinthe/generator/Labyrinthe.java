/*
 
 */
package fr.doklaim.cramoysi.botdiscord.jdr.Labyrinthe.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author CRamoysi
 */
public final class Labyrinthe {
    
    private static final int MAXTURNRATIO = 10;
    
    /**
     * Generation d'un labyrinthe de a sur b cases
     * @param m
     * @param n
     * @return ArrayList<Integer>[]
     */
    public static ArrayList<Integer>[] generate(int m, int n){
        
        //Les transitions dans le Labyrinthe
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
        List<Integer> l = new ArrayList<>(); //liste des case autour de la courrante

        
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
        
        return laby;
    }
    
    
    
    
    
    
            
            
    public static void main(String[] args){
        System.out.println("Test gen lab");
        
        int m = 20, n = 20;
        
        long t = System.currentTimeMillis();
        ArrayList<Integer>[] laby = generate(m,n);
        long t2 = System.currentTimeMillis();
        
        System.out.printf("Temps d'execution: %dms\n", t2-t);
        
//        System.out.println(laby);
        
//        for(int i = 0; i < m*n; i++){
//            StringBuilder sb = new StringBuilder();
//            
//            sb.append("case("+i+")=>");
//            for(int j=0; j<laby[i].size(); j++){
//                sb.append("["+laby[i].get(j)+"]");
//            }
//            
//            
//            System.out.println(sb.toString());
//        }
        
        
        
    }
    
    
    
    
    
    
    
    
}
