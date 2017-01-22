package rest.controllers

import org.springframework.web.bind.annotation.*

/**
 * @author Max Neverov
 * @since
 */

@RestController
class DocsController {

    @GetMapping("/doc/{id}")
    fun getDoc(@PathVariable id: String): String {
        return "Here is doc $id"
    }

}