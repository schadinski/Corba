package Server;

import ChatApplication.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class ChatServer {
    public static void main(String args[]) {
      try
      {
        // create and initialize the ORB
        ORB orb = ORB.init(args, null);
  
        // get reference to rootpoa and activate the POAManager
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();
  
        // create servant and register it with the ORB
        ServerImpl serverImpl = new ServerImpl();
        serverImpl.setORB(orb); 
  
        // get object reference from the servant
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(serverImpl);
        Server href = ServerHelper.narrow(ref);
            
        // get the root naming context
        org.omg.CORBA.Object objRef =
            orb.resolve_initial_references("NameService");
        // Use NamingContextExt which is part of the Interoperable
        // Naming Service (INS) specification.
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
  
        // bind the Object Reference in Naming
        String name = "Chat";
        NameComponent path[] = ncRef.to_name(name);
        ncRef.rebind(path, href);
  
        System.out.println("ChatServer ready and waiting ...");        
  
        // wait for invocations from clients
        orb.run();
      } 
      catch (Exception e)
      {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
           
      System.out.println("ChatServer Exiting ...");    
    }
  }