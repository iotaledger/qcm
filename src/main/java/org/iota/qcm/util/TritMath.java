package org.iota.qcm.util;

public class TritMath {
  public static final int TRITS_PER_SHORT = 9;
  public static int RADIX = 3;
  public static int SHORT_RADIX = 19683;

  public static short[] short_radix = new short[] {1,3,9,27,81,243,729,2187,6561};

  public static int shortTrit(short v) {
    int buf = 0;
    for(int i = TRITS_PER_SHORT; i-- > 0;) {
      buf <<=2;
      buf |= 3;
      if (v > short_radix[i] / 2) {
        v -= short_radix[i];
        buf ^= 2;
      } else if (v < -(short_radix[i] / 2)) {
        v += short_radix[i];
        buf ^= 1;
      }
    }
    return buf;
  }

}
