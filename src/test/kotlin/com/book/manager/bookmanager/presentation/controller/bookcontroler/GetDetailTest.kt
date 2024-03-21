package com.book.manager.bookmanager.presentation.controller.bookcontroler

import com.book.manager.bookmanager.application.service.BookService
import com.book.manager.bookmanager.domain.model.Book
import com.book.manager.bookmanager.domain.model.BookWithRental
import com.book.manager.bookmanager.domain.model.Rental
import com.book.manager.bookmanager.presentation.controller.BookController
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.FunSpec
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

class GetDetailTest : FunSpec() {

    @InjectMockKs
    private lateinit var bookController: BookController

    @MockK
    private lateinit var bookService: BookService

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    private val RENTAL_TERM_DAYS = 14L

    val bookId = 1L

    init {
        beforeTest {
            MockKAnnotations.init(this)

            mockkStatic(LocalDateTime::class)
            every { LocalDateTime.now() } returns LocalDateTime.of(2023, 2, 1, 0, 0)

            val releaseDate = LocalDateTime.now()
            val returnDeadline = releaseDate.plusDays(RENTAL_TERM_DAYS)
            val result = BookWithRental(
                Book(bookId, "タイトル1", "著者1", releaseDate.toLocalDate()),
                Rental(bookId, 10, releaseDate, returnDeadline)
            )
            every { bookService.getDetail(bookId) } returns result
            mockMvc = MockMvcBuilders.standaloneSetup(bookController).build()

        }
        test("Get /books/{id} should return the book") {
            mockMvc.get("/books/${bookId}").andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(
                        """
                                {
                                    "id":1,
                                    "title":"タイトル1",
                                    "author":"著者1",
                                    "releaseDate":"2023/02/01",
                                    "rental":{
                                        "userId":10,
                                        "rentalDateTime":"2023/02/01",
                                        "returnDeadline":"2023/02/15"
                                    }
                                }
                             """
                    )
                }
            }.andReturn()
            verify { bookService.getDetail(bookId) }
        }
    }
}


