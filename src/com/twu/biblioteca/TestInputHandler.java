package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by esiow on 15/01/2015.
 */
public class TestInputHandler implements InputHandler {

    private List<String> inputs;

    public TestInputHandler() {
        this.inputs = new LinkedList<String>();
    }

    public void add(String input) {
        this.inputs.add(input);
    }

    public void add(List<String> inputs) {
        this.inputs.addAll(inputs);
    }

    @Override
    public String nextLine() {
        if (inputs.isEmpty()) {
            return "";
        }
        String result = inputs.get(0);
        inputs.remove(0);
        return result;
    }
}
