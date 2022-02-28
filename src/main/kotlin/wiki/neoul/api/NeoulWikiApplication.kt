package wiki.neoul.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NeoulWikiApplication

fun main(args: Array<String>) {
    runApplication<NeoulWikiApplication>(*args)
}
