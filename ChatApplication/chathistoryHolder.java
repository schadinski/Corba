package ChatApplication;


/**
* ChatApplication/chathistoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* Dienstag, 23. April 2019 21:49 Uhr MESZ
*/

public final class chathistoryHolder implements org.omg.CORBA.portable.Streamable
{
  public ChatApplication.ChatMessage value[] = null;

  public chathistoryHolder ()
  {
  }

  public chathistoryHolder (ChatApplication.ChatMessage[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ChatApplication.chathistoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ChatApplication.chathistoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ChatApplication.chathistoryHelper.type ();
  }

}
