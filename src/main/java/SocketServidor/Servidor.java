/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketServidor;

/**
 *
 * @author PC
 */
import java.io.*;
import java.net.*;

public class Servidor {

    private static final int PUERTO = 8080;

    public static void main(String[] args) throws Exception {
        ServerSocket servidorSocket = new ServerSocket(PUERTO);
        System.out.println("Servidor escuchando en el puerto " + PUERTO);

        while (true) {
            Socket clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress().getHostAddress());

            // Crear hilos para manejar cada cliente
            new ManejadorCliente(clienteSocket).start();
        }
    }

    private static class ManejadorCliente extends Thread {

        private final Socket clienteSocket;

        public ManejadorCliente(Socket clienteSocket) {
            this.clienteSocket = clienteSocket;
        }

        @Override
        public void run() {
            try {
                // Obtener flujos de entrada y salida del socket
                DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());
                DataOutputStream salida = new DataOutputStream(clienteSocket.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Cliente desconectado");
            } finally {
                try {
                    clienteSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}