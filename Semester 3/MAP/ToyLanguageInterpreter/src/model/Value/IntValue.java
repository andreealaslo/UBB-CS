package model.Value;

import model.Type.IntType;
import model.Type.Type;

public class IntValue implements Value{
    private int val;
    public IntValue(int v){
        val = v;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }

    @Override
    public boolean equals(Value anotherValue) {
        if(!(anotherValue instanceof IntType))
            return false;
        IntValue castValue = (IntValue) anotherValue;
        return this.val == castValue.val;
    }

    @Override
    public String toString() {
        return String.format("%d", this.val);
    }
}
