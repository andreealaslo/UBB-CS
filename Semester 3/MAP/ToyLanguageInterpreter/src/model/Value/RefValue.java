package model.Value;

import model.Type.RefType;
import model.Type.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    public Type getLocationType(){
        return locationType;
    }
    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }

    @Override
    public boolean equals(Value anotherValue) {
        return true;
    }

    @Override
    public String toString() {
        return String.format("(%d, %s)", address, locationType);
    }

}
