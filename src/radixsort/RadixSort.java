/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radixsort;

import java.util.ArrayList;

/**
 *
 * @author BrixZ
 */
public class RadixSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    int length;
        int [] ourList = {24,2, 587245242, 2, 4, 466, 48, 66, 8, 44};
        ArrayList<String> numbersInString = new ArrayList<String>();
        int diff;
        double result;
        int maxLength =-1;
        
        //N*1(x++),2(ourList[x]/2.0) % 1),3(if),4(if),5(if/else),6(if),
        //N*6C
        
        for(int x =0; x<ourList.length;x++){
            
            //Tests if the numbers are even or below 0
            result = (ourList[x]/2.0) % 1;
            if(result > 0 || ourList[x] < 0){
                System.out.println("*** Abort *** At least one key with uneven or negative digits ");
                System.exit(0);
            }
            if(ourList[x] == 0){
                length = 1;
            }
            else{
                length = (int) (Math.log10(ourList[x]) + 1); //log method seems to be one of the fastest to determine number of integers in a number
            }
            if(length > maxLength){
                maxLength = length;
            }
        }
        //T= N*2C    
        //Converts integers to strings and adds them to an arraylist
        for(int n = 0;n<ourList.length;n++){
            numbersInString.add(Integer.toString(ourList[n]));
        }
        
        //T=N*2C+(diff*2C) 
        for(int k = 0;k < numbersInString.size();k++){

            //if the current string is smaller than maxLength
            if(numbersInString.get(k).length() < maxLength){
               diff = maxLength - numbersInString.get(k).length();
               
               //Add that many zeros to the string
               for(int r=0;r<diff;r++){
                   numbersInString.set(k, "0" + numbersInString.get(k));
               }
            }
        }
        //T=21C
     //Initialize 2D-arraylist
     ArrayList<ArrayList<String>> buckets = new ArrayList<>(9);
     //Create 10 arraylists for 10 buckets
        for(int i = 0;i<10;i++){
            buckets.add(new ArrayList());
        }
        //1C
        int iterateMax = maxLength-1;
        
        //T=d*(n+b)
        //Outer loop
        for(int z = 0; z< maxLength; z++){
        
            int lengthOf = numbersInString.size();
            //Inner loop 
            for(int p=0;p< lengthOf;p++){ 
                
                int currentValue = Character.getNumericValue(numbersInString.get(p).charAt(iterateMax-z));//Decides which bucket 0-9
                buckets.get(currentValue).add(numbersInString.get(p));//fills that specific bucket with list value 
            } 
            
            //Here is where we clear the list
            numbersInString.clear();
            
            for(int r=0; r<10; r++){//x-axis
                for(int q =0;q < buckets.get(r).size(); q++){//y-axis
                    numbersInString.add(buckets.get(r).get(q));
                }   
                buckets.get(r).clear();//Clearing each bucket
            }
            //Here is where we clear each bucket
            /*
            for(int t=0;t<10;t++){
                buckets.get(t).clear();
            }*/
         }      
        
        //Display numbers
         for(int r=0;r<numbersInString.size();r++){
             System.out.print(Integer.valueOf(numbersInString.get(r)));
             if( r != numbersInString.size() - 1)
             System.out.print(", ");
         }
         System.out.println();
         }
    }
