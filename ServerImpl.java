//package Server;

import ChatApplication.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CosNaming.*;

import java.util.HashMap;

import org.omg.CORBA.*;
import org.omg.PortableServer.POA;

class ServerImpl extends ServerPOA 
{
    private ORB orb;
    private HashMap<String, ChatApplication.Client> allClients;
    private History chatHistory;

    public ServerImpl()
    {
        allClients = new HashMap<String, ChatApplication.Client>();
    } 

    public void setORB(ORB orb_val)
    {
       orb = orb_val;
    }

    // a new member joined the chat
    public boolean attach(String nickname, ChatApplication.Client newRef) 
    {
        System.out.println("Client attaching\n");
        boolean ret = true;
        if( allClients.containsKey(nickname) )
        {
          ret = false;
        }
        else
        {
            ChatMessage msg = new ChatMessage();
            msg.nickname = nickname;
            msg.text = "";
            for (Client ref : allClients.values())
            {
                ref.join(msg);
            }
            allClients.put(nickname, newRef);
            ChatMessage[] currHistory = new ChatMessage[5];
            short noOfMsgs = 5;
            currHistory = chatHistory.getNMessages(noOfMsgs);
            newRef.putHistory(currHistory);

        }
        return ret;
    }

    // a member will leave the chat
    public boolean detach(String nickname)
    {
        boolean ret = true;
        // is Null if client isn't in Map
        if (allClients.remove(nickname) == null )
        {
            ret = false;    
        }
        else
        {
            ChatMessage msg = new ChatMessage();
            msg.nickname = nickname;
            msg.text = "";
            for(Client ref : allClients.values())
            {
                ref.leave(msg);
            } 
        }
        return ret;
    }

    // History server will call this at start up to register at a client 
    public boolean attachHistory(ChatApplication.History ref)
    {
        chatHistory = ref;
        return true;
    }

    // a member wrote a new message
    // send it to all clients and history server
    public void notify(ChatApplication.ChatMessage msg)
    {
        chatHistory.message(msg);
        if (!allClients.isEmpty()){
            for (Client ref : allClients.values()) {
                ref.message(msg);
            }
        }
    }
}