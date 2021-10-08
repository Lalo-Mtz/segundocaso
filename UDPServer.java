import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String[] args) {

        DatagramSocket aSocket = null;

        try{
            aSocket = new DatagramSocket(6789);

            byte [] buffer = new byte[1000];

            while(true){
                System.out.println("The Server is running... ");
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                //Modificaciones, linea 20,21,23
                System.out.println(new String(request.getData()) + " desde el cliente " + request.getAddress().toString());
                byte [] rep = (new String(request.getData()).concat(" modificado desde el servidor 192.168.1.120")).getBytes();

                
                DatagramPacket reply = new DatagramPacket(rep, rep.length,
                    request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }catch(SocketException e){ System.out.println("Socket: " + e.getMessage());
        }catch(IOException e) { System.out.println("Io: " + e.getMessage()); 
        }finally{if(aSocket != null){ 
            System.out.println("Se va a cerrar");
            aSocket.close();}}
    }
}

