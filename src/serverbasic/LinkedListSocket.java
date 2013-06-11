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

    
}
