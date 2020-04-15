package com.jwere.swingy.view;

import com.jwere.swingy.controller.StartViewController;

import java.util.Scanner;

public class StartConsoleView implements StartView{

    private StartViewController controller;

    public void start(){
        controller = new StartViewController(this);
        
        System.out.println("Swingy running in Console mode.\n");
        Scanner scanner = Helper.getScanner();

        System.out.println("Available Commands: CREATE, SELECT, SWITCH:\n");
        System.out.println("CREATE - to create hero");
        System.out.println("SELECT - to select already created hero");
        System.out.println("SWITCH - to switch to GUI view");
        
        while(scanner.hasNext()){
            String input = scanner.nextLine();

            if (input.toLowerCase().equalsIgnoreCase("create")){
                controller.onCreateHeroButtonPressed();
                break ;
            }
            else if (input.toLowerCase().equalsIgnoreCase("select")){
                controller.onSelectHeroButtonPressed();
                break ;
            }
            else if (input.toLowerCase().equalsIgnoreCase("switch")){
                controller.onSwitchButtonPressed();
                break ;
            }
            else
                System.out.println("Unknown command, Available Commands: CREATE, SELECT, SWITCH.");
        }
    }

    @Override
    public void openCreateHero() {
        new CreateHeroViewConsole().start();
    }

    @Override
    public void switchView() {
        new StartGuiView().start();
    }

    @Override
    public void openSelectHero() {
       new SelectHeroViewConsole().start();
    }
}