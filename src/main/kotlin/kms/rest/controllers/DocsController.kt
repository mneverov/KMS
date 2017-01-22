package kms.rest.controllers

import kms.service.KmsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * @author Maxim Neverov
 */

@RestController
class DocsController(val service: KmsService) {

    @GetMapping("/doc/{id}")
    fun getDoc(@PathVariable id: String) = service.get(id)

}