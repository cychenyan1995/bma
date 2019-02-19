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

/**
 * Latest Gps record with bindTime
 **/
public class LatestGpsGetRequestWithBindTime implements java.lang.Cloneable, java.io.Serializable
{
    public long userID;

    public int apType;

    public int extraMapReq;

    public int bindTime;

    public LatestGpsGetRequestWithBindTime()
    {
    }

    public LatestGpsGetRequestWithBindTime(long userID, int apType, int extraMapReq, int bindTime)
    {
        this.userID = userID;
        this.apType = apType;
        this.extraMapReq = extraMapReq;
        this.bindTime = bindTime;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        LatestGpsGetRequestWithBindTime _r = null;
        if(rhs instanceof LatestGpsGetRequestWithBindTime)
        {
            _r = (LatestGpsGetRequestWithBindTime)rhs;
        }

        if(_r != null)
        {
            if(userID != _r.userID)
            {
                return false;
            }
            if(apType != _r.apType)
            {
                return false;
            }
            if(extraMapReq != _r.extraMapReq)
            {
                return false;
            }
            if(bindTime != _r.bindTime)
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::HDaoGps::LatestGpsGetRequestWithBindTime");
        __h = IceInternal.HashUtil.hashAdd(__h, userID);
        __h = IceInternal.HashUtil.hashAdd(__h, apType);
        __h = IceInternal.HashUtil.hashAdd(__h, extraMapReq);
        __h = IceInternal.HashUtil.hashAdd(__h, bindTime);
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
        __os.writeLong(userID);
        __os.writeInt(apType);
        __os.writeInt(extraMapReq);
        __os.writeInt(bindTime);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        userID = __is.readLong();
        apType = __is.readInt();
        extraMapReq = __is.readInt();
        bindTime = __is.readInt();
    }

    public static final long serialVersionUID = 126781250L;
}