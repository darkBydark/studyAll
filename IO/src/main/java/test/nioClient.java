package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class nioClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        os.write("Hello,Server\0".getBytes());

        int b;
        while((b = is.read())!=0){
            System.out.print((char)b);
        }
        socket.close();
    }
}
