package com.selenium.valbetafarmer;

import com.valbetafarmer.view.MainMenu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        MainMenu m = new MainMenu();
        m.setLocationRelativeTo(null);
        m.setTitle("Valorant beta farmer");
        m.setResizable(false);
        m.setVisible(true);
    }
}
