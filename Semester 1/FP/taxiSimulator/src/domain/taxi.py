
class Taxi:
    def __init__(self, id, x, y):
        self.__id = id
        self.__current_location = (x, y)
        self.__total_fare = 0

    @property
    def id(self):
        return self.__id

    @property
    def total_fare(self):
        return self.__total_fare

    def set_total_fare(self, new_total_fair):
        self.__total_fare = new_total_fair

    @property
    def current_location(self):
        return self.__current_location

    @property
    def get_x_coordinate(self):
        return self.__current_location[0]

    @property
    def get_y_coordinate(self):
        return self.__current_location[1]

    def set_current_location(self, new_location):
        self.__current_location = new_location

    def to_string(self):
        return f"Taxi with id: {self.id} is at location: {self.current_location} and has the total fair: {self.total_fare}"

#
# taxi = Taxi(1, 1, 1)
# print(taxi.to_string())



