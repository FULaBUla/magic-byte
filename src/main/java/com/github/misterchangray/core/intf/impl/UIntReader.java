package com.github.misterchangray.core.intf.impl;

import com.github.misterchangray.core.clazz.FieldMetaInfo;
import com.github.misterchangray.core.clazz.warpper.UInt;
import com.github.misterchangray.core.intf.MReader;
import com.github.misterchangray.core.util.DynamicByteBuffer;

/**
 * @description:
 * @author: Ray.chang
 * @create:  2023-04-14 13:15
 **/
public class UIntReader extends MReader {
    public UIntReader(FieldMetaInfo _fieldMetaInfo) {
        super(_fieldMetaInfo);
    }

    @Override
    public Object readFormObject(Object object) throws IllegalAccessException {
        return this.fieldMetaInfo.getField().get(object);
    }

    @Override
    public Object readFormBuffer(DynamicByteBuffer buffer, Object entity)  throws IllegalAccessException {
        super.saveDelayCalcIfDynamic(buffer, entity);
        return UInt.build().signed(buffer.getInt());
    }
}
