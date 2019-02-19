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

public abstract class Callback_DaoGpsService_getMaxVolDiff extends Ice.TwowayCallback
{
    public abstract void response(boolean __ret, DiffVoltageGetResponse res);

    public final void __completed(Ice.AsyncResult __result)
    {
        DaoGpsServicePrx __proxy = (DaoGpsServicePrx)__result.getProxy();
        boolean __ret = false;
        DiffVoltageGetResponseHolder res = new DiffVoltageGetResponseHolder();
        try
        {
            __ret = __proxy.end_getMaxVolDiff(res, __result);
        }
        catch(Ice.LocalException __ex)
        {
            exception(__ex);
            return;
        }
        response(__ret, res.value);
    }
}
