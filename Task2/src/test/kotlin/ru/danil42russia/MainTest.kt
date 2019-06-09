package ru.danil42russia

import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

@Suppress("unused")
object MainTest : Spek({
    val p = EmployeeImpl()
    group("test addNewEmp") {
        test("addNewEmp test 1") {
            val user = p.addNewEmp(Employee("1", "Шашлов", "Эрнст", "Глебович", "Инженер"))
            assertEquals(true, user)
        }

        test("addNewEmp test 2") {
            val user = p.addNewEmp(Employee("2", "Шашлов", "Эрнст", "Глебович", "Программист"))
            assertEquals(false, user)
        }
    }

    group("test listEmpsBySurname") {
        beforeEachTest {
            //user2
            p.addNewEmp(Employee("2", "Шашлов", "Филипп", "Яковович", "Программист"))
            //user3
            p.addNewEmp(Employee("3", "Шашлов", "Осип", "Наумович", "Программист"))
            //user4
            p.addNewEmp(Employee("4", "Шашлов", "Моисей", "Алексеевич", "Программист"))
            //user5
            p.addNewEmp(Employee("5", "Шашлов", "Пимен", "Назарович", "Администратор"))
            //user6
            p.addNewEmp(Employee("6", "Шашлов", "Фока", "Валериевич", "Программист"))
            //user7
            p.addNewEmp(Employee("7", "Травкин", "Вадим", "Елизарович", "Программист"))
            //user8
            p.addNewEmp(Employee("8", "Шашлов", "Давид", "Фролович", "Инженер"))
            //user9
            p.addNewEmp(Employee("9", "Шашлов", "Евграф", "Адамович", "Программист"))
            //user10
            p.addNewEmp(Employee("10", "Шашлов", "Егор", "Адамович", "Программист"))
            //user11
            p.addNewEmp(Employee("11", "Ивашев", "Мирослав", "Куприянович", "Программист"))
            //user12
            p.addNewEmp(Employee("12", "Шашлов", "Архип", "Елисеевич", "Программист"))
            //user13
            p.addNewEmp(Employee("13", "Шашлов", "Рубен", "Казимирович", "Инженер"))
            //user14
            p.addNewEmp(Employee("14", "Шашлов", "Арсений", "Адамович", "Программист"))
            //user15
            p.addNewEmp(Employee("15", "Аспидов", "Елизар", "Денисович", "Инженер"))
            //user16
            p.addNewEmp(Employee("16", "Курзыбов", "Родион", "Никифорович", "Программист"))
            //user17
            p.addNewEmp(Employee("17", "Аспидов", "Пахом", "Ерофеевич", "Программист"))
            //user18
            p.addNewEmp(Employee("18", "Добронравов", "Лавр", "Иванович", "Программист"))
            //user19
            p.addNewEmp(Employee("19", "Ярославов", "Юлиан", "Кондратиевич", "Программист"))
            //user20
            p.addNewEmp(Employee("20", "Эссен", "Агап", "Фомевич", "Программист"))
            //user21
            p.addNewEmp(Employee("21", "Модзалевский", "Ростислав", "Сократович", "Программист"))
            //user22
            p.addNewEmp(Employee("22", "Другаков", "Марк", "Ермолаевич", "Инженер"))
            //user23
            p.addNewEmp(Employee("23", "Модзалевский", "Валерий", "Левович", "Программист"))
            //user24
            p.addNewEmp(Employee("24", "Месяц", "Богдан", "Елизарович", "Программист"))
            //user25
            p.addNewEmp(Employee("25", "Месяц", "Филимон", "Саввевич", "Программист"))
            //user26
            p.addNewEmp(Employee("26", "Седегов", "Виссарион", "Ипатиевич", "Программист"))
            //user27
            p.addNewEmp(Employee("27", "Добронравов", "Лука", "Кириллович", "Инженер"))
            //user28
            p.addNewEmp(Employee("28", "Аспидов", "Владислав", "Тарасович", "Программист"))
        }

        test("listEmpsBySurname test 1") {
            val list = p.listEmpsBySurname("Шашлов")

            assertEquals(10, list.size)
        }

        test("listEmpsBySurname test 2") {
            val temp = mutableListOf<Employee>()

            temp.add(Employee("1", "Шашлов", "Эрнст", "Глебович", "Инженер"))
            temp.add(Employee("2", "Шашлов", "Филипп", "Яковович", "Программист"))
            temp.add(Employee("3", "Шашлов", "Осип", "Наумович", "Программист"))
            temp.add(Employee("4", "Шашлов", "Моисей", "Алексеевич", "Программист"))
            temp.add(Employee("5", "Шашлов", "Пимен", "Назарович", "Администратор"))
            temp.add(Employee("6", "Шашлов", "Фока", "Валериевич", "Программист"))
            temp.add(Employee("8", "Шашлов", "Давид", "Фролович", "Инженер"))
            temp.add(Employee("9", "Шашлов", "Евграф", "Адамович", "Программист"))
            temp.add(Employee("10", "Шашлов", "Егор", "Адамович", "Программист"))
            temp.add(Employee("12", "Шашлов", "Архип", "Елисеевич", "Программист"))

            val list = p.listEmpsBySurname("Шашлов")

            assertEquals(temp, list)
        }
    }

    group("test listEmpsByPosition") {
        test("listEmpsByPosition test 1") {
            val list = p.listEmpsByPosition("Программист")

            assertEquals(10, list.size)
        }

        test("listEmpsByPosition test 2") {
            val temp = mutableListOf<Employee>()

            temp.add(Employee("28", "Аспидов", "Владислав", "Тарасович", "Программист"))
            temp.add(Employee("17", "Аспидов", "Пахом", "Ерофеевич", "Программист"))
            temp.add(Employee("18", "Добронравов", "Лавр", "Иванович", "Программист"))
            temp.add(Employee("11", "Ивашев", "Мирослав", "Куприянович", "Программист"))
            temp.add(Employee("16", "Курзыбов", "Родион", "Никифорович", "Программист"))
            temp.add(Employee("24", "Месяц", "Богдан", "Елизарович", "Программист"))
            temp.add(Employee("25", "Месяц", "Филимон", "Саввевич", "Программист"))
            temp.add(Employee("23", "Модзалевский", "Валерий", "Левович", "Программист"))
            temp.add(Employee("21", "Модзалевский", "Ростислав", "Сократович", "Программист"))
            temp.add(Employee("26", "Седегов", "Виссарион", "Ипатиевич", "Программист"))

            val list = p.listEmpsByPosition("Программист")

            assertEquals(temp, list)
        }
    }

    group("test deleteEmp") {
        test("deleteEmp test 1") {
            val list = p.listEmpsBySurname("Аспидов")

            assertEquals(3, list.size)
        }

        test("deleteEmp test 2") {
            p.deleteEmp("28")
            val list = p.listEmpsBySurname("Аспидов")

            assertEquals(2, list.size)
        }
    }
})