from src.domain.entity import Student
from src.repositories.repo_in_memory import RepoMemory


class RepoTextFile(RepoMemory):

    def __init__(self, file_path):
        self.__file_path = file_path
        super().__init__()
        self.clean_memory()
        self._write_data_to_file()
        self._read_data_from_file()

    def _read_data_from_file(self):
        self._students = self.clean_memory()
        with open(self.__file_path, "rt") as f:
            lines = f.readlines()
            f.close()
            for line in lines:
                if line != "":
                    line = line.strip()
                    parts = line.split(",")
                    id_student = int(parts[0].strip())
                    name = parts[1].strip()
                    grade = parts[2].strip()
                    student = Student(id_student, name, grade)
                    self._students[id_student] = student

    def _write_data_to_file(self):
        with open(self.__file_path, "wt") as f:
            for student in self.get_all_students_repo():
                f.write(str(student) + "\n")
            f.close()

    def add_student_repo(self, student_to_be_added):
        self._read_data_from_file()
        student = super().add_student_repo(student_to_be_added)
        self._write_data_to_file()
        return student

    def remove_student_repo(self, id_student):
        self._read_data_from_file()
        student = super().remove_student_repo(id_student)
        self._write_data_to_file()
        return student

    def modify_student_repo(self, new_student):
        self._read_data_from_file()
        student = super().modify_student_repo(new_student)
        self._write_data_to_file()
        return student

    def search_student_repo(self, id_student):
        self._read_data_from_file()
        student = super().search_student_repo(id_student)
        return student

    def get_all_students_repo(self):
        return super().get_all_students_repo()

    def size(self):
        return super().size()

    def undo_repo(self):
        students = super().undo_repo()
        self._write_data_to_file()
        return students
