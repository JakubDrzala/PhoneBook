package org.example.Controller;

public class Encrypt {

    public String encrypt(String pas){
        StringBuilder str = new StringBuilder();
        for(char ch : pas.toCharArray()){
            if(ch != ' '){
                int orgPos = ch - 'a';
                int newPos = (orgPos + 7) % 26;
                char newCh = (char) ('a' + newPos);
                str.append(newCh);
            }else{
                str.append(ch);
            }
        }
        return str.toString();
    }

    public String decrypt(String pas){
        StringBuilder str = new StringBuilder();
        for(char ch : pas.toCharArray()){
            if(ch != ' '){
                int orgPos = ch - 'a';
                int newPos = (orgPos + 19) % 26;
                char newCh = (char) ('a' + newPos);
                str.append(newCh);
            }else{
                str.append(ch);
            }
        }
        return str.toString();
    }
}
