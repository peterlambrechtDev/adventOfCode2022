package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader


object Day10 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        val iterator = stringLines.iterator()

        // we need a loop like do while operations
        var instructions = mutableListOf<Instruct>()

        var register = 1
        var cycles = 0

        var signalStrengths = mutableMapOf<Int, Int>()

        do {
            cycles++
            if (instructions.isEmpty()) {
                val next = iterator.next()

                if (next == "noop") {
                    instructions.add(Instruct(true, 0))
                } else {
                    val amount = next.split(" ")[1].toInt()
                    instructions.add(Instruct(true, 0))
                    instructions.add(Instruct(false, amount))
                }
            }

            val currentInstruction = instructions.removeAt(0)

            signalStrengths.put(cycles, register * cycles)

            if (!currentInstruction.noop) {
                register = currentInstruction.addx(register)
            }


        } while (iterator.hasNext())

        return signalStrengths[20]!! + signalStrengths[60]!! + signalStrengths[100]!! + signalStrengths[140]!! + signalStrengths[180]!! + signalStrengths[220]!!
    }

    fun part2(filePath: String): String {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        val iterator = stringLines.iterator()

        // we need a loop like do while operations
        var instructions = mutableListOf<Instruct>()

        var register = 1
        var cycles = 0

        var output = "\n"
        var linePosition = 0

        do {
            cycles++
            if (instructions.isEmpty()) {
                val next = iterator.next()

                if (next == "noop") {
                    instructions.add(Instruct(true, 0))
                } else {
                    val amount = next.split(" ")[1].toInt()
                    instructions.add(Instruct(true, 0))
                    instructions.add(Instruct(false, amount))
                }
            }

            val currentInstruction = instructions.removeAt(0)

            if (linePosition == 40) {
                output += "\n"
                linePosition = 0
            }
            if (linePosition in register-1..register+1) {
                output += "#"
            } else {
                output += "."
            }

            if (!currentInstruction.noop) {
                println("register before: $register amount: ${currentInstruction.amount} cycle: $cycles" )
                register = currentInstruction.addx(register)
            }

            ++linePosition

        } while (iterator.hasNext())

        return output

    }
}

class Instruct(val noop: Boolean, val amount: Int) {

    fun addx(number: Int): Int {
        return number + amount
    }
}


fun main() {
    val day = "day10"
    val dayObj = Day10

    println("Example 1: ${dayObj.part1("/${day}_example.txt")}")
    println("Solution 1: ${dayObj.part1("/${day}.txt")}")
    println("Example 2: ${dayObj.part2("/${day}_example.txt")}")
    println("Solution 2: ${dayObj.part2("/${day}.txt")}")
}
