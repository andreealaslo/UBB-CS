def validate_animal(animal):
    errors = ""
    if animal[0] == "":
        raise ValueError("Invalid code!")
    if animal[1] == "":
        raise ValueError("Invalid name!")
    if animal[2] == "":
        raise ValueError("Invalid type!")
    if animal[3] == "":
        raise ValueError("Invalid species!")
    if len(errors) > 0:
        raise ValueError(errors)


def validate_type(animal):
    errors = ""
    if animal[2] == "":
        raise ValueError("Invalid type!")
    if len(errors) > 0:
        raise ValueError(errors)
