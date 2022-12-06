package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader

object Day05 {

    fun parseCrates(stringLines: List<String>): MutableMap<Int, MutableList<String>> {
        var index = 0
        val cratesMap = mutableMapOf<Int, MutableList<String>>()
        while (!stringLines[index].contains("1")) {
            val currentLine = stringLines[index]

            val crates = currentLine.chunked(4)

            var stackIndex = 0
            for (crate in crates) {
                if (cratesMap[stackIndex] == null) {
                    cratesMap[stackIndex] = mutableListOf()
                }
                val cratesList = cratesMap[stackIndex]!!.toMutableList()
                if (crate.contains("[")) {
                    val element = crate.subSequence(1, 2).toString()
                    cratesList.add(element)
                    cratesMap[stackIndex] = cratesList
                }
                stackIndex++
            }
            index++
        }
        return cratesMap
    }

    fun parseInstructions(stringLines: List<String>): List<Instruction> {
        return stringLines.filter { it.startsWith("move") }
            .map { line ->
            val pieces = line.split(" ")
            Instruction(move = pieces[1].toInt(), from = pieces[3].toInt(), to = pieces[5].toInt())

        }.toList()
    }

    fun part1(filePath: String): String {
        val crates = parseCrates(FileLoader.loadFromFile<String>(filePath))

        for (stack in crates) {
            stack.setValue(stack.value.reversed().toMutableList())
        }
        println(crates.toString())

        val instructions = parseInstructions(FileLoader.loadFromFile<String>(filePath))

        for (instruction in instructions) {
            for (i in 1..instruction.move) {
                val from = instruction.from - 1
                val to = instruction.to - 1
                val cratesInFromStack = crates[from]!!

                val lastIndex = cratesInFromStack.lastIndex
                val crate = cratesInFromStack.removeAt(lastIndex)
                crates[to]!!.add(crate)
            }
        }

        println(crates.toString())

        var string =""
        for (stack in crates) {
            string += stack.value.last()
        }
        return string
    }

    fun part2(filePath: String): String {
        val crates = parseCrates(FileLoader.loadFromFile<String>(filePath))

        for (stack in crates) {
            stack.setValue(stack.value.reversed().toMutableList())
        }
        println(crates.toString())

        val instructions = parseInstructions(FileLoader.loadFromFile<String>(filePath))

        for (instruction in instructions) {
            var cratesToMove = mutableListOf<String>()
            val from = instruction.from - 1
            val to = instruction.to - 1
            for (i in 1..instruction.move) {
                cratesToMove.add(crates[from]!!.last())
                crates[from]!!.removeLast()
            }
            cratesToMove.reversed().forEach { crates[to]!!.add(it)}

        }

        println(crates.toString())

        var string =""
        for (stack in crates) {
            string += stack.value.last()
        }
        return string
    }
}

data class Instruction(
    val move: Int,
    val from: Int,
    val to: Int
)


fun main() {
    val day = "day05"
    val dayObj = Day05

    println("Example 1: ${dayObj.part1("/${day}_example.txt")}")
    println("Solution 1: ${dayObj.part1("/${day}.txt")}")
    println("Example 2: ${dayObj.part2("/${day}_example.txt")}")
    println("Solution 2: ${dayObj.part2("/${day}.txt")}")
}