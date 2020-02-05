/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.bookmyshow.app.enums;

public enum AuditoriumRowPos {

    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    I("I"),
    J("J"),
    K("K"),
    L("L"),
    M("M"),
    N("N"),
    O("O"),
    P("P"),
    Q("Q"),
    R("R"),
    S("S"),
    T("T"),
    U("U"),
    V("V"),
    W("W"),
    X("X"),
    Y("Y"),
    Z("Z");

    private String pos;

    public String getPos() {
        return pos;
    }

    private AuditoriumRowPos(String pos) {
        this.pos = pos;
    }

    public static AuditoriumRowPos getByPos(String pos) {
        for (AuditoriumRowPos auditoriumRowPos : values()){
            if(auditoriumRowPos.getPos().equalsIgnoreCase(pos)){
                return auditoriumRowPos;
            }
        }
        return null;
    }
}