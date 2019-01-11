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

    in.flow();

    Assert.assertArrayEquals(inTestValue, out.data.value);
  }

  @Test
  public void testLookupTransition() {
    Data input;
    Flow in0, in1, in2, out;
    Lut eqLut;
    byte[] exp = new byte[]{1};

    eqLut = new Lut();
    eqLut.addCell(1,1,1, (byte) 1);
    eqLut.addCell(2,2,2, (byte) 2);
    eqLut.addCell(3,3,3, (byte) 3);

    input = new Data(3);
    in0 = new Flow(input, 0, 1);
    in1 = new Flow(input, 1, 1);
    in2 = new Flow(input, 2, 1);
    out = new Flow(eqLut, new Flow[]{in0, in1, in2});

    input.value[0] = 1;
    input.value[1] = 1;
    input.value[2] = 1;

    input.update();

    Assert.assertArrayEquals(exp, out.data.value);

    input.value[0] = 0;
    input.value[1] = 0;
    input.value[2] = 0;
    exp[0] = 0;
    input.update();

    Assert.assertArrayEquals(exp, out.data.value);

  }
}
