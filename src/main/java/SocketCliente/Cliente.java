/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketCliente;

/**
 *
 * @author PC
 */
import java.io.*;
import java.net.*;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 8080;

    public static void main(String[] args) throws Exception {
        Socket clienteSocket = new Socket(HOST, PUERTO);
        System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

        // Crear flujos de entrada y salida del socket
        DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());
        DataOutputStream salida = new DataOutputStream(clienteSocket.getOutputStream());
        // Cerrar el socket
        clienteSocket.close();
    }
}
