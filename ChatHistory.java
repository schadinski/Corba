//package History;

import ChatApplication.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.*;

//import java.lang.Thread;

public class ChatHistory {
    public static void main(String args[]) {
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

        // get reference to rootpoa and activate the POAManager
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();
        //Get a ref for client
        HistoryImpl historyImpl = new HistoryImpl();
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(historyImpl);
        History refHistory = HistoryHelper.narrow(ref);
     
        // resolve the Object Reference in Naming
        String name = "Chat";
        Server server = ServerHelper.narrow(ncRef.resolve_str(name));

        server.attachHistory(refHistory);

        System.out.print("History server is up and running.\n");
        while(true)
        {}

      }
      catch (Exception e)
      {
        System.out.println("ERROR : " + e) ;
        e.printStackTrace(System.out);
      } 
    }
  }