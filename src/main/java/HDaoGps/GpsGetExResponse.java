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

public class GpsGetExResponse implements java.lang.Cloneable, java.io.Serializable
{
    public int oprResult;

    public GpsRecord[] records;

    public boolean fullFlag;

    public int lastGpsTime;

    public GpsGetExResponse()
    {
    }

    public GpsGetExResponse(int oprResult, GpsRecord[] records, boolean fullFlag, int lastGpsTime)
    {
        this.oprResult = oprResult;
        this.records = records;
        this.fullFlag = fullFlag;
        this.lastGpsTime = lastGpsTime;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        GpsGetExResponse _r = null;
        if(rhs instanceof GpsGetExResponse)
        {
            _r = (GpsGetExResponse)rhs;
        }

        if(_r != null)
        {
            if(oprResult != _r.oprResult)
            {
                return false;
            }
            if(!java.util.Arrays.equals(records, _r.records))
            {
                return false;
            }
            if(fullFlag != _r.fullFlag)
            {
                return false;
            }
            if(lastGpsTime != _r.lastGpsTime)
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::HDaoGps::GpsGetExResponse");
        __h = IceInternal.HashUtil.hashAdd(__h, oprResult);
        __h = IceInternal.HashUtil.hashAdd(__h, records);
        __h = IceInternal.HashUtil.hashAdd(__h, fullFlag);
        __h = IceInternal.HashUtil.hashAdd(__h, lastGpsTime);
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
        GpsRecordsHelper.write(__os, records);
        __os.writeBool(fullFlag);
        __os.writeInt(lastGpsTime);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        oprResult = __is.readInt();
        records = GpsRecordsHelper.read(__is);
        fullFlag = __is.readBool();
        lastGpsTime = __is.readInt();
    }

    public static final long serialVersionUID = 1788191615L;
}
