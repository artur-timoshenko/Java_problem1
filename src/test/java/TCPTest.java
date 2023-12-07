import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class TCPTest {

    private Thread serverThread;

    @Before
    public void setUp() {
        serverThread = new Thread(() -> {
            try {
                TCPServer.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    @After
    public void tearDown() {
        serverThread.interrupt();
    }

    @Test
    public void testTCPCommunication() throws Exception {
        TestObject originalObject = new TestObject();
        originalObject.a = 37;
        originalObject.d = 2.7D;

        Thread clientThread = new Thread(() -> {
            try {
                TCPClient.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        clientThread.start();
        clientThread.join();

        Socket clientSocket = new Socket("localhost", 55555);
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        TestObject receivedObject = (TestObject) ois.readObject();
        clientSocket.close();

        assertEquals(originalObject.a, receivedObject.a);
        assertEquals(originalObject.d, receivedObject.d, 0.0);
    }
}
