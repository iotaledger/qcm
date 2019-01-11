package org.iota.qcm.dataflow;

import org.junit.Assert;
import org.junit.Test;

public class FlowTest {

  @Test
  public void testCopyingTransition() throws Exception {
    Flow in, out;
    Transition copyingTransition;
    final int testSize = 9;
    final byte inTestValue[] = {1,3,3,7,0,7,3,3,1};

    copyingTransition = new CopyingTransition(testSize);
    in = new Flow(copyingTransition, new Flow[0], null);
    out = new Flow(copyingTransition, new Flow[]{in}, null);

    in.data.value = inTestValue;

    in.invoke();

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

    input = new Data(3, null);
    in0 = new Flow(input, 0, 1, null);
    in1 = new Flow(input, 1, 1, null);
    in2 = new Flow(input, 2, 1, null);
    out = new Flow(eqLut, new Flow[]{in0, in1, in2}, null);

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

  @Test
  public void testMergerTransition() {
    Data input;
    Flow in0, in1, in2, out;
    Merger merger;
    byte[] exp = new byte[]{1};

    merger = new Merger(1);

    input = new Data(3, null);
    in0 = new Flow(input, 0, 1, null);
    in1 = new Flow(input, 1, 1, null);
    in2 = new Flow(input, 2, 1, null);
    out = new Flow(merger, new Flow[]{in0, in1, in2}, null);

    input.value[0] = 1;

    input.update();

    Assert.assertArrayEquals(exp, out.data.value);

    input.value[1] = 1;
    input.update();

    input.value[2] = 3;
    try {
      input.update();
      Assert.fail();
    } catch (Exception e) { }
  }
}
