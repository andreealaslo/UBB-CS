class RepoError(Exception):
    pass


class Repository:
    def __init__(self):
        self.__dictionary_of_taxis = {}

    def add_a_taxi(self, taxi):
        taxi_id = taxi.id
        if taxi_id in self.__dictionary_of_taxis:
            raise RepoError("Taxi already in the dictionary!")
        self.__dictionary_of_taxis[taxi_id] = taxi

    def search_taxi(self, taxi_id):
        if taxi_id not in self.__dictionary_of_taxis:
            raise RepoError("Taxi with corresponding id does not exists!")
        return self.__dictionary_of_taxis[taxi_id]

    def update_a_taxi(self, taxi_id, new_taxi):
        if taxi_id not in self.__dictionary_of_taxis:
            raise RepoError("Taxi with corresponding id does not exists!")
        self.__dictionary_of_taxis[taxi_id] = new_taxi

    def get_all_taxis(self):
        return [x for x in self.__dictionary_of_taxis.values()]

    def __len__(self):
        return len(self.__dictionary_of_taxis)
