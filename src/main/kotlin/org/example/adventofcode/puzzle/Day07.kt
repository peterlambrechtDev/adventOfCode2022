package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader


object Day07 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)
        var tree: FileNode? = buildTree(stringLines)

        populateDirectorySizes(tree!!)

        val list = getListOfDirectories(tree)

        return list.filter { it.size < 100000 }
            .map { it.size }
            .sum()
    }

    fun part2(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        var tree: FileNode? = buildTree(stringLines)

        populateDirectorySizes(tree!!)

        val list = getListOfDirectories(tree)

        val freeSpace = 70000000 - tree.size

        val amountToDelete = 30000000 - freeSpace

        return list.filter { it.size > amountToDelete }
            .sortedBy { it.size }
            .first().size
    }

    private fun buildTree(stringLines: List<String>): FileNode? {
        var tree: FileNode? = null
        var currentNode: FileNode? = null

        for (line in stringLines) {
            if (line.startsWith("$ cd")) {
                if (line.contains("..")) {
                    currentNode = currentNode?.parent
                } else {
                    val name = line.split(" ")[2]
                    if (tree == null) {
                        tree = FileNode(Type.FOLDER, name, 0, null, mutableListOf())
                        currentNode = tree
                    } else {
                        val newFileNode = FileNode(Type.FOLDER, name, 0, currentNode, mutableListOf())
                        currentNode?.children?.add(newFileNode)
                        currentNode = newFileNode
                    }
                }
            } else {
                if (line[0].isDigit()) {
                    val fileParts = line.split(" ")
                    currentNode?.children?.add(
                        FileNode(
                            Type.FILE,
                            fileParts[1],
                            fileParts[0].toInt(),
                            currentNode,
                            null
                        )
                    )
                }
            }
        }
        return tree
    }

    fun getListOfDirectories(node: FileNode): MutableList<FileNode> {
        val list = mutableListOf<FileNode>()
        if (node.type == Type.FOLDER) {
            list.add(node)
            for (child in node.children!!) {
                list.addAll(getListOfDirectories(child))
            }
        }

        return list
    }

    fun populateDirectorySizes(node: FileNode): Int {
        if (node.size == 0) {
            for (child in node.children!!) {
                node.size += populateDirectorySizes(child)
            }
        } else {
            return node.size
        }

        return node.size
    }
}

data class FileNode(
    val type: Type,
    val name: String,
    var size: Int,
    var parent: FileNode?,
    var children: MutableList<FileNode>?
) {
    override fun toString(): String {
        return "{$type, $name, $size, children: $children}"
    }
}

enum class Type {
    FILE,
    FOLDER
}

fun main() {
    val day = "day07"
    val dayObj = Day07

    println("Example 1: ${dayObj.part1("/${day}_example.txt")}")
    println("Solution 1: ${dayObj.part1("/${day}.txt")}")
    println("Example 2: ${dayObj.part2("/${day}_example.txt")}")
    println("Solution 2: ${dayObj.part2("/${day}.txt")}")
}
