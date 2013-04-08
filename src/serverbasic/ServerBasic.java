/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbasic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrador
 */
public class ServerBasic extends Thread{
    private static int puerto=5000;
//    private static DataOutputStream dos_CR;
//    private static DataInputStream dis_CR;
    private static ObjectInputStream entrada_imagen;
    private static ObjectOutputStream salida_imagen;
    private static LinkedListSocket linkedListSocket;
//    private static ArrayList listaPeticioens;

    private static Socket sk_add;
    private static int idSesion=0;


    /**
     * @param args the command line arguments
     */
    public ServerBasic(Socket sk, int id,LinkedListSocket list) throws IOException{
        super(""+id); // le damos un nombre al hilo
        System.out.println("Socket sk add: "+ sk_add);
        System.out.println("Nombre del hilo: "+getName ()+". Id del hilo: "+getId()); 
        this.sk_add = sk;  //Sino en cada hilo que genere me qdare con el ultimo socket
        this.idSesion = id;  // este es paso por valor no hay problema.
        this.linkedListSocket=list;
//        this.listaPeticioens=listaPeti;
    }

    public synchronized void run() {
      
    }
    public static void leerImagenCliente() throws IOException{


        System.out.println("Imagen gardada de cliente");
    }
    public static void enviarImagen(Socket sk_cr) throws IOException{

  
    }
    


}
