from validation import *


def create_animal(code: str, name: str, type: str, species: str):
    """
    Function to create an animal.
    :param code:
    :param name:
    :param type:
    :param species:
    :return:
    """
    return [code, name, type, species]


def get_code(animal):
    return animal[0]


def get_name(animal):
    return animal[1]


def get_type(animal):
    return animal[2]


def get_species(animal):
    return animal[3]


def set_type(animal, new_type):
    animal[2] = new_type


def set_species(animal, new_species):
    animal[3] = new_species


def add_animal(animals_list, animal_to_be_added):
    """
    Fucntion which adds the animal to the animals list
    :param animals_list: animals list
    :param animal_to_be_added: animal to be added
    :return:
    """
    for animal in animals_list:
        if get_code(animal) == get_code(animal_to_be_added):
            raise ValueError("Duplicate code!")
    validate_animal(animal_to_be_added)
    animals_list.append(animal_to_be_added)


def modify_type(animals_list, code, new_type):
    for position in range(len(animals_list)):
        if get_code(animals_list[position]) == code:
            set_type(animals_list[position], new_type)


def change_type_of_species(animals_list, species, new_type):
    for position in range(len(animals_list)):
        if get_species(animals_list[position]) == species:
            set_type(animals_list[position], new_type)


def show_animals_with_given_type(animals_list, type):
    new_list = []
    for position in range(len(animals_list)):
        if get_type(animals_list[position]) == type:
            new_list.append(animals_list[position])
    sorted(new_list, key=get_name)
    return new_list
