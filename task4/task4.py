"""Дан массив целых чисел nums.
Напишите программу, выводящую минимальное количество ходов, требуемых для
приведения всех элементов к одному числу.
За один ход можно уменьшить или увеличить число массива на 1.

Пример:
nums = [1, 2, 3]
Решение: [1, 2, 3] => [2, 2, 3] => [2, 2, 2].
Минимальное количество ходов: 2."""
import sys

path = sys.argv[1]

with open(path, "r") as f1:
    nums = list(map(int, f1.readlines()))
    nums.sort()
    med = nums[len(nums) // 2]
    actions = sum(abs(v - med) for v in nums)
    print(actions)