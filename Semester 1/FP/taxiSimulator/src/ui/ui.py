class UI:
    def __init__(self, taxi_service, ride_service):
        self.__taxi_service = taxi_service
        self.__ride_service = ride_service

    @staticmethod
    def print_menu():
        print("1. Add a ride.")
        print("2. Simulate a ride.")
        print("X. Exit.")

    def print_list_of_taxis(self, taxis):
        sorted = self.__taxi_service.get_taxis_sorted_decreasingly_by_fare(taxis)
        for taxi in sorted:
            if taxis is not None:
                print(taxi.to_string())

    def split_command(self, location):
        location = location.strip()  # (10,10) -> 10 10
        tokens = location.split(",")
        tokens[0] = tokens[0].replace('(', '')
        tokens[1] = tokens[1].replace(')', '')
        return int(tokens[0]), int(tokens[1])

    def add_a_ride_ui(self):
        start_location = input("Give the start location, in this format: (x,y): ")
        start_x, start_y = self.split_command(start_location)
        end_location = input("From there, you want to go to: ")
        end_x, end_y = self.split_command(end_location)
        ride = self.__ride_service.process_a_ride(start_x, start_y, end_x, end_y)
        taxi_assigned = self.__ride_service.assign_a_taxi_for_ride(ride)
        output = f"The ride from {ride.start} to {ride.end} is assigned to: taxi with id: {taxi_assigned.id}, having total fare: {taxi_assigned.total_fare}."
        print(output)

    def simulate_ride_ui(self):
        simulated_ride, closest_taxi = self.__ride_service.simulate_ride()
        output = f"The simulated ride from {simulated_ride.start} to {simulated_ride.end} is assigned to: taxi with id: {closest_taxi.id}, having total fare: {closest_taxi.total_fare}."
        print(output)

    def start(self):
        print("----Taxi simulator! Tell us where you want to go:D-----")
        number_of_taxis = int(input("Give the number of taxis you want to generate(between 0 and 10): "))
        self.__taxi_service.generate_multiple_random_taxis(number_of_taxis)
        self.print_list_of_taxis(self.__taxi_service.get_all_taxis_service())
        while True:
            self.print_menu()
            option = (input(">"))
            if option == "1":
                self.add_a_ride_ui()
                self.print_list_of_taxis(self.__taxi_service.get_all_taxis_service())
            elif option == "2":
                self.simulate_ride_ui()
                self.print_list_of_taxis(self.__taxi_service.get_all_taxis_service())
            elif option == "X":
                break
            else:
                print("Bad command! Try again!")
