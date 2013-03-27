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
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrador
 */
public class ServerBasic {
    private static int puerto=5000;
    private static DataOutputStream dos_SC, dos_CP, dos_CR;
    private static DataInputStream dis_SC, dis_CP, dis_CR;
    private static ObjectInputStream entrada_imagen;
    private static ObjectOutputStream salida_imagen;
    private static LinkedListSocket linkedListSocket;
    private static String accion;
    private static Socket sk_in, sk_cr, sk_cp;
    private static int idSesion=0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            linkedListSocket = new LinkedListSocket();
            System.out.print("Inicializando servidor. "+" Puerto: "+puerto+" ...");
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("\t[OK]");
            // esperamos al cliente y vinculamos el socket con el
                do{
                   sk_in = servidor.accept(); 
                    // conexion aceptada con el cliente.
                    System.out.println("Conected in: "+sk_in.getRemoteSocketAddress());
                    // canales de entrada y salida 
                    dos_SC = new DataOutputStream(sk_in.getOutputStream());  // Canal para responder a la peticion de Captcha
                    dis_SC = new DataInputStream(sk_in.getInputStream());    // Canal para recibir el tipo de cliente de peticion o de resolucion
                    

                    // leemos el string que nos manda el cliente.
                    accion=dis_SC.readUTF();
                    if (accion.equals("resolucion")){
                        //guardamos la conexion con dicho cliente en la lista enlazada.
                        linkedListSocket.addSocket(sk_in);     
                        System.out.println("Socket guardado....");
                    }else{
                        if(accion.equals("peticion")){
                            
                            dos_SC.writeUTF("vamos mandame la imagen");// mandamos el foco al clientePetecion para que nos mande la imagen
                            entrada_imagen = new ObjectInputStream( sk_in.getInputStream() );  // Para obtener la imagen"Cliente peticion".
                            leerImagenCliente();
//                            linkedListSocket.deleteNULL(); // borrarmos los socket null [ PROBANDO ] ... [KO]
                            boolean fallo;
                            do{
                                fallo=false;
                                try{
                                    sk_cr = linkedListSocket.getSocketFirst(); // obtenemos el cliente resolutor
                                    dos_CR = new DataOutputStream(sk_cr.getOutputStream());  // Canal para responder a la peticion de Captcha
                                    dis_CR = new DataInputStream(sk_cr.getInputStream());
                                    dos_CR.writeUTF("Captcha..."); // mandamos el foco al cliente resolutor y le mandamos la imagen
                                    enviarImagen(); // como el cliente resolutor ya tiene el foco le mandamos la imagen
                                     String respuesta = dis_CR.readUTF(); // recibimos la respuesta del cliente resolutor
                                     // ahora tenemos que recuperar la conexion con el cliente peticion.
                                     dos_SC.writeUTF(respuesta); // aqui SC es el cliente peticion porque a sido la ultima conexion entrante con el sever SC
                                }
                                catch (IOException ex){ // si falla la conexion obtenida buscamos otra. [Probando]... [OK]
                                    System.out.println("Fallo obteniendo Socket");
                                    fallo=true;
                                }
                            } while(fallo);
                        }
                    }
                    linkedListSocket.listSocket();

                    System.out.println("Esperando nueva conexion!!");
                    idSesion++;
            }while(true);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerBasic.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("excepcion... main server");
        }
    }

        public static void leerImagenCliente() throws IOException{
              try {
                byte[] bytesImagen = (byte[]) entrada_imagen.readObject();
                ByteArrayInputStream entradaImagen = new ByteArrayInputStream(bytesImagen);
                BufferedImage bufferedImage = ImageIO.read(entradaImagen);
 
                String nombreFichero="C:\\Documents and Settings\\Administrador\\Escritorio\\"+idSesion+".jpg";
                System.out.println("Generando el fichero: "+nombreFichero );
                FileOutputStream out = new FileOutputStream(nombreFichero);
                // esbribe la imagen a fichero
                ImageIO.write(bufferedImage, "jpg", out);
            }
 
            // atrapar problemas que pueden ocurrir al tratar de leer del cliente
            catch ( ClassNotFoundException excepcionClaseNoEncontrada ) {
                System.out.println( "\nSe recibió un tipo de objeto desconocido" );
            }      
        System.out.println("Imagen gardada de cliente");
    }
    public static void enviarImagen() throws IOException{

        try{
            salida_imagen = new ObjectOutputStream( sk_cr.getOutputStream() );
            BufferedImage bufferedImage = ImageIO.read(new File("C:\\Documents and Settings\\Administrador\\Escritorio\\"+idSesion+".jpg"));
            ByteArrayOutputStream salidaImagen = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", salidaImagen);
            byte[] bytesImagen = salidaImagen.toByteArray();
            salida_imagen.writeObject( bytesImagen );
            salida_imagen.flush();
            System.out.println( "Se ha enviado la imagen desde el server" );
 // procesar los problemas que pueden ocurrir al enviar el objeto
        }catch ( IOException excepcionES ) {
            System.out.println( "\nError al escribir el objeto" );
        }    catch(Exception e){
            System.out.println("Mensage de error.. "+e.getMessage());
        }   
    }
}
