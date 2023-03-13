from src.errors.repo_errors import RepoError
from src.errors.validation_errors import ValidError


class UI:

    def __init__(self, service):
        self.__service = service

    @staticmethod
    def print_menu():
        print("\n1. Add a student.")
        print("2. Display the students.")
        print("3. Filter the list so that students in a given group are deleted from the list.")
        print("4. Undo.")
        print("x. Exit\n")

    def add_student_ui(self):
        id_student = int(input("Give the id of the student: "))
        name = input("Give the name of the student: ")
        group = int(input("Give the group: "))
        self.__service.add_student_service(id_student, name, group)
        print("Student added with success!")

    def filter_students_ui(self):
        initial_size = self.__service.size_service()
        group = int(input("Give the group from which you want to delete students: "))
        self.__service.filter_students_delete_given_group(group)
        if self.__service.size_service() == initial_size:
            raise ValueError("No such group!")
        print("Students from group " + str(group) + " were deleted!")

    def print_students_ui(self):
        if self.__service.size_service == 0:
            raise ValueError("No students!")
        students = self.__service.get_all_students_service()
        if students is not None:
            for student in students:
                print(student)

    def undo_ui(self):
        self.__service.undo_service()

    def run(self):
        while True:
            self.print_menu()
            cmd = input(">>")
            try:
                if cmd == "1":
                    self.add_student_ui()
                elif cmd == "2":
                    self.print_students_ui()
                elif cmd == "3":
                    self.filter_students_ui()
                elif cmd == "4":
                    self.undo_ui()
                elif cmd == "x":
                    break
                else:
                    print("Bad command!")
            except ValueError as v:
                print(f"UiError: {v}")
            except ValidError as ve:
                print(f"ValidError: {ve}")
            except RepoError as re:
                print(f"RepoError: {re}")
