from functions import *


def print_menu():
    print("                      \n ZOO application!           ")
    print("1. Add an animal.")
    print("2. Modify the type of a given animal.")
    print("3. Change the type of an specie.")
    print("4. Show all animals with a given type.")
    print("X. Exit")


def to_str(animal):
    return f"Animal with code {get_code(animal)}, name: {get_name(animal)}, " \
           f"type: {get_type(animal)} and species: {get_species(animal)}"


def print_list(animals_list):
    print("Animals list: \n")
    for animal in animals_list:
        print(to_str(animal))


def generate_animals(animals_list):
    add_animal(animals_list, create_animal("001", "Alex", "carnivore", "bear"))
    add_animal(animals_list, create_animal("002", "Ana", "herbivore", "aaaaa"))
    add_animal(animals_list, create_animal("003", "Anamaria", "carnivore", "horse"))
    add_animal(animals_list, create_animal("004", "Maria", "herbivore", "cow"))
    add_animal(animals_list, create_animal("005", "Mara", "herbivore", "zebra"))


def add_animal_ui(animals_list):
    code = input("Give the code: ")
    name = input("Give the name: ")
    type = input("Give the type: ")
    species = input("Give the species: ")
    animal_to_be_added = create_animal(code, name, type, species)
    try:
        add_animal(animals_list, animal_to_be_added)
    except ValueError as ve:
        print(str(ve))


def modify_type_ui(animals_list):
    code = input("Give the code: ")
    new_type = input("Give the new type: ")
    modify_type(animals_list, code, new_type)


def change_type_of_species_ui(animals_list):
    species = input("Give the species of whose type you want to change: ")
    new_type = input("Give the new type: ")
    change_type_of_species(animals_list, species, new_type)


def show_all_animals_of_given_type_ui(animals_list):
    type = input("Give the type: ")
    filtered = show_animals_with_given_type(animals_list, type)
    return filtered


def start():
    animals_list = []
    generate_animals(animals_list)
    print_list(animals_list)
    while True:
        print_menu()
        cmd = input(">>>")
        if cmd == "1":
            add_animal_ui(animals_list)
            print_list(animals_list)
        elif cmd == "2":
            modify_type_ui(animals_list)
            print_list(animals_list)
        elif cmd == "3":
            change_type_of_species_ui(animals_list)
            print_list(animals_list)
        elif cmd == "4":
            filtered = show_all_animals_of_given_type_ui(animals_list)
            print_list(filtered)
        elif cmd == "X":
            break
        else:
            print("Bad input!")
