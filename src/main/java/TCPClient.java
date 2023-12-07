import java.io.ObjectOutputStream;
import java.net.Socket;

class TCPClient {
    TCPClient() {
    }

    public static void main(String[] argv) throws Exception {
        TestObject testObject = new TestObject();
        testObject.a = 37;
        testObject.d = 2.7D;
        Socket clientSocket = new Socket("localhost", 55555);
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        oos.writeObject(testObject);
        oos.flush();
        clientSocket.close();
    }
}
