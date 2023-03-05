# language: ru
@TEST
Функционал: Jira
  Сценарий: Проверка задачи
    Когда находим задачу "TestSelenium"
    Тогда выполняем поиск задачи
    И проверяем информацию в задаче со статусом "В РАБОТЕ" и версией "Version 2.0"

  Сценарий: Создаем баг
    Когда создаем задачу с summary "Баг", description "Возникла ошибка работы системы", environment "Windows10", priority "Highest", issueType "Ошибка"
    Тогда выполняем поиск задачи
    И закрываем задачу