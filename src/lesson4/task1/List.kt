@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson5.task1.propagateHandshakes
import lesson7.task1.deleteMarked
import java.lang.NullPointerException
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.sumOf { i -> i * i })


/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty())
        return 0.0
    return list.sum() / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val medium = list.sum() / list.size
    for (i in list.indices) {
        list[i] -= medium
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var s = 0.0
    var pow = 1
    for (i in p.indices) {
        s += p[i] * pow
        pow *= x
    }
    return s.toInt()
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n == 0)
        return listOf(0)
    val result = mutableListOf<Int>()
    var number = n
    while (number != 0) {
        result.add(number % base)
        number /= base
    }
    return result.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 0
    var baseF = 1
    str.reversed().forEach {
        val digit = if (it.isDigit()) it.digitToInt() else it - 'a' + 10
        result += digit * baseF
        baseF *= base
    }
    return result
}

fun main() {
    println(decimalFromString("a", 16))
}


/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val units: String = when (n % 10) {
        1 -> "I"
        2 -> "II"
        3 -> "III"
        4 -> "IV"
        5 -> "V"
        6 -> "VI"
        7 -> "VII"
        8 -> "VIII"
        9 -> "IX"
        else -> ""
    }

    val tens: String = when (n / 10 % 10) {
        1 -> "X"
        2 -> "XX"
        3 -> "XXX"
        4 -> "XL"
        5 -> "L"
        6 -> "LX"
        7 -> "LXX"
        8 -> "LXXX"
        9 -> "XC"
        else -> ""
    }

    val hund: String = when (n / 100 % 10) {
        1 -> "C"
        2 -> "CC"
        3 -> "CCC"
        4 -> "CD"
        5 -> "D"
        6 -> "DC"
        7 -> "DCC"
        8 -> "DCCC"
        9 -> "CM"
        else -> ""
    }

    val thous: String = when (n / 1000 % 10) {
        1 -> "M"
        2 -> "MM"
        3 -> "MMM"
        4 -> "MMMM"
        else -> ""

    }
    return thous + hund + tens + units

}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val number = StringBuilder().append(n)
    for (i in 1..6 - number.length)
        number.insert(0, 0)
    val result = mutableListOf<String>()
    var flagHunds = false
    var flagTens = false
    number.forEachIndexed { i, elem ->
        if (i == 1 && elem.digitToInt() == 1)
            flagHunds = true
        if (i == 4 && elem.digitToInt() == 1)
            flagTens = true
        result.add(
            when (i) {
                0, 3 -> when (elem.digitToInt()) {
                    1 -> "сто"
                    2 -> "двести"
                    3 -> "триста"
                    4 -> "четыреста"
                    5 -> "пятьсот"
                    6 -> "шестьсот"
                    7 -> "семьсот"
                    8 -> "восемьсот"
                    9 -> "девятьсот"
                    else -> ""
                }

                1, 4 -> when (elem.digitToInt()) {
                    2 -> "двадцать"
                    3 -> "тридцать"
                    4 -> "сорок"
                    5 -> "пятьдесят"
                    6 -> "шестьдесят"
                    7 -> "семьдесят"
                    8 -> "восемьдесят"
                    9 -> "девяносто"
                    else -> ""
                }

                2 -> if (!flagHunds) when (elem.digitToInt()) {
                    1 -> "одна тысяча"
                    2 -> "две тысячи"
                    3 -> "три тысячи"
                    4 -> "четыре тысячи"
                    5 -> "пять тысяч"
                    6 -> "шесть тысяч"
                    7 -> "семь тысяч"
                    8 -> "восемь тысяч"
                    9 -> "девять тысяч"
                    else -> if (n.toString().length > 4) "тысяч" else ""

                } else when (elem.digitToInt()) {
                    1 -> "одиннадцать"
                    2 -> "двенадцать"
                    3 -> "тринадцать"
                    4 -> "четырнадцать"
                    5 -> "пятнадцать"
                    6 -> "шестнадцать"
                    7 -> "семнадцать"
                    8 -> "восемнадцать"
                    9 -> "девятнадцать"
                    else -> "десять"
                } + " тысяч"

                5 -> if (!flagTens) when (elem.digitToInt()) {
                    1 -> "один"
                    2 -> "два"
                    3 -> "три"
                    4 -> "четыре"
                    5 -> "пять"
                    6 -> "шесть"
                    7 -> "семь"
                    8 -> "восемь"
                    9 -> "девять"
                    else -> ""
                } else when (elem.digitToInt()) {
                    1 -> "одиннадцать"
                    2 -> "двенадцать"
                    3 -> "тринадцать"
                    4 -> "четырнадцать"
                    5 -> "пятнадцать"
                    6 -> "шестнадцать"
                    7 -> "семнадцать"
                    8 -> "восемнадцать"
                    9 -> "девятнадцать"
                    else -> "десять"
                }

                else -> ""
            }
        )
    }
    result.removeAll { it == "" }
    return result.joinToString(" ")
}
