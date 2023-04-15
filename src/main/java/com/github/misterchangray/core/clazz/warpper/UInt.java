package com.github.misterchangray.core.clazz.warpper;

import com.github.misterchangray.core.util.ConverterUtil;

import java.util.Objects;

public class UInt {
    private int aint;

    /**
     *  get of unsigned
     * @return
     */
    public long get() {
        return aint < 0 ? aint  & 0xFFFFFFFFL : aint;
    }

    /**
     * get of signed
     * @return
     */
    public int raw() {
        return  aint;
    }

    public void set(int aint) {
        this.aint = aint;
    }

    public UInt(int aint) {
        this.aint = aint;
    }

    public UInt() {
    }

    public static UInt valueOf(int aint) {
        return new UInt(aint);
    }

    @Override
    public String toString() {
        return "UInt{val=" + get() + '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UInt uInt = (UInt) o;
        return aint == uInt.aint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aint);
    }
}
