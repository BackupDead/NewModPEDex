package com.rmpi.newmodpe;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

import java.lang.reflect.Field;

public class N_Proxy extends ScriptableObject {
    private final static Thread scriptingEnabler = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Field scriptingEnabled = Class.forName("net.zhuoweizhang.mcpelauncher.ScriptManager")
                        .getDeclaredField("scriptingEnabled");

                while (true) {
                    scriptingEnabled.setAccessible(true);
                    scriptingEnabled.setBoolean(null, true);
                }
            } catch (Exception e) {
                // ?
            }
        }
    });

    public N_Proxy() {
        super();
    }

    @Override
    public String getClassName() {
        return "N_Proxy";
    }

    @JSStaticFunction
    public static void setFixedScripting(boolean enable) {
        if (enable)
            if (!scriptingEnabler.isAlive())
                scriptingEnabler.start();
        else
            if (scriptingEnabler.isAlive())
                scriptingEnabler.stop();
    }

    @JSStaticFunction
    public static Thread startThread(final Function func) {
        final Context ctx = Context.enter();
        Thread ret = new Thread(new Runnable() {
            @Override
            public void run() {
                func.call(ctx, func.getParentScope(), func.getParentScope(), new Object[] {});
            }
        });
        ret.start();
        return ret;
    }
}
