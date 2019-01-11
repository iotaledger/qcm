package org.iota.qcm.dataflow;

import org.junit.Assert;
import org.junit.Test;

public class FlowTest {

  @Test
  public void testCopyingTransition() {
    Flow in, out;
    Transition copyingTransition;
    final int testSize = 9;
    final byte inTestValue[] = {1,3,3,7,0,7,3,3,1};

    copyingTransition = new CopyingTransition(testSize);
    in = new Flow(copyingTransition, new Flow[0]);
    out = new Flow(copyingTransition, new Flow[]{in});

    in.data.value = inTestValue;

    in.update();

    Assert.assertArrayEquals(inTestValue, out.data.value);
  }
}
