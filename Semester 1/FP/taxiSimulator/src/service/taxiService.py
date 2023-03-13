import random
from src.domain.manhattanDistance import *
from src.domain.taxi import *
from random import randint


class TaxiService:
    def __init__(self, taxi_repository):
        self._taxi_repository = taxi_repository

    def verify_if_taxi_is_valid_to_be_added(self, taxi):
        for taxi_from_repo in self._taxi_repository.get_all_taxis():
            if ManhattanDistance.calculate_distance_between_2_points((taxi.get_x_coordinate, taxi.get_y_coordinate), (
                    taxi_from_repo.get_x_coordinate, taxi_from_repo.get_y_coordinate)) <= 5:
                return False
        return True

    def generate_one_random_taxi(self):
        while True:
            random_taxi = Taxi(len(self._taxi_repository) + 1, random.randint(0, 100), random.randint(0, 100))
            if self.verify_if_taxi_is_valid_to_be_added(random_taxi):
                self._taxi_repository.add_a_taxi(random_taxi)
                break

    def generate_multiple_random_taxis(self, number_of_taxis):
        for i in range(0, number_of_taxis):
            self.generate_one_random_taxi()

    def get_all_taxis_service(self):
        return self._taxi_repository.get_all_taxis()

    def get_taxis_sorted_decreasingly_by_fare(self, taxis):
        sorted_list = sorted(taxis, key=lambda taxi: taxi.total_fare, reverse=True)
        return sorted_list
