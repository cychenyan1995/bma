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

public class GpsGetResponse implements java.lang.Cloneable, java.io.Serializable
{
    public int oprResult;

    public GpsRecord[] records;

    public GpsGetResponse()
    {
    }

    public GpsGetResponse(int oprResult, GpsRecord[] records)
    {
        this.oprResult = oprResult;
        this.records = records;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        GpsGetResponse _r = null;
        if(rhs instanceof GpsGetResponse)
        {
            _r = (GpsGetResponse)rhs;
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

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::HDaoGps::GpsGetResponse");
        __h = IceInternal.HashUtil.hashAdd(__h, oprResult);
        __h = IceInternal.HashUtil.hashAdd(__h, records);
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
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        oprResult = __is.readInt();
        records = GpsRecordsHelper.read(__is);
    }

    public static final long serialVersionUID = 1890828358L;
}