package com.biller.enigma.root.socket.pkg;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

import static android.content.ContentValues.TAG;

public class pushThread {

    public void start(){
        new SocketThread("123").start();
    }

    class SocketThread extends Thread{
        private Socket socket;
        private final int SERVER_PORT = 9080;
        private final String SERVER_ADDRESS = "192.168.43.174";
        private final String CLIENT_KEY = "client_id";
        private String CLIENT_ID ;
        private String sendData;
        private String response;

        public SocketThread(String CLIENT_ID) {
            this.CLIENT_ID = CLIENT_ID;
            this.sendData = this.CLIENT_KEY+" : "+this.CLIENT_ID;
        }

        @Override
        public void run() {
            try {
                socket = new Socket(SERVER_ADDRESS,SERVER_PORT);

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
                printWriter.println(this.sendData);
                printWriter.flush();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;

                InputStream inputStream = socket.getInputStream();

                /*
                 * notice:
                 * inputStream.read() will block if no data return
                 */
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response += byteArrayOutputStream.toString("UTF-8");
                }

                Log.i(TAG, "run: socket loop"+response);
//                while (socket.isConnected()){
//                }

                Log.i(TAG, "run: socket loop out");


            } catch (IOException e) {
                Log.i(TAG, "run: exception");
            }
        }
    }
}
