/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LongWar2HitCalculator;

import java.util.Scanner;

public class LongWar2HitCalculator {
    
    private static Scanner scanner = new Scanner( System.in );  

    public static void main(String[] args) { 
        
        System.out.println("INPUT YOUR HIT/CRIT/DODGE Format: (\"HIT CRT DDG)\"");
        String hcd_str = scanner.nextLine();
        String[] hcd = hcd_str.split(" ");
        Double hit = 0.01 * Double.parseDouble(hcd[0]);
        Double crit = 0.01 * Double.parseDouble(hcd[1]);
        Double dodge = 0.01 * Double.parseDouble(hcd[2]);
        
        if (dodge > 1 || crit > 1 || crit < 0 || dodge < 0) 
        {
            System.out.println("FIX YOUR FUCKING INPUT");
            System.exit(0);
        }
        
        Double miss_base_chance = 0d;
        Double hit_base_chance = 0d;
        Double graze_base_chance = 0d;
         
        if ( hit > 0.1 && hit < 0.9 ) 
        {
            miss_base_chance = 1 - hit - 0.1 ;
            hit_base_chance = hit - 0.1 ;
            graze_base_chance = 0.2 ;
        }       
        else if (hit <= 0.1)
        {   
            miss_base_chance = 1 - hit - 0.1 ; 
            hit_base_chance = 0.0 ;
            graze_base_chance = 0.1 + hit ;
        }         
        else if (hit >= 0.9)
        {    
            miss_base_chance = 0.0 ;
            hit_base_chance = hit - 0.1 ;
            graze_base_chance = 0.1 + 1 - hit ;
        }
        else 
        {
            System.out.println("FIX YOUR FUCKING INPUT");
            System.exit(0);
        }           
        
        Double crit_hit_chance = hit_base_chance * crit ;
        Double normal_hit_chance = hit_base_chance * ( 1 - crit - dodge ) + graze_base_chance * crit ;
        Double graze_hit_chance = graze_base_chance * ( 1 - crit - dodge ) + hit_base_chance * dodge ;
        Double miss_hit_chance = miss_base_chance + graze_base_chance * dodge ;       
        

        System.out.println ( "Crit: " + (double)Math.round(crit_hit_chance * 1000d) / 10d  + "% " + "Hit: " + (double)Math.round(normal_hit_chance * 1000d) / 10d + "% " + "Graze: " + Math.round(graze_hit_chance * 1000d) / 10d + "% " + "Miss: " +  Math.round(miss_hit_chance * 1000d) / 10d + "%" );

    
    }
}