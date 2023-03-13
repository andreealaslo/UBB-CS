from src.errors.repo_errors import RepoDisciplineError


class RepoDiscipline:
    def __init__(self):
        self.__disciplines = {}

    def add_discipline_repo(self, discipline_to_be_added):
        id_discipline = discipline_to_be_added.discipline_id
        if id_discipline in self.__disciplines:
            raise RepoDisciplineError("Existent discipline!")
        self.__disciplines[id_discipline] = discipline_to_be_added

    def get_all_disciplines_repo(self):
        return [x for x in self.__disciplines.values()]

    def size_repo(self):
        return len(self.__disciplines)

    def search_discipline_by_id(self, discipline_id):
        if discipline_id not in self.__disciplines:
            raise RepoDisciplineError("Inexistent discipline!")
        return self.__disciplines[discipline_id]

    def remove_discipline_by_id(self, discipline_id):
        self.search_discipline_by_id(discipline_id)
        del self.__disciplines[discipline_id]
