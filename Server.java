// Importiamo il necessario...
import java.io.*;  // Utilità di input/output
import java.net.*; // Utilità per il networking, necesarie per i socket

// Import necessari al solo fine di ottenere l'orario/data
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Server {
    // La VM Java al lancio di un jar cercherà di avviare il metodo statico "main"
    public static void main(String args[]) throws Exception {

        // Ci "bindiamo" sulla porta 9000, o su un'altra qualsiasi a nostro piacere*
        ServerSocket socket = new ServerSocket(9000);

        // Iniziamo un loop, il server deve rimanere sempre in attesa di richieste dai client
        while(true) {
            // Attendiamo che qualcuno si connetta al nostro socket
            Socket socketConnessione = socket.accept();
            // Come risultato avremo un socket di connessione

            // Prendiamo lo stream di dati in input dal client nel socket
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socketConnessione.getInputStream()));

            // Prendiamo lo stream su cui inviare i nostri dati
            DataOutputStream outToClient = new DataOutputStream(socketConnessione.getOutputStream());

            // Ora leggiamo cosa ha da dirci il client
            // Leggeremo tutto fin quando non avremo un newline ("\n")
            String comando = inFromClient.readLine();

            // Controlliamo se il client ha inviato un comando
            if (comando.equals("/data")) {
                // Se il client ha inviato il comando /data allora gli rispondiamo con la data riscontrata dal server
                String giorno = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                String orario = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

                // "assembliamo" la risposta
                String risposta = "Oggi e' il " + giorno + " e sono le " + orario + "\n";

                // Ed inviamola tramite il buffer di output al client
                outToClient.writeBytes(risposta);
                continue; // Con continue passiamo al prossimo ciclo del nostro while, evitando di dire "comando sconosciuto"
            }

            // Se il client ha inviato un comando sconosciuto glielo diciamo
            outToClient.writeBytes("Comando sconosciuto.");
            // Questo passo è necessario per evitare che il client rimanga in attesa "per sempre"
        }
    }
}
