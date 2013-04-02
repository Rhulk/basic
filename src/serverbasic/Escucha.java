/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbasic;



import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Escucha extends Thread {
    public static int puerto=5000;
//    public LinkedListSocket linkedListSocket;
    public ServerSocket ss;
    public Socket socket;
    public Escucha(int puerto){
        Servidor.puerto=puerto;
    }

    public Escucha() {
        
    }

    
    @Override
    public synchronized void  run() {  // testeando synchronized
        
        System.out.print("Inicializando servidor. "+" Puerto: "+puerto+" ...");
//        int idSession = 0;
        try {
//            linkedListSocket = new LinkedListSocket();
            ss = new ServerSocket(puerto);
            System.out.println("\t[OK]");
            
//            while (true) {
                
                socket = ss.accept();
                System.out.println("Conected in: "+socket.getRemoteSocketAddress());

                System.out.println("Hola mundo por internet");

//            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
           
        }            
        
        }
    public void desconectar() throws IOException{
//        ss.close();
        socket.close();
        socket.close();
        socket.shutdownOutput();
    }

    public static void main(String []arg){
        ((Escucha) new Escucha()).start();
    }
}