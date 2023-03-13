class Discipline:
    def __init__(self, discipline_id, discipline_name):
        self.__discipline_id = discipline_id
        self.__discipline_name = discipline_name

    @property
    def discipline_id(self):
        return self.__discipline_id

    @property
    def discipline_name(self):
        return self.__discipline_name

    def set_discipline_id(self, new_id):
        self.__discipline_id = new_id

    def set_discipline_name(self, new_name):
        self.__discipline_name = new_name

    def __str__(self):
        return f"Discipline with id: {self.__discipline_id} and name: {self.__discipline_name}"

