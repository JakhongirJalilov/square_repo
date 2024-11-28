import com.example.data.remote.SquareRepoService
import com.example.data.remote.dto.response.SquareRepoItemResponse
import com.example.data.repository.SquareRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SquareRepositoryImplTest {

    private lateinit var squareRepoService: SquareRepoService
    private lateinit var squareRepository: SquareRepositoryImpl

    @Before
    fun setup() {
        squareRepoService = mockk()
        squareRepository = SquareRepositoryImpl(squareRepoService)
    }

    @Test
    fun `should return success result with repos`() = runTest {
        val mockResponse =
            listOf(SquareRepoItemResponse(name = "Repo1"), SquareRepoItemResponse(name = "Repo2"))
        coEvery { squareRepoService.getSquareRepos() } returns mockResponse

        val result = squareRepository.getSquareRepos().first()

        assertTrue(result.isSuccess)
        result.onSuccess { repos ->
            assertTrue(repos.size == 2)
            assertTrue(repos[0].name == "Repo1")
            assertTrue(repos[1].name == "Repo2")
        }
        coVerify { squareRepoService.getSquareRepos() }
    }

    @Test
    fun `should return failure result when exception occurs`() = runTest {
        coEvery { squareRepoService.getSquareRepos() } throws Exception("Network Error")
        val result = squareRepository.getSquareRepos().first()

        assertTrue(result.isFailure)
        result.onFailure { exception ->
            assertTrue(exception is Exception)
            assertTrue(exception.message == "Network Error")
        }
        coVerify { squareRepoService.getSquareRepos() }
    }
}