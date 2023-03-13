package model.Type;

import model.Value.IntValue;
import model.Value.Value;

public class IntType implements Type{
    @Override
    public boolean equals(Object another) {
        return another instanceof IntType;
    }
    @Override
    public String toString(){
        return "int";
    }
    @Override
    public Value default_value(){
        return new IntValue(0);
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }
}
