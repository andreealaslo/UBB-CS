class Ride:
    def __init__(self, start_location:tuple, end_location:tuple):
        self.__start = start_location
        self.__end = end_location

    @property
    def start(self):
        return self.__start

    @property
    def end(self):
        return self.__end

    def set_start_location(self, new_location: tuple):
        self.__start = new_location

    def set_end_location(self, new_location : tuple):
        self.__end = new_location


