from src.domain.discipline import Discipline
from src.domain.grade import Grade
from src.domain.student import Student


class Service:
    def __init__(self, repo_students, repo_disciplines, repo_grades, validation_students, validation_disciplines,
                 validation_grades):
        self.__repo_students = repo_students
        self.__repo_disciplines = repo_disciplines
        self.__repo_grades = repo_grades
        self.__validation_students = validation_students
        self.__validation_disciplines = validation_disciplines
        self.__validation_grades = validation_grades

    def add_student_service(self, id_student, name_student):
        student_to_be_added = Student(id_student, name_student)
        self.__validation_students.validate_student(student_to_be_added)
        self.__repo_students.add_student_repo(student_to_be_added)
        return student_to_be_added

    def add_discipline_service(self, id_discipline, name_discipline):
        discipline = Discipline(id_discipline, name_discipline)
        self.__validation_disciplines.validate_discipline(discipline)
        self.__repo_disciplines.add_discipline_repo(discipline)
        return discipline

    def add_grade_to_student_service(self, student_id, discipline_id, grade_value):
        grade = Grade(discipline_id, student_id, grade_value)
        self.__validation_grades.validate_grade(grade)
        self.__repo_grades.add_grade_to_student_repo(grade)

    def update_student_service(self, id_student, new_name):
        student = self.__repo_students.search_student_by_id(id_student)
        student.set_student_name(new_name)

    def update_discipline_service(self, id_discipline, new_name_discipline):
        discipline = self.__repo_disciplines.search_discipline_by_id(id_discipline)
        discipline.set_discipline_name(new_name_discipline)

    def get_all_students_service(self):
        return self.__repo_students.get_all_students_repo()

    def get_all_disciplines_service(self):
        return self.__repo_disciplines.get_all_disciplines_repo()

    def get_all_disciplines_from_grades_service(self):
        return self.__repo_grades.get_all_disciplines_repo()

    def get_students_as_keys_service(self, discipline_id):
        return self.__repo_grades.get_students_as_keys_repo(discipline_id)

    def get_all_grades_from_dict_of_disciplines(self, discipline_id, student_id):
        return self.__repo_grades.get_all_grades_per_discipline_repo(discipline_id, student_id)

    def size_service_student(self):
        return self.__repo_students.size_repo()

    def size_service_discipline(self):
        return self.__repo_disciplines.size_repo()

    def size_service_grade(self):
        return self.__repo_grades.size_grade_repo()

    def search_student_by_id_service(self, student_id):
        return self.__repo_students.search_student_by_id(student_id)

    def search_discipline_by_id_service(self, discipline_id):
        return self.__repo_disciplines.search_discipline_by_id(discipline_id)

    def search_students_by_name_service(self, name):
        dictionary = {}
        name = name.casefold()
        for student in self.get_all_students_service():
            name_student = student.student_name.casefold()
            count = name_student.count(name)
            if count > 0:
                dictionary[student.student_id] = student
        if not dictionary:
            raise ValueError("No matching!")
        return dictionary.values()

    def search_disciplines_by_name_service(self, name):
        dictionary = {}
        name = name.casefold()
        for discipline in self.get_all_disciplines_service():
            name_discipline = discipline.discipline_name.casefold()
            count = name_discipline.count(name)
            if count > 0:
                dictionary[discipline.discipline_id] = discipline
        if not dictionary:
            raise ValueError("No matching!")
        return dictionary.values()

    def remove_student_grades_service(self, student_id):
        return self.__repo_grades.remove_student_grade_repo(student_id)

    def remove_student_service(self, student_id):
        return self.__repo_students.remove_student_by_id_repo(student_id)

    def remove_discipline_service(self, discipline_id):
        return self.__repo_disciplines.remove_discipline_by_id(discipline_id)

    def remove_discipline_from_grades_service(self, discipline_id):
        return self.__repo_grades.remove_discipline_from_grades(discipline_id)

    def get_students_as_keys_from_repo_s_service(self):
        return self.__repo_students.get_students_as_keys_repo()

    def students_failing_at_one_or_more_disciplines(self):
        new_dict_of_students = {}
        for discipline in self.get_all_disciplines_from_grades_service():
            for student in self.get_students_as_keys_service(discipline):
                ok = 0
                sum = 0
                for grade in self.get_all_grades_from_dict_of_disciplines(discipline, student):
                    sum += grade
                    length = len(self.get_all_grades_from_dict_of_disciplines(discipline, student))
                    average = sum // length
                    if average < 5:
                        ok = 1
                if ok == 1:
                    student_w_all = self.search_student_by_id_service(student)
                    if student not in new_dict_of_students.keys():
                        new_dict_of_students[student] = student_w_all
        return new_dict_of_students

    def students_with_best_situation(self):
        new_dictionary = {}
        for student in self.get_students_as_keys_from_repo_s_service():
            counter_discipline = 0
            sum_aggregate = 0
            for discipline in self.get_all_disciplines_from_grades_service():
                sum = 0
                if student in self.get_students_as_keys_service(discipline):
                    counter_discipline = counter_discipline + 1
                    for grade in self.get_all_grades_from_dict_of_disciplines(discipline, student):
                        sum += grade
                    length = len(self.get_all_grades_from_dict_of_disciplines(discipline, student))
                    average = sum / length
                    sum_aggregate += average
            if counter_discipline != 0:
                average = sum_aggregate / counter_discipline
                new_dictionary[student] = average
        sort_dict = sorted(new_dictionary.items(), key=lambda x: x[1], reverse=True)
        return sort_dict

    def disciplines_with_at_least_one_grade(self):
        new_dictionary = {}
        for discipline in self.get_all_disciplines_from_grades_service():
            sum = 0
            counter = 0
            for student in self.get_students_as_keys_service(discipline):
                if len(self.get_all_grades_from_dict_of_disciplines(discipline, student)) >= 1:
                    for grade in self.get_all_grades_from_dict_of_disciplines(discipline, student):
                        sum += grade
                    counter += len(self.get_all_grades_from_dict_of_disciplines(discipline, student))
            if counter != 0:
                average = sum / counter
                new_dictionary[discipline] = average
        sort_dict = sorted(new_dictionary.items(), key=lambda x: x[1], reverse=True)
        return sort_dict

    def generate_students(self):
        self.add_student_service(1, "Andreea")
        self.add_student_service(2, "Oana")
        self.add_student_service(3, "Stefana")
        self.add_student_service(4, "Ionut")
        self.add_student_service(5, "Irina")
        self.add_student_service(6, "Andrei")
        self.add_student_service(7, "Mara")
        self.add_student_service(8, "Maria")
        self.add_student_service(9, "Matei")
        self.add_student_service(10, "Dan")

    def generate_disciplines(self):
        self.add_discipline_service(1, "Fundamentals of programming")
        self.add_discipline_service(2, "Object oriented programming")
        self.add_discipline_service(3, "Operating systems")
        self.add_discipline_service(4, "Advanced methods of programming")
        self.add_discipline_service(5, "Computer networks")
        self.add_discipline_service(6, "Databases")
        self.add_discipline_service(7, "Algebra")
        self.add_discipline_service(8, "Analysis")
        self.add_discipline_service(9, "Logics")
        self.add_discipline_service(10, "Probability and statistics")

    def generate_grades(self):
        self.add_grade_to_student_service(1, 1, 10)
        self.add_grade_to_student_service(1, 2, 10)
        self.add_grade_to_student_service(2, 1, 9)
        self.add_grade_to_student_service(2, 4, 5)
        self.add_grade_to_student_service(2, 1, 10)
        self.add_grade_to_student_service(3, 1, 7)
        self.add_grade_to_student_service(3, 3, 10)
        self.add_grade_to_student_service(4, 1, 2)
        self.add_grade_to_student_service(5, 2, 10)
        self.add_grade_to_student_service(4, 3, 10)
