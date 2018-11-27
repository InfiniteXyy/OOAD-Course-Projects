package utils

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class ConfigManager {
    companion object {
        fun loadConfig(path: String): List<List<String>> {
            try {
                Files.newBufferedReader(Paths.get(path)).use {
                    return it.readLines().map { it.split(",") }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
            return emptyList()
        }

        fun saveConfig(path: String, data: List<List<Any>>) {
            try {
                Files.newBufferedWriter(Paths.get(path)).use {
                    it.write(data.joinToString("\n") { line -> line.joinToString(",") })
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

}

