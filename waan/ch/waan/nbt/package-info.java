/**
 * This package provides a lightweight solution for reading / writing data in
 * the <i>Named Binary String<i> data format as defined by Notch. The format is
 * however expanded a bit to allow for a lower uncompressed memory usage.
 * 
 * For example an additional Boolean-Array Tag has been introduced to allow for
 * 1-bit-per-boolean encoding instead of the default 8-bit-per-boolean solution.
 * 
 * @author Andreas Wälchli (andreas.waelchli@me.com)
 * @since Util 1.0
 */
package ch.waan.nbt;