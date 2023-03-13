package model.Value;

import model.Type.Type;

public interface Value {
    Type getType();
    Value deepCopy();

    boolean equals(Value anotherValue);
}
