package Client;

import ChatApplication.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

class ClientImpl extends ClientPOA
{
     //get a message, print it
     public void message(ChatMessage msg)
     {
        System.out.printf("%s: %s\n", msg.nickname, msg.text);
     }

     //a new member joined chat
     public void join(ChatMessage msg)
     {
        System.out.printf("%s joined the chat\n", msg.nickname);
     }

     //a member leaves chat
     public void leave(ChatMessage msg)
     {
        System.out.printf("%s leaved the chat\n", msg.nickname);
     }

     // get the history from server, print it all
     public void putHistory(ChatMessage[] history)
     {
        System.out.print("Chat history:\n");
        for( ChatMessage msg : history)
        {
          System.out.printf("%s: %s\n", msg.nickname, msg.text);
        }
     }
}