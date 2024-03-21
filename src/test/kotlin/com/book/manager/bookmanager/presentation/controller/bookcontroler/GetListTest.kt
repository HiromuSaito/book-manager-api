package com.book.manager.bookmanager.presentation.controller.bookcontroler

import com.book.manager.bookmanager.application.service.BookService
import com.book.manager.bookmanager.domain.model.BookWithRental
import com.book.manager.bookmanager.domain.model.Rental
import com.book.manager.bookmanager.presentation.controller.BookController
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.FunSpec
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDate
import java.time.LocalDateTime


@SpringBootTest
class GetListTest : FunSpec() {

    @InjectMockKs
    private lateinit var bookController: BookController

    @MockK
    private lateinit var bookService: BookService

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    init {
        beforeTest {
            MockKAnnotations.init(this)
            val result = listOf(
                BookWithRental(
                    com.book.manager.bookmanager.domain.model.Book(1, "タイトル1", "著者1", LocalDate.now()),
                    Rental(1, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(14))
                ),
                BookWithRental(
                    com.book.manager.bookmanager.domain.model.Book(2, "タイトル2", "著者2", LocalDate.now()),
                    null
                )
            )

            every { bookService.getList() } returns result
            mockMvc = MockMvcBuilders.standaloneSetup(bookController).build()
        }

        test("Get /books/list should return the book list") {
            mockMvc.get("/books/list").andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(
                        """{
                            "bookList":[
                                {
                                    "id":1,
                                    "title":"タイトル1",
                                    "author":"著者1",
                                    "isRental":true
                                },
                                {
                                    "id":2,
                                    "title":"タイトル2",
                                    "author":"著者2",
                                    "isRental":false}]
                                 }
                             """
                    )
                }
            }.andReturn()
            verify { bookService.getList() }
        }
    }
}

