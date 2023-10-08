package com.atguigu.spark.mock;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

/**
 * network数据模拟生成
 */
public class $01_NetWorkDataMock {
    private static Logger log = Logger.getLogger($01_NetWorkDataMock.class);
    public static void main(String[] args) throws IOException, InterruptedException {
        //创建socket 连接服务器
        //Socket socket = new Socket("hadoop102", 6666);
        ServerSocket serverSocket = new ServerSocket(6666);
        //serverSocket.bind(new InetSocketAddress("hadoop102",6666));

        Socket socket = serverSocket.accept();
        //获取输出流,发送数据给服务器
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //OutputStream outputStream = socket.getOutputStream();
        //模拟生成数据对象
        String[] s ={"hello atguigu spark","java flink scala","hadoop redis hbase"};
        //创建定时器
        Timer timer = new Timer();
        //创建定时器任务
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int random = (int)(Math.random()*3);
                try {
                    log.info(s[random]);
                    //System.out.println(s[random]);
                    bw.write((s[random]+"\n"));
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask,10,5);


        //while(true){
            //int random = (int)(Math.random()*3);
            //outputStream.write((s[random]+"\n\r").getBytes(StandardCharsets.UTF_8));
            //bw.write((s[random]+"\n\r"));
            //bw.flush();
            //Thread.sleep(1000*10);
        }
//        int random = (int)(Math.random()*3);
//        outputStream.write((s[random]+"\r\n").getBytes());
//        outputStream.flush();

    }

