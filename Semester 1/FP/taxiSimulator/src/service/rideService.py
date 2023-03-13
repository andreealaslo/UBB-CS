import random

from src.domain.ride import Ride
from src.domain.manhattanDistance import *


class RideService:
    def __init__(self, taxi_repository):
        self._taxi_repository = taxi_repository

    def find_the_closest_taxi_to_point(self, point: tuple):
        closest_taxi = None
        for taxi_from_repo in self._taxi_repository.get_all_taxis():
            if closest_taxi is None or ManhattanDistance.calculate_distance_between_2_points(
                    (closest_taxi.get_x_coordinate, closest_taxi.get_y_coordinate),
                    point) > ManhattanDistance.calculate_distance_between_2_points((taxi_from_repo.get_x_coordinate,
                                                                                    taxi_from_repo.get_y_coordinate),
                                                                                   point):
                closest_taxi = taxi_from_repo
        return closest_taxi

    def process_a_ride(self, start_x, start_y, end_x, end_y):
        start_location = (start_x, start_y)
        end_location = (end_x, end_y)
        ride = Ride(start_location, end_location)
        self.assign_a_taxi_for_ride(ride)
        return ride

    def assign_a_taxi_for_ride(self, ride):
        closest_taxi = self.find_the_closest_taxi_to_point(ride.start)
        fare_to_add = ManhattanDistance.calculate_distance_between_2_points(ride.start, ride.end)
        total_fare = closest_taxi.total_fare + fare_to_add
        closest_taxi.set_total_fare(total_fare)
        closest_taxi.x = ride.end[0]
        closest_taxi.y = ride.end[1]
        self._taxi_repository.update_a_taxi(closest_taxi.id, closest_taxi)
        return closest_taxi

    def simulate_ride(self):
        start_location = (random.randint(0, 100), random.randint(0, 100))
        end_location = (random.randint(0, 100), random.randint(0, 100))
        while ManhattanDistance.calculate_distance_between_2_points(start_location, end_location) < 10:
            end_location = (random.randint(0, 100), random.randint(0, 100))
        simulated_ride = Ride(start_location, end_location)
        taxi_updated = self.assign_a_taxi_for_ride(simulated_ride)
        return simulated_ride, taxi_updated
