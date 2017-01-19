package rest.controllers

import org.springframework.web.bind.annotation.*

/**
 * @author Max Neverov
 * @since
 */

@RestController
open class DocsController {

    @GetMapping("/doc/{id}")
    open fun getDoc(@PathVariable id: String): String {
        return "Here is doc $id"
    }

}