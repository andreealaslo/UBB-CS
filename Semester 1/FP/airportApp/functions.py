from validation import *


def create_flight(code: str, duration: int, departure_city: str, destination_city: str):
    """
    Function which creates a flight
    :param code: code, identifier, something like: 0B3002
    :param duration: duration of the flight, in minutes
    :param departure_city: the city from which the plane is taking off
    :param destination_city: the city in which the plane arrives
    :return: a list with all those above
    """
    return [code, duration, departure_city, destination_city]


def get_code_flight(flight):
    """
    Returns the code of the flight
    :param flight: the flight of which code we want to find
    :return: the code of the fight
    """
    return flight[0]


def get_duration_flight(flight):
    return flight[1]


def get_departure_city(flight):
    return flight[2]


def get_destination_city(flight):
    return flight[3]


def set_duration(flight, new_duration):
    flight[1] = new_duration


def set_destination_city(flight, new_destination_city):
    flight[3] = new_destination_city


def add_flight(flights_list, flight_to_be_added):
    """
    Function which adds a flight to a list of flights, but first it validates the flight
    :param flights_list: the list of flights
    :param flight_to_be_added: the flight we try to add
    :return: the flight is added, or some error if the data is invalid
    """
    validate_flight(flight_to_be_added)
    flights_list.append(flight_to_be_added)


def modify_duration_of_flight(flights_list, flight_code, new_duration):
    for flight in flights_list:
        if get_code_flight(flight) == flight_code:
            set_duration(flight, new_duration)


def change_destination_for_rerouted_flights(flights_list, initial_destination_city, new_destination_city):
    for flight in flights_list:
        if get_destination_city(flight) == initial_destination_city:
            validate_destination_city(new_destination_city)
            set_destination_city(flight, new_destination_city)


def show_flights_with_given_departure_city(flights_list, departure_city):
    new_list = []
    for flight in flights_list:
        if get_departure_city(flight) == departure_city:
            new_list.append(flight)
    new_list.sort(key=get_duration_flight)
    return new_list
