package org.iota.qcm.dataflow;

import org.junit.Assert;
import org.junit.Test;

public class FlowTest {

  @Test
  public void testCopyingTransition() {
    Flow in, out;
    Transition copyingTransition;

    copyingTransition = new CopyingTransition(9);
    in = new Flow(copyingTransition, new Flow[0]);
    out = new Flow(copyingTransition, new Flow[]{in});

    in.data.value[0] = 24;

    in.update();

    Assert.assertArrayEquals(in.data.value, out.data.value);
  }
}
