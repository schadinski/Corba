package ChatApplication;

/**
* ChatApplication/ServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* Dienstag, 23. April 2019 21:49 Uhr MESZ
*/

public final class ServerHolder implements org.omg.CORBA.portable.Streamable
{
  public ChatApplication.Server value = null;

  public ServerHolder ()
  {
  }

  public ServerHolder (ChatApplication.Server initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ChatApplication.ServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ChatApplication.ServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ChatApplication.ServerHelper.type ();
  }

}
