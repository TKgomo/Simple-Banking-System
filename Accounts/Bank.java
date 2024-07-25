package Accounts;

import java.util.Scanner;

public class Bank
{
	Scanner input = new Scanner(System.in);  
    public void login()
    {
        String Username,id;
        System.out.print("Please Enter your Username : ");
        Username = input.nextLine();
        System.out.print("Please Enter your ID : ");
        id = input.nextLine();
        if (Username.equals("Tshegofatso") && id.equals("0209095591084"))
        {
            System.out.println("You have Successfully logged in!! \n");
        }
        else
        {
          System.out.println("Please enter correct Username or Password, System will Terminate!");
          System.exit(0);
        }        
    }
    
    public void logout()
    {
    	System.out.print("Program will Terminate!, You have successuflly logged out!");
        System.exit(0);
    }
}