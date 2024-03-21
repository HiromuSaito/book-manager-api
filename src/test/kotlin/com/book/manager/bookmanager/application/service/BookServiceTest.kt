package com.book.manager.bookmanager.application.service


import com.book.manager.bookmanager.domain.repository.BookRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest : FunSpec() {
    val bookRepository = mockk<BookRepository>()
    val bookService = BookService(bookRepository)
    val bookId = 1L

    init {

        beforeTest {
            every { bookRepository.findWithRental(bookId) } returns null
        }

        test("If the book does not exit,IllegalArgumentException should be thrown") {
            val exception = shouldThrow<Exception> {
                bookService.getDetail(bookId)
            }
            exception.message should startWith("存在しない書籍IDです")
        }
    }
}
