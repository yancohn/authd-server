// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   authd.java

import com.goldhuman.Common.*;
import com.goldhuman.IO.PollIO;
import com.goldhuman.IO.Protocol.Manager;
import com.goldhuman.IO.Protocol.Protocol;
import com.goldhuman.xml.parser;
import java.io.FileInputStream;
import java.io.PrintStream;
import org.apache.commons.logging.Log;
import protocol.*;

public class authd
{

	SM sm;

	authd(Conf conf, String s)
		throws Exception
	{
		System.out.println((new StringBuilder("load Enthrallment data:")).append(s).toString());
		sm = new SM();
		sm.add(Enthrallment.getInstance());
		sm.load(s);
	}

	public static void main(String args[])
	{
		try
		{
			String s = System.getProperty("com.wanmei.au.HitEncoding");
			Conf conf = Conf.GetInstance("../lib/authd.conf", s);
			try
			{
				String s1 = null;
				if (args.length > 1)
					s1 = args[1];
				else
					s1 = "osppw_auth";
				if (CertVerify.getInstance().initJKS("../lib/auth.keystore", s1) == 0)
					CertVerify.getInstance().test(CertVerify.decodeHex("d480fa524d83b8e6".toCharArray()));
				else
					System.out.println("ERROR:load key failed!");
			}
			catch (Exception exception1)
			{
				exception1.printStackTrace();
			}
			GAuthServer.GetInstance().enable_enthrallment = "true".equals(conf.find("GAuthServer", "enable_enthrallment"));
			if (GAuthServer.GetInstance().enable_enthrallment)
			{
				String s2 = null;
				if (args.length > 2)
					s2 = args[2];
				authd authd1 = new authd(conf, (String)s2);
			}
			GAuthServer gauthserver = GAuthServer.GetInstance();
			GAuthServer.GetInstance().shared_key = new Octets(conf.find("GAuthServer", "shared_key").getBytes());
			Protocol.Server((Manager)gauthserver);
			System.out.println("authd:: add PollIO task.");
			GAuthServer.GetLog().info("authd:: add PollIO task.");
			try
			{
				parser.parse(new FileInputStream(args[0]));
			}
			catch (Exception exception2)
			{
				exception2.printStackTrace();
			}
			ThreadPool.AddTask(PollIO.GetTask());
			do
				try
				{
					Thread.sleep(1000L);
				}
				catch (Exception exception3) { }
			while (true);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
