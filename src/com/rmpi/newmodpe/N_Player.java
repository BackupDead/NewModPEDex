package com.rmpi.newmodpe;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class N_Player extends ScriptableObject {
    public N_Player() {
        super();
    }

    @Override
    public String getClassName() {
        return "N_Player";
    }

    @JSStaticFunction
    public static void setRoundAttack(boolean enable, boolean hitMob, int distance) {
        if (distance > 0)
            nativeSetRoundAttack(enable, hitMob, distance);
    }

    private static native void nativeSetRoundAttack(boolean enable, boolean hitMob, int distance);

    @JSStaticFunction
    public static void setCanFly(boolean enable) {
        nativeSetCanFly(enable);
    }

    private static native void nativeSetCanFly(boolean enable);

    @JSStaticFunction
    public static void sendSimpleChat(String msg) {
        sendRawChat(msg, false);
    }

    @JSStaticFunction
    public static void sendRawChat(String msg, boolean hasSender) {
        sendChat(msg, hasSender, true);
    }

    @JSStaticFunction
    public static void sendChat(String msg, boolean hasSender, boolean safe) {
        if (safe)
            if (msg.equals(""))
                return;
        nativeSendChat(msg, hasSender);
    }

    private static native void nativeSendChat(String msg, boolean hasSender);
}
