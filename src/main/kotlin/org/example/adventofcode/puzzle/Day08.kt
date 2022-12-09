package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader

object Day08 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        val forest = mutableListOf<List<Int>>()

        for (line in stringLines) {
            val treeLine: List<Int> = line.map { Integer.parseInt(it.toString()) }.toList()
            forest.add(treeLine)
        }

        var visibleTrees = mutableMapOf<String, Int>()

        val lineLength = forest[0].size

        visibleTrees.putAll(visible(forest, lineLength))

        return visibleTrees.size
    }

    private fun visible(forest: List<List<Int>>, lineLength: Int): Map<out String, Int> {
        val visibleMap = mutableMapOf<String, Int>()

        var treeLineIndex = 0
        for (treeLine in forest) {
            var treeColumnIndex = 0
            for (tree in treeLine) {
                if (treeLineIndex == 0 || treeLineIndex == forest.size - 1) {
                    visibleMap.put("$treeLineIndex=$treeColumnIndex", tree)
                } else if (treeColumnIndex == 0 || treeColumnIndex == treeLine.size - 1) {
                    visibleMap.put("$treeLineIndex=$treeColumnIndex", tree)
                } else {
                    var visibleFromRight = true
                    //look to the right
                    var rightPosition = treeColumnIndex + 1
                    var rightScore = 0
                    while (rightPosition <= lineLength - 1) {
                        if (treeLine[rightPosition] >= tree) {
                            visibleFromRight = false
                            break
                        }
                        ++rightScore
                        ++rightPosition
                    }
                    //look to the left
                    var visibleFromLeft = true
                    var leftPosition = treeColumnIndex - 1
                    var leftScore = 0
                    while (leftPosition >= 0) {
                        if (treeLine[leftPosition] >= tree) {
                            visibleFromLeft = false
                            break
                        }
                        ++leftScore
                        --leftPosition
                    }

                    //look from north
                    var visibleFromNorth = true
                    var northPosition = treeLineIndex - 1
                    var northScore = 0
                    while (northPosition >= 0) {
                        if (forest[northPosition][treeColumnIndex] >= tree) {
                            visibleFromNorth = false
                            break
                        }
                        ++northScore
                        --northPosition
                    }

                    //look from south
                    var visibleFromSouth = true
                    var southPosition = treeLineIndex + 1
                    var southScore = 0
                    while (southPosition <= forest.size - 1) {
                        if (forest[southPosition][treeColumnIndex] >= tree) {
                            visibleFromSouth = false
                            break
                        }
                        ++southScore
                        ++southPosition
                    }

                    if (visibleFromNorth || visibleFromLeft || visibleFromRight || visibleFromSouth) {
                        visibleMap.put(
                            "$treeLineIndex-$treeColumnIndex",
                            leftScore * rightScore * northScore * southScore
                        )
                    }

                }
                ++treeColumnIndex
            }
            ++treeLineIndex
        }
        return visibleMap
    }

    private fun treeSeeing(forest: List<List<Int>>, lineLength: Int): Map<out String, Int> {
        val visibleMap = mutableMapOf<String, Int>()

        var treeLineIndex = 0
        for (treeLine in forest) {
            var treeColumnIndex = 0
            for (tree in treeLine) {
                if (treeLineIndex == 0 || treeLineIndex == forest.size - 1) {
                } else if (treeColumnIndex == 0 || treeColumnIndex == treeLine.size - 1) {
                } else {
                    //look to the right
                    var rightPosition = treeColumnIndex + 1
                    var rightScore = 0
                    while (rightPosition <= lineLength - 1) {
                        ++rightScore
                        if (treeLine[rightPosition] >= tree) {
                            break
                        }
                        ++rightPosition
                    }
                    //look to the left
                    var leftPosition = treeColumnIndex - 1
                    var leftScore = 0
                    while (leftPosition >= 0) {
                        ++leftScore
                        if (treeLine[leftPosition] >= tree) {
                            break
                        }
                        --leftPosition
                    }

                    //look from north
                    var northPosition = treeLineIndex - 1
                    var northScore = 0
                    while (northPosition >= 0) {
                        ++northScore
                        if (forest[northPosition][treeColumnIndex] >= tree) {
                            break
                        }
                        --northPosition
                    }

                    //look from south
                    var southPosition = treeLineIndex + 1
                    var southScore = 0
                    while (southPosition <= forest.size - 1) {
                        ++southScore
                        if (forest[southPosition][treeColumnIndex] >= tree) {
                            break
                        }
                        ++southPosition
                    }

                        visibleMap.put(
                            "$treeLineIndex-$treeColumnIndex",
                            leftScore * rightScore * northScore * southScore
                        )

                }
                ++treeColumnIndex
            }
            ++treeLineIndex
        }
        return visibleMap
    }

    fun part2(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        val forest = mutableListOf<List<Int>>()

        for (line in stringLines) {
            val treeLine: List<Int> = line.map { Integer.parseInt(it.toString()) }.toList()
            forest.add(treeLine)
        }

        var visibleTrees = mutableMapOf<String, Int>()

        val lineLength = forest[0].size

        visibleTrees.putAll(treeSeeing(forest, lineLength))

        return visibleTrees.map { it.value }.maxOf { it }

    }


}

data class VisibleTree(
    val height: Int,
    val row: Int,
    val column: Int
)


fun main() {
    val day = "day08"
    val dayObj = Day08

    println("Example 1: ${dayObj.part1("/${day}_example.txt")}")
    println("Solution 1: ${dayObj.part1("/${day}.txt")}")
    println("Example 2: ${dayObj.part2("/${day}_example.txt")}")
    println("Solution 2: ${dayObj.part2("/${day}.txt")}")
}