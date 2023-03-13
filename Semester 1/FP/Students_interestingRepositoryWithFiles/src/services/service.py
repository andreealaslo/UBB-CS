from src.domain.entity import Student


class Service:

    def __init__(self, validation_student, repo):
        self.__validation_student = validation_student
        self.__repo = repo

    def add_student_service(self, id, name, group):
        """
        Function to add a student in service, after receiving from UI the required parameters
        :param id: id of the student, positive integer, unique
        :param name: name of the student, string, nonempty
        :param group: positive integer
        :return: the student is added to the repository if it passes the validation
        """
        student = Student(id, name, group)
        self.__validation_student.validate(student)
        self.__repo.add_student_repo(student)

    def filter_students_delete_given_group(self, group):
        self.__repo.filter_students_delete_given_group(group)

    def get_all_students_service(self):
        return self.__repo.get_all_students_repo()

    def size_service(self):
        return self.__repo.size()

    def generate_students(self):
        self.add_student_service(1, "Maria", 921)
        self.add_student_service(2, "Ana", 921)
        self.add_student_service(3, "Larisa", 922)
        self.add_student_service(4, "Andrei", 923)
        self.add_student_service(5, "Mara", 923)
        self.add_student_service(6, "Alex", 921)
        self.add_student_service(7, "Diana", 927)
        self.add_student_service(8, "Irina", 921)
        self.add_student_service(9, "Stefana", 923)
        self.add_student_service(10, "Andreea", 924)

    def undo_service(self):
        return self.__repo.undo_repo()
