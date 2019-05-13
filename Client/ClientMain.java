package Client;

import ChatApplication.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class ChatClient
{
    public static void main(String args[])
    {
      Scanner scanner = new Scanner(System.in);
      try
      {
        // create and initialize the ORB
        ORB orb = ORB.init(args, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = 
            orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        // resolve the Object Reference in Naming
        String name = "Chat";
        Server server = ServerHelper.narrow(ncRef.resolve_str(name));

        // COPY from ChatServer
        // get reference to rootpoa and activate the POAManager
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();
        //Get a ref for client
        ClientImpl clientImpl = new ClientImpl();
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(clientImpl);
        Client refClient = ClientHelper.narrow(ref);
        //COPY end

        // Get nickname and connect to server
        System.out.println("Choose a nickname\n");

        String nickname = scanner.nextLine();
        server.attach(nickname, refClient);

        boolean wantChat = true;
        while(wantChat)
        {
          ChatMessage chatMsg = new ChatMessage();
          chatMsg.nickname = nickname;
          chatMsg.text = scanner.nextLine();

          if(chatMsg.text.equals("!Exit"))
          {
            server.detach(nickname);
            wantChat = false;
          }
          else
          {
            server._notify(chatMsg);
          }  
        }  
      }
      catch (Exception e)
      {
        System.out.println("ERROR : " + e) ;
        e.printStackTrace(System.out);
      }
      finally
      {
        scanner.close(); 
      }
    }

}