package ChatApplication;

/**
* ChatApplication/HistoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* Dienstag, 23. April 2019 21:49 Uhr MESZ
*/

public final class HistoryHolder implements org.omg.CORBA.portable.Streamable
{
  public ChatApplication.History value = null;

  public HistoryHolder ()
  {
  }

  public HistoryHolder (ChatApplication.History initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ChatApplication.HistoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ChatApplication.HistoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ChatApplication.HistoryHelper.type ();
  }

}
