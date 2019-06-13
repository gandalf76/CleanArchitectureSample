package presentation;

import java.util.Scanner;

public class Console {

    private Scanner input;

    public Console(){
        input = new Scanner(System.in);
    }

    public String readInput() {
        System.out.print(">");
        return input.nextLine();
    }

    public void writeMessage(String text) {
        System.out.print(">");
        System.out.println(text);

    }

}
