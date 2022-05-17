/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PasswordChecker {

    static HashMap<String, Boolean> PC = new HashMap<>();
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws InputMismatchException, IOException {

        System.out.println("Please Enter the Password");
        String searchText = scn.next();
        searchText = removeNumber(searchText);

        String fileString = new String(Files.readAllBytes(Paths.get("words.txt")), StandardCharsets.UTF_8);
        String[] words = fileString.split("\\s+");
        
        System.out.println(words.length);

        for (int i = 0; i <= words.length - 1; i++) {
            
            String temp = (words[i]);
            
            if (!PC.containsKey(temp)) {
                PC.put(temp, true);
            }  
        }
        if(PC.containsKey(searchText)){
            PC.put(searchText, false);
        }else{
            PC.put(searchText, true);
        }

        if (searchText.length() >= 5 && PC.get(searchText)) {
            System.out.println("Good Password");
        } else {
            System.err.println("Bad Password");
        }

    }

    public static String removeNumber(String str) {
        str = str.replaceAll("1", "");
        str = str.replaceAll("2", "");
        str = str.replaceAll("3", "");
        str = str.replaceAll("4", "");
        str = str.replaceAll("5", "");
        str = str.replaceAll("6", "");
        str = str.replaceAll("7", "");
        str = str.replaceAll("8", "");
        str = str.replaceAll("9", "");
        str = str.replaceAll("0", "");

        return str;
    }

}
