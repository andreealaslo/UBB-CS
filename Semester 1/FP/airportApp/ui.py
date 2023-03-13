from functions import *


def print_menu():
    print("\n")
    print("                  Airport application!!                  ")
    print("1. Add a flight. ")
    print("2. Modify the duration of a given flight.")
    print("3. Show the rerouted flight of a given destination city.")
    print("4. Show all flights with a given departure city, sorted increasing by their duration.")
    print("X. Exit.")


def generate_flights(flights_list):
    add_flight(flights_list, create_flight("0A0001", 80, "Cluj-Napoca", "Paris"))
    add_flight(flights_list, create_flight("0A0002", 110, "Bucharest", "Paris"))
    add_flight(flights_list, create_flight("0A0003", 180, "Cluj-Napoca", "London"))
    add_flight(flights_list, create_flight("0A0004", 90, "Iasi", "Paris"))
    add_flight(flights_list, create_flight("0A0005", 30, "Cluj-Napoca", "Bucharest"))


def to_str(flight):
    return f"Flight with code: {get_code_flight(flight)} has a duration of {get_duration_flight(flight)} minutes " \
           f"and goes from {get_departure_city(flight)} to {get_destination_city(flight)}"


def print_flights_list(flights_list):
    print("\n Flights: ")
    if flights_list is not None:
        for flight in flights_list:
            print(to_str(flight))


def add_flight_ui(flights_list):
    code = input("Give the code of the flight: ")
    duration = int(input("Give the duration of the flight: "))
    departure_city = input("Give the departure city of the flight: ")
    destination_city = input("Give the departure city of the flight: ")
    flight_to_add = create_flight(code, duration, departure_city, destination_city)
    try:
        add_flight(flights_list, flight_to_add)
    except ValueError as ve:
        print(str(ve))


def modify_duration_of_flight_ui(flights_list):
    code = input("Give the code of the flight whose duration you want to modify: ")
    new_duration = int(input("Give the new duration: "))
    modify_duration_of_flight(flights_list, code, new_duration)


def change_destination_for_rerouted_flights_ui(flights_list):
    initial_destination_city = input("Give the initial destination city: ")
    new_destination_city = input("Give the new destination city (flights are rerouted here): ")
    try:
        change_destination_for_rerouted_flights(flights_list, initial_destination_city, new_destination_city)
    except ValueError as ve:
        print(str(ve))


def show_flights_with_given_departure_city_ui(flights_list):
    departure_city = input("Give the departure city: ")
    filtered_list = show_flights_with_given_departure_city(flights_list, departure_city)
    return filtered_list


def start():
    flights_list = []
    generate_flights(flights_list)
    print_flights_list(flights_list)
    while True:
        print_menu()
        cmd = input(">")
        if cmd == "1":
            add_flight_ui(flights_list)
            print_flights_list(flights_list)
        elif cmd == "2":
            modify_duration_of_flight_ui(flights_list)
            print_flights_list(flights_list)
        elif cmd == "3":
            change_destination_for_rerouted_flights_ui(flights_list)
            print_flights_list(flights_list)
        elif cmd == "4":
            filtered_list = show_flights_with_given_departure_city_ui(flights_list)
            print_flights_list(filtered_list)
        elif cmd == "X":
            break
        else:
            print("Bad command!")
