package model.Value;

import model.Type.BoolType;
import model.Type.IntType;
import model.Type.Type;

public class BoolValue implements Value {
    private boolean val;

    public BoolValue(boolean v) {
        val = v;
    }

    public boolean isVal() {
        return val;
    }

    public void setVal(boolean val) {
        this.val = val;
    }
    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }

    @Override
    public boolean equals(Value anotherValue) {
        if(!(anotherValue instanceof IntType))
            return false;
        BoolValue castValue = (BoolValue) anotherValue;
        return this.val == castValue.val;
    }

    @Override
    public String toString() {
        return this.val ? "true" : "false";
    }

}
