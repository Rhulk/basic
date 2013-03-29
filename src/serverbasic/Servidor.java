package serverbasic;


import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Servidor extends Thread {
    public static int puerto=5000;
    public LinkedListSocket linkedListSocket;
    public Servidor(int puerto){
        Servidor.puerto=puerto;
    }

    private Servidor() {
    }

    
    @Override
    public synchronized void  run() {  // testeando synchronized
        ServerSocket ss;
        System.out.print("Inicializando servidor. "+" Puerto: "+puerto+" ...");
        int idSession = 0;
        try {
            linkedListSocket = new LinkedListSocket();
            ss = new ServerSocket(puerto);
            System.out.println("\t[OK]");
            
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Conected in: "+socket.getRemoteSocketAddress());
                if (idSession== 200000){// :/
                    idSession=0;
                }
                ((ServerBasic) new ServerBasic(socket, idSession, linkedListSocket)).start();
                idSession++;
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
           
        }            
        
        }

    public static void main(String []arg){
        ((Servidor) new Servidor()).start();
    }
}