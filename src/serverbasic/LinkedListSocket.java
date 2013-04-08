/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbasic;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 *
 * Esta clase gestiona las diferentes conexiones entrantes "Socket" de los Clientes Resolutores. Para la app de resolucion de capchas.
 * Añadiendo o borrando dichas conexioens.
 * 
 * @author Enrique Carvajal                 Creada el: 19/03/2013
 *                              Ultima modificacion:   29/03/2013
 */
public class LinkedListSocket {

    public LinkedList<Socket> linkedListSocket;
    private int index;
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

    }

    public synchronized Socket getSocketFirst() throws NoSuchElementException{

    }
    public synchronized void listSocket(){
System.out.println(it.next()+"");
       }        
    }
    
    /*
     * Busca socket null y los borra.
     */
    public void deleteNULL() throws SocketException, IOException{
        System.out.println("Buscando socket Null");
        Socket tem;
        List lista2 = new ArrayList(linkedListSocket);
        Iterator it = lista2.iterator();
        index=0; // 1?
       while (it.hasNext()){
           System.out.println("parametros");
           tem=linkedListSocket.get(index);
           System.out.println(tem.isClosed());
           System.out.println(tem.getKeepAlive());
           System.out.println(tem.getOOBInline());
           System.out.println(tem.getSoLinger());
           System.out.println(tem.getChannel());
           System.out.println(tem.getInputStream());
           System.out.println(tem.getOutputStream());
           System.out.println(tem.getRemoteSocketAddress());
           System.out.println(tem.getReuseAddress());
           System.out.println(tem.getSoTimeout());
           System.out.println(tem.getTcpNoDelay());
           System.out.println(tem.getTrafficClass());
           System.out.println(tem.isBound());
           System.out.println(tem.isConnected());
           System.out.println(tem.isInputShutdown());
           System.out.println(tem.isOutputShutdown());
           
           
           if( tem.isClosed() ){
               System.out.println("null");
               
               linkedListSocket.remove(index);
           }
           it.next();
           index++;
       }
       listSocket();
        
    }
    public List<Socket> getListSocket(){
        return new LinkedList<>(linkedListSocket);
    }
    
}
