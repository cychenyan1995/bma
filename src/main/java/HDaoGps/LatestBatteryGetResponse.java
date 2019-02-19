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

public class LatestBatteryGetResponse implements java.lang.Cloneable, java.io.Serializable
{
    public int oprResult;

    public BatteryRecord record;

    public LatestBatteryGetResponse()
    {
    }

    public LatestBatteryGetResponse(int oprResult, BatteryRecord record)
    {
        this.oprResult = oprResult;
        this.record = record;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        LatestBatteryGetResponse _r = null;
        if(rhs instanceof LatestBatteryGetResponse)
        {
            _r = (LatestBatteryGetResponse)rhs;
        }

        if(_r != null)
        {
            if(oprResult != _r.oprResult)
            {
                return false;
            }
            if(record != _r.record)
            {
                if(record == null || _r.record == null || !record.equals(_r.record))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::HDaoGps::LatestBatteryGetResponse");
        __h = IceInternal.HashUtil.hashAdd(__h, oprResult);
        __h = IceInternal.HashUtil.hashAdd(__h, record);
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
        __os.writeInt(oprResult);
        record.__write(__os);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        oprResult = __is.readInt();
        record = new BatteryRecord();
        record.__read(__is);
    }

    public static final long serialVersionUID = -1140653209L;
}
