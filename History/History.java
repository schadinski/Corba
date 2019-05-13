package History;

import ChatApplication.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.omg.CORBA.*;

// implements ClientOperations to get messages from server like a client
class HistoryImpl extends HistoryPOA implements ClientOperations
{
    private List<ChatMessage> allMsgs;

    public HistoryImpl()
    {
        allMsgs = new ArrayList<ChatMessage>();
    }

    public ChatMessage[] getNMessages (short numberOfmessages)
    {
        ChatMessage[] lastNElements;
        //if no msgs in History create a dummy msgs to tell client therer are no msgs in history
        if(allMsgs.size() == 0)
        {
            ChatMessage dummyMsg = new ChatMessage();
            dummyMsg.nickname = "HistoryServer";
            dummyMsg.text = "No messages in history\n";
            lastNElements = new ChatMessage[1];
            lastNElements[0]= dummyMsg;
        }
        // if fewer msgs in history present than i need
        else if(numberOfmessages>(short) allMsgs.size())
        {
                // if not enough msgs in history
                lastNElements = new ChatMessage[allMsgs.size()];
                ListIterator it = allMsgs.listIterator();
                for(int i = 0; i < allMsgs.size(); i++)
                {
                    lastNElements[i] = (ChatMessage) it.next();
                }
        }
        // if more elements in last than i need
        else
        {
            lastNElements = new ChatMessage[numberOfmessages];
            ListIterator it = allMsgs.listIterator(allMsgs.size() - numberOfmessages);
            for(int i = 0; i < numberOfmessages; i++)
            {
                lastNElements[i] = (ChatMessage) it.next();
            }
        }
        return lastNElements;

    }
    
    public void message (ChatApplication.ChatMessage message){
        allMsgs.add(message);
    }

    // interface methods
    public void join(ChatMessage msg)
    {}

    public void leave(ChatMessage msg)
    {}

    public void putHistory(ChatMessage[] history)
    {}
}