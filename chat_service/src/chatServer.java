import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class chatServer {

    public static void main(String args[]) {

        Runnable runnable = new workerThread();
        Thread t = new Thread(runnable);
        t.run();


        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(7777);

        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {

            try {
                assert serverSocket != null;
                Socket socket = serverSocket.accept();

                OutputStream out = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(out);

                dos.writeUTF("서버 전송");

                dos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

class workerThread implements Runnable {

    @Override
    public void run() {
        System.out.println("worker thread!");
    }
}
