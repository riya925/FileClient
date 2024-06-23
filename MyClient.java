import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient
{
	public static void main(String[] args)
	{
		try
		{
			Socket server=new Socket("localhost",4321);
			
			DataInputStream dis=new DataInputStream(server.getInputStream());
			DataOutputStream dos=new DataOutputStream(server.getOutputStream());
			Scanner scan=new Scanner(System.in);
			String fna="";
			
			System.out.println("\n Enter file name : ");
			fna=scan.nextLine();
			
			dos.writeUTF(fna);
			
			String msg=dis.readUTF();
			
			if(msg.equals("FILE EXISTS"))
			{
				System.out.println("\n File exists.... ");
				
				FileWriter fw=new FileWriter("src//"+fna);
				String data=dis.readUTF();
				
				fw.write(data);
				fw.close();
				System.out.print("\n File Downloaded Successfully.... ");
			}
			else
			{
				System.out.println("\n File doesn't exists.... ");
			}
			
			dis.close();
			dos.close();
			server.close();
		}
		catch(Exception e)
		{
			System.out.println("\n CLIENT ERROR : "+e.getMessage());
		}
	}
}