package com.twu.biblioteca;

/**
 * Created by esiow on 15/01/2015.
 */
public class TestOutputHandler implements OutputHandler {

    private StringBuffer sb = new StringBuffer();

    @Override
    public void println(String str) {
        sb.append(str+"\n");
    }

    @Override
    public void print(String str) {
        sb.append(str);
    }

    public boolean hasMessage(String message) {
        return sb.toString().contains(message);
    }

//    public boolean lastMessageIs(String message) {
//        return sb.toString().endsWith(message) || sb.toString().endsWith(message+"\n");
//    }
}
