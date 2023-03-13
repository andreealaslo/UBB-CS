package model.Type;

import model.Value.StringValue;
import model.Value.Value;

public class StringType implements Type{
    @Override
    public boolean equals(Object another){
        return another instanceof StringType;
    }
    @Override
    public Value default_value() {
        return new StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }

    @Override
    public String toString() {
        return "string";
    }
}
