from src.errors.repo_errors import *


class RepoStudent:
    def __init__(self):
        self.__students = {}

    def add_student_repo(self, student_to_be_added):
        id_student = student_to_be_added.student_id
        if id_student in self.__students:
            raise RepoStudentError("Existent student!")
        self.__students[id_student] = student_to_be_added

    def remove_student_by_id_repo(self, id_student):
        self.search_student_by_id(id_student)
        del self.__students[id_student]

    def search_student_by_id(self, student_id):
        if student_id not in self.__students:
            raise RepoStudentError("Inexistent student!")
        return self.__students[student_id]

    def get_all_students_repo(self):
        return [x for x in self.__students.values()]

    def size_repo(self):
        return len(self.__students)

    def get_students_as_keys_repo(self):
        return self.__students.keys()