import java.io.*;
import java.net.*;

// Import necessario per creare lo scanner della tastiera
import java.util.Scanner;

public class Client {
    public static void main(String argv[]) throws Exception {
        // Creiamo uno scanner ed diciamogli di prendere l'input da "System.in", ovvero la nostra tastiera
        Scanner scanner = new Scanner(System.in);

        // Sappiamo che il server sta attendendo sulla porta 9000
        // Quindi apriamo un socket verso quella porta
        Socket clientSocket = new Socket("127.0.0.1", 9000);

        // Come nel server otteniamo gli stream input output
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Diciamo all'utente cosa stiamo aspettando
        System.out.print("Comando: ");

        // L'utente inserirà quindi il comando
        String comando = scanner.nextLine();

        // Inviamo l'input al server
        outToServer.writeBytes(comando + "\n");

        // Leggiamo dal buffer la prima riga (tutto fino al primo "\n")
        String risposta = inFromServer.readLine();

        // Printiamo nella console la risposta del server
        System.out.println(risposta);

        // Chiudiamo il socket
        clientSocket.close();
    }
}
