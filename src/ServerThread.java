import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by zcy on 2017/5/4.
 */
public class ServerThread implements Runnable {
    //该线程所处理的Socket对应的输入流
    BufferedReader br=null;
    //定义当前线程所处理的Socket
    Socket s=null;
    public ServerThread(Socket s)throws IOException
    {
        this.s=s;
        br=new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println("客户:"+this.s.getInetAddress()+"已上线");
    }

    public void run()
    {
        try
        {
            String content=null;
            //采用循环不断地从socket中读取客户端发送过来的数据
            while ((content = readFromClient())!=null)
            {
                //遍历socketlist中的每个socket
                //将读到的内容向每个Socket发送一次
                for (Socket s: MyServer.socketList )
                {
                    PrintStream ps=new PrintStream(s.getOutputStream());
                    ps.println(content+this.s.getInetAddress());


                }
                System.out.println(this.s.getInetAddress()+":"+content);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //定义读取客户端数据的方法
    private String readFromClient()
    {
        try
        {
            return br.readLine();
        }
        //如果遇到异常，则表明该socket对应的客户端已经关闭
        catch (IOException e)
        {
            //删除该socket
            MyServer.socketList.remove(s);
        }
        return null;
    }
}
