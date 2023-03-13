from functions import *


def test_add():
    flights_list = []
    assert len(flights_list) == 0
    flight1 = create_flight("0A0001", 50, "Cluj-Napoca", "Bucharest")
    assert get_code_flight(flight1) == "0A0001"
    assert get_duration_flight(flight1) == 50
    assert get_departure_city(flight1) == "Cluj-Napoca"
    assert get_destination_city(flight1) == "Bucharest"
    add_flight(flights_list, flight1)
    assert len(flights_list) == 1

    try:
        flight2 = create_flight("0A", 50, "Bucharest", "Cluj-Napoca")
        add_flight(flights_list, flight2)
        assert False
    except ValueError as ve:
        assert str(ve) == "Invalid code of flight!"

    try:
        flight3 = create_flight("0A1111", 10, "Bucharest", "Cluj-Napoca")
        add_flight(flights_list, flight3)
        assert False
    except ValueError as ve:
        assert str(ve) == "Invalid duration!"

    try:
        flight4 = create_flight("0A1111", 50, "Bu", "Cluj-Napoca")
        add_flight(flights_list, flight4)
        assert False
    except ValueError as ve:
        assert str(ve) == "Invalid departure city!"

    try:
        flight5 = create_flight("0A1111", 50, "Bucharest", "Cn")
        add_flight(flights_list, flight5)
        assert False
    except ValueError as ve:
        assert str(ve) == "Invalid destination city!"
    assert len(flights_list) == 1
    print("test for add is done!!!")
