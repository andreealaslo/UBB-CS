class Student:

    def __init__(self, id_student, name, group):
        self._id_student = id_student
        self._name = name
        self._group = group

    def get_id_student(self):
        return self._id_student

    def get_name_student(self):
        return self._name

    def get_group(self):
        return self._group

    def set_name_student(self, new_name):
        self._name = new_name

    def set_group(self, new_group):
        self._group = new_group

    def __str__(self):
        return f"{self._id_student},{self._name},{self._group}"

