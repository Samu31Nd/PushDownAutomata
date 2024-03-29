package PDA;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;

public class Main {
    public static ProgramFrame mainProgram;
    public static String Z0 = "Z0",
        X = "X";
    static int add = 0,
            remove = 0;
    static Stack<String> PDA;
    static String string;
    public static boolean accepted = true,
        thisCanStart = true;

    public static void main(String[] args) throws newError {
        string = OptionPane.askUser();
        mainProgram = new ProgramFrame();
        checkPDA(string.toCharArray());
    }
    static SendToFileManager buffer;

    static void checkPDA(char []string) throws newError{
        int index = 0;
        PDA = new Stack<>();
        PDA.push(Z0);
        System.out.println("Printing stack process: ");
        buffer = new SendToFileManager("output");
        buffer.appendTextToFile("String: " + Main.string + "\n");
        printCurrentStateOfStack(PDA,buffer);
        if(string.length>10*2)
            thisCanStart=false;
        while(index < string.length){
            if(string[index] == '0'){
                PDA.push(X);
                printCurrentStateOfStack(PDA,buffer);
                add++;
            } else if(string[index] == '1'){
                break;
            } else{
                OptionPane.ShowNotDefinedDialog();
                thisCanStart = false;
                throw new newError("Not a valid character [" + string[index] + "].");
            }
            index++;
        }
        while(index < string.length){
            if(string[index] == '1'){
                try{
                    remove++;
                    PDA.pop();
                    printCurrentStateOfStack(PDA,buffer);
                } catch(EmptyStackException e){
                    accepted = false;
                    //if it is removing more than it puts
                    remove = add + 1;
                    //throw new newError("Not defined by the automata.");
                }
            } else if(string[index] == '0'){
                accepted = false;
                mainProgram.start(add,remove);
                OptionPane.ShowNotDefinedDialog();
                throw new newError("Not defined by the automata.");
            } else{
                OptionPane.ShowNotDefinedDialog();
                thisCanStart = false;
                throw new newError("Not a valid character [" + string[index] + "].");
            }
            index++;
        }
        if( thisCanStart )
            mainProgram.start(add,remove);
        else showFinalDialogWindow();
    }

    static Stack<String> copyAuxiliary = new Stack<>();
    static StringBuilder ID = new StringBuilder();
    static public void printCurrentStateOfStack(Stack<String> stack,SendToFileManager buffer){
        ID.delete(0,ID.length());
        copyAuxiliary.clear();
        copyAuxiliary.addAll(stack);

        while(!copyAuxiliary.isEmpty()){
            ID.append('[').append(copyAuxiliary.pop()).append("] ");
        }
        if(stack.isEmpty()){
            ID.append("[]");
        }
        System.out.println(ID);
        buffer.appendTextToFile(ID.toString() + '\n');
    }

    public static void showFinalDialogWindow(){

        String aux = "";
        StringBuilder message;

        if(accepted && !PDA.empty() && Objects.equals(aux = PDA.pop(), Z0)){
                message = new StringBuilder("String valid");
                buffer.appendTextToFile("String is valid.");
            } else{
            message = new StringBuilder("String\n" + string + "\nis invalid...\nShowing actual state of the stack: \n[" + aux + "]");
            buffer.appendTextToFile("String is invalid.");
                while(!PDA.empty())
                    message.append(" [").append(PDA.pop()).append("]");
            }
        buffer.closeBinaryFile();
        OptionPane.showFinalDialog(message.toString());
        mainProgram.dispose();
    }

}

