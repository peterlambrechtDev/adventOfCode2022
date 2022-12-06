package org.example.adventofcode.puzzle2021

import org.example.adventofcode.util.FileLoader
import kotlin.math.max

object Day032021 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        var gamma = ""
        var epsilon = ""

        var bitmap = createBitMap(stringLines)

        bitmap.entries.forEach { gamma = gamma.plus(findCommonBit(it.value)) }
        bitmap.entries.forEach { epsilon = epsilon.plus(findLeastCommonBit(it.value)) }

        println("gamma: $gamma epsilon: $epsilon")

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    private fun createBitMap(stringLines: List<String>): MutableMap<Int, MutableList<String>> {
        var bitmap = mutableMapOf<Int, MutableList<String>>()

        for (line in stringLines) {
            if (line.isNotEmpty()) {
                var i = 0
                for (char in line.asIterable()) {
                    val entry = bitmap[i]
                    if (entry == null) {
                        bitmap[i] = mutableListOf()
                    }
                    entry?.add(char.toString())
                    i++
                }
            }
        }
        return bitmap
    }

    fun part2(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        var oxygen = ""
        var co2 = ""

        var bitmap = createBitMap(stringLines)
        var freqMap = mutableMapOf<Int, Pair<Int, Int>>()

        var remainingLines = stringLines


        bitmap.entries.forEach { freqMap.put(it.key, findBitFreq(it.value)) }

        var i = 0
        while (remainingLines.size > 1 && i < freqMap.size) {
            var lines = remainingLines.filter { it.isNotEmpty() }.filter {
                it[i].toInt() == getDigit(freqMap, i)
            }.toMutableList()
            remainingLines = lines
            i++
        }

        println(remainingLines)


        return 0
    }

    private fun getDigit(
        freqMap: MutableMap<Int, Pair<Int, Int>>,
        i: Int
    ): Int {
        if (freqMap[i]?.first!! > freqMap[i]?.second!!) {
            return 0
        } else if (freqMap[i]?.first!! < freqMap[i]?.second!!) {
            return 1
        } else return 1
    }

    fun findBitFreq(bits: List<String>): Pair<Int, Int> {
        var zero = 0
        var one = 0

        for (bit in bits) {
            if (bit == "0") {
                zero++
            } else {
                one++
            }
        }

        return Pair(zero, one)
    }

    fun findCommonBit(bits: List<String>): String {
        var zero = 0
        var one = 0

        for (bit in bits) {
            if (bit == "0") {
                zero++
            } else {
                one++
            }
        }

        return if (zero > one) {
            "0"
        } else {
            "1"
        }
    }

    fun findLeastCommonBit(bits: List<String>): String {
        var zero = 0
        var one = 0

        for (bit in bits) {
            if (bit == "0") {
                zero++
            } else {
                one++
            }
        }
        return if (zero < one) {
            "0"
        } else {
            "1"
        }
    }

}

fun main() {
    var day = "day032021"

    println("Example 1: ${Day032021.part1("/${day}_example.txt")}")
    println("Solution 1: ${Day032021.part1("/${day}.txt")}")
    println("Example 2: ${Day032021.part2("/${day}_example.txt")}")
    println("Solution 2: ${Day032021.part2("/${day}.txt")}")
}
