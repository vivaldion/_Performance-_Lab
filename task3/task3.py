"""Задание 3
На вход в качестве аргументов программы поступают три пути к файлу (в приложении
к заданию находятся примеры этих файлов):
● values.json содержит результаты прохождения тестов с уникальными id
● tests.json содержит структуру для построения отчета на основе прошедших
тестов (вложенность может быть большей, чем в примере)
● report.json - сюда записывается результат.
Напишите программу, которая формирует файл report.json с заполненными полями
value для структуры tests.json на основании values.json.
Структура report.json такая же, как у tests.json, только заполнены поля “value”."""

import json


values_path, tests_path, report_path = input(), input(), input()

def reader(file : str) -> dict:
    with open(file, "r") as file:
        data = json.load(file)
    return data

def for_check(values:dict):
    new ={}
    for i in values.values():
        for j in i:
            new.setdefault(j["id"], j["value"])
    return new



def filler(dict_for_check: dict, elem: dict|list):
    if isinstance(elem, dict):
        if elem['id'] in dict_for_check:
            elem['value'] = dict_for_check[elem['id']]
        if 'values' in elem:
            for i in elem['values']:
                filler(values, i)


# values_path, tests_path, report_path = 'values.json', 'tests.json', 'report.json'
values = reader(values_path)
tests = reader(tests_path)
dict_for_check = for_check(values)

for i in tests['tests']:
    filler(dict_for_check, i)

print(tests)
with open(report_path, 'w') as f:
    json.dump(tests, f, indent=2)





