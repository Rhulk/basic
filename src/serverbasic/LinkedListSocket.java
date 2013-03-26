/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbasic;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * Esta clase gestiona las diferentes conexiones entrantes "Socket" de los Clientes Resolutores. Para la app de resolucion de capchas.
 * Añadiendo o borrando dichas conexioens.
 * 
 * @author Enrique Carvajal                 Creada el: 19/03/2013
 *                              Ultima modificacion:   20/03/2013
 */
public class LinkedListSocket {

    public LinkedList<Socket> linkedListSocket;
//    public List listSocket;
    /**
     *
     */
    public LinkedListSocket() {
//       listSocket = Collections.synchronizedList(new LinkedList());
//        linkedListSocket = Collections.synchronizedList();
        linkedListSocket = new LinkedList();
    }
    public synchronized void addSocket(Socket sk){
        linkedListSocket.addLast(sk);
        System.out.println(linkedListSocket.size());
    }
    public synchronized void deleteSocketFirst(){
        linkedListSocket.removeFirst();
        System.out.println(linkedListSocket.size());
    }
    public synchronized Socket getSocketFirst(){
        Socket temp= linkedListSocket.getFirst();
        linkedListSocket.removeFirst();
        System.out.println(linkedListSocket.size());
        return temp;
    }
    public synchronized void listSocket(){
      System.out.println( "La lista es la siguiente" );
      List lista2 = new ArrayList(linkedListSocket);
      Iterator it = lista2.iterator();
      while (it.hasNext()){
           System.out.println(it.next()+"");
       }        
    }
    public List<Socket> getListSocket(){
        return new LinkedList<>(linkedListSocket);
    }
    
}
