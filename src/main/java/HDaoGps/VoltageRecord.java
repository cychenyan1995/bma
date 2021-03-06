// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `HDao_gps.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package HDaoGps;

public class VoltageRecord implements java.lang.Cloneable, java.io.Serializable
{
    public int gpsTime;

    public int voltage;

    public int storeTime;

    public VoltageRecord()
    {
    }

    public VoltageRecord(int gpsTime, int voltage, int storeTime)
    {
        this.gpsTime = gpsTime;
        this.voltage = voltage;
        this.storeTime = storeTime;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        VoltageRecord _r = null;
        if(rhs instanceof VoltageRecord)
        {
            _r = (VoltageRecord)rhs;
        }

        if(_r != null)
        {
            if(gpsTime != _r.gpsTime)
            {
                return false;
            }
            if(voltage != _r.voltage)
            {
                return false;
            }
            if(storeTime != _r.storeTime)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::HDaoGps::VoltageRecord");
        __h = IceInternal.HashUtil.hashAdd(__h, gpsTime);
        __h = IceInternal.HashUtil.hashAdd(__h, voltage);
        __h = IceInternal.HashUtil.hashAdd(__h, storeTime);
        return __h;
    }

    public java.lang.Object
    clone()
    {
        java.lang.Object o = null;
        try
        {
            o = super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return o;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeInt(gpsTime);
        __os.writeInt(voltage);
        __os.writeInt(storeTime);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        gpsTime = __is.readInt();
        voltage = __is.readInt();
        storeTime = __is.readInt();
    }

    public static final long serialVersionUID = -594470148L;
}
