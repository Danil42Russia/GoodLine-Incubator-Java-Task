### Задание 2

> Hey, not too rough

Необходимо реализовать интерфейс и покрыть код модульными тестами.
  
```
interface IEmployee {
    // Добавить сотрудника в список.
    // Если сотрудник с таким именем уже есть то вернуть false и не добавлять в коллекцию
    // иначе добавить и вернуть  true 
    fun addNewEmp(emp: Employee): Boolean
      
    // Вернуть список 10 сотрудников, с указанной фамилией.
    // Если есть несколько сотрудников с одинаковыми именем и фамилией, то
    // в списке к их ФИО добавить должность
    fun listEmpsBySurname(surname: String): List<Employee>
    
    // Вернуть список 10 сотрудников с должностью, отсортированный по ФИО
    fun listEmpsByPosition(position: String): List<Employee>
      
    // Удалить по ид
    fun deleteEmp(id: String)
}
```

## Инструкция

Для запуска тестов выполнить `gradlew test`

## Объяснение

Мне не было понятна функция `listEmpsBySurname`, почему она должна добавлять должности и откуда мы должны получить должность

```
// Вернуть список 10 сотрудников, с указанной фамилией.
// Если есть несколько сотрудников с одинаковыми именем и фамилией, то
// в списке к их ФИО добавить должность
fun listEmpsBySurname(surname: String): List<Employee>
```

Поэтому было принято поменять функционал функции на `Вернуть список 10 сотрудников, с указанной фамилией.`