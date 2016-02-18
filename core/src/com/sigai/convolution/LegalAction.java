package com.sigai.convolution;

import java.util.Queue;

/**
 * Created by Richard on 2/17/2016.
 */
public class LegalAction {
    // maps each available move to a protocol (jump, split, warp, move)
    public Queue<String> steps;

    public LegalAction (Queue<String> moveStep)
    {
        steps = moveStep;
    }
}
