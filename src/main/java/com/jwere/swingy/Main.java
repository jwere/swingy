package com.jwere.swingy;
import com.jwere.swingy.view.StartConsoleView;
import com.jwere.swingy.view.StartGuiView;
import com.jwere.swingy.database.*;

public class Main{
    public static void main(String[] args){
        if (args.length == 1){
            if (args[0].toLowerCase().equals("console"))
                new StartConsoleView().start();
            else if (args[0].toLowerCase().equals("gui"))
                new StartGuiView().start();
            else{
                System.out.println("Usage: program console | gui");
                System.exit(1);
            }
        }
        else{
            System.out.println("Usage: program console | gui");
            System.exit(1);
        }
        Database.connect();
    }
}