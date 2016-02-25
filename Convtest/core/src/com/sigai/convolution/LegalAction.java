package com.sigai.convolution;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Richard on 2/17/2016.
 */
public class LegalAction extends ArrayDeque<String> {
    // maps each available move to a protocol (jump, split, warp, move)
    public ArrayDeque<String> steps;

    public LegalAction (ArrayDeque<String> moveStep)
    {
        steps = moveStep;
    }

    @Override
    public boolean add(String s) {
        return false;
    }

    @Override
    public boolean offer(String s) {
        return false;
    }

    @Override
    public String remove() {
        return null;
    }

    @Override
    public String poll() {
        return null;
    }

    @Override
    public String element() {
        return null;
    }

    @Override
    public String peek() {
        return null;
    }
}
