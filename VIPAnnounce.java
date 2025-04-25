package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class VIPAnnounce extends Protocol
{
    public int userid;
    public int aid;
    public int zoneid;
    public int currenttime;
    public int starttime;
    public int endtime;
    public int status;
    public int viplevel;
    public int totalcash;
    public int recenttotalcash;
    public int infolack;
    public int reserved1;

    public VIPAnnounce()
    {
    }

    public OctetsStream marshal(OctetsStream os)
    {
        os.marshal(userid);
        os.marshal(aid);
        os.marshal(zoneid);
        os.marshal(currenttime);
        os.marshal(starttime);
        os.marshal(endtime);
        os.marshal(status);
        os.marshal(viplevel);
        os.marshal(totalcash);
        os.marshal(recenttotalcash);
        os.marshal(infolack);
        os.marshal(reserved1);
        return os;
    }

    public OctetsStream unmarshal(OctetsStream os) throws MarshalException
    {
        userid = os.unmarshal_int();
        aid = os.unmarshal_int();
        zoneid = os.unmarshal_int();
        currenttime = os.unmarshal_int();
        starttime = os.unmarshal_int();
        endtime = os.unmarshal_int();
        status = os.unmarshal_int();
        viplevel = os.unmarshal_int();
        totalcash = os.unmarshal_int();
        recenttotalcash = os.unmarshal_int();
        infolack = os.unmarshal_int();
        reserved1 = os.unmarshal_int();
        return os;
    }

    public Object clone()
    {
        try
        {
            VIPAnnounce o = (VIPAnnounce)super.clone();
            return o;
        }
        catch (Exception e) { }
        return null;
    }

    public void Process(Manager manager, Session session) throws ProtocolException
    {
    }
}