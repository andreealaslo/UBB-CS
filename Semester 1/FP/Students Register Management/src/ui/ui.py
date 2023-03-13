from src.errors.repo_errors import RepoStudentError, RepoDisciplineError
from src.errors.validation_errors import ValidStudentError, ValidDisciplineError


class UI:
    def __init__(self, service):
        self.__service = service

    @staticmethod
    def print_menu():
        print("              \nStudents Register Management!!!")
        print("1. Add")
        print("2. Remove")
        print("3. Update")
        print("4. Display")
        print("5. Grade student at a given discipline")
        print("6. Search for disciplines/students based on ID or name/title")
        print("7. Statistics")
        print("8. Print all grades")
        print("x. Exit")

    @staticmethod
    def print_menu_add():
        print("1. Add a student")
        print("2. Add a discipline")

    @staticmethod
    def print_menu_remove():
        print("1. Remove a student(also remove all student's grades)")
        print("2. Remove a discipline(also remove all grades of that discipline for all students)")

    @staticmethod
    def print_menu_update():
        print("1. Update a student")
        print("2. Update a discipline")

    @staticmethod
    def print_menu_display():
        print("1. Display the students")
        print("2. Display the disciplines")

    @staticmethod
    def print_menu_search():
        print("1. Search student by ID")
        print("2. Search students by name")
        print("3. Search discipline by ID")
        print("4. Search discipline by name")

    @staticmethod
    def print_menu_statistics():
        print("1. All students failing at one or more disciplines.")
        print("2. Students with the best school situation, sorted in descending order of their aggregate average")
        print(
            "3. All disciplines at which there is at least one grade, sorted in descending order of the average grades received by all students.")

    def add_student_ui(self):
        id_student = int(input("Give the student's id: "))
        name_student = input("Give the student's name: ")
        self.__service.add_student_service(id_student, name_student)
        print("Student added successfully!")

    def add_discipline_ui(self):
        id_discipline = int(input("Give the discipline's id: "))
        name_discipline = input("Give the discipline's name: ")
        self.__service.add_discipline_service(id_discipline, name_discipline)
        print("Discipline added succesfully!")

    def display_students_ui(self):
        if self.__service.size_service_student() == 0:
            raise ValueError("No students!")
        students = self.__service.get_all_students_service()
        for student in students:
            print(student)

    def display_disciplines_ui(self):
        if self.__service.size_service_discipline() == 0:
            raise ValueError("No disciplines!")
        disciplines = self.__service.get_all_disciplines_service()
        for discipline in disciplines:
            print(discipline)

    def update_student_ui(self):
        id_student = int(input("Give the id of the student you want to update: "))
        new_name = input("Give the new name of the student: ")
        self.__service.update_student_service(id_student, new_name)
        print("Student updated successfully!")

    def update_discipline_ui(self):
        id_discipline = int(input("Give the id of the discipline you want to update: "))
        new_name_discipline = input("Give the new name of the discipline: ")
        self.__service.update_discipline_service(id_discipline, new_name_discipline)
        print("Discipline updated successfully!")

    def grade_student_ui(self):
        student_id = int(input("Give the id of the student you want to give a grade to: "))
        discipline_id = int(input("Give the id of the discipline at which you want to grade the student: "))
        grade_value = int(input("Give the grade: "))
        self.__service.add_grade_to_student_service(student_id, discipline_id, grade_value)
        print("Grade given with success!")

    def print_all_grades(self):
        for discipline in self.__service.get_all_disciplines_from_grades_service():
            print("--->At discipline with id:" + str(discipline) + " the students have the following grades: ")
            for key in self.__service.get_students_as_keys_service(discipline):
                print("\nStudent with id:" + str(key) + " have the grades: ")
                print(str(self.__service.get_all_grades_from_dict_of_disciplines(discipline, key)))

    def search_student_by_id_ui(self):
        id = int(input("Give the id of the student you want to search: "))
        student = self.__service.search_student_by_id_service(id)
        print(student)

    def search_discipline_by_id_ui(self):
        id = int(input("Give the id of the discipline you want to search: "))
        discipline = self.__service.search_discipline_by_id_service(id)
        print(discipline)

    def search_students_by_name_ui(self):
        name = input("Give the string for searching: ")
        students = self.__service.search_students_by_name_service(name)
        for student in students:
            print(student)

    def search_discipline_by_name_ui(self):
        name = input("Give the string for searching: ")
        disciplines = self.__service.search_disciplines_by_name_service(name)
        for discipline in disciplines:
            print(discipline)

    def remove_student_ui(self):
        student_id = int(input("Give the id of the student you want to remove: "))
        self.__service.remove_student_service(student_id)
        self.__service.remove_student_grades_service(student_id)
        print("Student deleted successfully!")

    def remove_discipline_ui(self):
        discipline_id = int(input("Give the id of the discipline ypu want to remove: "))
        self.__service.remove_discipline_service(discipline_id)
        self.__service.remove_discipline_from_grades_service(discipline_id)
        print("Discipline deleted successfully!")

    def statistics_1(self):
        dictionary = self.__service.students_failing_at_one_or_more_disciplines()
        for student in dictionary.values():
            print(str(student) + " is failing at one or more disciplines")

    def statistics_2(self):
        dictionary = self.__service.students_with_best_situation()
        for student in dictionary:
            print("Student with id: " + str(student[0]) + " has aggregate average: " + str(student[1]))

    def statistics_3(self):
        dictionary = self.__service.disciplines_with_at_least_one_grade()
        for discipline in dictionary:
            print("Discipline with id: " + str(discipline[0]) + " has aggregate average: " + str(discipline[1]))

    def start(self):
        while True:
            self.print_menu()
            cmd1 = input(">>>")
            try:
                if cmd1 == "1":
                    self.print_menu_add()
                    cmd2 = input(">>>")
                    if cmd2 == "1":
                        self.add_student_ui()
                    elif cmd2 == "2":
                        self.add_discipline_ui()
                elif cmd1 == "2":
                    self.print_menu_remove()
                    cmd3 = input(">>>")
                    if cmd3 == "1":
                        self.remove_student_ui()
                    elif cmd3 == "2":
                        self.remove_discipline_ui()
                elif cmd1 == "3":
                    self.print_menu_update()
                    cmd4 = input(">>>")
                    if cmd4 == "1":
                        self.update_student_ui()
                    elif cmd4 == "2":
                        self.update_discipline_ui()
                elif cmd1 == "4":
                    self.print_menu_display()
                    cmd5 = input(">>>")
                    if cmd5 == "1":
                        self.display_students_ui()
                    elif cmd5 == "2":
                        self.display_disciplines_ui()
                elif cmd1 == "5":
                    self.grade_student_ui()
                elif cmd1 == "6":
                    self.print_menu_search()
                    cmd6 = input(">>>")
                    if cmd6 == "1":
                        self.search_student_by_id_ui()
                    elif cmd6 == "2":
                        self.search_students_by_name_ui()
                    elif cmd6 == "3":
                        self.search_discipline_by_id_ui()
                    elif cmd6 == "4":
                        self.search_discipline_by_name_ui()
                elif cmd1 == "7":
                    self.print_menu_statistics()
                    cmd7 = input(">>>")
                    if cmd7 == "1":
                        self.statistics_1()
                    elif cmd7 == "2":
                        self.statistics_2()
                    elif cmd7 == "3":
                        self.statistics_3()
                elif cmd1 == "8":
                    self.print_all_grades()
                elif cmd1 == "x":
                    break
                else:
                    print("Bad command!")
            except ValueError as ve:
                print(str(ve))
            except RepoStudentError as rs:
                print(str(rs))
            except ValidStudentError as vs:
                print(str(vs))
            except RepoDisciplineError as rd:
                print(str(rd))
            except ValidDisciplineError as vd:
                print(str(vd))
