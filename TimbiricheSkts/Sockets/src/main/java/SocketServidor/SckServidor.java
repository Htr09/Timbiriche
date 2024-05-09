/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package SocketServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class SckServidor {

    static volatile List<SckHilos> hilos = new ArrayList<>();
    static int MAX = 4;
    private SocketPartida sp = new SocketPartida();
    
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(1234);
        
        System.out.println("Servidor inicializando en el puerto 1234");
        System.out.println("Soportando un total de "+MAX+" jugadores");
        
        Socket s;
        
        while(true){
            s = ss.accept();
            
            if(hilos.size() < MAX){
                System.out.println("Nueva conexión por parte del cliente: " + s);
                SckHilos sh = new SckHilos(s, hilos, MAX);
                Thread t = new Thread(sh);
                hilos.add(sh);
                t.start();
            } else{
                System.out.println("Servidor lleno. No se pueden conectar mas de 4 jugadores.");
            }
        }
        
        
        
        
        
        
    }
    
    
}
