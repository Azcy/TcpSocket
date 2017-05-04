import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by zcy on 2017/5/4.
 */
public class MyCllient {
    public static void main(String[] args)throws IOException {
        Socket socket=new Socket("127.0.0.1",30000);
        //客户端启动ClientThread线程不断地读取来自服务器的数据
        new Thread(new ClientThread(socket)).start();
        //获取该socket的输出流
        PrintStream ps=new PrintStream(socket.getOutputStream());

        //新建个BufferedReader brIn来获取键盘输入的值
        BufferedReader brIn=new BufferedReader(new InputStreamReader(System.in));
        String lineIn=null;
        while((lineIn=brIn.readLine())!=null)
        {
            //把用户输入内容写入socket对应的输出流
            ps.println(lineIn);
        }

    }
}
