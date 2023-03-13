def validate_flight(flight):
    """
    Function to validate a flight
    In case of the code, the departure city and the destination one, their length should be greater than 3 characters
    In case of duration, it should be greater than 20 minutes
    :param flight: flight to be validated
    :return: error messages, in case one of the above condition is not satisfied.
    """
    errors = ""
    if len(flight[0]) < 3:
        errors += "Invalid code of flight!"
    if len(flight[2]) < 3:
        errors += "Invalid departure city!"
    if len(flight[3]) < 3:
        errors += "Invalid destination city!"
    if flight[1] < 20:
        errors += "Invalid duration!"
    if len(errors) > 0:
        raise ValueError(errors)


def validate_destination_city(destination_city):
    errors = ""
    if len(destination_city) < 3:
        errors += "Invalid rerouted destination city!"
    if len(errors) > 0:
        raise ValueError(errors)