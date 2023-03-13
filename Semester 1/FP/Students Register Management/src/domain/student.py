class Student:
    def __init__(self, student_id, student_name):
        self.__student_id = student_id
        self.__student_name = student_name

    @property
    def student_id(self):
        return self.__student_id

    @property
    def student_name(self):
        return self.__student_name

    def set_student_id(self, new_id):
        self.__student_id = new_id

    def set_student_name(self, new_name):
        self.__student_name = new_name

    def __str__(self):
        return f"Student with id: {self.__student_id} and name: {self.__student_name}"