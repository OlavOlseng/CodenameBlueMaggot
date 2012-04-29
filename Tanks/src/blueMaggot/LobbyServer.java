package blueMaggot;
 
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
 
public class LobbyServer {
        public static void main(String[] args) throws IOException {
                MulticastSocket socket = new MulticastSocket(4446);
                InetAddress group = InetAddress.getByName("224.123.123.222");
                socket.joinGroup(group);
                DatagramSocket serverSocket = new DatagramSocket(9876);
 
                DatagramPacket receivePacket;
                while(true) {
                    byte[] buf = new byte[256];
                    receivePacket = new DatagramPacket(buf, buf.length);
                    socket.receive(receivePacket);
 
                    String received = new String(receivePacket.getData());
                    System.out.println(received);
                   
                   
                    InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = received.toUpperCase();
            buf = capitalizedSentence.getBytes();
            DatagramPacket sendPacket =
            new DatagramPacket(buf, buf.length, IPAddress, port);
            serverSocket.send(sendPacket);
           
                }
        }
}