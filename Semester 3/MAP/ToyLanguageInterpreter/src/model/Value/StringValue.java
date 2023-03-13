package model.Value;

import model.Type.IntType;
import model.Type.StringType;
import model.Type.Type;

import java.util.Objects;

public class StringValue implements Value{
    private String val;

    public StringValue(String val) {
        this.val = val;
    }

    public String getVal() {
        return  val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(val);
    }

    @Override
    public boolean equals(Value anotherValue) {
        if(!(anotherValue instanceof IntType))
            return false;
        StringValue castValue = (StringValue) anotherValue;
        return Objects.equals(this.val, castValue.val);
    }

    @Override
    public String toString() {
        return "\"" + this.val + "\"";
    }
}
