import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by zcy on 2017/5/4.
 */
public class ClientThread implements Runnable {
    //该线程负责处理的socket
    private Socket s;
    //该线程对应的socket输入流
    BufferedReader br=null;
    public ClientThread(Socket s)throws IOException {
        this.s=s;
        br=new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    public  void run(){
        try {
            String content = null;
            //不断地读取socket 输入流中的内容,并将这些内容打印输出
            while((content=br.readLine())!=null){
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
