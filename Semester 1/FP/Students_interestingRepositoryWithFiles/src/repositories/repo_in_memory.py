from src.errors.repo_errors import RepoError
import copy


class RepoMemory:

    def __init__(self):
        self._students = {}
        self._history = []
        self._count = -10

    def add_student_repo(self, student_to_be_added):
        """
        Function which add the student in the dictionary of students
        :param student_to_be_added: object student passed from service
        :return: student is added in the dictionary, error is raised if the id is not unique
        """
        id_student = student_to_be_added.get_id_student()
        if id_student in self._students:
            raise RepoError("Existent student!")
        dict_copy = copy.deepcopy(self._students)
        self._count = self._count + 1
        self._history.append(dict_copy)
        self._students[id_student] = student_to_be_added

        return student_to_be_added

    def remove_student_repo(self, id_student):
        if id_student not in self._students:
            raise RepoError("Nonexistent student!")
        student = self.search_student_repo(id_student)
        del self._students[id_student]
        return student

    def filter_students_delete_given_group(self, group):
        dict_copy = copy.deepcopy(self._students)
        self._count = self._count + 1
        self._history.append(dict_copy)
        for student in self.get_all_students_repo():
            if student.get_group() == group:
                self.remove_student_repo(student.get_id_student())

    def modify_student_repo(self, new_student):
        id_student = new_student.get_id_student()
        if id_student not in self._students:
            raise RepoError("Nonexistent student!")
        self._students[id_student] = new_student
        return new_student

    def search_student_repo(self, id_student):
        if id_student not in self._students:
            raise RepoError("Nonexistent student!")
        return self._students[id_student]

    def get_all_students_repo(self):
        return [x for x in self._students.values()]

    def size(self):
        return len(self._students)

    def clean_memory(self):
        self._students = {}
        return self._students

    def undo_repo(self):
        if self._count == 0:
            raise RepoError("Cannot undo!")
        else:
            self._students = self.clean_memory()
            self._count = self._count - 1
            self._students = self._history.pop()

