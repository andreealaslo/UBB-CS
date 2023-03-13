from src.repositories.repo_in_memory import RepoMemory
import pickle


class RepoBinaryFile(RepoMemory):
    def __init__(self, file_path):
        self.__file_path = file_path
        super().__init__()
        self._write_data_to_file()
        self._read_data_from_file()

    def _read_data_from_file(self):
        with open(self.__file_path, 'rb') as f:
            students_list = pickle.load(f)
            f.close()
        for student in students_list:
            super().add_student_repo(student)

    def _write_data_to_file(self):
        with open(self.__file_path, 'wb') as f:
            pickle.dump(super().get_all_students_repo(), f)
            f.close()

    def add_student_repo(self, student_to_be_added):
        student = super().add_student_repo(student_to_be_added)
        self._write_data_to_file()
        return student

    def remove_student_repo(self, id_student):
        student = super().remove_student_repo(id_student)
        self._write_data_to_file()
        return student

    def modify_student_repo(self, new_student):
        student = super().modify_student_repo(new_student)
        self._write_data_to_file()
        return student

    def search_student_repo(self, id_student):
        return super().search_student_repo(id_student)

    def get_all_students_repo(self):
        return super().get_all_students_repo()

    def size(self):
        return super().size()

    def undo_repo(self):
        students = super().undo_repo()
        self._write_data_to_file()
        return students

