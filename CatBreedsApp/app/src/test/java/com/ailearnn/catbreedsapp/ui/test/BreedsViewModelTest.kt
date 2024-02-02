package com.ailearnn.catbreedsapp.ui.test

import com.ailearnn.catbreedsapp.data.Breed
import com.ailearnn.catbreedsapp.data.ICatService
import com.ailearnn.catbreedsapp.ui.BreedsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class BreedsViewModelTest {

    private lateinit var viewModel: BreedsViewModel
    private lateinit var catServiceMock: ICatService

    @Before
    fun setup(): Unit = runTest {
        catServiceMock = mock(ICatService::class.java)
    }

    @Test
    fun testGetBreeds_Success() = runTest {
        val mockResponse = listOf(
            Breed(
                id = "1", name = "MockBreed",
                "", ""
            )
        )
        Mockito.`when`(catServiceMock.getBreeds())
            .thenReturn(mockResponse)

        viewModel = BreedsViewModel(catServiceMock)
        viewModel.getBreeds()

        verify(catServiceMock).getBreeds()

        val viewModelState = viewModel.breedsLiveData.value
        assertEquals(mockResponse, viewModelState.data)
    }

}