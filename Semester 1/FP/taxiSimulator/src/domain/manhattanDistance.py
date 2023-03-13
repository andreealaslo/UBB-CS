

class ManhattanDistance:
    @staticmethod
    def calculate_distance_between_2_points(location1: tuple, location2: tuple):
        return abs(location2[0] - location1[0]) + abs(location2[1] - location1[1])
