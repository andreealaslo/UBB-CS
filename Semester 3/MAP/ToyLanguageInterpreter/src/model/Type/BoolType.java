package model.Type;

import model.Value.BoolValue;
import model.Value.Value;

public class BoolType implements Type{
    @Override
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    @Override
    public Value default_value() {
        return new BoolValue(false);
    }

    @Override
    public Type deepCopy() {return new BoolType();}

    @Override
    public String toString(){
        return "bool";
    }

}
