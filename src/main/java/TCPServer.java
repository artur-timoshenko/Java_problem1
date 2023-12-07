import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer {
    TCPServer() {
    }

    public static void main(String[] argv) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(55555);

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(connectionSocket.getInputStream());
            TestObject testObject = (TestObject)ois.readObject();
            System.out.println(testObject.a);
            System.out.println(testObject.d);
        }
    }
}
